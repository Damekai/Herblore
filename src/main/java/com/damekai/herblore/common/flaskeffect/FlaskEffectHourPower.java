package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.flaskeffect.base.AttributeFlaskEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;

import java.util.UUID;

public class FlaskEffectHourPower extends AttributeFlaskEffect
{
    private final int primeTime; // Time at which this effect is most powerful.

    public FlaskEffectHourPower(int primeTime, UUID uuid)
    {
        super("hour_power",
                uuid,
                100,
                new AttributePotencyFactor(Attributes.ATTACK_DAMAGE, AttributeModifier.Operation.ADDITION, FlaskEffectHourPower::calculateDamageModifierAmount));

        this.primeTime = primeTime;
    }

    private static float calculateDamageModifierAmount(AttributeFlaskEffect flaskAttributeEffect, LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {
        if (flaskAttributeEffect instanceof FlaskEffectHourPower)
        {
            FlaskEffectHourPower hourpower = (FlaskEffectHourPower) flaskAttributeEffect;
            long currentTime = livingEntity.getEntityWorld().getDayTime();

            long distanceFromPrimeTime; // Circular nature of time, linear nature of Minecraft time. Both need to be accounted for.

            if ((currentTime >= 12000 && hourpower.primeTime >= 12000) || (currentTime <= 12000 && hourpower.primeTime <= 12000))
            {
                // Shortest time calculation DOES NOT require the pass over time 0=24000.
                distanceFromPrimeTime = Math.abs(currentTime - hourpower.primeTime);
            }
            else
            {
                // Shortest time calculation DOES require the pass over time 0=24000.
                distanceFromPrimeTime = Math.min(24000 - Math.abs(currentTime - hourpower.primeTime), Math.abs(currentTime - hourpower.primeTime));
            }

            return potency * (1 - (distanceFromPrimeTime / 12000f)) / 2f; // For example, if it's prime time and the potency is 5, the attack damage boost is 2.5.
        }
        return 0;
    }
}
