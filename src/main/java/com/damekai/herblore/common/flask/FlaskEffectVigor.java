package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.AttributeFlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;

public class FlaskEffectVigor extends FlaskEffect implements FlaskEffect.IApplicable, FlaskEffect.ITickable, FlaskEffect.IExpirable
{
    private static final float ABSORPTION_PER_POTENCY = 4;
    private static final int FREQUENCY = 200; // Every 10 seconds.

    public FlaskEffectVigor(Properties properties)
    {
        super(properties);
    }

    @Override
    public void onApply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        setAbsorption(flaskEffectInstance.getPotency(), livingEntity);
    }

    @Override
    public void onTick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (flaskEffectInstance.getDurationRemaining() % FREQUENCY == 0)
        {
            setAbsorption(flaskEffectInstance.getPotency(), livingEntity);
        }
    }

    @Override
    public void onExpire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        setAbsorption(flaskEffectInstance.getPotency(), livingEntity);
    }

    private void setAbsorption(int potency, LivingEntity livingEntity)
    {
        float currentAbsorption = livingEntity.getAbsorptionAmount();
        float flaskAbsorption = potency * ABSORPTION_PER_POTENCY;

        // Only add enough absorption to bring the LivingEntity up to what this flask can provide.
        if (currentAbsorption < flaskAbsorption)
        {
            livingEntity.setAbsorptionAmount(flaskAbsorption);
        }
    }
}
