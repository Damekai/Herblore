package com.damekai.herblore.common.capability.flaskhandler;

import com.damekai.herblore.common.flaskeffect.base.FlaskEffect;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import net.minecraft.entity.LivingEntity;

public interface IFlaskHandler
{
    void applyFlasks(LivingEntity livingEntity, FlaskInstance... herbloreEffectInstances);

    FlaskInstance getFlask(Flask flask);

    FlaskInstance getFlaskWithEffect(FlaskEffect flaskEffect);

    void tickFlasks(LivingEntity livingEntity);

    void removeFlasks(LivingEntity livingEntity, FlaskInstance... herbloreEffectInstances);

    void removeAllFlasks(LivingEntity livingEntity);

}
