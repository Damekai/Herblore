package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.flask.FlaskInstance;
import net.minecraft.entity.LivingEntity;

public interface IFlaskHandler
{
    void applyFlasks(LivingEntity livingEntity, FlaskInstance... herbloreEffectInstances);

    void tickFlasks(LivingEntity livingEntity);

    void removeFlasks(LivingEntity livingEntity, FlaskInstance... herbloreEffectInstances);

    void removeAllFlasks(LivingEntity livingEntity);

}
