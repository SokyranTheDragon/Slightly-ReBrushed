package com.github.sokyranthedragon.slightlyrebrushed.fabric;

import com.github.sokyranthedragon.slightlyrebrushed.components.fabric.ComponentEventHandler;
import net.fabricmc.api.ModInitializer;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import net.fabricmc.fabric.api.event.registry.RegistryAttributeHolder;
import net.fabricmc.fabric.api.item.v1.DefaultItemComponentEvents;
import net.minecraft.core.registries.Registries;

public final class SlightlyRebrushedModFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        // This code runs as soon as Minecraft is in a mod-load-ready state.
        // However, some things (like resources) may still be uninitialized.
        // Proceed with mild caution.

        ComponentEventHandler.registerEvents();

        // Run our common setup.
        SlightlyRebrushedMod.init();
    }
}
