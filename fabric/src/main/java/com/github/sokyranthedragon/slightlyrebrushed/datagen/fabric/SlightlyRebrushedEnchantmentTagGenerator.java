package com.github.sokyranthedragon.slightlyrebrushed.datagen.fabric;

import com.github.sokyranthedragon.slightlyrebrushed.enchantment.SlightlyRebrushedEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.EnchantmentTags;

import java.util.concurrent.CompletableFuture;

public class SlightlyRebrushedEnchantmentTagGenerator extends FabricTagProvider.EnchantmentTagProvider
{
    public SlightlyRebrushedEnchantmentTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        getOrCreateTagBuilder(EnchantmentTags.NON_TREASURE)
            .addOptional(SlightlyRebrushedEnchantments.BRUSH_BRUSH_SMOOTH_SWEEPING)
            .addOptional(SlightlyRebrushedEnchantments.BRUSH_TREASURES_OF_THE_REMNANTS)
            .addOptional(SlightlyRebrushedEnchantments.BRUSH_SOFT_TOUCH);
    }
}