package com.damekai.herblore.effusion;

import com.damekai.herblore.common.block.tile.TileEffusion;
import com.damekai.herblore.effusion.base.Effusion;
import net.minecraft.block.IGrowable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class EffusionVerdure extends Effusion
{
    private static final int RADIUS = 4;
    private static final float VERDURE_CHANCE = 0.5f;

    public EffusionVerdure()
    {
        super(0x017D08);
    }

    @Override
    public void onTick(TileEffusion effusionTile)
    {
        World world = effusionTile.getLevel();
        if (world != null && !world.isClientSide)
        {
            // Roll to see if a block gets affected.
            if (world.random.nextFloat() <= VERDURE_CHANCE)
            {
                // Find all blocks within the radius that are growable.
                List<BlockPos> validPos = effusionTile.getBlockPosInRadius(RADIUS, false).stream()
                        .filter((pos) -> world.getBlockState(pos).getBlock() instanceof IGrowable)
                        .collect(Collectors.toList());

                if (!validPos.isEmpty())
                {
                    // Choose a random valid block to grow.
                    BlockPos verdurePos = validPos.get(ThreadLocalRandom.current().nextInt(0, validPos.size()));

                    // Trigger an extra random tick to simulate growth acceleration.
                    world.getBlockState(verdurePos).randomTick((ServerWorld) world, verdurePos, world.random);
                }
            }
        }
    }
}
