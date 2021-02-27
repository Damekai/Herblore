package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.Herblore;
import net.minecraft.entity.LivingEntity;

public class FlaskEffectDebug extends FlaskEffect
{
    private final String debugTag;

    public FlaskEffectDebug(String translationName, String debugTag)
    {
        super(translationName);
        this.debugTag = debugTag;
    }

    @Override
    public void onApply(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        Herblore.LOGGER.debug("Applying \"" + debugTag + "\" debug effect to " + livingEntity.getName().getString());
    }

    @Override
    public void onTick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        Herblore.LOGGER.debug("Ticking \"" + debugTag + "\" debug effect on " + livingEntity.getName().getString());
    }

    @Override
    public void onExpire(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        Herblore.LOGGER.debug("Expired \"" + debugTag + "\" debug effect from " + livingEntity.getName().getString());
    }

    @Override
    public void onRemove(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        Herblore.LOGGER.debug("Removed \"" + debugTag + "\" debug effect from " + livingEntity.getName().getString());
    }
}
