package com.damekai.herblore.common.effect;

import net.minecraft.entity.LivingEntity;

public class FlaskEffectHealthRegeneration extends TickingFlaskEffect
{
    public FlaskEffectHealthRegeneration()
    {
        super("health_regeneration", 5);
    }

    @Override
    protected void tick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        livingEntity.heal(1);
    }
}
