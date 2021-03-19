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
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

import java.util.List;

public class FlaskEffectComet extends FlaskEffect
{
    private static final float PERCENT_DAMAGE_PREVENTED_PER_POTENCY = 0.2f;
    private static final int RADIUS_PER_POTENCY = 2;

    public FlaskEffectComet(Properties properties)
    {
        super(properties);
    }

    public static void onLivingDamage(LivingDamageEvent event)
    {
        if (EffectiveSide.get() == LogicalSide.CLIENT)
        {
            return;
        }

        LivingEntity livingEntity = event.getEntityLiving();

        if (event.getSource().damageType.equals(DamageSource.FALL.damageType))
        {
            FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(livingEntity);

            if (flaskHandler != null)
            {
                FlaskEffectInstance comet = flaskHandler.getFlaskEffectInstance(ModFlaskEffects.COMET.get());

                if (comet != null)
                {
                    int potency = comet.getPotency();

                    // Prevent the approproate amount of damage.
                    float initialAmount = event.getAmount();
                    float damagePrevented = initialAmount * PERCENT_DAMAGE_PREVENTED_PER_POTENCY * potency;

                    event.setAmount(initialAmount - damagePrevented);

                    // Cause damage to all entities in radius.
                    World world = livingEntity.getEntityWorld();

                    List<MobEntity> mobsInRange = world.getEntitiesWithinAABB(MobEntity.class, new AxisAlignedBB(
                            livingEntity.getPosX() - RADIUS_PER_POTENCY * potency,
                            livingEntity.getPosY() - 1,
                            livingEntity.getPosZ() - RADIUS_PER_POTENCY * potency,
                            livingEntity.getPosX() + RADIUS_PER_POTENCY * potency,
                            livingEntity.getPosY() + 1,
                            livingEntity.getPosZ() + RADIUS_PER_POTENCY * potency));

                    mobsInRange.forEach((mob) -> mob.attackEntityFrom(DamageSource.causeMobDamage(livingEntity), damagePrevented));

                    if (event.getAmount() <= 0f)
                    {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}
