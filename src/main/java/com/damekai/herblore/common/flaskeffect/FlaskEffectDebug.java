package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.flaskeffect.base.TickingFlaskEffect;
import net.minecraft.entity.LivingEntity;

public class FlaskEffectDebug extends TickingFlaskEffect
{
    private final String debugTag;

    public FlaskEffectDebug(String translationName, String debugTag)
    {
        super(translationName, 1);
        this.debugTag = debugTag;
    }

    @Override
    protected void apply(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Applying \"" + debugTag + "\" debug effect to " + livingEntity.getName().getString());
    }

    @Override
    protected void expire(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Expired \"" + debugTag + "\" debug effect from " + livingEntity.getName().getString());
    }

    @Override
    protected void remove(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Removed \"" + debugTag + "\" debug effect from " + livingEntity.getName().getString());
    }

    @Override
    protected void tick(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Ticking \"" + debugTag + "\" debug effect on " + livingEntity.getName().getString());
    }
}
