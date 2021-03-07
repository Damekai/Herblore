package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.base.TickingFlaskEffect;
import net.minecraft.entity.LivingEntity;

public class FlaskEffectHealthRegeneration extends TickingFlaskEffect
{
    public FlaskEffectHealthRegeneration(FlaskEffect.Properties properties)
    {
        super(properties, 5);
    }

    @Override
    protected void apply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity) { }

    @Override
    protected void expire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity) { }

    @Override
    protected void remove(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity) { }

    @Override
    protected void tick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        livingEntity.heal(1);
    }
}
