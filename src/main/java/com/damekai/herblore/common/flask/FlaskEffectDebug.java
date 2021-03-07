package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.base.TickingFlaskEffect;
import net.minecraft.entity.LivingEntity;

public class FlaskEffectDebug extends TickingFlaskEffect
{
    private final String debugTag;

    public FlaskEffectDebug(FlaskEffect.Properties properties, String debugTag)
    {
        super(properties, 1);
        this.debugTag = debugTag;
    }

    @Override
    protected void apply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Applying \"" + debugTag + "\" debug effect to " + livingEntity.getName().getString());
    }

    @Override
    protected void expire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Expired \"" + debugTag + "\" debug effect from " + livingEntity.getName().getString());
    }

    @Override
    protected void remove(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Removed \"" + debugTag + "\" debug effect from " + livingEntity.getName().getString());
    }

    @Override
    protected void tick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Ticking \"" + debugTag + "\" debug effect on " + livingEntity.getName().getString());
    }
}
