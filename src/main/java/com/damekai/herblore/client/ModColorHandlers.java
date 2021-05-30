package com.damekai.herblore.client;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.block.tile.TileEffusion;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.util.EffusionHelper;
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
        event.getBlockColors().register(EffusionHelper::getEffusionBlockColor, ModBlocks.EFFUSION.get());
    }

    public static void onRegisterItemColors(ColorHandlerEvent.Item event)
    {
        event.getItemColors().register(EffusionHelper::getEffusionItemColor, ModItems.EFFUSION.get());
    }
}
