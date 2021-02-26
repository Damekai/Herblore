package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.effect.HerbloreEffectInstance;
import net.minecraft.entity.LivingEntity;

public interface IHerbloreEffectHandler
{
    void applyHerbloreEffects(LivingEntity livingEntity, HerbloreEffectInstance... herbloreEffectInstances);

    void tickHerbloreEffects(LivingEntity livingEntity);

    void removeHerbloreEffects(LivingEntity livingEntity, HerbloreEffectInstance... herbloreEffectInstances);

    void removeAllHerbloreEffects(LivingEntity livingEntity);

}
