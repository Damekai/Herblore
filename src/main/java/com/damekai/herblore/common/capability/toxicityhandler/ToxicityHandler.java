package com.damekai.herblore.common.capability.toxicityhandler;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.ModEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.DamageSource;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.living.PotionEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

public class ToxicityHandler implements IToxicityHandler
{
    private static final int MAX_TOXICITY = 25;
    private static final int MAX_TOXICITY_TIER = 5;
    private static final int TOXICITY_DECAY_RATE = 100; // Number of ticks required for toxicity to decrease by 1.
    private static final int CRITICAL_THRESHOLD = 20;
    private static final float HUNGER_EXHAUSTION_PER_TOXICITY = 0.01f; // In testing, a value of 0.01f requires eating a steak every ~8 sec (accounting for saturation) to maintain a full hunger bar.

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
        toxicity = Math.min(toxicity + amount, MAX_TOXICITY);
        onToxicityChange(livingEntity);
    }

    @Override
    public void removeToxicity(LivingEntity livingEntity, int amount)
    {
        toxicity = Math.max(0, toxicity - amount);
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
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("toxicity", toxicity);
        nbt.putInt("toxicity_tier", toxicityTier);
        nbt.putInt("ticks_since_last_decay", ticksSinceLastDecay);
        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        toxicity = nbt.getInt("toxicity");
        toxicityTier = nbt.getInt("toxicity_tier");
        ticksSinceLastDecay = nbt.getInt("ticks_since_last_decay");
    }

    /**
     * @return True if the toxicity tier was changed.
     */
    private boolean updateToxicityTier()
    {
        int updatedToxicityTier = toxicity == 0 ? 0 : Math.min(toxicity / (CRITICAL_THRESHOLD / 4) + 1, MAX_TOXICITY_TIER);
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

    private void tickToxicity(LivingEntity livingEntity)
    {
        if (livingEntity instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) livingEntity;
            playerEntity.addExhaustion(toxicity * HUNGER_EXHAUSTION_PER_TOXICITY);
        }
        if (toxicity >= CRITICAL_THRESHOLD && livingEntity.getHealth() > 1f)
        {
            livingEntity.attackEntityFrom(DamageSource.MAGIC, 1f);
        }
        tickToxicityDecay(livingEntity);
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
            toxicityHandler.tickToxicity(livingEntity);
        }
    }

    public static void onPotionApplicable(PotionEvent.PotionApplicableEvent event)
    {
        // Cause significant toxicity damage to the player if they attempt to drink a vanilla potion while their toxicity levels are above 0.

        if (ModEffects.getEffectFromRegistry(event.getPotionEffect().getPotion().getRegistryName().toString()) == null) // Check whether or not the Effect is a GUI Effect from this mod.
        {
            LivingEntity livingEntity = event.getEntityLiving();

            ToxicityHandler toxicityHandler = getToxicityHandlerOf(livingEntity);
            if (toxicityHandler != null && toxicityHandler.getToxicity() > 0)
            {
                toxicityHandler.setToxicity(livingEntity, MAX_TOXICITY);
                event.setResult(Event.Result.DENY);
            }
        }
    }
}
