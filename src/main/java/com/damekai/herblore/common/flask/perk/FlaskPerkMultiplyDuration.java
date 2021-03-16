package com.damekai.herblore.common.flask.perk;

import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.perk.base.FlaskPerk;
import net.minecraft.entity.LivingEntity;

public class FlaskPerkMultiplyDuration extends FlaskPerk
{
    private final float amount;

    public FlaskPerkMultiplyDuration(String translationName, float amount)
    {
        super(translationName);

        this.amount = amount;
    }

    @Override
    public void applyPerk(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        flaskEffectInstance.setDuration(Math.round(flaskEffectInstance.getDurationFull() * amount));
    }
}
