package com.github.sokyranthedragon.slightlyrebrushed.datagen.fabric;

import com.github.sokyranthedragon.slightlyrebrushed.items.SlightlyRebrushedItems;
import com.github.sokyranthedragon.slightlyrebrushed.tags.CommonItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class SlightlyRebrushedItemTagGenerator extends FabricTagProvider.ItemTagProvider
{
    public SlightlyRebrushedItemTagGenerator(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
    {
        super(output, completableFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider)
    {
        // Vanilla tags
        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
            .addTag(CommonItemTags.BRUSH_ENCHANTABLE);

        // Common tags
        getOrCreateTagBuilder(CommonItemTags.BRUSHES)
            .add(Items.BRUSH)
            .add(SlightlyRebrushedItems.DIAMOND_BRUSH.get())
            .add(SlightlyRebrushedItems.NETHERITE_BRUSH.get());
        getOrCreateTagBuilder(CommonItemTags.BRUSH_ENCHANTABLE)
            .addTag(CommonItemTags.BRUSHES);
        getOrCreateTagBuilder(CommonItemTags.COPPER_TOOL_MATERIAL)
            .add(Items.COPPER_INGOT);
    }
}