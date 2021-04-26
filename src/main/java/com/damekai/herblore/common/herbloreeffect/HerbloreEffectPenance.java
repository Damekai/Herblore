package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.Effect;
import net.minecraft.util.DamageSource;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public class HerbloreEffectPenance extends HerbloreEffect implements HerbloreEffect.ITickable, HerbloreEffect.IExpirable
{
    private static final int HEALTH_REGEN_FREQUENCY = 20;
    private static final float HEALTH_REGEN_AMOUNT = 1f;
    private static final float HEALTH_PENALTY_PER_HEALTH_REGENERATED = 0.5f;

    public HerbloreEffectPenance(Supplier<Effect> guiEffect)
    {
        super(guiEffect);
    }

    @Override
    public void onTick(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        if (herbloreEffectInstance.getDurationRemaining() % HEALTH_REGEN_FREQUENCY != 0)
        {
            return;
        }

        float prehealHealth = livingEntity.getHealth();
        livingEntity.heal(HEALTH_REGEN_AMOUNT);
        float amountHealed = Math.max(0, livingEntity.getHealth() - prehealHealth); // Prevent case of "negative healing", just in case.

        CompoundNBT tag = herbloreEffectInstance.getOrCreateTag();
        tag.putFloat("penance_penalty", tag.getFloat("penance_penalty") + amountHealed * HEALTH_PENALTY_PER_HEALTH_REGENERATED);
    }

    @Override
    public void onExpire(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        float penalty = herbloreEffectInstance.getOrCreateTag().getFloat("penance_penalty");
        if (penalty > 0f)
        {
            livingEntity.hurt(DamageSource.MAGIC, penalty);
        }
    }

    @Nullable
    @Override
    public boolean combineInstances(HerbloreEffectInstance left, HerbloreEffectInstance right)
    {
        if (!super.combineInstances(left, right))
        {
            return false;
        }

        CompoundNBT leftTag = left.getOrCreateTag();
        CompoundNBT rightTag = right.getOrCreateTag();

        left.getOrCreateTag().putFloat("penance_penalty", leftTag.getFloat("penance_penalty") + rightTag.getFloat("penance_penalty"));

        return true;
    }
}
