package com.damekai.herblore.common.data.effusion;

import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class EffusionItemResult
{
    private final Item item;
    private final IItemCount count;

    public EffusionItemResult(Item item, int count)
    {
        this.item = item;
        this.count = new ItemCountFixed(count);
    }

    public EffusionItemResult(Item item, int minCount, int maxCount)
    {
        this.item = item;
        this.count = new ItemCountRange(minCount, maxCount);
    }

    public ItemStack createItemStack()
    {
        return new ItemStack(item, count.getCount());
    }

    public static EffusionItemResult fromJson(JsonObject jsonObject)
    {
        Item item = ForgeRegistries.ITEMS.getValue(new ResourceLocation(jsonObject.get("item").getAsString()));

        JsonObject itemCountJsonObject = jsonObject.get("count").getAsJsonObject();
        switch (itemCountJsonObject.get("type").getAsString())
        {
            case "fixed":
                return new EffusionItemResult(item, itemCountJsonObject.get("count").getAsInt());
            case "range":
                return new EffusionItemResult(item, itemCountJsonObject.get("min").getAsInt(), itemCountJsonObject.get("max").getAsInt());
            default:
                return new EffusionItemResult(item, 1);
        }
    }

    private interface IItemCount
    {
        int getCount();
    }

    private static class ItemCountFixed implements IItemCount
    {
        private final int count;

        public ItemCountFixed(int count)
        {
            this.count = count;
        }

        @Override
        public int getCount()
        {
            return count;
        }
    }

    private static class ItemCountRange implements IItemCount
    {
        private final int min;
        private final int max;

        public ItemCountRange(int min, int max)
        {
            if (min < 0 || min >= max)
            {
                throw new IllegalArgumentException("Invalid values for an ItemCountRange minimum and maximum.");
            }

            this.min = min;
            this.max = max;
        }

        public int getCount()
        {
            return ThreadLocalRandom.current().nextInt(min, max + 1);
        }
    }
}
