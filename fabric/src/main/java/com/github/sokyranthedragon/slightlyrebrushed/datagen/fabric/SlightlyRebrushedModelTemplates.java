package com.github.sokyranthedragon.slightlyrebrushed.datagen.fabric;

import net.minecraft.client.data.models.model.*;
import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public class SlightlyRebrushedModelTemplates
{
    public static final ModelTemplate BRUSH_BASE = createBrush("brush");
    public static final ModelTemplate BRUSH_BRUSHING_0 = createBrush("brush_brushing_0");
    public static final ModelTemplate BRUSH_BRUSHING_1 = createBrush("brush_brushing_1");
    public static final ModelTemplate BRUSH_BRUSHING_2 = createBrush("brush_brushing_2");

    private static ModelTemplate createBrush(String path)
    {
        return new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("item/" + path)), Optional.empty(), TextureSlot.LAYER0);
    }

    private SlightlyRebrushedModelTemplates()
    {
        // No intialization
    }
}
