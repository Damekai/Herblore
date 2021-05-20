package com.damekai.herblore.common.item.effusion;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.effusion.base.ItemEffusion;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ItemEffusionPheromone extends ItemEffusion
{
    private static final int RADIUS = 4;
    private static final float PHEROMONE_CHANCE = 0.01f;

    public ItemEffusionPheromone()
    {
        super(2400);
    }

    @Override
    public void tick(ItemStack itemStack, World world, BlockPos blockPos)
    {
        if (!world.isClientSide)
        {
            if (world.random.nextFloat() <= PHEROMONE_CHANCE)
            {
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
}
