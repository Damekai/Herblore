package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.capability.flaskhandler.HerbloreEffectHandler;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

public class HerbloreEffectStrider extends HerbloreEffect
{
    private static final float HORIZONTAL_VELOCITY_MULTIPLIER = 4f;
    private static final float VERTICAL_VELOCITY_MULTIPLIER = 2f;

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

        HerbloreEffectHandler herbloreEffectHandler = HerbloreEffectHandler.getHerbloreEffectHandlerOf(livingEntity);
        if (herbloreEffectHandler != null)
        {
            HerbloreEffectInstance strider = herbloreEffectHandler.getHerbloreEffectInstance(ModHerbloreEffects.STRIDER.get());
            if (strider != null)
            {
                Vector3d currentMotion = livingEntity.getMotion();
                livingEntity.setMotion(
                        currentMotion.getX() * HORIZONTAL_VELOCITY_MULTIPLIER,
                        currentMotion.getY() * VERTICAL_VELOCITY_MULTIPLIER,
                        currentMotion.getZ() * HORIZONTAL_VELOCITY_MULTIPLIER);

                livingEntity.velocityChanged = true;
            }
        }
    }
}
