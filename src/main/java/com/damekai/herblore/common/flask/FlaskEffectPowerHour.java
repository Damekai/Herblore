package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.base.AttributeFlaskEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.Attributes;

import java.util.UUID;

public class FlaskEffectPowerHour extends AttributeFlaskEffect
{
    private final int primeTime; // Time at which this effect is most powerful.

    public FlaskEffectPowerHour(FlaskEffect.Properties properties, int primeTime, UUID uuid)
    {
        super(properties, uuid, 100,
                new AttributePotencyFactor(Attributes.ATTACK_DAMAGE, AttributeModifier.Operation.ADDITION, FlaskEffectPowerHour::calculateDamageModifierAmount));

        this.primeTime = primeTime;
    }

    private static float calculateDamageModifierAmount(AttributeFlaskEffect flaskAttributeEffect, FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (flaskAttributeEffect instanceof FlaskEffectPowerHour)
        {
            FlaskEffectPowerHour powerhour = (FlaskEffectPowerHour) flaskAttributeEffect;
            long currentTime = livingEntity.getEntityWorld().getDayTime();

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

            return flaskEffectInstance.getPotency() * (1 - (distanceFromPrimeTime / 12000f)) / 2f; // For example, if it's prime time and the potency is 5, the attack damage boost is 2.5.
        }
        return 0;
    }
}
