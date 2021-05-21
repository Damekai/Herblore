package com.damekai.herblore.common.data;

import com.damekai.herblore.common.data.effusion.EffusionBlockToItemTableManager;
import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

public class ModAssetManagers
{
    public static final EffusionBlockToItemTableManager CRUMBLEMIST_TABLE = new EffusionBlockToItemTableManager("effusion/crumblemist");

    public static void onAddReloadListener(AddReloadListenerEvent event)
    {
        event.addListener(CRUMBLEMIST_TABLE);
    }
}
