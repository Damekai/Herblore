package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingEvent;

import javax.annotation.Nullable;

public class ToxicityHandler implements IToxicityHandler
{
    private static final int TOXICITY_DECAY_RATE = 60; // Number of ticks required for toxicity to decrease by 1.

    private int toxicity;
    private int ticksSinceLastDecay;

    public ToxicityHandler()
    {
        toxicity = 0;
        ticksSinceLastDecay = 0;
    }

    @Override
    public void addToxicity(LivingEntity livingEntity, int amount)
    {
        toxicity += amount;
        updateToxicityEffect(livingEntity);
    }

    @Override
    public void removeToxicity(LivingEntity livingEntity, int amount)
    {
        toxicity -= amount;
        updateToxicityEffect(livingEntity);
    }

    @Override
    public void setToxicity(LivingEntity livingEntity, int amount)
    {
        toxicity = Math.max(amount, 0);
        updateToxicityEffect(livingEntity);
    }

    @Override
    public int getToxicity()
    {
        return toxicity;
    }

    @Override
    public void clearToxicity(LivingEntity livingEntity)
    {
        toxicity = 0;
        ticksSinceLastDecay = 0;
        updateToxicityEffect(livingEntity);
    }

    public void tickToxicityDecay(LivingEntity livingEntity)
    {
        if (toxicity > 0)
        {
            if (ticksSinceLastDecay == TOXICITY_DECAY_RATE)
            {
                toxicity--;
                ticksSinceLastDecay = 0;
                updateToxicityEffect(livingEntity);
            }
            else
            {
                ticksSinceLastDecay++;
            }
        }
    }

    public CompoundNBT serializeNBT()
    {
        return new CompoundNBT(); // TODO
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        // TODO
    }

    private void updateToxicityEffect(LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Updating toxicity to be " + toxicity);

        livingEntity.removePotionEffect(ModEffects.TOXICITY_RENDER.get());
        int toxicityTier = getToxicityTier();
        if (toxicityTier > 0)
        {
            livingEntity.addPotionEffect(new EffectInstance(ModEffects.TOXICITY_RENDER.get(), Integer.MAX_VALUE, getToxicityTier(), false, false));
        }
    }

    private int getToxicityTier()
    {
        return toxicity / 10;
    }

    @Nullable
    public static ToxicityHandler getToxicityHandlerOf(LivingEntity livingEntity)
    {
        return livingEntity.getCapability(CapabilityToxicityHandler.TOXICITY_HANDLER_CAPABILITY).orElse(null);
    }

    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();
        ToxicityHandler toxicityHandler = getToxicityHandlerOf(livingEntity);
        if (toxicityHandler != null)
        {
            toxicityHandler.tickToxicityDecay(livingEntity);
        }
    }
}
