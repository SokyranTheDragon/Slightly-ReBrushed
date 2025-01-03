package com.github.sokyranthedragon.slightlyrebrushed.enchantment;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.*;
import net.minecraft.world.item.enchantment.effects.EnchantmentValueEffect;
import net.minecraft.world.level.block.BrushableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import org.apache.commons.lang3.mutable.MutableFloat;

import java.util.Optional;

public class SlightlyRebrushedEnchantmentHelper
{
    public static boolean canBreakBlock(LivingEntity entity, BlockState blockState)
    {
        // No effect unless brushable
        if (!(blockState.getBlock() instanceof BrushableBlock))
            return false;

        // Check if either item blocks the block from being broken
        if (SlightlyRebrushedEnchantmentHelper.isPreventingBlockFromBreaking(entity.getItemBySlot(EquipmentSlot.MAINHAND)))
            return true;
        if (SlightlyRebrushedEnchantmentHelper.isPreventingBlockFromBreaking(entity.getItemBySlot(EquipmentSlot.OFFHAND)))
            return true;

        // No effect
        return false;
    }

    public static boolean isPreventingBlockFromBreaking(ItemStack stack)
    {
        return EnchantmentHelper.has(stack, SlightlyRebrushedEnchantmentEffectComponents.PREVENT_BRUSHABLE_BLOCK_BREAKING.get());
    }

    public static float getBonusArcheologyItemChance(ServerLevel server, ItemStack stack, Entity entity)
    {
        MutableFloat result = new MutableFloat(0f);

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : stack.getOrDefault(DataComponents.ENCHANTMENTS, ItemEnchantments.EMPTY).entrySet())
        {
            Enchantment enchantment = entry.getKey().value();
            int enchantmentLevel = entry.getIntValue();
            LootContext context = getLootContextForBlock(server, stack, enchantmentLevel);

            modifyBonusArcheologyItemChance(context, entity, enchantment, enchantmentLevel, result);
        }

        return result.floatValue();
    }

    private static void modifyBonusArcheologyItemChance(LootContext context, Entity entity, Enchantment enchantment, int enchantmentLevel, MutableFloat result)
    {
        for (ConditionalEffect<EnchantmentValueEffect> effect : enchantment.getEffects(SlightlyRebrushedEnchantmentEffectComponents.EXTRA_ARCHEOLOGY_LOOT.get()))
        {
            if (effect.matches(context))
            {
                result.setValue(effect.effect().process(enchantmentLevel, entity.getRandom(), result.floatValue()));
            }
        }
    }

    private static LootContext getLootContextForBlock(ServerLevel server, ItemStack stack, int enchantmentLevel)
    {
        LootParams lootParams = (new LootParams.Builder(server))
            .withParameter(LootContextParams.TOOL, stack)
            .withParameter(LootContextParams.ENCHANTMENT_LEVEL, enchantmentLevel)
            .create(LootContextParamSets.ENCHANTED_ITEM);
        return (new LootContext.Builder(lootParams)).create(Optional.empty());
    }

    private SlightlyRebrushedEnchantmentHelper()
    {
        // Prevent initialization
    }
}
