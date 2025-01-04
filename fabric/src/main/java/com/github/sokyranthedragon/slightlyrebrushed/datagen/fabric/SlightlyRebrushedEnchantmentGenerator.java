package com.github.sokyranthedragon.slightlyrebrushed.datagen.fabric;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import com.github.sokyranthedragon.slightlyrebrushed.enchantment.SlightlyRebrushedEnchantmentEffectComponents;
import com.github.sokyranthedragon.slightlyrebrushed.enchantment.SlightlyRebrushedEnchantments;
import com.github.sokyranthedragon.slightlyrebrushed.tags.CommonItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class SlightlyRebrushedEnchantmentGenerator extends FabricDynamicRegistryProvider
{
    public SlightlyRebrushedEnchantmentGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
    {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider provider, Entries entries)
    {
        HolderLookup.RegistryLookup<Item> items = provider.lookupOrThrow(Registries.ITEM);

        Holder.Reference<Attribute> brushDuration = BuiltInRegistries.ATTRIBUTE
            .get(ResourceLocation.fromNamespaceAndPath("librush", "brush_sweep_duration"))
            .orElseThrow();

        register(entries, SlightlyRebrushedEnchantments.BRUSH_BRUSH_SMOOTH_SWEEPING,
            Enchantment.enchantment(Enchantment.definition
                    (
                        items.getOrThrow(CommonItemTags.BRUSH_ENCHANTABLE),
                        7,
                        3,
                        Enchantment.dynamicCost(1, 5),
                        Enchantment.dynamicCost(35, 10),
                        2,
                        EquipmentSlotGroup.HAND
                    )
                )
                .withEffect(EnchantmentEffectComponents.ATTRIBUTES, new EnchantmentAttributeEffect
                    (
                        SlightlyRebrushedMod.resourceLocation("enchantment.brush_efficiency"),
                        brushDuration,
                        LevelBasedValue.perLevel(-1),
                        AttributeModifier.Operation.ADD_VALUE
                    )
                )
        );

        register(entries, SlightlyRebrushedEnchantments.BRUSH_TREASURES_OF_THE_REMNANTS,
            Enchantment.enchantment(Enchantment.definition
                    (
                        items.getOrThrow(CommonItemTags.BRUSH_ENCHANTABLE),
                        5,
                        3,
                        Enchantment.dynamicCost(10, 5),
                        Enchantment.dynamicCost(35, 10),
                        4,
                        EquipmentSlotGroup.HAND
                    )
                )
                .withEffect(SlightlyRebrushedEnchantmentEffectComponents.EXTRA_ARCHEOLOGY_LOOT.get(), new AddValue(LevelBasedValue.perLevel(0.2f, 0.15f)))
        );

        register(entries, SlightlyRebrushedEnchantments.BRUSH_SOFT_TOUCH,
            Enchantment.enchantment(Enchantment.definition
                    (
                        items.getOrThrow(CommonItemTags.BRUSH_ENCHANTABLE),
                        3,
                        1,
                        Enchantment.constantCost(10),
                        Enchantment.constantCost(40),
                        6,
                        EquipmentSlotGroup.HAND
                    )
                )
                .withEffect(SlightlyRebrushedEnchantmentEffectComponents.PREVENT_BRUSHABLE_BLOCK_BREAKING.get())
        );
    }

    public static void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder)
    {
        entries.add(key, builder.build(key.location()));
    }

    @NotNull
    @Override
    public String getName()
    {
        return "Enchantment Generator";
    }
}