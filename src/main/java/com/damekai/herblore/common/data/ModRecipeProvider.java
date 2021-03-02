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
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.REAGENT_A_MILLED.get())
                .addIngredient(ModItems.PESTLE_AND_MORTAR.get())
                .addIngredient(ModItems.REAGENT_A.get())
                .setGroup("herblore")
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "reagent_a_milled"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.REAGENT_B_MILLED.get())
                .addIngredient(ModItems.PESTLE_AND_MORTAR.get())
                .addIngredient(ModItems.REAGENT_B.get())
                .setGroup("herblore")
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "reagent_b_milled"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.REAGENT_C_MILLED.get())
                .addIngredient(ModItems.PESTLE_AND_MORTAR.get())
                .addIngredient(ModItems.REAGENT_C.get())
                .setGroup("herblore")
                .addCriterion("has_iron_ingot", hasItem(Tags.Items.INGOTS_IRON))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "reagent_c_milled"));
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
