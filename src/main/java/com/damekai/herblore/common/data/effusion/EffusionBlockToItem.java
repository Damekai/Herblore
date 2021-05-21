package com.damekai.herblore.common.data.effusion;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Supplier;

public class EffusionBlockToItem
{
    private final Supplier<Block> block;
    private final ImmutableList<EffusionItemResult> items;

    private EffusionBlockToItem(Supplier<Block> block, List<EffusionItemResult> items)
    {
        this.block = block;
        this.items = ImmutableList.copyOf(items);
    }

    public Supplier<Block> getBlock()
    {
        return block;
    }

    public ImmutableList<EffusionItemResult> getItems()
    {
        return items;
    }

    public static class Builder
    {
        private Supplier<Block> block;
        private final List<EffusionItemResult> items;

        public Builder()
        {
            items = new ArrayList<>();
        }

        public Builder blockInput(Supplier<Block> block)
        {
            this.block = block;
            return this;
        }

        public Builder itemOutput(EffusionItemResult item)
        {
            items.add(item);
            return this;
        }

        public EffusionBlockToItem build()
        {
            return new EffusionBlockToItem(block, items);
        }
    }
}
