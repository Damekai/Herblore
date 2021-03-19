package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

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
        if (EffectiveSide.get() == LogicalSide.CLIENT)
        {
            return;
        }

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

                livingEntity.velocityChanged = true;
            }
        }
    }
}
