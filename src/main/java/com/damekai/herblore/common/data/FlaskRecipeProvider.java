package com.damekai.herblore.common.data;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.recipe.CrudeFlaskRecipe;
import net.minecraft.data.*;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class FlaskRecipeProvider extends RecipeProvider implements IConditionBuilder
{
    public FlaskRecipeProvider(DataGenerator generator)
    {
        super(generator);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer)
    {
        String folder = "flask/";

        CustomRecipeBuilder.customRecipe(CrudeFlaskRecipe.SERIALIZER).build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "crude_flask").toString());
    }
}
