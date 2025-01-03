package com.github.sokyranthedragon.slightlyrebrushed.components.fabric;

import com.github.sokyranthedragon.slightlyrebrushed.config.SlightlyRebrushedConfig;
import com.github.sokyranthedragon.slightlyrebrushed.tags.CommonItemTags;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantable;
import net.minecraft.world.item.enchantment.Repairable;

public class ComponentEventHandler
{
    public static void registerEvents()
    {
        DefaultItemComponentEvents.MODIFY.register(ComponentEventHandler::onModifyDefaultComponentsEvent);
    }

    private static void onModifyDefaultComponentsEvent(DefaultItemComponentEvents.ModifyContext event)
    {
        if (SlightlyRebrushedConfig.getIsVanillaBrushEnchantable())
            event.modify(Items.BRUSH, builder -> builder.set(DataComponents.ENCHANTABLE, new Enchantable(1)));
        if (SlightlyRebrushedConfig.getIsVanillaBrushRepairable())
            event.modify(Items.BRUSH, builder -> builder.set(DataComponents.REPAIRABLE, new Repairable(BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.ITEM).getOrThrow(CommonItemTags.COPPER_TOOL_MATERIAL))));
    }
}
