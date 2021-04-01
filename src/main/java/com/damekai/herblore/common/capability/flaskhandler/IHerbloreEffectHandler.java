package com.damekai.herblore.common.capability.flaskhandler;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraft.entity.LivingEntity;

public interface IHerbloreEffectHandler
{
    void applyHerbloreEffectInstance(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity);

    HerbloreEffectInstance getHerbloreEffectInstance(HerbloreEffect herbloreEffect);

    void removeHerbloreEffectInstance(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity);

    void removeAllHerbloreEffectInstances(LivingEntity livingEntity);

}
