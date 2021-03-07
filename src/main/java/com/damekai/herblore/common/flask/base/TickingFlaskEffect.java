package com.damekai.herblore.common.flask.base;

import net.minecraft.entity.LivingEntity;

public abstract class TickingFlaskEffect extends DurationFlaskEffect
{
    protected final int tickRate;

    public TickingFlaskEffect(FlaskEffect.Properties properties, int tickRate)
    {
        super(properties);

        this.tickRate = tickRate;
    }

    public final void onTick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (flaskEffectInstance.getDurationRemaining() % tickRate == 0)
        {
            // Space for debug code.
            this.tick(flaskEffectInstance, livingEntity);
        }
    }

    protected abstract void tick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity);
}
