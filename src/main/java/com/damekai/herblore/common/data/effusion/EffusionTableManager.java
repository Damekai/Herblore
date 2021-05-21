package com.damekai.herblore.common.data.effusion;

import com.google.gson.Gson;
import net.minecraft.client.resources.JsonReloadListener;

public abstract class EffusionTableManager extends JsonReloadListener
{
    private static final Gson GSON = new Gson();

    public EffusionTableManager(String path)
    {
        super(GSON, path);
    }
}
