package com.damekai.herblore.common.flaskeffect.base;

import com.damekai.herblore.common.flask.FlaskInstance;
import net.minecraft.entity.LivingEntity;

public abstract class DurationFlaskEffect extends ApplicableFlaskEffect
{
    public DurationFlaskEffect(String translationName)
    {
        super(translationName);
    }

    public final void onExpire(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        // Space for debug code.
        expire(flaskInstance, livingEntity);
    }

    protected abstract void expire(FlaskInstance flaskInstance, LivingEntity livingEntity);

    public final void onRemove(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        // Space for debug code.
        remove(flaskInstance, livingEntity);
    }

    protected abstract void remove(FlaskInstance flaskInstance, LivingEntity livingEntity);
}
