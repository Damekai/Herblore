package com.damekai.herblore.common.effect;

import net.minecraft.entity.LivingEntity;

public class FlaskEffectHealthRegeneration extends FlaskEffect
{
    public FlaskEffectHealthRegeneration()
    {
        super("health_regeneration");
    }

    @Override
    public void onTick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        if (durationRemaining % 5 == 0)
        {
            livingEntity.heal(1);
        }
    }
}
