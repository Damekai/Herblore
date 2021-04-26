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
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
    {
        registerFlasks(consumer, "flask/");
        registerMiscItems(consumer, "item/");
        registerSeeds(consumer, "item/");
    }

    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        if (event.includeServer())
        {
            generator.addProvider(new ModRecipeProvider(generator));
        }
    }

    private void registerFlasks(Consumer<IFinishedRecipe> consumer, String folder)
    {

    }

    private void registerSeeds(Consumer<IFinishedRecipe> consumer, String folder)
    {
        ShapelessRecipeBuilder.shapeless(ModItems.WINDY_LICHEN_SEEDS::get)
                .requires(ModItems.WINDY_LICHEN::get)
                .group("herblore")
                .unlockedBy("has_windy_lichen", has(ModItems.WINDY_LICHEN::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SUNSPECKLE_SEEDS::get)
                .requires(ModItems.SUNSPECKLE::get)
                .group("herblore")
                .unlockedBy("has_sunspeckle", has(ModItems.SUNSPECKLE::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.MOONSPECKLE_SEEDS::get)
                .requires(ModItems.MOONSPECKLE::get)
                .group("herblore")
                .unlockedBy("has_moonspeckle", has(ModItems.MOONSPECKLE::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.STONESTEM_SEEDS::get)
                .requires(ModItems.STONESTEM::get)
                .group("herblore")
                .unlockedBy("has_stonestem", has(ModItems.STONESTEM::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.WILLOW_WORT_SEEDS::get)
                .requires(ModItems.WILLOW_WORT::get)
                .group("herblore")
                .unlockedBy("has_willow_wort", has(ModItems.WILLOW_WORT::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.RUMBLEROOT_SEEDS::get)
                .requires(ModItems.RUMBLEROOT::get)
                .group("herblore")
                .unlockedBy("has_rumbleroot", has(ModItems.RUMBLEROOT::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.PHANTOM_FROND_SEEDS::get)
                .requires(ModItems.PHANTOM_FROND::get)
                .group("herblore")
                .unlockedBy("has_phantom_frond", has(ModItems.PHANTOM_FROND::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.BREEZEBLOOM_SEEDS::get)
                .requires(ModItems.BREEZEBLOOM::get)
                .group("herblore")
                .unlockedBy("has_breezebloom", has(ModItems.BREEZEBLOOM::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.DESERTS_THIRST_SEEDS::get)
                .requires(ModItems.DESERTS_THIRST::get)
                .group("herblore")
                .unlockedBy("has_deserts_thirst", has(ModItems.DESERTS_THIRST::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.THUNDERSTAR_SEEDS::get)
                .requires(ModItems.THUNDERSTAR::get)
                .group("herblore")
                .unlockedBy("has_thunderstar", has(ModItems.THUNDERSTAR::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SUNSTRIDERS_SIN_SEEDS::get)
                .requires(ModItems.SUNSTRIDERS_SIN::get)
                .group("herblore")
                .unlockedBy("has_sunstriders_sin", has(ModItems.SUNSTRIDERS_SIN::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SLAKEMOSS_SEEDS::get)
                .requires(ModItems.SLAKEMOSS::get)
                .group("herblore")
                .unlockedBy("has_slakemoss", has(ModItems.SLAKEMOSS::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SLAG_RIND_SEEDS::get)
                .requires(ModItems.SLAG_RIND::get)
                .group("herblore")
                .unlockedBy("has_slag_rind", has(ModItems.SLAG_RIND::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.VENGERVINE_SEEDS::get)
                .requires(ModItems.VENGERVINE::get)
                .group("herblore")
                .unlockedBy("has_vengervine", has(ModItems.VENGERVINE::get))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(ModItems.SKYGLOM_SEEDS::get)
                .requires(ModItems.SKYGLOM::get)
                .group("herblore")
                .unlockedBy("has_skyglom", has(ModItems.SKYGLOM::get))
                .save(consumer);
    }

    private void registerMiscItems(Consumer<IFinishedRecipe> consumer, String folder)
    {
        ShapedRecipeBuilder.shaped(ModItems.EMPTY_FLASK::get, 4)
                .define('A', ItemTags.PLANKS)
                .define('B', Tags.Items.GLASS)
                .pattern(" A ")
                .pattern(" B ")
                .pattern("BBB")
                .group("herblore")
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .unlockedBy("has_glass", has(Tags.Items.GLASS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(ModItems.FLASK_STATION::get)
                .define('A', ModItems.EMPTY_FLASK::get)
                .define('B', ItemTags.PLANKS)
                .pattern("AAA")
                .pattern("BBB")
                .pattern("B B")
                .group("herblore")
                .unlockedBy("has_empty_flask", has(ModItems.EMPTY_FLASK::get))
                .unlockedBy("has_planks", has(ItemTags.PLANKS))
                .save(consumer);
    }
}
