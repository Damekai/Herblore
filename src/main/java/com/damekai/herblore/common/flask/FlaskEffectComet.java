package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

import java.util.List;

public class FlaskEffectComet extends FlaskEffect
{
    private static final int RADIUS_PER_POTENCY = 1;

    public FlaskEffectComet(Properties properties)
    {
        super(properties);
    }

    public static void onLivingDamage(LivingDamageEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();

        if (event.getSource().damageType.equals(DamageSource.FALL.damageType))
        {
            FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(livingEntity);

            if (flaskHandler != null)
            {
                FlaskEffectInstance comet = flaskHandler.getFlaskEffectInstance(ModFlaskEffects.COMET.get());

                if (comet != null)
                {
                    World world = livingEntity.getEntityWorld();

                    List<MobEntity> mobsInRange = world.getEntitiesWithinAABB(MobEntity.class, new AxisAlignedBB(
                            livingEntity.getPosX() - RADIUS_PER_POTENCY * comet.getPotency(),
                            livingEntity.getPosY() - 1,
                            livingEntity.getPosZ() - RADIUS_PER_POTENCY * comet.getPotency(),
                            livingEntity.getPosX() + RADIUS_PER_POTENCY * comet.getPotency(),
                            livingEntity.getPosY() + 1,
                            livingEntity.getPosZ() + RADIUS_PER_POTENCY * comet.getPotency()));

                    mobsInRange.forEach((mob) -> mob.attackEntityFrom(DamageSource.causeMobDamage(livingEntity), event.getAmount()));

                    event.setCanceled(true);
                }
            }
        }
    }
}
