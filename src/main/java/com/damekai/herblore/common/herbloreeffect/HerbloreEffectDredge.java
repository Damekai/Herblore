package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.player.PlayerEvent;

public class HerbloreEffectDredge extends HerbloreEffect
{
    private static final float SEA_LEVEL = 62;
    private static final float MAX_BREAK_SPEED_MULTIPLIER_BONUS_PER_POTENCY = 0.8f;

    protected HerbloreEffectDredge(HerbloreEffect.Properties properties)
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
            HerbloreEffectInstance dredge = flaskHandler.getFlaskEffectInstance(ModHerbloreEffects.DREDGE.get());
            if (dredge != null)
            {
                float breakSpeedMultiplier = 1f + MAX_BREAK_SPEED_MULTIPLIER_BONUS_PER_POTENCY * dredge.getPotency() * (1f - (float) Math.min(playerEntity.getPosY(), SEA_LEVEL) / SEA_LEVEL);
                
                event.setNewSpeed(event.getOriginalSpeed() * breakSpeedMultiplier);
            }
        }
    }
}
