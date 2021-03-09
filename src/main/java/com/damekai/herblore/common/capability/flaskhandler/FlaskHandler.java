package com.damekai.herblore.common.capability.flaskhandler;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.toxicityhandler.ToxicityHandler;
import com.damekai.herblore.common.flask.base.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingEvent;

import javax.annotation.Nullable;
import java.util.*;

public class FlaskHandler implements IFlaskHandler
{
    /* A map of all currently active Flask Effect Instances. */
    private final List<FlaskEffectInstance> activeFlaskEffectInstances;

    public FlaskHandler()
    {
        activeFlaskEffectInstances = new ArrayList<>();
    }

    public void applyFlaskEffectInstance(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        FlaskEffect flaskEffect = flaskEffectInstance.getFlaskEffect();

        // If there is a Flask Effect Instance with this Flask Effect already, expire it.
        FlaskEffectInstance existingInstance = getFlaskEffectInstance(flaskEffect);
        if (existingInstance != null)
        {
            removeFlaskEffectInstance(existingInstance, livingEntity);
        }

        // Add Flask Effect Instance to list of active Instances.
        activeFlaskEffectInstances.add(flaskEffectInstance);

        // Always add toxicity first so that it shows up first in the GUI (most of the time, at least; can't control vanilla).
        ToxicityHandler toxicityHandler = ToxicityHandler.getToxicityHandlerOf(livingEntity);
        if (toxicityHandler != null)
        {
            toxicityHandler.addToxicity(livingEntity, flaskEffectInstance.getPotency());
        }

        // Call onApply if the Flask Effect is applicable.
        if (flaskEffect instanceof FlaskEffect.IApplicable)
        {
            ((FlaskEffect.IApplicable) flaskEffect).onApply(flaskEffectInstance, livingEntity);
        }

        // Add the GUI Effect, if there is one.
        Effect guiEffect = flaskEffect.getGuiEffect();
        if (guiEffect != null)
        {
            livingEntity.addPotionEffect(new EffectInstance(guiEffect, flaskEffectInstance.getDurationFull()));
        }
    }

    public FlaskEffectInstance getFlaskEffectInstance(FlaskEffect flaskEffect)
    {
        return activeFlaskEffectInstances.stream()
                .filter((activeFlaskEffectInstance) -> activeFlaskEffectInstance.getFlaskEffect() == flaskEffect)
                .findAny()
                .orElse(null);
    }

    private void tickFlaskEffectInstances(LivingEntity livingEntity)
    {
        Iterator<FlaskEffectInstance> iter = activeFlaskEffectInstances.iterator(); // Use iterator for in-place removal.
        while (iter.hasNext())
        {
            FlaskEffectInstance flaskEffectInstance = iter.next();
            FlaskEffect flaskEffect = flaskEffectInstance.getFlaskEffect();

            // Call onTick if the Flask Effect is tickable.
            if (flaskEffect instanceof FlaskEffect.ITickable)
            {
                ((FlaskEffect.ITickable) flaskEffect).onTick(flaskEffectInstance, livingEntity);
            }

            // Decrement the duration remaining on the Flask Instance, and handle the case of expiry for Expirable Flask Effects.
            if (flaskEffectInstance.decrementDuration())
            {
                // Handle expiry for Expirable Flask Effects.
                if (flaskEffect instanceof FlaskEffect.IExpirable)
                {
                    ((FlaskEffect.IExpirable) flaskEffect).onExpire(flaskEffectInstance, livingEntity);
                }

                // Remove from list of active flasks.
                iter.remove();

                // Remove effect from GUI, which may or may not be redundant, but is here just in case.
                Effect guiEffect = flaskEffect.getGuiEffect();
                if (guiEffect != null)
                {
                    livingEntity.removePotionEffect(guiEffect);
                }
            }
        }
    }

    public void removeFlaskEffectInstance(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity)
    {
        if (activeFlaskEffectInstances.remove(flaskEffectInstance))
        {
            FlaskEffect flaskEffect = flaskEffectInstance.getFlaskEffect();

            // Handle removal for Expirable Flask Effects.
            if (flaskEffect instanceof FlaskEffect.IExpirable)
            {
                ((FlaskEffect.IExpirable) flaskEffect).onExpire(flaskEffectInstance, livingEntity);
            }

            // Remove effect from GUI.
            Effect guiEffect = flaskEffect.getGuiEffect();
            if (guiEffect != null)
            {
                livingEntity.removePotionEffect(guiEffect);
            }
        }
    }

    public void removeAllFlaskEffectInstances(LivingEntity livingEntity)
    {
        activeFlaskEffectInstances.forEach(
                (flaskEffectInstance) ->
                {
                    FlaskEffect flaskEffect = flaskEffectInstance.getFlaskEffect();

                    // Handle removal for Expirable Flask Effects.
                    if (flaskEffect instanceof FlaskEffect.IExpirable)
                    {
                        ((FlaskEffect.IExpirable) flaskEffect).onExpire(flaskEffectInstance, livingEntity);
                    }

                    // Remove effect from GUI.
                    Effect guiEffect = flaskEffect.getGuiEffect();
                    if (guiEffect != null)
                    {
                        livingEntity.removePotionEffect(guiEffect);
                    }
                }
        );
        activeFlaskEffectInstances.clear();
    }


    public CompoundNBT serializeNBT()
    {
        ListNBT nbtList = new ListNBT();
        activeFlaskEffectInstances.forEach((flaskInstance) -> nbtList.add(flaskInstance.write(new CompoundNBT())));

        CompoundNBT nbt = new CompoundNBT();
        nbt.put("active_flask_effect_instances", nbtList);

        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        if (nbt.contains("active_flask_effect_instances"))
        {
            ListNBT nbtList = nbt.getList("active_flask_effect_instances", Constants.NBT.TAG_COMPOUND);
            nbtList.forEach((inbt) -> activeFlaskEffectInstances.add(FlaskEffectInstance.read((CompoundNBT) inbt)));
        }
    }

    @Nullable
    public static FlaskHandler getFlaskHandlerOf(LivingEntity livingEntity)
    {
        return livingEntity.getCapability(CapabilityFlaskHandler.FLASK_HANDLER_CAPABILITY).orElse(null);
    }

    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();
        FlaskHandler flaskHandler = getFlaskHandlerOf(livingEntity);
        if (flaskHandler != null)
        {
            flaskHandler.tickFlaskEffectInstances(livingEntity);
        }
    }
}
