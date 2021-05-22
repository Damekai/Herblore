package com.damekai.herblore.common.data;

import com.damekai.herblore.common.data.effusion.EffusionConversionJsonDeserializers;
import com.damekai.herblore.common.data.effusion.EffusionConversionTableManager;
import com.damekai.herblore.common.data.effusion.EffusionItemResult;
import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;
import net.minecraft.block.Block;
import net.minecraftforge.event.AddReloadListenerEvent;

public class ModAssetManagers
{
    public static final EffusionConversionTableManager<Block, ImmutableList<EffusionItemResult>> CRUMBLEMIST_TABLE =
            new EffusionConversionTableManager<>(new Gson(), "effusion/crumblemist", EffusionConversionJsonDeserializers.BLOCK_TO_ITEMS);

    public static void onAddReloadListener(AddReloadListenerEvent event)
    {
        event.addListener(CRUMBLEMIST_TABLE);
    }
}
