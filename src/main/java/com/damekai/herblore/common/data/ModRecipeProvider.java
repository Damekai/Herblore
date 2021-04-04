package com.damekai.herblore.common.data;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.ModItems;
import net.minecraft.data.*;
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
        registerSeeds(consumer, "item/");
    }

    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(new ModRecipeProvider(generator));
    }

    private void registerCrudeFlask(Consumer<IFinishedRecipe> consumer, String folder)
    {
        //CustomRecipeBuilder.customRecipe(CrudeFlaskRecipe.SERIALIZER).build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "crude_flask").toString());
    }

    private void registerSeeds(Consumer<IFinishedRecipe> consumer, String folder)
    {
        ShapelessRecipeBuilder.shapelessRecipe(ModItems.WINDY_LICHEN_SEEDS::get)
                .addIngredient(ModItems.WINDY_LICHEN::get)
                .setGroup("herblore")
                .addCriterion("has_windy_lichen", hasItem(ModItems.WINDY_LICHEN::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "windy_lichen_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.SUNSPECKLE_SEEDS::get)
                .addIngredient(ModItems.SUNSPECKLE::get)
                .setGroup("herblore")
                .addCriterion("has_sunspeckle", hasItem(ModItems.SUNSPECKLE::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "sunspeckle_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.MOONSPECKLE_SEEDS::get)
                .addIngredient(ModItems.MOONSPECKLE::get)
                .setGroup("herblore")
                .addCriterion("has_moonspeckle", hasItem(ModItems.MOONSPECKLE::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "moonspeckle_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.STONESTEM_SEEDS::get)
                .addIngredient(ModItems.STONESTEM::get)
                .setGroup("herblore")
                .addCriterion("has_stonestem", hasItem(ModItems.STONESTEM::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "stonestem_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.WILLOW_WORT_SEEDS::get)
                .addIngredient(ModItems.WILLOW_WORT::get)
                .setGroup("herblore")
                .addCriterion("has_willow_wort", hasItem(ModItems.WILLOW_WORT::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "willow_wort_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.RUMBLEROOT_SEEDS::get)
                .addIngredient(ModItems.RUMBLEROOT::get)
                .setGroup("herblore")
                .addCriterion("has_rumbleroot", hasItem(ModItems.RUMBLEROOT::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "rumbleroot_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.PHANTOM_FROND_SEEDS::get)
                .addIngredient(ModItems.PHANTOM_FROND::get)
                .setGroup("herblore")
                .addCriterion("has_phantom_frond", hasItem(ModItems.PHANTOM_FROND::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "phantom_frond_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.BREEZEBLOOM_SEEDS::get)
                .addIngredient(ModItems.BREEZEBLOOM::get)
                .setGroup("herblore")
                .addCriterion("has_breezebloom", hasItem(ModItems.BREEZEBLOOM::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "breezebloom_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.DESERTS_THIRST_SEEDS::get)
                .addIngredient(ModItems.DESERTS_THIRST::get)
                .setGroup("herblore")
                .addCriterion("has_deserts_thirst", hasItem(ModItems.DESERTS_THIRST::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "deserts_thirst_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.THUNDERSTAR_SEEDS::get)
                .addIngredient(ModItems.THUNDERSTAR::get)
                .setGroup("herblore")
                .addCriterion("has_thunderstar", hasItem(ModItems.THUNDERSTAR::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "thunderstar_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.SUNSTRIDERS_SIN_SEEDS::get)
                .addIngredient(ModItems.SUNSTRIDERS_SIN::get)
                .setGroup("herblore")
                .addCriterion("has_sunstriders_sin", hasItem(ModItems.SUNSTRIDERS_SIN::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "sunstriders_sin_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.SLAKEMOSS_SEEDS::get)
                .addIngredient(ModItems.SLAKEMOSS::get)
                .setGroup("herblore")
                .addCriterion("has_slakemoss", hasItem(ModItems.SLAKEMOSS::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "slakemoss_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.SLAG_RIND_SEEDS::get)
                .addIngredient(ModItems.SLAG_RIND::get)
                .setGroup("herblore")
                .addCriterion("has_slag_rind", hasItem(ModItems.SLAG_RIND::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "slag_rind_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.VENGERVINE_SEEDS::get)
                .addIngredient(ModItems.VENGERVINE::get)
                .setGroup("herblore")
                .addCriterion("has_vengervine", hasItem(ModItems.VENGERVINE::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "vengervine_seeds"));

        ShapelessRecipeBuilder.shapelessRecipe(ModItems.SKYGLOM_SEEDS::get)
                .addIngredient(ModItems.SKYGLOM::get)
                .setGroup("herblore")
                .addCriterion("has_skyglom", hasItem(ModItems.SKYGLOM::get))
                .build(consumer, new ResourceLocation(Herblore.MOD_ID, folder + "skyglom_seeds"));
    }

    private void registerMiscItems(Consumer<IFinishedRecipe> consumer, String folder)
    {
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
    }
}
