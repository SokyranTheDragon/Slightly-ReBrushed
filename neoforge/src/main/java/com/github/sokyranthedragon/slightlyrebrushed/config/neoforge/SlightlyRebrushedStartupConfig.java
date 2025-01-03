package com.github.sokyranthedragon.slightlyrebrushed.config.neoforge;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.UnmodifiableCommentedConfig;
import com.github.sokyranthedragon.slightlyrebrushed.config.SlightlyRebrushedConfigConstants;
import net.neoforged.fml.config.IConfigSpec;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

public class SlightlyRebrushedStartupConfig implements IConfigSpec
{
    public static final SlightlyRebrushedStartupConfig CONFIG;
    public static final ModConfigSpec CONFIG_SPEC;

    public final ModConfigSpec.BooleanValue makeVanillaBrushRepairable;
    public final ModConfigSpec.BooleanValue makeVanillaBrushEnchantable;

    private SlightlyRebrushedStartupConfig(ModConfigSpec.Builder builder)
    {
        makeVanillaBrushRepairable = builder
            .translation(SlightlyRebrushedConfigConstants.VANILLA_BRUSH_REPAIRABLE_TRANSLATION_KEY)
            .comment("If enabled, the vanilla brush will be repairable with copper.")
            .gameRestart()
            .define(SlightlyRebrushedConfigConstants.VANILLA_BRUSH_REPAIRABLE_ID, true);

        makeVanillaBrushEnchantable = builder
            .translation(SlightlyRebrushedConfigConstants.VANILLA_BRUSH_ENCHANTABLE_TRANSLATION_KEY)
            .comment("If enabled, the vanilla brush will be enchantable at an enchanting table.")
            .comment("If disabled, it will only be possible to enchant it using an anvil.")
            .comment("Enabling this, in a more technical term, will mean that the copper brush will have an enchantability value of 1.")
            .gameRestart()
            .define(SlightlyRebrushedConfigConstants.VANILLA_BRUSH_ENCHANTABLE_ID, true);
    }

    static
    {
        Pair<SlightlyRebrushedStartupConfig, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(SlightlyRebrushedStartupConfig::new);

        CONFIG = pair.getLeft();
        CONFIG_SPEC = pair.getRight();
    }

    @Override
    public boolean isCorrect(UnmodifiableCommentedConfig config)
    {
        return false;
    }

    @Override
    public void validateSpec(ModConfig config)
    {
    }

    @Override
    public boolean isEmpty()
    {
        return false;
    }

    @Override
    public void correct(CommentedConfig config)
    {

    }

    @Override
    public void acceptConfig(@Nullable ILoadedConfig config)
    {

    }
}
