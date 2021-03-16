package com.damekai.herblore.common.capability.flaskhandler;

import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.perk.base.FlaskPerk;
import net.minecraft.entity.LivingEntity;

public interface IFlaskHandler
{
    void applyFlaskEffectInstance(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity, FlaskPerk... flaskPerks);

    FlaskEffectInstance getFlaskEffectInstance(FlaskEffect flaskEffect);

    void removeFlaskEffectInstance(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity);

    void removeAllFlaskEffectInstances(LivingEntity livingEntity);

}
