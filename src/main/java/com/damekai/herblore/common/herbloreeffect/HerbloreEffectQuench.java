package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

public class HerbloreEffectQuench extends HerbloreEffect
{
    private static final float BASE_EXHAUSTION_PENALTY_PER_DAMAGE = 3f;
    private static final float PENALTY_REDUCTION_PER_POTENCY = 0.5f;

    public HerbloreEffectQuench(HerbloreEffect.Properties properties)
    {
        super(properties);
    }

    public static void onLivingDamage(LivingDamageEvent event)
    {
        if (EffectiveSide.get() == LogicalSide.CLIENT)
        {
            return;
        }

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
                        HerbloreEffectInstance quench = flaskHandler.getFlaskEffectInstance(ModHerbloreEffects.QUENCH.get());
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
