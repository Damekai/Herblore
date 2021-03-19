package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class FlaskEffectFallarbor extends FlaskEffect
{
    private static final float BREAK_SPEED_MULTIPLIER_BONUS_PER_POTENCY = 0.7f;

    public FlaskEffectFallarbor(Properties properties)
    {
        super(properties);
    }

    // Must be called on both client and server.
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event)
    {
        if (BlockTags.LOGS.contains(event.getState().getBlock())) // Affect blocks tagged as Logs only.
        {
            PlayerEntity playerEntity = event.getPlayer();

            FlaskHandler flaskHandler = FlaskHandler.getFlaskHandlerOf(playerEntity);
            if (flaskHandler != null)
            {
                FlaskEffectInstance fallarbor = flaskHandler.getFlaskEffectInstance(ModFlaskEffects.FALLARBOR.get());
                if (fallarbor != null)
                {
                    float breakSpeedMultiplier = 1f + BREAK_SPEED_MULTIPLIER_BONUS_PER_POTENCY * fallarbor.getPotency();

                    event.setNewSpeed(event.getOriginalSpeed() * breakSpeedMultiplier);
                }
            }
        }
    }
}
