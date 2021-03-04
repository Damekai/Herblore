package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.flaskeffect.base.FlaskEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;

public class FlaskEffectBounding extends FlaskEffect
{
    public FlaskEffectBounding()
    {
        super("bounding");
    }

    public static void onLivingJump(LivingEvent.LivingJumpEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();

        FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(livingEntity);
        if (flaskHandler != null)
        {
            FlaskInstance bounding = flaskHandler.getFlaskWithEffect(ModFlaskEffects.BOUNDING.get());
            if (bounding != null)
            {
                livingEntity.setMotion(livingEntity.getMotion().add(
                        livingEntity.getMotion().getX() * (livingEntity.isSprinting() ? 1 : 3) * (bounding.getPotency() / 5d), // Compound current motion for every 5 potency. Tripled net effect if not sprinting.
                        livingEntity.getMotion().getY() * (bounding.getPotency() / 5d), // Compound current motion for every 5 potency.
                        livingEntity.getMotion().getZ() * (livingEntity.isSprinting() ? 1 : 3) * (bounding.getPotency() / 5d))); // Compound current motion for every 5 potency. Tripled net effect if not sprinting.
                livingEntity.velocityChanged = true;
            }
        }
    }
}
