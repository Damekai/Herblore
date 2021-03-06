package com.damekai.herblore.common.flaskeffect.base;

import com.damekai.herblore.common.flask.FlaskInstance;
import net.minecraft.entity.LivingEntity;

public abstract class TickingFlaskEffect extends DurationFlaskEffect
{
    private final int tickRate;

    public TickingFlaskEffect(String translationName, int tickRate)
    {
        super(translationName);

        this.tickRate = tickRate;
    }

    public final void onTick(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        if (flaskInstance.getDurationRemaining() % tickRate == 0)
        {
            // Space for debug code.
            this.tick(flaskInstance, livingEntity);
        }
    }

    protected abstract void tick(FlaskInstance flaskInstance, LivingEntity livingEntity);
}
