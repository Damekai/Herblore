package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.AttributeFlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class FlaskEffectNomad extends AttributeFlaskEffect
{
    private static final float MOVEMENT_SPEED_PER_POTENCY = 0.125f;
    private static final int BASE_RADIUS = 20;
    private static final int RADIUS_REDUCTION_PER_POTENCY = 1;

    protected FlaskEffectNomad(Properties properties, UUID uuid)
    {
        super(properties, uuid, 10,
                new AttributePotencyFactor(Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL, FlaskEffectNomad::calculateMovementSpeedModifierAmount));
    }

    private static float calculateMovementSpeedModifierAmount(AttributeFlaskEffect attributeFlaskEffect, FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        World world = livingEntity.getEntityWorld();

        List<MobEntity> monstersInRange = world.getEntitiesWithinAABB(MonsterEntity.class, new AxisAlignedBB(
                livingEntity.getPosX() - BASE_RADIUS - RADIUS_REDUCTION_PER_POTENCY * flaskEffectInstance.getPotency(),
                livingEntity.getPosY() - 3,
                livingEntity.getPosZ() - BASE_RADIUS - RADIUS_REDUCTION_PER_POTENCY * flaskEffectInstance.getPotency(),
                livingEntity.getPosX() + BASE_RADIUS - RADIUS_REDUCTION_PER_POTENCY * flaskEffectInstance.getPotency(),
                livingEntity.getPosY() + 3,
                livingEntity.getPosZ() + BASE_RADIUS - RADIUS_REDUCTION_PER_POTENCY * flaskEffectInstance.getPotency()));

        if (monstersInRange.size() == 0)
        {
            return flaskEffectInstance.getPotency() * MOVEMENT_SPEED_PER_POTENCY;
        }
        else
        {
            return 0;
        }
    }
}
