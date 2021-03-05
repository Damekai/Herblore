package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.flaskeffect.base.FlaskEffect;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.player.AttackEntityEvent;

public class FlaskEffectFireEater extends FlaskEffect
{
    private static final ImmutableList<String> PREVENTABLE_DAMAGE_TYPES = ImmutableList.of(
            DamageSource.IN_FIRE.damageType,
            DamageSource.ON_FIRE.damageType,
            DamageSource.LAVA.damageType
    );

    public FlaskEffectFireEater()
    {
        super("fire_eater");
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
                        FlaskInstance fireEater = flaskHandler.getFlaskWithEffect(ModFlaskEffects.FIRE_EATER.get());
                        if (fireEater != null)
                        {
                            playerEntity.addExhaustion(event.getAmount() * 5f / fireEater.getPotency()); // Higher potencies cause less exhaustion.
                            event.setCanceled(true); // Cancel damage occurence.
                        }
                    }
                }
            }
        }
    }
}
