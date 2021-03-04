package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.flaskeffect.base.TickingFlaskEffect;
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
