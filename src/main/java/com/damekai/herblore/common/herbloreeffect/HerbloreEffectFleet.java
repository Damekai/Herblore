package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.AttributeHerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;
import java.util.function.Supplier;

public class HerbloreEffectFleet extends AttributeHerbloreEffect
{
    private static final float MOB_COUNT_FACTOR = 0.5f;
    private static final int MAX_MOB_COUNT_CONTRIBUTION = 5; // Maximum benefit is reached when this number of mobs are within the radius. More mobs than this value do not increase the benefit further.

    private static final int DETECTION_RADIUS_HORIZONTAL = 20;
    private static final int DETECTION_RADIUS_VERTICAL = 3;

    protected HerbloreEffectFleet(Supplier<Effect> guiEffect, UUID uuid)
    {
        super(guiEffect, uuid, 10,
                new AttributeHerbloreEffect.AttributePotencyFactor(() -> Attributes.MOVEMENT_SPEED, AttributeModifier.Operation.MULTIPLY_TOTAL, HerbloreEffectFleet::calculateMovementSpeedModifierAmount));
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

        if (monstersInRange.size() > 0)
        {
            return Math.min(monstersInRange.size(), MAX_MOB_COUNT_CONTRIBUTION) * MOB_COUNT_FACTOR;
        }
        else
        {
            return 0;
        }
    }
}
