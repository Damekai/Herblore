package com.damekai.herblore.common.item.effusion;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.effusion.base.ItemEffusion;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class ItemEffusionVerdure extends ItemEffusion
{
    private static final int TICK_FREQUENCY = 100;
    private static final float DOUBLE_TICK_PROBABILITY = 0.01f;

    public ItemEffusionVerdure()
    {
        super(2400);
    }

    @Override
    public void tick(ItemStack itemStack, World world, BlockPos blockPos)
    {
        if (!world.isClientSide)
        {
            int damageValue = itemStack.getDamageValue();
            //Herblore.LOGGER.debug("Bonemealing crops.");

            getBlockPosInRadius(blockPos, 4).forEach((pos) ->
            {
                if (world.random.nextFloat() < DOUBLE_TICK_PROBABILITY)
                {
                    BlockState blockState = world.getBlockState(pos);
                    if (blockState.getBlock() instanceof CropsBlock)
                    {
                        CropsBlock cropsBlock = (CropsBlock) blockState.getBlock();
                        cropsBlock.randomTick(blockState, (ServerWorld) world, pos, world.random);
                    }
                }
            });
        }
    }

    private static List<BlockPos> getBlockPosInRadius(BlockPos origin, int radius)
    {
        List<BlockPos> positions = new ArrayList<>();

        for (int i = -radius; i <= radius; i++)
        {
            for (int k = -radius; k <= radius; k++)
            {
                positions.add(new BlockPos(origin.getX() + i, origin.getY(), origin.getZ() + k));
            }
        }

        return positions;
    }
}
