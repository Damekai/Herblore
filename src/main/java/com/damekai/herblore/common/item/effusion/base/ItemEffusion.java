package com.damekai.herblore.common.item.effusion.base;

import com.damekai.herblore.common.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class ItemEffusion extends Item
{
    public ItemEffusion(int defaultDurability)
    {
        super(ModItems.defaultItemProperties().defaultDurability(defaultDurability).setNoRepair());
    }

    public abstract void tick(ItemStack itemStack, World world, BlockPos blockPos);
}
