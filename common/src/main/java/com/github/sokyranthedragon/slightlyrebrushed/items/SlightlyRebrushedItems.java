package com.github.sokyranthedragon.slightlyrebrushed.items;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import com.github.sokyranthedragon.slightlyrebrushed.tags.CommonItemTags;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterials;

import java.util.Optional;

@SuppressWarnings("UnstableApiUsage")
public class SlightlyRebrushedItems
{
    // Resources
    private static final ResourceLocation SWEEP_DURATION_ID = SlightlyRebrushedMod.resourceLocation("enchantment.sweep_duration");

    // Items and register
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(SlightlyRebrushedMod.MOD_ID, Registries.ITEM);

    public static final RegistrySupplier<Item> DIAMOND_BRUSH = ITEMS.register("diamond_brush", () ->
    {
        // Init tag early so it can be accessed in NeoForge's ModifyDefaultComponentsEvent
        BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ITEM).getOrThrow(CommonItemTags.COPPER_TOOL_MATERIAL);

        Item.Properties properties = new Item.Properties()
            .setId(createId("diamond_brush"))
            .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
            .durability(ToolMaterial.DIAMOND.durability() / 10)
            .enchantable(ToolMaterial.DIAMOND.enchantmentValue())
            .repairable(ToolMaterial.DIAMOND.repairItems());
        applyBrushDurationAttribute(properties, -1);

        return new BrushItem(properties);
    });

    public static final RegistrySupplier<Item> NETHERITE_BRUSH = ITEMS.register("netherite_brush", () ->
    {
        Item.Properties properties = new Item.Properties()
            .setId(createId("netherite_brush"))
            .arch$tab(CreativeModeTabs.TOOLS_AND_UTILITIES)
            .durability(ToolMaterial.NETHERITE.durability() / 10)
            .enchantable(ToolMaterial.NETHERITE.enchantmentValue())
            .repairable(ToolMaterial.NETHERITE.repairItems())
            .fireResistant();
        applyBrushDurationAttribute(properties, -2);

        return new BrushItem(properties);
    });

    public static void init()
    {
        ITEMS.register();
    }

    private static void applyBrushDurationAttribute(Item.Properties properties, int value)
    {
        Optional<Holder.Reference<Attribute>> brushDuration = BuiltInRegistries.ATTRIBUTE
            .get(ResourceLocation.fromNamespaceAndPath("librush", "brush_sweep_duration"));

        brushDuration.ifPresent(attributeReference -> properties.attributes(
            ItemAttributeModifiers
                .builder()
                .add(attributeReference, new AttributeModifier(SWEEP_DURATION_ID, value, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HAND)
                .build()));
    }

    private static ResourceKey<Item> createId(String itemId)
    {
        return ResourceKey.create(Registries.ITEM, SlightlyRebrushedMod.resourceLocation(itemId));
    }

    private SlightlyRebrushedItems()
    {
        // Prevent initialization
    }
}