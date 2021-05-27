package com.damekai.herblore.effusion;

import com.damekai.herblore.common.block.tile.TileEffusion;
import com.damekai.herblore.effusion.base.Effusion;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class EffusionPheromone extends Effusion
{
    private static final int RADIUS = 4;
    private static final float PHEROMONE_CHANCE = 0.01f;

    public EffusionPheromone()
    {
        super(0xF04FB4);
    }

    @Override
    public void onTick(TileEffusion effusionTile)
    {
        World world = effusionTile.getLevel();
        if (world != null && !world.isClientSide && world.random.nextFloat() <= PHEROMONE_CHANCE)
        {
            BlockPos blockPos = effusionTile.getBlockPos();
            List<AnimalEntity> validAnimalEntities = world.getEntitiesOfClass(AnimalEntity.class,
                    new AxisAlignedBB(
                            blockPos.getX() - RADIUS,
                            blockPos.getY() - 1,
                            blockPos.getZ() - RADIUS,
                            blockPos.getX() + RADIUS,
                            blockPos.getY() + 1,
                            blockPos.getZ() + RADIUS),
                    AnimalEntity::canFallInLove);

            if (!validAnimalEntities.isEmpty())
            {
                validAnimalEntities.get(ThreadLocalRandom.current().nextInt(0, validAnimalEntities.size())).setInLove(null);
            }
        }
    }
}
