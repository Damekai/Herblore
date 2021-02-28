package com.damekai.herblore.common.item;

import com.damekai.herblore.common.util.FlaskHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.ItemColors;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;

public class ModItemColors
{
    public static void onLoadComplete(FMLLoadCompleteEvent event)
    {
        ItemColors itemColors = Minecraft.getInstance().getItemColors();

        itemColors.register((stack, color) -> color > 0 ? -1 : FlaskHelper.getFlaskColor(stack), ModItems.FLASK.get());
    }
}
