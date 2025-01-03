package com.github.sokyranthedragon.slightlyrebrushed.neoforge;

import com.github.sokyranthedragon.slightlyrebrushed.config.neoforge.SlightlyRebrushedStartupConfig;
import net.minecraft.world.item.Items;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(SlightlyRebrushedMod.MOD_ID)
public final class SlightlyRebrushedModNeoForge
{
    public SlightlyRebrushedModNeoForge(ModContainer container, Dist dist)
    {
        // Init config
        container.registerConfig(ModConfig.Type.COMMON, SlightlyRebrushedStartupConfig.CONFIG_SPEC);
        if (dist.isClient())
            container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);

        // Run our common setup.
        SlightlyRebrushedMod.init();
    }
}