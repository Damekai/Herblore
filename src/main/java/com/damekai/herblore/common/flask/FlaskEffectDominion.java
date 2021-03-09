package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.AttributeFlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.Direction;

import java.util.UUID;

public class FlaskEffectDominion extends AttributeFlaskEffect
{
    private static final float ATTACK_SPEED_PER_POTENCY = 0.5f;

    private final Block dominionBlock;

    public FlaskEffectDominion(Properties properties, UUID uuid, Block dominionBlock)
    {
        super(properties, uuid, 10,
                new AttributePotencyFactor(Attributes.ATTACK_SPEED, AttributeModifier.Operation.ADDITION, FlaskEffectDominion::calculateAttackSpeedModifierAmount));

        this.dominionBlock = dominionBlock;
    }


    private static float calculateAttackSpeedModifierAmount(AttributeFlaskEffect flaskAttributeEffect, FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (flaskAttributeEffect instanceof FlaskEffectDominion)
        {
            FlaskEffectDominion dominion = (FlaskEffectDominion) flaskAttributeEffect;

            if (livingEntity.getEntityWorld().getBlockState(livingEntity.getPosition().offset(Direction.DOWN, 1)).getBlock() == dominion.dominionBlock)
            {
                return flaskEffectInstance.getPotency() * ATTACK_SPEED_PER_POTENCY;
            }
        }
        return 0;
    }
}
