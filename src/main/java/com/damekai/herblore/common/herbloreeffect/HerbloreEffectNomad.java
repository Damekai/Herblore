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
    private static final float MOVEMENT_SPEED = 1;
    private static final int DETECTION_RADIUS_HORIZONTAL = 16;
    private static final int DETECTION_RADIUS_VERTICAL = 3;

    protected HerbloreEffectNomad(Properties properties, UUID uuid)
    {
        super(properties, uuid, 10,
                new AttributePotencyFactor(() -> Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL, HerbloreEffectNomad::calculateMovementSpeedModifierAmount));
    }

    private static float calculateMovementSpeedModifierAmount(AttributeHerbloreEffect attributeFlaskEffect, HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        World world = livingEntity.getEntityWorld();

        List<MobEntity> monstersInRange = world.getEntitiesWithinAABB(MonsterEntity.class, new AxisAlignedBB(
                livingEntity.getPosX() - DETECTION_RADIUS_HORIZONTAL,
                livingEntity.getPosY() - DETECTION_RADIUS_VERTICAL,
                livingEntity.getPosZ() - DETECTION_RADIUS_HORIZONTAL,
                livingEntity.getPosX() + DETECTION_RADIUS_HORIZONTAL,
                livingEntity.getPosY() + DETECTION_RADIUS_VERTICAL,
                livingEntity.getPosZ() + DETECTION_RADIUS_HORIZONTAL));

        if (monstersInRange.size() == 0)
        {
            return MOVEMENT_SPEED;
        }
        else
        {
            return 0;
        }
    }
}
