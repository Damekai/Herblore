package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.AttributeHerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.util.Direction;

import java.util.UUID;

public class HerbloreEffectDominion extends AttributeHerbloreEffect
{
    private static final float ATTACK_SPEED_PER_POTENCY = 1f;

    private final ImmutableList<Block> dominionBlocks;

    public HerbloreEffectDominion(Properties properties, UUID uuid, ImmutableList<Block> dominionBlocks)
    {
        super(properties, uuid, 10,
                new AttributePotencyFactor(() -> Attributes.ATTACK_SPEED, AttributeModifier.Operation.ADDITION, HerbloreEffectDominion::calculateAttackSpeedModifierAmount));

        this.dominionBlocks = dominionBlocks;
    }


    private static float calculateAttackSpeedModifierAmount(AttributeHerbloreEffect attributeFlaskEffect, HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        if (attributeFlaskEffect instanceof HerbloreEffectDominion)
        {
            HerbloreEffectDominion dominion = (HerbloreEffectDominion) attributeFlaskEffect;

            if (dominion.dominionBlocks.contains(livingEntity.getEntityWorld().getBlockState(livingEntity.getPosition().offset(Direction.DOWN, 1)).getBlock()))
            {
                return herbloreEffectInstance.getPotency() * ATTACK_SPEED_PER_POTENCY;
            }
        }
        return 0;
    }
}
