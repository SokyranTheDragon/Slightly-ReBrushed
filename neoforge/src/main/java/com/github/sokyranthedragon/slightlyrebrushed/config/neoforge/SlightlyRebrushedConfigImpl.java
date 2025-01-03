package com.github.sokyranthedragon.slightlyrebrushed.config.neoforge;

import net.minecraft.core.component.DataComponentMap;
import net.neoforged.neoforge.internal.RegistrationEvents;

public class SlightlyRebrushedConfigImpl
{
    public static boolean getIsVanillaBrushRepairable()
    {
        return SlightlyRebrushedStartupConfig.CONFIG.makeVanillaBrushRepairable.getAsBoolean();
    }

    public static boolean getIsVanillaBrushEnchantable()
    {
        return SlightlyRebrushedStartupConfig.CONFIG.makeVanillaBrushEnchantable.getAsBoolean();
    }
}
