package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.AttributeFlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class FlaskEffectFleet extends AttributeFlaskEffect
{
    private static final float POTENCY_FACTOR = 0.1f;
    private static final float MOB_COUNT_FACTOR = 0.5f;
    private static final int MAX_MOB_COUNT_CONTRIBUTION = 5; // Maximum benefit is reached when this number of mobs are within the radius. More mobs than this value do not increase the benefit further.

    private static final int BASE_RADIUS = 15;
    private static final int RADIUS_INCREASE_PER_POTENCY = 2;

    protected FlaskEffectFleet(FlaskEffect.Properties properties, UUID uuid)
    {
        super(properties, uuid, 10,
                new AttributeFlaskEffect.AttributePotencyFactor(Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL, FlaskEffectFleet::calculateMovementSpeedModifierAmount));
    }

    private static float calculateMovementSpeedModifierAmount(AttributeFlaskEffect attributeFlaskEffect, FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        World world = livingEntity.getEntityWorld();

        List<MobEntity> monstersInRange = world.getEntitiesWithinAABB(MonsterEntity.class, new AxisAlignedBB(
                livingEntity.getPosX() - BASE_RADIUS + RADIUS_INCREASE_PER_POTENCY * flaskEffectInstance.getPotency(),
                livingEntity.getPosY() - 3,
                livingEntity.getPosZ() - BASE_RADIUS + RADIUS_INCREASE_PER_POTENCY * flaskEffectInstance.getPotency(),
                livingEntity.getPosX() + BASE_RADIUS + RADIUS_INCREASE_PER_POTENCY * flaskEffectInstance.getPotency(),
                livingEntity.getPosY() + 3,
                livingEntity.getPosZ() + BASE_RADIUS + RADIUS_INCREASE_PER_POTENCY * flaskEffectInstance.getPotency()));

        if (monstersInRange.size() > 0)
        {
            return flaskEffectInstance.getPotency() * POTENCY_FACTOR * Math.min(monstersInRange.size(), MAX_MOB_COUNT_CONTRIBUTION) * MOB_COUNT_FACTOR;
        }
        else
        {
            return 0;
        }
    }
}
