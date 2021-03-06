package com.damekai.herblore.common.flaskeffect.base;

import com.damekai.herblore.common.flask.FlaskInstance;
import net.minecraft.entity.LivingEntity;

/**
 * When used instead of DurationFlaskEffect and such, it means that this FlaskEffect is similar to a vanilla instant Effect.
 */
public abstract class ApplicableFlaskEffect extends FlaskEffect
{
    public ApplicableFlaskEffect(String translationName)
    {
        super(translationName);
    }

    public final void onApply(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        // Space for debug code.
        apply(flaskInstance, livingEntity);
    }

    protected abstract void apply(FlaskInstance flaskInstance, LivingEntity livingEntity);
}
