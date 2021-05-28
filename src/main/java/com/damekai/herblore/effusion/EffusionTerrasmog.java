package com.damekai.herblore.effusion;

import com.damekai.herblore.common.block.tile.TileEffusion;
import com.damekai.herblore.effusion.base.Effusion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class EffusionTerrasmog extends Effusion
{
    private static final int RADIUS = 5;
    private static final float TERRASMOG_CHANCE = 0.3f;

    public EffusionTerrasmog()
    {
        super(0x4F8300);
    }

    @Override
    public void onTick(TileEffusion effusionTile)
    {
        World world = effusionTile.getLevel();
        if (world != null && !world.isClientSide)
        {
            // Roll to see if a block should be affected.
            if (world.random.nextFloat() <= TERRASMOG_CHANCE)
            {
                List<BlockPos> validBlockPos = effusionTile.getBlockPosInBounds(new AxisAlignedBB(-RADIUS, 0, -RADIUS, RADIUS, RADIUS, RADIUS), false).stream()
                        .filter((pos) ->
                        {
                            BlockState state = world.getBlockState(pos);
                            float strength = state.getDestroySpeed(world, pos);
                            return state.getBlock() != Blocks.AIR && strength >= 0 && strength <= 2.0f;
                        })
                        .collect(Collectors.toList());

                if (!validBlockPos.isEmpty())
                {
                    world.destroyBlock(validBlockPos.get(ThreadLocalRandom.current().nextInt(0, validBlockPos.size())), false);
                }
            }
        }
    }
}
