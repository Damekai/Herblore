package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.capability.flaskhandler.HerbloreEffectHandler;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

public class HerbloreEffectQuench extends HerbloreEffect
{
    private static final float EXHAUSTION_PENALTY_PER_DAMAGE = 1f;

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

                if (playerEntity.getFoodStats().getFoodLevel() > 0) // No need to continue if the player has no hunger remaining, since the effect should not do anything in this case.
                {
                    HerbloreEffectHandler herbloreEffectHandler = HerbloreEffectHandler.getHerbloreEffectHandlerOf(playerEntity);
                    if (herbloreEffectHandler != null)
                    {
                        HerbloreEffectInstance quench = herbloreEffectHandler.getHerbloreEffectInstance(ModHerbloreEffects.QUENCH.get());
                        if (quench != null)
                        {
                            playerEntity.addExhaustion(event.getAmount() * (EXHAUSTION_PENALTY_PER_DAMAGE));
                            event.setCanceled(true); // Cancel damage occurence.
                        }
                    }
                }
            }
        }
    }
}
