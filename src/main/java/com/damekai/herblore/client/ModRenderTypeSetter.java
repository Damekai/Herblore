package com.damekai.herblore.client;

import com.damekai.herblore.common.block.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModRenderTypeSetter
{
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        RenderTypeLookup.setRenderLayer(ModBlocks.PERENNIAL_PATCH.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(ModBlocks.WINDY_LICHEN_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SUNSPECKLE_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.MOONSPECKLE_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.STONESTEM_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.WILLOW_WORT_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.RUMBLEROOT_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PHANTOM_FROND_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.BREEZEBLOOM_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.DESERTS_THIRST_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.THUNDERSTAR_CROP.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SUNSTRIDERS_SIN_CROP.get(), RenderType.getCutout());
    }
}
