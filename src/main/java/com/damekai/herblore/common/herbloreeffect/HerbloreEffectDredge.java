package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.capability.herbloreeffecthandler.HerbloreEffectHandler;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.function.Supplier;

public class HerbloreEffectDredge extends HerbloreEffect
{
    private static final float SEA_LEVEL = 62;
    private static final float MAX_BREAK_SPEED_MULTIPLIER_BONUS = 2f; // The resulting maximum multiplier will be this value plus one.

    protected HerbloreEffectDredge(Supplier<Effect> guiEffect)
    {
        super(guiEffect);
    }

    // Must be called on both client and server.
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event)
    {
        PlayerEntity playerEntity = event.getPlayer();

        HerbloreEffectHandler herbloreEffectHandler = HerbloreEffectHandler.getHerbloreEffectHandlerOf(playerEntity);
        if (herbloreEffectHandler != null)
        {
            HerbloreEffectInstance dredge = herbloreEffectHandler.getHerbloreEffectInstance(ModHerbloreEffects.DREDGE.get());
            if (dredge != null)
            {
                float breakSpeedMultiplier = 1f + MAX_BREAK_SPEED_MULTIPLIER_BONUS * (1f - (float) Math.min(playerEntity.getY(), SEA_LEVEL) / SEA_LEVEL);
                
                event.setNewSpeed(event.getOriginalSpeed() * breakSpeedMultiplier);
            }
        }
    }
}
