package com.damekai.herblore.common.data;

import net.minecraft.resources.IReloadableResourceManager;
import net.minecraft.resources.IResourceManager;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;

public class ModAssetManagers
{
    public static final CrumblemistLootTableManager CRUMBLEMIST_LOOT_TABLE = new CrumblemistLootTableManager();

    public static void onAddReloadListener(AddReloadListenerEvent event)
    {
        event.addListener(CRUMBLEMIST_LOOT_TABLE);
    }
}
