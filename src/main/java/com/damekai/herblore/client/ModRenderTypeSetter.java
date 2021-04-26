package com.damekai.herblore.client;

import com.damekai.herblore.common.block.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModRenderTypeSetter
{
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        RenderTypeLookup.setRenderLayer(ModBlocks.PERENNIAL_PATCH.get(), RenderType.cutout());

        RenderTypeLookup.setRenderLayer(ModBlocks.WINDY_LICHEN_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SUNSPECKLE_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.MOONSPECKLE_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.STONESTEM_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.WILLOW_WORT_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.RUMBLEROOT_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PHANTOM_FROND_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BREEZEBLOOM_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.DESERTS_THIRST_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.THUNDERSTAR_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SUNSTRIDERS_SIN_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SLAKEMOSS_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SLAG_RIND_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.VENGERVINE_CROP.get(), RenderType.cutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SKYGLOM_CROP.get(), RenderType.cutout());

    }
}
