package com.github.sokyranthedragon.slightlyrebrushed.config;

import dev.architectury.injectables.annotations.ExpectPlatform;

public class SlightlyRebrushedConfig
{

    @ExpectPlatform
    public static boolean getIsVanillaBrushRepairable()
    {
        throw new AssertionError("This method should have been replaced by Architectury.");
    }

    @ExpectPlatform
    public static boolean getIsVanillaBrushEnchantable()
    {
        throw new AssertionError("This method should have been replaced by Architectury.");
    }
}
