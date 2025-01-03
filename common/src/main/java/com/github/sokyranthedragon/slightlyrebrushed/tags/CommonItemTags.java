package com.github.sokyranthedragon.slightlyrebrushed.tags;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.jetbrains.annotations.NotNull;

public class CommonItemTags
{
    public static final TagKey<Item> COPPER_TOOL_MATERIAL = bind("copper_tool_materials");
    public static final TagKey<Item> BRUSHES = bind("brushes");
    public static final TagKey<Item> BRUSH_ENCHANTABLE = bind("enchantable/brush");

    @NotNull
    private static TagKey<Item> bind(final @NotNull String string)
    {
        return SlightlyRebrushedTags.bind(Registries.ITEM, "c", string);
    }
}
