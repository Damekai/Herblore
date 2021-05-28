package com.damekai.herblore.client;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.block.tile.TileEffusion;
import com.damekai.herblore.effusion.base.EffusionInstance;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ColorHandlerEvent;

public class ModColorHandlers
{
    @OnlyIn(Dist.CLIENT)
    public static void onRegisterBlockColors(ColorHandlerEvent.Block event)
    {
        event.getBlockColors().register((blockState, blockDisplayReader, blockPos, tintIndex) ->
        {
            if (blockPos != null && tintIndex == 1)
            {
                World world = Minecraft.getInstance().level;
                if (world != null)
                {
                    TileEffusion effusionTile = (TileEffusion) world.getBlockEntity(blockPos);
                    if (effusionTile != null)
                    {
                        EffusionInstance effusionInstance = effusionTile.getEffusionInstance();
                        if (effusionInstance != null)
                        {
                            return effusionInstance.getEffusion().getColor();
                        }
                    }
                }
            }
            return 0xFFFFFF;
        }, ModBlocks.EFFUSION.get());
    }
}
