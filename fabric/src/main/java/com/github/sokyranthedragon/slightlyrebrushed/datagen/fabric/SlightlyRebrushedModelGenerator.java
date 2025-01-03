package com.github.sokyranthedragon.slightlyrebrushed.datagen.fabric;

import com.github.sokyranthedragon.slightlyrebrushed.items.SlightlyRebrushedItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ItemModelUtils;
import net.minecraft.client.data.models.model.ModelLocationUtils;
import net.minecraft.client.data.models.model.ModelTemplate;
import net.minecraft.client.data.models.model.TextureMapping;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.renderer.item.properties.numeric.UseCycle;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;

public class SlightlyRebrushedModelGenerator extends FabricModelProvider
{
    public SlightlyRebrushedModelGenerator(FabricDataOutput output)
    {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators)
    {
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators)
    {
        generateBrush(itemModelGenerators, SlightlyRebrushedItems.DIAMOND_BRUSH.get());
        generateBrush(itemModelGenerators, SlightlyRebrushedItems.NETHERITE_BRUSH.get());
    }

    public void generateBrush(ItemModelGenerators itemModelGenerators, Item item)
    {
        ItemModel.Unbaked unbaked = ItemModelUtils.plainModel(itemModelGenerators.createFlatItemModel(item, SlightlyRebrushedModelTemplates.BRUSH_BASE));
        ItemModel.Unbaked unbaked2 = ItemModelUtils.plainModel(createFlatItemModelWithBaseItemTexture(itemModelGenerators, item, "_brushing_0", SlightlyRebrushedModelTemplates.BRUSH_BRUSHING_0));
        ItemModel.Unbaked unbaked3 = ItemModelUtils.plainModel(createFlatItemModelWithBaseItemTexture(itemModelGenerators, item, "_brushing_1", SlightlyRebrushedModelTemplates.BRUSH_BRUSHING_1));
        ItemModel.Unbaked unbaked4 = ItemModelUtils.plainModel(createFlatItemModelWithBaseItemTexture(itemModelGenerators, item, "_brushing_2", SlightlyRebrushedModelTemplates.BRUSH_BRUSHING_2));

        itemModelGenerators.itemModelOutput.accept(item,
            ItemModelUtils.rangeSelect(new UseCycle(10.0F), 0.1F, unbaked,
                ItemModelUtils.override(unbaked2, 0.25F),
                ItemModelUtils.override(unbaked3, 0.5F),
                ItemModelUtils.override(unbaked4, 0.75F)));
    }

    public final ResourceLocation createFlatItemModelWithBaseItemTexture(ItemModelGenerators itemModelGenerators, Item item, String string, ModelTemplate modelTemplate)
    {
        return modelTemplate.create(ModelLocationUtils.getModelLocation(item, string), TextureMapping.layer0(item), itemModelGenerators.modelOutput);
    }
}