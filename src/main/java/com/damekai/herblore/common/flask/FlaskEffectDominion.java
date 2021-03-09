package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.AttributeFlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.Direction;

import java.util.UUID;

public class FlaskEffectDominion extends AttributeFlaskEffect
{
    private static final float ATTACK_SPEED_PER_POTENCY = 0.5f;

    private final ImmutableList<Block> dominionBlocks;

    public FlaskEffectDominion(Properties properties, UUID uuid, ImmutableList<Block> dominionBlocks)
    {
        super(properties, uuid, 10,
                new AttributePotencyFactor(Attributes.ATTACK_SPEED, AttributeModifier.Operation.ADDITION, FlaskEffectDominion::calculateAttackSpeedModifierAmount));

        this.dominionBlocks = dominionBlocks;
    }


    private static float calculateAttackSpeedModifierAmount(AttributeFlaskEffect attributeFlaskEffect, FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (attributeFlaskEffect instanceof FlaskEffectDominion)
        {
            FlaskEffectDominion dominion = (FlaskEffectDominion) attributeFlaskEffect;

            if (dominion.dominionBlocks.contains(livingEntity.getEntityWorld().getBlockState(livingEntity.getPosition().offset(Direction.DOWN, 1)).getBlock()))
            {
                return flaskEffectInstance.getPotency() * ATTACK_SPEED_PER_POTENCY;
            }
        }
        return 0;
    }
}
