package com.damekai.herblore.common.flask.perk;

import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.perk.base.FlaskPerk;
import net.minecraft.entity.LivingEntity;

public class FlaskPerkHeal extends FlaskPerk
{
    private final float healthPerPotency;

    public FlaskPerkHeal(String translationName, float healthPerPotency)
    {
        super(translationName);

        this.healthPerPotency = healthPerPotency;
    }

    @Override
    public void applyPerk(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        livingEntity.heal(flaskEffectInstance.getPotency() * healthPerPotency);
    }
}
