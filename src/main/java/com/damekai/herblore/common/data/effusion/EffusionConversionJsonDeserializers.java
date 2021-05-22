package com.damekai.herblore.common.data.effusion;

import com.damekai.herblore.common.data.util.JsonDeserializer;
import com.google.common.collect.ImmutableList;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.block.Block;

import java.util.ArrayList;
import java.util.List;

public final class EffusionConversionJsonDeserializers
{
    public static final JsonDeserializer<EffusionConversion<Block, ImmutableList<EffusionItemResult>>> BLOCK_TO_ITEMS =
            new JsonDeserializer<EffusionConversion<Block, ImmutableList<EffusionItemResult>>>()
            {
                @Override
                public EffusionConversion<Block, ImmutableList<EffusionItemResult>> fromJson(JsonObject jsonObject)
                {
                    Block input = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(jsonObject.get("input").getAsString()));

                    List<EffusionItemResult> outputs = new ArrayList<>();
                    jsonObject.getAsJsonArray("outputs").forEach((outputJsonElement) -> outputs.add(EffusionItemResult.fromJson(outputJsonElement.getAsJsonObject())));

                    return new EffusionConversion<>(input, ImmutableList.copyOf(outputs));
                }
            };

    public static final JsonDeserializer<EffusionConversion<Item, ImmutableList<EffusionItemResult>>> ITEM_TO_ITEMS =
            new JsonDeserializer<EffusionConversion<Item, ImmutableList<EffusionItemResult>>>()
            {
                @Override
                public EffusionConversion<Item, ImmutableList<EffusionItemResult>> fromJson(JsonObject jsonObject)
                {
                    Item input = ForgeRegistries.ITEMS.getValue(new ResourceLocation(jsonObject.get("input").getAsString()));

                    List<EffusionItemResult> outputs = new ArrayList<>();
                    jsonObject.getAsJsonArray("outputs").forEach((outputJsonElement) -> outputs.add(EffusionItemResult.fromJson(outputJsonElement.getAsJsonObject())));

                    return new EffusionConversion<>(input, ImmutableList.copyOf(outputs));
                }
            };
}
