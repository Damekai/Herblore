package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class FlaskEffectHaptic extends FlaskEffect
{
    private static final float KNOCKBACK_PER_POTENCY = 0.7f;

    public FlaskEffectHaptic(Properties properties)
    {
        super(properties);
    }

    public static void onLivingDamage(LivingDamageEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();

        if (event.getSource().getImmediateSource() instanceof LivingEntity)
        {
            FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(livingEntity);

            if (flaskHandler != null)
            {
                FlaskEffectInstance haptic = flaskHandler.getFlaskEffectInstance(ModFlaskEffects.HAPTIC.get());

                if (haptic != null)
                {
                    LivingEntity attacker = (LivingEntity) event.getSource().getImmediateSource();
                    if (attacker != null)
                    {
                        attacker.applyKnockback(haptic.getPotency() * KNOCKBACK_PER_POTENCY, -MathHelper.sin(attacker.rotationYaw * ((float)Math.PI / 180F)), MathHelper.cos(attacker.rotationYaw * ((float)Math.PI / 180F)));
                    }
                }
            }
        }
    }
}
