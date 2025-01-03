package com.github.sokyranthedragon.slightlyrebrushed.datagen.fabric;

import com.github.sokyranthedragon.slightlyrebrushed.config.SlightlyRebrushedConfigConstants;
import com.github.sokyranthedragon.slightlyrebrushed.enchantment.SlightlyRebrushedEnchantments;
import com.github.sokyranthedragon.slightlyrebrushed.items.SlightlyRebrushedItems;
import com.github.sokyranthedragon.slightlyrebrushed.tags.CommonItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.Util;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.StringUtil;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class SlightlyRebrushedLanguageGenerator extends FabricLanguageProvider
{
    protected SlightlyRebrushedLanguageGenerator(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup)
    {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(HolderLookup.Provider provider, TranslationBuilder builder)
    {
        // Items
        builder.add(SlightlyRebrushedItems.DIAMOND_BRUSH.get(), "Diamond brush");
        builder.add(SlightlyRebrushedItems.NETHERITE_BRUSH.get(), "Netherite brush");

        // Enchantments
        addEnchantment(builder, SlightlyRebrushedEnchantments.BRUSH_BRUSH_SMOOTH_SWEEPING, "Smooth Sweeping", "Makes brushing faster by making a single \"sweep\" take less time.");
        // Was originally going to be "Treasures of the Ancients", but we've got archeology advancement
        // called "Respecting the Remnants" so I thought using Remnants would fit the game more.
        addEnchantment(builder, SlightlyRebrushedEnchantments.BRUSH_TREASURES_OF_THE_REMNANTS, "Treasures of the Remnants", "Some brushed blocks may give an additional reward. You may end up getting the same or a different reward.");
        addEnchantment(builder, SlightlyRebrushedEnchantments.BRUSH_SOFT_TOUCH, "Soft Touch", "Prevents suspicious blocks from being mined if held in either hand.");

        // Tags
        builder.add(CommonItemTags.BRUSHES, "Brushes");
        builder.add(CommonItemTags.BRUSH_ENCHANTABLE, "Brush Enchantable");
        builder.add(CommonItemTags.COPPER_TOOL_MATERIAL, "Copper Tool Material");

        // Config
        addConfig(builder, SlightlyRebrushedConfigConstants.VANILLA_BRUSH_REPAIRABLE_TRANSLATION_KEY, "Vanilla brush is repairable with copper", null);
        addConfig(builder, SlightlyRebrushedConfigConstants.VANILLA_BRUSH_ENCHANTABLE_TRANSLATION_KEY, "Vanilla brush can be enchanted at enchanting table", null);
    }

    public void addEnchantment(@NotNull TranslationBuilder builder, @NotNull final ResourceKey<Enchantment> enchantment, @NotNull final String value, @Nullable final String description)
    {
        String path = Util.makeDescriptionId("enchantment", enchantment.location());

        builder.add(path, value);
        if (description != null)
            builder.add(path + ".desc", description);
    }

    private void addConfig(@NotNull TranslationBuilder builder, @NotNull final String key, @NotNull final String value, @Nullable final String tooltip)
    {
        builder.add(key, value);
        if (!StringUtil.isNullOrEmpty(tooltip))
            builder.add(key + ".tooltip", tooltip);
    }
}
