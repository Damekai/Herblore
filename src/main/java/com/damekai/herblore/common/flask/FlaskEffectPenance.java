package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public class FlaskEffectPenance extends FlaskEffect implements FlaskEffect.IApplicable, FlaskEffect.ITickable
{
    private static final float HEALTH_RESTORED_PER_POTENCY = 2f;


    public FlaskEffectPenance(FlaskEffect.Properties properties)
    {
        super(properties);
    }

    @Override
    public void onApply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        float prehealHealth = livingEntity.getHealth();
        livingEntity.heal(flaskEffectInstance.getPotency() * HEALTH_RESTORED_PER_POTENCY);
        float amountHealed = Math.max(0, livingEntity.getHealth() - prehealHealth); // Prevent case of "negative healing", just in case.

        flaskEffectInstance.getOrCreateTag().putFloat("penance_penalty", amountHealed / 2f);
    }

    @Override
    public void onTick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (flaskEffectInstance.getDurationRemaining() % (flaskEffectInstance.getDurationFull() / 10) != 0) // Divide the damage over ten damage instances.
        {
            return;
        }

        float penalty = flaskEffectInstance.getOrCreateTag().getFloat("penance_penalty");
        livingEntity.attackEntityFrom(DamageSource.MAGIC, penalty * (float) (flaskEffectInstance.getDurationFull() / 10) / (float) flaskEffectInstance.getDurationFull());
    }
}
