package com.damekai.herblore.common.data.effusion;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class EffusionItemResult
{
    private final Supplier<Item> item;
    private final IItemCount count;

    public EffusionItemResult(Supplier<Item> item, int count)
    {
        this.item = item;
        this.count = new ItemCountFixed(count);
    }

    public EffusionItemResult(Supplier<Item> item, int minCount, int maxCount)
    {
        this.item = item;
        this.count = new ItemCountRange(minCount, maxCount);
    }

    public ItemStack createItemStack()
    {
        return new ItemStack(item.get(), count.getCount());
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
