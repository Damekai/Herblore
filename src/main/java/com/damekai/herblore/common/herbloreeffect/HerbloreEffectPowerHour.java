package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.AttributeHerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.potion.Effect;

import java.util.UUID;
import java.util.function.Supplier;

public class HerbloreEffectPowerHour extends AttributeHerbloreEffect
{
    private static final float MAX_ATTACK_DAMAGE_BOOST = 4f;

    private final int primeTime; // Time at which this effect is most powerful.

    public HerbloreEffectPowerHour(Supplier<Effect> guiEffect, int primeTime, UUID uuid)
    {
        super(guiEffect, uuid, 100,
                new AttributePotencyFactor(() -> Attributes.ATTACK_DAMAGE, AttributeModifier.Operation.ADDITION, HerbloreEffectPowerHour::calculateDamageModifierAmount));

        this.primeTime = primeTime;
    }

    private static float calculateDamageModifierAmount(AttributeHerbloreEffect attributeFlaskEffect, HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        if (attributeFlaskEffect instanceof HerbloreEffectPowerHour)
        {
            HerbloreEffectPowerHour powerhour = (HerbloreEffectPowerHour) attributeFlaskEffect;
            long currentTime = livingEntity.level.getDayTime();

            long distanceFromPrimeTime; // Circular nature of time, linear nature of Minecraft time. Both need to be accounted for.

            if ((currentTime >= 12000 && powerhour.primeTime >= 12000) || (currentTime <= 12000 && powerhour.primeTime <= 12000))
            {
                // Shortest time calculation DOES NOT require the pass over time 0=24000.
                distanceFromPrimeTime = Math.abs(currentTime - powerhour.primeTime);
            }
            else
            {
                // Shortest time calculation DOES require the pass over time 0=24000.
                distanceFromPrimeTime = Math.min(24000 - Math.abs(currentTime - powerhour.primeTime), Math.abs(currentTime - powerhour.primeTime));
            }

            return MAX_ATTACK_DAMAGE_BOOST * (1 - (distanceFromPrimeTime / 12000f));
        }
        return 0;
    }
}
