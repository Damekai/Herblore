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
    }
}
