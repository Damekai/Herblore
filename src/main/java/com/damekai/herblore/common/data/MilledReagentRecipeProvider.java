package com.damekai.herblore.common.data;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.ModItems;
import net.minecraft.data.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class MilledReagentRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public MilledReagentRecipeProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        String folder = "milled_reagents/";

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
}
