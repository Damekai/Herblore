package com.damekai.herblore.common.data.effusion;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;

public class EffusionBlockToItem
{
    private final Supplier<Block> block;
    private final ImmutableMap<Supplier<Item>, IItemCount> items;

    private EffusionBlockToItem(Supplier<Block> block, Map<Supplier<Item>, IItemCount> items)
    {
        this.block = block;
        this.items = ImmutableMap.copyOf(items);
    }

    public Supplier<Block> getBlock()
    {
        return block;
    }

    public ImmutableMap<Supplier<Item>, IItemCount> getItems()
    {
        return items;
    }

    public static class Builder
    {
        private Supplier<Block> block;
        private final Map<Supplier<Item>, IItemCount> items;

        public Builder()
        {
            items = new HashMap<>();
        }

        public Builder block(Supplier<Block> block)
        {
            this.block = block;
            return this;
        }

        public Builder item(Supplier<Item> item, int count)
        {
            items.put(item, new ItemCountFixed(count));
            return this;
        }

        public Builder item(Supplier<Item> item, int minCount, int maxCount)
        {
            items.put(item, new ItemCountRange(minCount, maxCount));
            return this;
        }

        public EffusionBlockToItem build()
        {
            return new EffusionBlockToItem(block, items);
        }
    }

    public interface IItemCount
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
