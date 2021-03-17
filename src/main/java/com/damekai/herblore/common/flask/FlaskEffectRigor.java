package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.AttributeFlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;

public class FlaskEffectRigor extends FlaskEffect implements FlaskEffect.IApplicable, FlaskEffect.ITickable, FlaskEffect.IExpirable
{
    private static final float ABSORPTION_PER_POTENCY = 4f;
    private static final float HEALTH_COST_PER_ABSORPTION = 0.25f;
    private static final int FREQUENCY = 200; // Every 10 seconds.

    public FlaskEffectRigor(Properties properties)
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
        float absorptionAddition = (potency * ABSORPTION_PER_POTENCY) - livingEntity.getAbsorptionAmount();
        float healthCost = absorptionAddition * HEALTH_COST_PER_ABSORPTION;

        // Scale down absorption addition and health cost if the health cost cannot be paid. Leaves the player with at least 1 health (half a heart).
        if (livingEntity.getHealth() - healthCost < 1f)
        {
            healthCost = livingEntity.getHealth() - 1f; // Set the cost to be all but the last point of health.
            absorptionAddition = healthCost / HEALTH_COST_PER_ABSORPTION;
        }

        // Only add enough absorption to bring the LivingEntity up to what this flask can provide.
        if (absorptionAddition > 0f && livingEntity.getHealth() > healthCost)
        {
            livingEntity.setHealth(livingEntity.getHealth() - healthCost);
            livingEntity.setAbsorptionAmount(livingEntity.getAbsorptionAmount() + absorptionAddition);
        }
    }
}
