package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

public class HerbloreEffectStrider extends HerbloreEffect
{
    private static final float HORIZONTAL_VELOCITY_MULTIPLIER_BONUS_PER_POTENCY = 1f;
    private static final float VERTICAL_VELOCITY_MULTIPLIER_BONUS_PER_POTENCY = 0.5f;

    public HerbloreEffectStrider(HerbloreEffect.Properties properties)
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
            HerbloreEffectInstance strider = flaskHandler.getFlaskEffectInstance(ModHerbloreEffects.STRIDER.get());
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
