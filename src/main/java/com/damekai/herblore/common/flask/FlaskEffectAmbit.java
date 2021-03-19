package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.AttributeFlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;

public class FlaskEffectAmbit extends AttributeFlaskEffect
{
    private static final int REACH_PER_POTENCY = 1;

    protected FlaskEffectAmbit(Properties properties, UUID uuid)
    {
        super(properties, uuid, Integer.MAX_VALUE /* Indicates that this Flask Effect doesn't need to recalculate/reapply over its duration. TODO: I shouldn't have to hack it like this. */,
                new AttributePotencyFactor(ForgeMod.REACH_DISTANCE, AttributeModifier.Operation.ADDITION, FlaskEffectAmbit::calculateReachModifierAmount));
    }

    private static float calculateReachModifierAmount(AttributeFlaskEffect attributeFlaskEffect, FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        return REACH_PER_POTENCY * flaskEffectInstance.getPotency();
    }
}
