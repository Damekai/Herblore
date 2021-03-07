package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.base.TickingFlaskEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public class FlaskEffectPenance extends TickingFlaskEffect
{
    private static final float HEALTH_RESTORED_PER_POTENCY = 2f;

    public FlaskEffectPenance(FlaskEffect.Properties properties)
    {
        super(properties, 20);
    }

    @Override
    protected void apply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        float prehealHealth = livingEntity.getHealth();
        livingEntity.heal(flaskEffectInstance.getPotency() * HEALTH_RESTORED_PER_POTENCY);
        float amountHealed = Math.max(0, livingEntity.getHealth() - prehealHealth); // Prevent case of "negative healing", just in case.

        flaskEffectInstance.getOrCreateTag().putFloat("penance_penalty", amountHealed / 2f);
    }

    @Override
    protected void expire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity) { }

    @Override
    protected void remove(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity) { }

    @Override
    protected void tick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        float penalty = flaskEffectInstance.getOrCreateTag().getFloat("penance_penalty");
        livingEntity.attackEntityFrom(DamageSource.MAGIC, penalty * tickRate / (float) flaskEffectInstance.getDurationFull());
    }
}
