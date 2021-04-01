package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.AttributeHerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.potion.Effect;
import net.minecraftforge.common.ForgeMod;

import java.util.UUID;
import java.util.function.Supplier;

public class HerbloreEffectAmbit extends AttributeHerbloreEffect
{
    private static final int REACH = 3;

    protected HerbloreEffectAmbit(Supplier<Effect> guiEffect, UUID uuid)
    {
        super(guiEffect, uuid, Integer.MAX_VALUE /* Indicates that this Herblore Effect doesn't need to recalculate/reapply over its duration. TODO: I shouldn't have to hack it like this. */,
                new AttributePotencyFactor(ForgeMod.REACH_DISTANCE, AttributeModifier.Operation.ADDITION, HerbloreEffectAmbit::calculateReachModifierAmount));
    }

    private static float calculateReachModifierAmount(AttributeHerbloreEffect attributeFlaskEffect, HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        return REACH;
    }
}
