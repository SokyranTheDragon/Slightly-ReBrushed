package com.github.sokyranthedragon.slightlyrebrushed.enchantment;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.minecraft.util.Unit;
import net.minecraft.world.item.enchantment.ConditionalEffect;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.function.UnaryOperator;

public class SlightlyRebrushedEnchantmentEffectComponents
{
    private static final DeferredRegister<DataComponentType<?>> ENCHANTMENT_COMPONENT_TYPES
        = DeferredRegister.create(SlightlyRebrushedMod.MOD_ID, Registries.ENCHANTMENT_EFFECT_COMPONENT_TYPE);

    public static final RegistrySupplier<DataComponentType<List<ConditionalEffect<EnchantmentValueEffect>>>> EXTRA_ARCHEOLOGY_LOOT = register
        (
            "extra_archeology_loot",
            (builder) -> builder.persistent(ConditionalEffect.codec(EnchantmentValueEffect.CODEC, LootContextParamSets.ENCHANTED_ITEM).listOf())
        );

    public static final RegistrySupplier<DataComponentType<Unit>> PREVENT_BRUSHABLE_BLOCK_BREAKING = register
        (
            "prevent_brushable_block_breaking",
            (builder) -> builder.persistent(Unit.CODEC)
        );

    public static void init()
    {
        ENCHANTMENT_COMPONENT_TYPES.register();
    }

    private static <T> RegistrySupplier<DataComponentType<T>> register(String id, UnaryOperator<DataComponentType.Builder<T>> builder)
    {
        return ENCHANTMENT_COMPONENT_TYPES.register(id, () -> builder.apply(DataComponentType.builder()).build());
    }

    private SlightlyRebrushedEnchantmentEffectComponents()
    {
        // No initialization
    }
}
