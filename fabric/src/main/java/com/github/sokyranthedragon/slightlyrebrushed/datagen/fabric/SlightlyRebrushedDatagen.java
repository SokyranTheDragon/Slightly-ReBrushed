package com.github.sokyranthedragon.slightlyrebrushed.datagen.fabric;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.loader.api.FabricLoader;

public class SlightlyRebrushedDatagen implements DataGeneratorEntrypoint
{
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator)
    {
        if (!FabricLoader.getInstance().isModLoaded("librush"))
            throw new RuntimeException("librush is not installed during datagen");

        FabricDataGenerator.Pack pack = generator.createPack();

        pack.addProvider(SlightlyRebrushedLanguageGenerator::new);
        pack.addProvider(SlightlyRebrushedModelGenerator::new);
        pack.addProvider(SlightlyRebrushedItemTagGenerator::new);
        pack.addProvider(SlightlyRebrushedEnchantmentTagGenerator::new);
        pack.addProvider(SlightlyRebrushedEnchantmentGenerator::new);
        pack.addProvider(SlightlyRebrushedRecipeGenerator.RebrushedRecipeRunner::new);
    }
}
