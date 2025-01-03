package com.github.sokyranthedragon.slightlyrebrushed.tags;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import org.jetbrains.annotations.NotNull;

class SlightlyRebrushedTags
{
    private SlightlyRebrushedTags()
    {
    }

    @NotNull
    protected static <T> TagKey<T> bind(final @NotNull ResourceKey<Registry<T>> registry, final @NotNull String path)
    {
        return bind(registry, SlightlyRebrushedMod.MOD_ID, path);
    }

    @NotNull
    protected static <T> TagKey<T> bind(final @NotNull ResourceKey<Registry<T>> registry, final @NotNull String namespace, final @NotNull String path)
    {
        return TagKey.create(registry, ResourceLocation.fromNamespaceAndPath(namespace, path));
    }
}