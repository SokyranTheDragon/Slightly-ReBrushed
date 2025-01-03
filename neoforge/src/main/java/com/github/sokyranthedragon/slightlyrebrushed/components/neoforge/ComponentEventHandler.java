package com.github.sokyranthedragon.slightlyrebrushed.components.neoforge;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import com.github.sokyranthedragon.slightlyrebrushed.config.SlightlyRebrushedConfig;
import com.github.sokyranthedragon.slightlyrebrushed.tags.CommonItemTags;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantable;
import net.minecraft.world.item.enchantment.Repairable;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.ModifyDefaultComponentsEvent;

@EventBusSubscriber(modid = SlightlyRebrushedMod.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
public class ComponentEventHandler
{
    @SubscribeEvent
    private static void onModifyDefaultComponentsEvent(ModifyDefaultComponentsEvent event)
    {
        if (SlightlyRebrushedConfig.getIsVanillaBrushEnchantable() && !Items.BRUSH.components().has(DataComponents.ENCHANTABLE))
            event.modify(Items.BRUSH, builder -> builder.set(DataComponents.ENCHANTABLE, new Enchantable(1)));
        if (SlightlyRebrushedConfig.getIsVanillaBrushRepairable() && !Items.BRUSH.components().has(DataComponents.REPAIRABLE))
            event.modify(Items.BRUSH, builder -> builder.set(DataComponents.REPAIRABLE, new Repairable(BuiltInRegistries.ITEM.getOrThrow(CommonItemTags.COPPER_TOOL_MATERIAL))));
    }
}