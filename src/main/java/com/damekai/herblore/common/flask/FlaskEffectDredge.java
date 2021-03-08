package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class FlaskEffectDredge extends FlaskEffect implements FlaskEffect.ITickable
{
    private static final int SEA_LEVEL = 62;
    private static final int LEEWAY = 10; // "Leeway" given to grant a higher effect, since I'm forced to use a vanilla EffectInstance.

    private final int tickRate;

    protected FlaskEffectDredge(FlaskEffect.Properties properties, int tickRate)
    {
        super(properties);
        this.tickRate = tickRate;
    }

    @Override
    public void onTick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (flaskEffectInstance.getDurationRemaining() % tickRate != 0)
        {
            return;
        }

        int layer = livingEntity.getPosition().getY() - LEEWAY;
        int amplifier = Math.round(flaskEffectInstance.getPotency() * (1f - layer / (float) SEA_LEVEL) / 2f) - 1; // Maximum effect at layers 10 and below. Amplifier will be equal to the half the potency (rounded to nearest int) minus 1 at that point.
        if (amplifier >= 0)
        {
            Herblore.LOGGER.debug("Applying Subterranean with amplifier of " + amplifier);
            livingEntity.addPotionEffect(new EffectInstance(Effects.HASTE, 20, amplifier, false, false, false));
        }
    }
}
