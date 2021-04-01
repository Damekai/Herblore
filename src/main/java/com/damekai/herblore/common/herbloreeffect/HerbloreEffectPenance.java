package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public class HerbloreEffectPenance extends HerbloreEffect implements HerbloreEffect.IApplicable, HerbloreEffect.ITickable, HerbloreEffect.IExpirable
{
    private static final float HEALTH_RESTORE_AMOUNT = 10f;
    private static final int DAMAGE_OCCURENCES = 5;


    public HerbloreEffectPenance(HerbloreEffect.Properties properties)
    {
        super(properties);
    }

    @Override
    public void onApply(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        float prehealHealth = livingEntity.getHealth();
        livingEntity.heal(HEALTH_RESTORE_AMOUNT);
        float amountHealed = Math.max(0, livingEntity.getHealth() - prehealHealth); // Prevent case of "negative healing", just in case.

        float penalty = amountHealed / 2f;
        herbloreEffectInstance.getOrCreateTag().putFloat("penance_penalty_total", penalty);
        herbloreEffectInstance.getOrCreateTag().putFloat("penance_penalty_remaining", penalty);
    }

    @Override
    public void onTick(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        if (herbloreEffectInstance.getDurationRemaining() == herbloreEffectInstance.getDurationFull() || herbloreEffectInstance.getDurationRemaining() % (herbloreEffectInstance.getDurationFull() / DAMAGE_OCCURENCES) != 0) // Divide the damage over five damage instances.
        {
            return;
        }

        float penalty = herbloreEffectInstance.getOrCreateTag().getFloat("penance_penalty_total");
        float damage = penalty * (float) (herbloreEffectInstance.getDurationFull() / DAMAGE_OCCURENCES) / (float) herbloreEffectInstance.getDurationFull();
        float penaltyRemaining = herbloreEffectInstance.getOrCreateTag().getFloat("penance_penalty_remaining");
        herbloreEffectInstance.getOrCreateTag().putFloat("penance_penalty_remaining", penaltyRemaining - damage);

        livingEntity.attackEntityFrom(DamageSource.MAGIC, damage);
    }

    @Override
    public void onExpire(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        float damageRemaining = herbloreEffectInstance.getOrCreateTag().getFloat("penance_penalty_remaining");
        if (damageRemaining > 0f)
        {
            livingEntity.attackEntityFrom(DamageSource.MAGIC, damageRemaining);
        }
    }
}
