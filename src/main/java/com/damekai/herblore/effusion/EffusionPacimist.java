package com.damekai.herblore.effusion;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.tile.TileEffusion;
import com.damekai.herblore.effusion.base.Effusion;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.stream.Collectors;

public class EffusionPacimist extends Effusion
{
    private static final int RADIUS = 5;

    public EffusionPacimist()
    {
        super(0xCDC5DE);
    }

    @Override
    public void onTick(TileEffusion effusionTile)
    {
        World world = effusionTile.getLevel();

        if (world != null && !world.isClientSide)
        {
            BlockPos blockPos = effusionTile.getBlockPos();
            AxisAlignedBB bounds = new AxisAlignedBB(
                    blockPos.getX() - RADIUS,
                    blockPos.getY() - 1,
                    blockPos.getZ() - RADIUS,
                    blockPos.getX() + RADIUS,
                    blockPos.getY() + 1,
                    blockPos.getZ() + RADIUS);

            List<MonsterEntity> monstersInRange = world.getEntitiesOfClass(MonsterEntity.class, bounds);

            // Note: This DRASTICALLY reduces the chance that mobs will attack you, but it's not 100% guaranteed.
            monstersInRange.forEach((monsterEntity) ->
            {
                monsterEntity.targetSelector.getRunningGoals()
                        .filter((prioritizedGoal) -> prioritizedGoal.getGoal() instanceof NearestAttackableTargetGoal)
                        .forEach(PrioritizedGoal::stop);
                monsterEntity.setTarget(null);
            });
        }
    }
}
