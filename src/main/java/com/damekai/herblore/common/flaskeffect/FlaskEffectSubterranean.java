package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flaskeffect.base.TickingFlaskEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FlaskEffectSubterranean extends TickingFlaskEffect
{
    private static final int SEA_LEVEL = 62;
    private static final int LEEWAY = 10; // "Leeway" given to grant a higher effect, since I'm forced to use a vanilla EffectInstance.

    protected FlaskEffectSubterranean()
    {
        super("subterranean", 20);
    }

    @Override
    protected void tick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        int layer = livingEntity.getPosition().getY() - LEEWAY;
        int amplifier = Math.round(potency * (1f - layer / (float) SEA_LEVEL) / 2f) - 1; // Maximum effect at layers 10 and below. Amplifier will be equal to the half the potency (rounded to nearest int) minus 1 at that point.
        if (amplifier >= 0)
        {
            Herblore.LOGGER.debug("Applying Subterranean with amplifier of " + amplifier);
            livingEntity.addPotionEffect(new EffectInstance(Effects.HASTE, 20, amplifier, false, false, false));
        }
    }
}
