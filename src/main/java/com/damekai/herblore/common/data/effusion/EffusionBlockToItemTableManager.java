package com.damekai.herblore.common.data.effusion;

import com.damekai.herblore.common.Herblore;
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
import java.util.function.Supplier;

public class EffusionBlockToItemTableManager extends EffusionTableManager
{
    private final String path;
    private ImmutableMap<ResourceLocation, EffusionBlockToItem> parsedData = ImmutableMap.of();

    public EffusionBlockToItemTableManager(String path)
    {
        super(path);
        this.path = path; // Only used for debug logging.
    }

    @Override
    protected void apply(Map<ResourceLocation, JsonElement> jsons, IResourceManager resourceManager, IProfiler profiler)
    {
        Herblore.LOGGER.debug("Loading Effusion block-to-item JSON files from " + path);

        Map<ResourceLocation, EffusionBlockToItem> data = new HashMap<>();

        jsons.entrySet().stream()
                .filter((entry) -> !entry.getKey().getPath().startsWith("_"))
                .forEach((entry) -> data.put(entry.getKey(), fromJson(entry.getValue().getAsJsonObject())));

        parsedData = ImmutableMap.copyOf(data);
    }

    public ImmutableMap<Block, EffusionBlockToItem> getTable()
    {
        Map<Block, EffusionBlockToItem> table = new HashMap<>();
        parsedData.values().forEach((value) -> table.put(value.getBlock().get(), value));
        return ImmutableMap.copyOf(table);
    }

    private static EffusionBlockToItem fromJson(JsonObject jsonObject)
    {
        EffusionBlockToItem.Builder builder = new EffusionBlockToItem.Builder();

        builder.blockInput(() -> ForgeRegistries.BLOCKS.getValue(new ResourceLocation(jsonObject.get("block").getAsString())));

        jsonObject.getAsJsonArray("items").forEach((itemJsonElement) ->
        {
            JsonObject itemJsonObject = itemJsonElement.getAsJsonObject();

            Supplier<Item> item = () -> ForgeRegistries.ITEMS.getValue(new ResourceLocation(itemJsonObject.get("item").getAsString()));

            JsonObject itemCountJsonObject = itemJsonObject.get("count").getAsJsonObject();
            switch (itemCountJsonObject.get("type").getAsString())
            {
                case "fixed":
                    builder.itemOutput(new EffusionItemResult(item, itemCountJsonObject.get("count").getAsInt()));
                    break;
                case "range":
                    builder.itemOutput(new EffusionItemResult(item, itemCountJsonObject.get("min").getAsInt(), itemCountJsonObject.get("max").getAsInt()));
                    break;
                default:
                    builder.itemOutput(new EffusionItemResult(item, 1));
                    break;
            }
        });

        return builder.build();
    }
}
