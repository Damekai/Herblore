package com.damekai.herblore.common.data;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.recipe.CrudeFlaskRecipe;
import net.minecraft.data.*;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.BlockTags;
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
        registerMilledReagents(consumer, "milled_reagent/");
        registerMiscItems(consumer, "item/");
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

    private void registerMilledReagents(Consumer<IFinishedRecipe> consumer, String folder)
    {
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_PEACEBLOOM::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.PEACEBLOOM::get)
                .setGroup("herblore")
                .addCriterion("has_peacebloom", hasItem(ModItems.PEACEBLOOM::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_peacebloom"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_SILVERLEAF::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.SILVERLEAF::get)
                .setGroup("herblore")
                .addCriterion("has_silverleaf", hasItem(ModItems.SILVERLEAF::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_silverleaf"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_EARTHROOT::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.EARTHROOT::get)
                .setGroup("herblore")
                .addCriterion("has_earthroot", hasItem(ModItems.EARTHROOT::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_earthroot"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_MAGEROYAL::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.MAGEROYAL::get)
                .setGroup("herblore")
                .addCriterion("has_mageroyal", hasItem(ModItems.MAGEROYAL::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_mageroyal"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_BRIARTHORN::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.BRIARTHORN::get)
                .setGroup("herblore")
                .addCriterion("has_briarthorn", hasItem(ModItems.BRIARTHORN::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_briarthorn"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_WILDBLOOM::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.WILDBLOOM::get)
                .setGroup("herblore")
                .addCriterion("has_wildbloom", hasItem(ModItems.WILDBLOOM::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_wildbloom"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_FADELEAF::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.FADELEAF::get)
                .setGroup("herblore")
                .addCriterion("has_fadeleaf", hasItem(ModItems.FADELEAF::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_fadeleaf"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_GOLDTHORN::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.GOLDTHORN::get)
                .setGroup("herblore")
                .addCriterion("has_goldthorn", hasItem(ModItems.GOLDTHORN::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_goldthorn"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_SUNGRASS::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.SUNGRASS::get)
                .setGroup("herblore")
                .addCriterion("has_sungrass", hasItem(ModItems.SUNGRASS::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_sungrass"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MILLED_DREAMFOIL::get)
                .addIngredient(ModItems.PESTLE_AND_MORTAR::get)
                .addIngredient(ModItems.DREAMFOIL::get)
                .setGroup("herblore")
                .addCriterion("has_dreamfoil", hasItem(ModItems.DREAMFOIL::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "milled_dreamfoil"));
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
                .addCriterion("has_wooden_rods", hasItem(Tags.Items.RODS_WOODEN))
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
