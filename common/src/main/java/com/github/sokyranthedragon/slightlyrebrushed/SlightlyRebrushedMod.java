package com.github.sokyranthedragon.slightlyrebrushed;

import com.github.sokyranthedragon.slightlyrebrushed.enchantment.SlightlyRebrushedEnchantmentEffectComponents;
import com.github.sokyranthedragon.slightlyrebrushed.items.SlightlyRebrushedItems;
import com.github.sokyranthedragon.slightlyrebrushed.tags.CommonItemTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class SlightlyRebrushedMod
{
    public static final String MOD_ID = "slightly_rebrushed";

    public static void init()
    {
        // Write common init code here.
        SlightlyRebrushedItems.init();
        SlightlyRebrushedEnchantmentEffectComponents.init();
    }

    public static ResourceLocation resourceLocation(String id)
    {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, id);
    }
}