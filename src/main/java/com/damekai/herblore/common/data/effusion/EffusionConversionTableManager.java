package com.damekai.herblore.common.data.effusion;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.data.util.JsonDeserializer;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;

import java.util.HashMap;
import java.util.Map;

public class EffusionConversionTableManager<I, O> extends JsonReloadListener
{
    private final String path;
    private final JsonDeserializer<EffusionConversion<I, O>> jsonDeserializer;

    private ImmutableMap<ResourceLocation, EffusionConversion<I, O>> parsedData = ImmutableMap.of();

    public EffusionConversionTableManager(Gson gson, String path, JsonDeserializer<EffusionConversion<I, O>> jsonDeserializer)
    {
        super(gson, path);

        this.path = path;
        this.jsonDeserializer = jsonDeserializer;
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, IResourceManager resourceManager, IProfiler profiler)
    {
        Herblore.LOGGER.debug("Loading effusion conversion JSONs from " + path);

        Map<ResourceLocation, EffusionConversion<I, O>> data = new HashMap<>();

        jsons.entrySet().stream()
                .filter((entry) -> !entry.getKey().getPath().startsWith("_"))
                .forEach((entry) -> data.put(entry.getKey(), jsonDeserializer.fromJson(entry.getValue().getAsJsonObject())));

        parsedData = ImmutableMap.copyOf(data);
    }

    public ImmutableMap<I, O> getTable()
    {
        Map<I, O> table = new HashMap<>();
        parsedData.values().forEach((conversion) -> table.put(conversion.getInput(), conversion.getOutput()));
        return ImmutableMap.copyOf(table);
    }
}
