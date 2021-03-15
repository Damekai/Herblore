package com.damekai.herblore.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPestleAndMortar extends Item
{
    public ItemPestleAndMortar()
    {
        super(ModItems.defaultItemProperties());
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean hasContainerItem()
    {
        return true;
    }

    @Override
    public ItemStack getContainerItem(ItemStack stack)
    {
        return stack.copy();
    }
}
