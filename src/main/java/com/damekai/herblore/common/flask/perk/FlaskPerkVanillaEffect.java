package com.damekai.herblore.common.flask.perk;

import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.perk.base.FlaskPerk;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;

public class FlaskPerkVanillaEffect extends FlaskPerk
{
    private final Effect effect;
    private final int duration;
    private final int amplifier;

    public FlaskPerkVanillaEffect(String translationName, Effect effect, int duration, int amplifier)
    {
        super(translationName);

        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    @Override
    public void applyPerk(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        livingEntity.addPotionEffect(new EffectInstance(effect, duration, amplifier, false, false, true));
    }
}
