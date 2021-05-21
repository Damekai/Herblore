package com.damekai.herblore.common.item.effusion;

import com.damekai.herblore.common.item.effusion.base.ItemEffusion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemEffusionAlterfog extends ItemEffusion
{
    private static final int RADIUS = 1;

    public ItemEffusionAlterfog()
    {
        super(2400);
    }

    @Override
    public void tick(ItemStack itemStack, World world, BlockPos blockPos)
    {

    }
}
