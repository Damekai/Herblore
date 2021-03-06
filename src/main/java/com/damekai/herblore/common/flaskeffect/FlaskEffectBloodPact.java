package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.flaskeffect.base.TickingFlaskEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;

public class FlaskEffectBloodPact extends TickingFlaskEffect
{
    private static final float HEALTH_RESTORED_PER_POTENCY = 2f;

    public FlaskEffectBloodPact()
    {
        super("blood_pact", 20);
    }

    @Override
    protected void apply(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        float prehealHealth = livingEntity.getHealth();
        livingEntity.heal(flaskInstance.getPotency() * HEALTH_RESTORED_PER_POTENCY);
        float amountHealed = Math.max(0, livingEntity.getHealth() - prehealHealth); // Prevent case of "negative healing", just in case.

        flaskInstance.getOrCreateTag().putFloat("blood_pact_penalty", amountHealed / 2f);
    }

    @Override
    protected void expire(FlaskInstance flaskInstance, LivingEntity livingEntity) { }

    @Override
    protected void remove(FlaskInstance flaskInstance, LivingEntity livingEntity) { }

    @Override
    protected void tick(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        float penalty = flaskInstance.getOrCreateTag().getFloat("blood_pact_penalty");
        livingEntity.attackEntityFrom(DamageSource.MAGIC, penalty * tickRate / (float) flaskInstance.getDurationFull());
    }
}
