package com.damekai.herblore.common.flask.base;

import net.minecraft.entity.LivingEntity;

/**
 * When used instead of DurationFlaskEffect and such, it means that this FlaskEffect is similar to a vanilla instant Effect.
 */
public abstract class ApplicableFlaskEffect extends FlaskEffect
{
    public ApplicableFlaskEffect(FlaskEffect.Properties properties)
    {
        super(properties);
    }

    public final void onApply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        // Space for debug code.
        apply(flaskEffectInstance, livingEntity);
    }

    protected abstract void apply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity);
}
