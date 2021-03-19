package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.thread.EffectiveSide;

public class FlaskEffectDredge extends FlaskEffect
{
    private static final float SEA_LEVEL = 62;
    private static final float MAX_BREAK_SPEED_MULTIPLIER_BONUS_PER_POTENCY = 0.8f;

    protected FlaskEffectDredge(FlaskEffect.Properties properties)
    {
        super(properties);
    }

    // Must be called on both client and server.
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event)
    {
        PlayerEntity playerEntity = event.getPlayer();

        FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(playerEntity);
        if (flaskHandler != null)
        {
            FlaskEffectInstance dredge = flaskHandler.getFlaskEffectInstance(ModFlaskEffects.DREDGE.get());
            if (dredge != null)
            {
                float breakSpeedMultiplier = 1f + MAX_BREAK_SPEED_MULTIPLIER_BONUS_PER_POTENCY * dredge.getPotency() * (1f - (float) Math.min(playerEntity.getPosY(), SEA_LEVEL) / SEA_LEVEL);
                
                event.setNewSpeed(event.getOriginalSpeed() * breakSpeedMultiplier);
            }
        }
    }
}
