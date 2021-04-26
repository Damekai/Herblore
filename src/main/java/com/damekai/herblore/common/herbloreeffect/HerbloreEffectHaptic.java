package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.capability.herbloreeffecthandler.HerbloreEffectHandler;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

import java.util.function.Supplier;

public class HerbloreEffectHaptic extends HerbloreEffect
{
    private static final float KNOCKBACK = 2.5f;

    public HerbloreEffectHaptic(Supplier<Effect> guiEffect)
    {
        super(guiEffect);
    }

    public static void onLivingDamage(LivingDamageEvent event)
    {
        if (EffectiveSide.get() == LogicalSide.CLIENT)
        {
            return;
        }

        LivingEntity livingEntity = event.getEntityLiving();

        if (event.getSource().getDirectEntity() instanceof LivingEntity)
        {
            HerbloreEffectHandler herbloreEffectHandler = HerbloreEffectHandler.getHerbloreEffectHandlerOf(livingEntity);

            if (herbloreEffectHandler != null)
            {
                HerbloreEffectInstance haptic = herbloreEffectHandler.getHerbloreEffectInstance(ModHerbloreEffects.HAPTIC.get());

                if (haptic != null)
                {
                    LivingEntity attacker = (LivingEntity) event.getSource().getDirectEntity();
                    if (attacker != null)
                    {
                        attacker.knockback(KNOCKBACK, -MathHelper.sin(attacker.yRot * ((float)Math.PI / 180F)), MathHelper.cos(attacker.yRot * ((float)Math.PI / 180F)));
                    }
                }
            }
        }
    }
}
