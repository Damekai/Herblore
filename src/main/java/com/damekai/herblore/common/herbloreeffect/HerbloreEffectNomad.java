package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.AttributeHerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class HerbloreEffectNomad extends AttributeHerbloreEffect
{
    private static final float MOVEMENT_SPEED_PER_POTENCY = 0.25f;
    private static final int BASE_RADIUS = 20;
    private static final int RADIUS_REDUCTION_PER_POTENCY = 2;

    protected HerbloreEffectNomad(Properties properties, UUID uuid)
    {
        super(properties, uuid, 10,
                new AttributePotencyFactor(() -> Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL, HerbloreEffectNomad::calculateMovementSpeedModifierAmount));
    }

    private static float calculateMovementSpeedModifierAmount(AttributeHerbloreEffect attributeFlaskEffect, HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        World world = livingEntity.getEntityWorld();

        List<MobEntity> monstersInRange = world.getEntitiesWithinAABB(MonsterEntity.class, new AxisAlignedBB(
                livingEntity.getPosX() - BASE_RADIUS - RADIUS_REDUCTION_PER_POTENCY * herbloreEffectInstance.getPotency(),
                livingEntity.getPosY() - 3,
                livingEntity.getPosZ() - BASE_RADIUS - RADIUS_REDUCTION_PER_POTENCY * herbloreEffectInstance.getPotency(),
                livingEntity.getPosX() + BASE_RADIUS - RADIUS_REDUCTION_PER_POTENCY * herbloreEffectInstance.getPotency(),
                livingEntity.getPosY() + 3,
                livingEntity.getPosZ() + BASE_RADIUS - RADIUS_REDUCTION_PER_POTENCY * herbloreEffectInstance.getPotency()));

        if (monstersInRange.size() == 0)
        {
            return herbloreEffectInstance.getPotency() * MOVEMENT_SPEED_PER_POTENCY;
        }
        else
        {
            return 0;
        }
    }
}
