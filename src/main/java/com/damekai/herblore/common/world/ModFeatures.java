package com.damekai.herblore.common.world;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModFeatures
{
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Herblore.MOD_ID);

    public static final RegistryObject<Feature<NoFeatureConfig>> PERENNIAL_PATCH = FEATURES.register("perennial_patch", () -> new FeaturePerennialPatch(NoFeatureConfig.field_236558_a_.stable(), ModBlocks.PERENNIAL_PATCH_DEBUG));

    public static final Supplier<ConfiguredFeature<?, ?>> PERENNIAL_PATCH_CONFIG = () -> PERENNIAL_PATCH.get()
            .withConfiguration(NoFeatureConfig.NO_FEATURE_CONFIG)
            .withPlacement(Features.Placements.VEGETATION_PLACEMENT)
            .withPlacement(Features.Placements.HEIGHTMAP_PLACEMENT);

    public static void onBiomeLoading(BiomeLoadingEvent event)
    {
        // TODO: Biome blacklist.

        event.getGeneration().withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, PERENNIAL_PATCH_CONFIG.get());
    }
}