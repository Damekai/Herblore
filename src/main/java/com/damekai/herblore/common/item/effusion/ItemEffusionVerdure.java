package com.damekai.herblore.common.item.effusion;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.effusion.base.ItemEffusion;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ItemEffusionVerdure extends ItemEffusion
{
    private static final int ADDITIONAL_TICKS = 2;
    private static final float EFFECT_PROBABILITY = 0.01f;

    public ItemEffusionVerdure()
    {
        super(2400);
    }

    @Override
    public void tick(ItemStack itemStack, World world, BlockPos blockPos)
    {
        // Select blocks that will be affected.
        List<BlockPos> selection = getBlockPosInRadius(blockPos, 4).stream()
                .filter((pos) -> world.random.nextFloat() < EFFECT_PROBABILITY)
                .collect(Collectors.toList());

        // On the server side, perform the effect.
        if (!world.isClientSide)
        {
            selection.forEach((pos) ->
            {
                BlockState blockState = world.getBlockState(pos);
                if (blockState.getBlock() instanceof CropsBlock)
                {
                    CropsBlock cropsBlock = (CropsBlock) blockState.getBlock();

                    for (int i = 0; i < ADDITIONAL_TICKS; i++)
                    {
                        cropsBlock.randomTick(blockState, (ServerWorld) world, pos, world.random);
                    }
                }
            });
        }
        // On the client side, render some effect particles.
        else
        {
            selection.forEach((pos) ->
            {
                world.addParticle(ParticleTypes.EFFECT, pos.getX() + 0.5d, pos.getY() + 0.5d, pos.getZ() + 0.5d, 0d, 1d, 0d);
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
