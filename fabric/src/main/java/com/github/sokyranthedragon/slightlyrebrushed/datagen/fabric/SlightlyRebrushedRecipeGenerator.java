package com.github.sokyranthedragon.slightlyrebrushed.datagen.fabric;

import com.github.sokyranthedragon.slightlyrebrushed.SlightlyRebrushedMod;
import com.github.sokyranthedragon.slightlyrebrushed.items.SlightlyRebrushedItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.CompletableFuture;

public class SlightlyRebrushedRecipeGenerator extends RecipeProvider
{
    protected SlightlyRebrushedRecipeGenerator(HolderLookup.Provider provider, RecipeOutput recipeOutput)
    {
        super(provider, recipeOutput);
    }

    @Override
    public void buildRecipes()
    {
        shaped(RecipeCategory.TOOLS, SlightlyRebrushedItems.DIAMOND_BRUSH.get())
            .define('X', Items.FEATHER)
            .define('#', ItemTags.DIAMOND_TOOL_MATERIALS)
            .define('I', Items.STICK)
            .pattern("X")
            .pattern("#")
            .pattern("I")
            .unlockedBy("has_diamond", has(ItemTags.DIAMOND_TOOL_MATERIALS))
            .save(output);

        netheriteSmithing(SlightlyRebrushedItems.DIAMOND_BRUSH.get(), RecipeCategory.TOOLS, SlightlyRebrushedItems.NETHERITE_BRUSH.get());
    }

    @Override
    public void netheriteSmithing(Item item, RecipeCategory recipeCategory, Item item2)
    {
        // Replace vanilla netheriteSmithing method, as its recipe unlock advancment will always
        // reference "minecraft:<item_name>_smithing" as the recipe, rather than using proper ID.
        SmithingTransformRecipeBuilder
            .smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(item), this.tag(ItemTags.NETHERITE_TOOL_MATERIALS), recipeCategory, item2)
            .unlocks("has_netherite_ingot", has(ItemTags.NETHERITE_TOOL_MATERIALS))
            .save(output, ResourceKey.create(Registries.RECIPE, SlightlyRebrushedMod.resourceLocation(getItemName(item2) + "_smithing")));
    }

    public static class RebrushedRecipeRunner extends FabricRecipeProvider
    {
        public RebrushedRecipeRunner(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture)
        {
            super(output, registriesFuture);
        }

        @NotNull
        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput)
        {
            return new SlightlyRebrushedRecipeGenerator(provider, recipeOutput);
        }

        @NotNull
        @Override
        public String getName()
        {
            return "Recipe Generator";
        }
    }

}
