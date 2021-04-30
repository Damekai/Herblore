package com.damekai.herblore.common.item.effusion;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.effusion.base.ItemEffusion;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemEffusionVerdure extends ItemEffusion
{
    private static final int TICK_FREQUENCY = 100;

    public ItemEffusionVerdure()
    {
        super(400);
    }

    @Override
    public void tick(ItemStack itemStack, World world, BlockPos blockPos)
    {
        if (!world.isClientSide)
        {
            int damageValue = itemStack.getDamageValue();
            if (damageValue % TICK_FREQUENCY == 0)
            {
                Herblore.LOGGER.debug("Bonemealing crops.");
            }
        }
    }
}
