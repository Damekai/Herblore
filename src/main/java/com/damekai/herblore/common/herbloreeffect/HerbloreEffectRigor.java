package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;

public class HerbloreEffectRigor extends HerbloreEffect implements HerbloreEffect.IApplicable, HerbloreEffect.ITickable, HerbloreEffect.IExpirable
{
    private static final float ABSORPTION_AMOUNT = 6f;
    private static final float HEALTH_COST_PER_ABSORPTION = 0.25f;
    private static final int FREQUENCY = 200; // Every 10 seconds.

    public HerbloreEffectRigor(Properties properties)
    {
        super(properties);
    }

    @Override
    public void onApply(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        setAbsorption(livingEntity);
    }

    @Override
    public void onTick(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        if (herbloreEffectInstance.getDurationRemaining() % FREQUENCY == 0)
        {
            setAbsorption(livingEntity);
        }
    }

    @Override
    public void onExpire(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        setAbsorption(livingEntity);
    }

    private void setAbsorption(LivingEntity livingEntity)
    {
        float absorptionAddition = ABSORPTION_AMOUNT - livingEntity.getAbsorptionAmount();
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