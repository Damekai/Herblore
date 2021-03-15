package com.damekai.herblore.common.data;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.recipe.CrudeFlaskRecipe;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider
{
    public ModRecipeProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        registerCrudeFlask(consumer, "crude_flask/");
        registerMiscItems(consumer, "item/");
        registerCatalysts(consumer, "item/");
    }

    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new ModRecipeProvider(generator));
    }

    private void registerCrudeFlask(Consumer<IFinishedRecipe> consumer, String folder)
    {
        CustomRecipeBuilder.customRecipe(CrudeFlaskRecipe.SERIALIZER).build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "crude_flask").toString());
    }

    private void registerCatalysts(Consumer<IFinishedRecipe> consumer, String folder)
    {
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MARROW::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Tags.Items.BONES)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_bones", hasItem(Tags.Items.BONES))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "marrow"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.POWDERY_SINEW::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Tags.Items.BONES)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_rotten_flesh", hasItem(Items.ROTTEN_FLESH))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "powdery_sinew"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.ENDER_DUST::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Tags.Items.BONES)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_ender_pearls", hasItem(Tags.Items.ENDER_PEARLS))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "ender_dust"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.SPIDER_GRIT::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Items.SPIDER_EYE)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_spider_eye", hasItem(Items.SPIDER_EYE))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "spider_grit"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.EERIE_MUCOUS::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Items.PHANTOM_MEMBRANE)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_phantom_membrane", hasItem(Items.PHANTOM_MEMBRANE))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "phantom_membrane"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.POPPY_EXTRACT::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Items.POPPY)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_poppy", hasItem(Items.POPPY))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "poppy_extract"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.CRUSHED_PUMPKIN_SEEDS::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Tags.Items.SEEDS_PUMPKIN)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_pumpkin_seeds", hasItem(Tags.Items.SEEDS_PUMPKIN))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "crushed_pumpkin_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.PUREED_BERRIES::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Items.SWEET_BERRIES)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_sweet_berries", hasItem(Items.SWEET_BERRIES))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "pureed_berries"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.COCOA_FLOUR::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Items.COCOA_BEANS)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_cocoa_beans", hasItem(Items.COCOA_BEANS))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "cocoa_flour"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.KELP_POWDER::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(Items.KELP)
                .setGroup("herblore")
                .addCriterion("has_pestle_and_mortar", hasItem(ModItems.PESTLE_AND_MORTAR::get))
                .addCriterion("has_kelp", hasItem(Items.KELP))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "kelp_powder"));
    }

    private void registerMiscItems(Consumer<IFinishedRecipe> consumer, String folder)
    {
        ShapedRecipeBuilder.shapedRecipe(ModItems.PESTLE_AND_MORTAR::get)
                .key('A', Ingredient.fromTag(Tags.Items.RODS_WOODEN))
                .key('B', Ingredient.fromTag(Tags.Items.STONE))
                .patternLine(" A ")
                .patternLine("B B")
                .patternLine(" B ")
                .setGroup("herblore")
                .addCriterion("has_stick", hasItem(Tags.Items.RODS_WOODEN))
                .addCriterion("has_stone", hasItem(Tags.Items.STONE))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "pestle_and_mortar"));

        ShapedRecipeBuilder.shapedRecipe(ModItems.EMPTY_FLASK::get, 4)
                .key('A', Ingredient.fromTag(ItemTags.PLANKS))
                .key('B', Ingredient.fromTag(Tags.Items.GLASS))
                .patternLine(" A ")
                .patternLine(" B ")
                .patternLine("BBB")
                .setGroup("herblore")
                .addCriterion("has_planks", hasItem(ItemTags.PLANKS))
                .addCriterion("has_glass", hasItem(Tags.Items.GLASS))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "empty_flask"));

        ShapedRecipeBuilder.shapedRecipe(ModItems.ATHANOR::get)
                .key('A', Ingredient.fromTag(Tags.Items.INGOTS_IRON))
                .key('B', Ingredient.fromTag(Tags.Items.SANDSTONE))
                .key('C', Ingredient.fromTag(ItemTags.COALS))
                .patternLine(" A ")
                .patternLine("BCB")
                .patternLine("BBB")
                .setGroup("herblore")
                .addCriterion("has_iron", hasItem(Tags.Items.INGOTS_IRON))
                .addCriterion("has_sandstone", hasItem(Tags.Items.SANDSTONE))
                .addCriterion("has_coals", hasItem(ItemTags.COALS))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "athanor"));
    }
}
