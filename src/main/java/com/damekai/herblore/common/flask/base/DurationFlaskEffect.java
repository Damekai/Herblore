package com.damekai.herblore.common.flask.base;

import net.minecraft.entity.LivingEntity;

public abstract class DurationFlaskEffect extends ApplicableFlaskEffect
{
    public DurationFlaskEffect(FlaskEffect.Properties properties)
    {
        super(properties);
    }

    public final void onExpire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        // Space for debug code.
        expire(flaskEffectInstance, livingEntity);
    }

    protected abstract void expire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity);

    public final void onRemove(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        // Space for debug code.
        remove(flaskEffectInstance, livingEntity);
    }

    protected abstract void remove(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity);
}
