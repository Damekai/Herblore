package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingEvent;

public class FlaskEffectStrider extends FlaskEffect
{
    private static final float HORIZONTAL_VELOCITY_MULTIPLIER_BONUS_PER_POTENCY = 1f;
    private static final float VERTICAL_VELOCITY_MULTIPLIER_BONUS_PER_POTENCY = 0.5f;

    public FlaskEffectStrider(FlaskEffect.Properties properties)
    {
        super(properties);
    }

    public static void onLivingJump(LivingEvent.LivingJumpEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();

        FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(livingEntity);
        if (flaskHandler != null)
        {
            FlaskEffectInstance strider = flaskHandler.getFlaskEffectInstance(ModFlaskEffects.STRIDER.get());
            if (strider != null)
            {
                int potency = strider.getPotency();
                float horizontalVelocityMultiplier = 1f + HORIZONTAL_VELOCITY_MULTIPLIER_BONUS_PER_POTENCY * potency;
                float verticalVelocityMultiplier = 1f + VERTICAL_VELOCITY_MULTIPLIER_BONUS_PER_POTENCY * potency;
                Vector3d currentMotion = livingEntity.getMotion();
                livingEntity.setMotion(
                        currentMotion.getX() * horizontalVelocityMultiplier,
                        currentMotion.getY() * verticalVelocityMultiplier,
                        currentMotion.getZ() * horizontalVelocityMultiplier);

                /*livingEntity.setMotion(livingEntity.getMotion().add(
                        livingEntity.getMotion().getX() * (livingEntity.isSprinting() ? 1 : 3) * (strider.getPotency() / 2.5d), // Compound current motion for every 2.5 potency. Tripled net effect if not sprinting.
                        livingEntity.getMotion().getY() * (strider.getPotency() / 2.5d), // Compound current motion for every 2.5 potency.
                        livingEntity.getMotion().getZ() * (livingEntity.isSprinting() ? 1 : 3) * (strider.getPotency() / 2.5d))); // Compound current motion for every 2.5 potency. Tripled net effect if not sprinting.
                */

                livingEntity.velocityChanged = true;
            }
        }
    }
}
