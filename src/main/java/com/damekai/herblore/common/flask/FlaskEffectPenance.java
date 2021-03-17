package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public class FlaskEffectPenance extends FlaskEffect implements FlaskEffect.IApplicable, FlaskEffect.ITickable, FlaskEffect.IExpirable
{
    private static final float HEALTH_RESTORED_PER_POTENCY = 4f;
    private static final int DAMAGE_OCCURENCES = 5;


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

        float penalty = amountHealed / 2f;
        flaskEffectInstance.getOrCreateTag().putFloat("penance_penalty_total", penalty);
        flaskEffectInstance.getOrCreateTag().putFloat("penance_penalty_remaining", penalty);
    }

    @Override
    public void onTick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (flaskEffectInstance.getDurationRemaining() == flaskEffectInstance.getDurationFull() || flaskEffectInstance.getDurationRemaining() % (flaskEffectInstance.getDurationFull() / DAMAGE_OCCURENCES) != 0) // Divide the damage over five damage instances.
        {
            return;
        }

        float penalty = flaskEffectInstance.getOrCreateTag().getFloat("penance_penalty_total");
        float damage = penalty * (float) (flaskEffectInstance.getDurationFull() / DAMAGE_OCCURENCES) / (float) flaskEffectInstance.getDurationFull();
        float penaltyRemaining = flaskEffectInstance.getOrCreateTag().getFloat("penance_penalty_remaining");
        flaskEffectInstance.getOrCreateTag().putFloat("penance_penalty_remaining", penaltyRemaining - damage);

        livingEntity.attackEntityFrom(DamageSource.MAGIC, damage);
    }

    @Override
    public void onExpire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        float damageRemaining = flaskEffectInstance.getOrCreateTag().getFloat("penance_penalty_remaining");
        if (damageRemaining > 0f)
        {
            livingEntity.attackEntityFrom(DamageSource.MAGIC, damageRemaining);
        }
    }
}
