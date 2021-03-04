package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.Herblore;
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
    public void onApply(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        Herblore.LOGGER.debug("Applying \"" + debugTag + "\" debug effect to " + livingEntity.getName().getString());
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

    @Override
    protected void tick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        Herblore.LOGGER.debug("Ticking \"" + debugTag + "\" debug effect on " + livingEntity.getName().getString());
    }
}
