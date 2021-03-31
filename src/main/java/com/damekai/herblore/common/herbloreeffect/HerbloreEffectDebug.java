package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;

public class HerbloreEffectDebug extends HerbloreEffect implements HerbloreEffect.IApplicable, HerbloreEffect.ITickable, HerbloreEffect.IExpirable
{
    private final String debugTag;

    public HerbloreEffectDebug(HerbloreEffect.Properties properties, String debugTag)
    {
        super(properties);
        this.debugTag = debugTag;
    }

    @Override
    public void onApply(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Applying \"" + debugTag + "\" debug effect to " + livingEntity.getName().getString());
    }

    @Override
    public void onTick(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Ticking \"" + debugTag + "\" debug effect on " + livingEntity.getName().getString());
    }

    @Override
    public void onExpire(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Expired \"" + debugTag + "\" debug effect from " + livingEntity.getName().getString());
    }
}
