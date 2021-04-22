package com.damekai.herblore.common.capability.toxicityhandler;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.herbloreeffecthandler.HerbloreEffectHandler;
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
    private static final int MAX_TOXICITY = 12000;
    private static final int MAX_TOXICITY_TIER = 5;
    private static final float HUNGER_EXHAUSTION_PER_TOXICITY_TIER = 0.5f;

    private int toxicity;
    private int toxicityTier;
    private int ticksSinceLastUpdate;

    public ToxicityHandler()
    {
        toxicity = 0;
        toxicityTier = 0;
        ticksSinceLastUpdate = 0;
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
        onToxicityChange(livingEntity);
    }

    public CompoundNBT serializeNBT()
    {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("toxicity", toxicity);
        nbt.putInt("toxicity_tier", toxicityTier);
        nbt.putInt("ticks_since_last_update", ticksSinceLastUpdate);
        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        toxicity = nbt.getInt("toxicity");
        toxicityTier = nbt.getInt("toxicity_tier");
        ticksSinceLastUpdate = nbt.getInt("ticks_since_last_update");
    }

    /**
     * @return True if the toxicity tier was changed.
     */
    private boolean updateToxicityTier()
    {
        int updatedToxicityTier = Math.min(MAX_TOXICITY_TIER, /* int cast floors value. */ (int) (((float) toxicity / (float) MAX_TOXICITY) * (float) MAX_TOXICITY_TIER));
        if (toxicityTier != updatedToxicityTier)
        {
            toxicityTier = updatedToxicityTier;
            return true;
        }
        return false;
    }

    private void onToxicityChange(LivingEntity livingEntity)
    {
        //Herblore.LOGGER.debug("Updating toxicity to be " + toxicity);

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
        if (ticksSinceLastUpdate != 20)
        {
            ticksSinceLastUpdate++;
            return;
        }
        else
        {
            ticksSinceLastUpdate = 0;
        }

        if (livingEntity instanceof PlayerEntity)
        {
            PlayerEntity playerEntity = (PlayerEntity) livingEntity;
            HerbloreEffectHandler herbloreEffectHandler = HerbloreEffectHandler.getHerbloreEffectHandlerOf(playerEntity);
            if (herbloreEffectHandler != null)
            {
                setToxicity(playerEntity, herbloreEffectHandler.getTotaledDurations());
            }
            playerEntity.addExhaustion(toxicityTier * HUNGER_EXHAUSTION_PER_TOXICITY_TIER);
        }
        if (toxicityTier == MAX_TOXICITY_TIER && livingEntity.getHealth() > 1f)
        {
            livingEntity.attackEntityFrom(DamageSource.MAGIC, 2f);
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
}
