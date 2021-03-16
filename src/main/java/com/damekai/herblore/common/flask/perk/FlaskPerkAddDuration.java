package com.damekai.herblore.common.flask.perk;

import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.perk.base.FlaskPerk;
import net.minecraft.entity.LivingEntity;

public class FlaskPerkAddDuration extends FlaskPerk
{
    private final int amount;

    public FlaskPerkAddDuration(String translationName, int amount)
    {
        super(translationName);

        this.amount = amount;
    }

    @Override
    public void applyPerk(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        flaskEffectInstance.setDuration(flaskEffectInstance.getDurationFull() + amount);
    }
}
