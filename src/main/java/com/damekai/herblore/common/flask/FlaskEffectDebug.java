package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;

public class FlaskEffectDebug extends FlaskEffect implements FlaskEffect.IApplicable, FlaskEffect.ITickable, FlaskEffect.IExpirable
{
    private final String debugTag;

    public FlaskEffectDebug(FlaskEffect.Properties properties, String debugTag)
    {
        super(properties);
        this.debugTag = debugTag;
    }

    @Override
    public void onApply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Applying \"" + debugTag + "\" debug effect to " + livingEntity.getName().getString());
    }

    @Override
    public void onTick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Ticking \"" + debugTag + "\" debug effect on " + livingEntity.getName().getString());
    }

    @Override
    public void onExpire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Expired \"" + debugTag + "\" debug effect from " + livingEntity.getName().getString());
    }
}
