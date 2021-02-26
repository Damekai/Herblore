package com.damekai.herblore.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ItemPestleAndMortar extends Item
{
    public ItemPestleAndMortar()
    {
        super(ModItems.defaultItemProperties());
    }

    // Container item to prevent consumption when crafting. TODO: Durability? Look at examples of similar tools from other mods.
    @Override
    public boolean hasContainerItem(ItemStack stack)
    {
        return true;
    }
    @Override
    public ItemStack getContainerItem(ItemStack itemStack)
    {
        return new ItemStack(ModItems.PESTLE_AND_MORTAR.get(), 1);
    }
}
