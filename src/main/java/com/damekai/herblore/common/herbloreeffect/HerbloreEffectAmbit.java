package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.AttributeHerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class HerbloreEffectAmbit extends AttributeHerbloreEffect
{
    private static final int REACH_PER_POTENCY = 1;

    protected HerbloreEffectAmbit(Properties properties, UUID uuid)
    {
        super(properties, uuid, Integer.MAX_VALUE /* Indicates that this Herblore Effect doesn't need to recalculate/reapply over its duration. TODO: I shouldn't have to hack it like this. */,
                new AttributePotencyFactor(ForgeMod.REACH_DISTANCE, AttributeModifier.Operation.ADDITION, HerbloreEffectAmbit::calculateReachModifierAmount));
    }

    private static float calculateReachModifierAmount(AttributeHerbloreEffect attributeFlaskEffect, HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        return REACH_PER_POTENCY * herbloreEffectInstance.getPotency();
    }
}
