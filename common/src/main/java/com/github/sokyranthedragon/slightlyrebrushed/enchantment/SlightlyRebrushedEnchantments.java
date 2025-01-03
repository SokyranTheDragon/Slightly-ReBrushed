package com.github.sokyranthedragon.slightlyrebrushed.enchantment;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class SlightlyRebrushedEnchantments
{
    public static final ResourceKey<Enchantment> BRUSH_BRUSH_SMOOTH_SWEEPING = key("brush_smooth_sweeping");
    public static final ResourceKey<Enchantment> BRUSH_TREASURES_OF_THE_REMNANTS = key("brush_treasures_of_the_ancients");
    public static final ResourceKey<Enchantment> BRUSH_SOFT_TOUCH = key("brush_prevent_brushable_block_breaking");

    public static ResourceKey<Enchantment> key(String name)
    {
        return ResourceKey.create(Registries.ENCHANTMENT, SlightlyRebrushedMod.resourceLocation(name));
    }
}