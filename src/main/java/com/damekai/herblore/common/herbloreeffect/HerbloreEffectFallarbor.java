package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.capability.herbloreeffecthandler.HerbloreEffectHandler;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.event.entity.player.PlayerEvent;

import java.util.function.Supplier;

public class HerbloreEffectFallarbor extends HerbloreEffect
{
    private static final float BREAK_SPEED_MULTIPLIER = 3f;

    public HerbloreEffectFallarbor(Supplier<Effect> guiEffect)
    {
        super(guiEffect);
    }

    // Must be called on both client and server.
    public static void onBreakSpeed(PlayerEvent.BreakSpeed event)
    {
        if (BlockTags.LOGS.contains(event.getState().getBlock())) // Affect blocks tagged as Logs only.
        {
            PlayerEntity playerEntity = event.getPlayer();

            HerbloreEffectHandler herbloreEffectHandler = HerbloreEffectHandler.getHerbloreEffectHandlerOf(playerEntity);
            if (herbloreEffectHandler != null)
            {
                HerbloreEffectInstance fallarbor = herbloreEffectHandler.getHerbloreEffectInstance(ModHerbloreEffects.FALLARBOR.get());
                if (fallarbor != null)
                {
                    event.setNewSpeed(event.getOriginalSpeed() * BREAK_SPEED_MULTIPLIER);
                }
            }
        }
    }
}
