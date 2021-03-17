package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;

public class FlaskEffectQuench extends FlaskEffect
{
    private static final float BASE_EXHAUSTION_PENALTY_PER_DAMAGE = 3f;
    private static final float PENALTY_REDUCTION_PER_POTENCY = 0.5f;

    public FlaskEffectQuench(FlaskEffect.Properties properties)
    {
        super(properties);
    }

    public static void onLivingDamage(LivingDamageEvent event)
    {
        if (event.getSource().isFireDamage())
        {
            LivingEntity livingEntity = event.getEntityLiving();

            if (livingEntity instanceof PlayerEntity)
            {
                PlayerEntity playerEntity = (PlayerEntity) livingEntity;

                if (playerEntity.getFoodStats().getFoodLevel() > 0) // No need to continue if the player has no hunger remaining, since the effect would not do anything in this case.
                {
                    FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(playerEntity);
                    if (flaskHandler != null)
                    {
                        FlaskEffectInstance quench = flaskHandler.getFlaskEffectInstance(ModFlaskEffects.QUENCH.get());
                        if (quench != null)
                        {
                            playerEntity.addExhaustion(event.getAmount() * (BASE_EXHAUSTION_PENALTY_PER_DAMAGE - PENALTY_REDUCTION_PER_POTENCY * quench.getPotency())); // Higher potencies cause less exhaustion.
                            event.setCanceled(true); // Cancel damage occurence.
                        }
                    }
                }
            }
        }
    }
}
