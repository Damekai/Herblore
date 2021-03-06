package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.flaskeffect.base.TickingFlaskEffect;
import net.minecraft.entity.LivingEntity;

public class FlaskEffectHealthRegeneration extends TickingFlaskEffect
{
    public FlaskEffectHealthRegeneration()
    {
        super("health_regeneration", 5);
    }

    @Override
    protected void apply(FlaskInstance flaskInstance, LivingEntity livingEntity) { }

    @Override
    protected void expire(FlaskInstance flaskInstance, LivingEntity livingEntity) { }

    @Override
    protected void remove(FlaskInstance flaskInstance, LivingEntity livingEntity) { }

    @Override
    protected void tick(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        livingEntity.heal(1);
    }
}
