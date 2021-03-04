package com.damekai.herblore.common.flaskeffect.base;

import net.minecraft.entity.LivingEntity;

public abstract class TickingFlaskEffect extends FlaskEffect
{
    private final int tickRate;

    public TickingFlaskEffect(String translationName, int tickRate)
    {
        super(translationName);

        this.tickRate = tickRate;
    }

    public final void onTick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        if (durationRemaining % tickRate == 0)
        {
            this.tick(livingEntity, potency, durationFull, durationRemaining);
        }
    }

    protected abstract void tick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining);
}
