package com.damekai.herblore.common.capability.flaskhandler;

import com.damekai.herblore.common.flaskeffect.base.FlaskEffect;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import net.minecraft.entity.LivingEntity;

public interface IFlaskHandler
{
    void applyFlask(FlaskInstance flaskInstance, LivingEntity livingEntity);

    FlaskInstance getFlask(Flask flask);

    FlaskInstance getFlaskWithEffect(FlaskEffect flaskEffect);

    void removeFlask(FlaskInstance flaskInstance, LivingEntity livingEntity);

    void removeAllFlasks(LivingEntity livingEntity);

}
