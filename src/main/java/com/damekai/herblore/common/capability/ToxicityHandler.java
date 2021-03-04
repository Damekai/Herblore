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
    private int toxicityTier;
    private int ticksSinceLastDecay;

    public ToxicityHandler()
    {
        toxicity = 0;
        toxicityTier = 0;
        ticksSinceLastDecay = 0;
    }

    @Override
    public void addToxicity(LivingEntity livingEntity, int amount)
    {
        toxicity += amount;
        onToxicityChange(livingEntity);
    }

    @Override
    public void removeToxicity(LivingEntity livingEntity, int amount)
    {
        toxicity -= amount;
        onToxicityChange(livingEntity);
    }

    @Override
    public void setToxicity(LivingEntity livingEntity, int amount)
    {
        toxicity = Math.max(amount, 0);
        onToxicityChange(livingEntity);
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
        onToxicityChange(livingEntity);
    }

    public CompoundNBT serializeNBT()
    {
        return new CompoundNBT(); // TODO
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        // TODO
    }

    /**
     * @return True if the toxicity tier was changed.
     */
    private boolean updateToxicityTier()
    {
        int updatedToxicityTier = toxicity == 0 ? 0 : toxicity / 10 + 1;
        if (toxicityTier != updatedToxicityTier)
        {
            toxicityTier = updatedToxicityTier;
            return true;
        }
        return false;
    }

    private void onToxicityChange(LivingEntity livingEntity)
    {
        Herblore.LOGGER.debug("Updating toxicity to be " + toxicity);

        if (updateToxicityTier())
        {
            if (toxicityTier == 0)
            {
                livingEntity.removePotionEffect(ModEffects.TOXICITY_RENDER.get());
            }
            else
            {
                livingEntity.removePotionEffect(ModEffects.TOXICITY_RENDER.get());
                livingEntity.addPotionEffect(new EffectInstance(ModEffects.TOXICITY_RENDER.get(), Integer.MAX_VALUE, toxicityTier - 1, false, false));
            }
        }
    }

    private void tickToxicityDecay(LivingEntity livingEntity)
    {
        if (toxicity > 0)
        {
            if (ticksSinceLastDecay == TOXICITY_DECAY_RATE)
            {
                removeToxicity(livingEntity, 1);
                ticksSinceLastDecay = 0;
            }
            else
            {
                ticksSinceLastDecay++;
            }
        }
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
