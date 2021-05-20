package com.damekai.herblore.common.data;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.effusion.ItemEffusionCrumblemist;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.block.Block;
import net.minecraft.client.resources.JsonReloadListener;
import net.minecraft.item.Item;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;

public class CrumblemistLootTableManager extends JsonReloadListener
{
    private static final Gson GSON = new Gson();

    private ImmutableMap<ResourceLocation, ItemEffusionCrumblemist.Result> crumblemistLootTable = ImmutableMap.of();

    public CrumblemistLootTableManager()
    {
        super(GSON, "crumblemist_loot_table");
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> entries, IResourceManager resourceManager, IProfiler profiler)
    {
        Herblore.LOGGER.debug("Loading Crumblemist loot table JSONs.");

        Map<ResourceLocation, ItemEffusionCrumblemist.Result> parsedCrumblemistLootTable = new HashMap<>();

        entries.forEach((resourceLocation, jsonElement) ->
        {
            if (!resourceLocation.getPath().startsWith("_"))
            {
                parsedCrumblemistLootTable.put(resourceLocation, fromJson(JSONUtils.convertToJsonObject(jsonElement, "top element")));
            }
        });

        crumblemistLootTable = ImmutableMap.copyOf(parsedCrumblemistLootTable);
    }

    public ImmutableMap<Block, ItemEffusionCrumblemist.Result> getResultsMap()
    {
        Map<Block, ItemEffusionCrumblemist.Result> results = new HashMap<>();
        crumblemistLootTable.values().forEach((result) -> results.put(result.getBlock().get(), result));
        return ImmutableMap.copyOf(results);
    }

    public static ItemEffusionCrumblemist.Result fromJson(JsonObject jsonObject)
    {
        Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(jsonObject.get("block").getAsString()));
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(jsonObject.get("item").getAsString()));
        int min = jsonObject.get("min").getAsInt();
        int max = jsonObject.get("max").getAsInt();

        return new ItemEffusionCrumblemist.Result(() -> block, () -> item, min, max);
    }
}
