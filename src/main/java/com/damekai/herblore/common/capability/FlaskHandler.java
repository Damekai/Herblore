package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.effect.FlaskEffect;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.util.MutableInt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingEvent;

import javax.annotation.Nullable;
import java.util.*;

public class FlaskHandler implements IFlaskHandler
{
    /* A map of all currently active Flask Instances (key) and their elapsed durations (value). */
    private final Map<FlaskInstance, MutableInt> activeFlaskInstances;

    public FlaskHandler()
    {
        activeFlaskInstances = new HashMap<>();
    }

    public void applyFlasks(LivingEntity livingEntity, FlaskInstance... flaskInstances)
    {
        int toxicityAdded = 0;
        for (FlaskInstance flaskInstance : flaskInstances)
        {
            activeFlaskInstances.put(flaskInstance, new MutableInt(flaskInstance.getDuration()));
            flaskInstance.getFlask().getFlaskEffects().forEach(
                    (flaskEffect) -> flaskEffect.onApply(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), flaskInstance.getDuration()));

            toxicityAdded += flaskInstance.getPotency();

            livingEntity.addPotionEffect(new EffectInstance(flaskInstance.getFlask().getGuiRenderEffect(), flaskInstance.getDuration()));
        }

        ToxicityHandler toxicityHandler = ToxicityHandler.getToxicityHandlerOf(livingEntity);
        if (toxicityHandler != null)
        {
            toxicityHandler.addToxicity(livingEntity, toxicityAdded);
        }
    }

    public FlaskInstance getFlask(Flask flask)
    {
         return activeFlaskInstances.keySet().stream().filter((activeFlaskInstance) -> flask == activeFlaskInstance.getFlask()).findAny().orElse(null);
    }

    /**
     *  Attempts to find a FlaskInstance of a Flask that has the desired FlaskEffect. If there are multiple
     *  FlaskInstances with Flasks that have that FlaskEffect, then the FlaskInstance with the highest potency
     *  is chosen.
     *
     * @param flaskEffect FlaskEffect to search for.
     * @return The FlaskInstance with the highest potency that has a Flask with the desired FlaskEffect.
     */
    public FlaskInstance getFlaskWithEffect(FlaskEffect flaskEffect)
    {
        return activeFlaskInstances.keySet().stream()
                .filter((activeFlaskInstance) -> activeFlaskInstance.getFlask().getFlaskEffects().contains(flaskEffect))
                .max(Comparator.comparingInt(FlaskInstance::getPotency))
                .orElse(null);
    }

    public void tickFlasks(LivingEntity livingEntity)
    {
        Iterator<Map.Entry<FlaskInstance, MutableInt>> iter = activeFlaskInstances.entrySet().iterator();

        while (iter.hasNext())
        {
            Map.Entry<FlaskInstance, MutableInt> activeFlaskInstance = iter.next();
            FlaskInstance flaskInstance = activeFlaskInstance.getKey();
            MutableInt durationRemaining = activeFlaskInstance.getValue();

            flaskInstance.getFlask().getFlaskEffects().forEach(
                    (flaskEffect -> flaskEffect.onTick(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), durationRemaining.get())));
            durationRemaining.decrement();

            if (durationRemaining.get() == 0)
            {
                flaskInstance.getFlask().getFlaskEffects().forEach(
                        (flaskEffect -> flaskEffect.onExpire(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), durationRemaining.get())));
                iter.remove();
                livingEntity.removePotionEffect(flaskInstance.getFlask().getGuiRenderEffect()); // May be redundant, but here just in case.
            }
        }
    }

    // Removes one or more specific effect instances.
    public void removeFlasks(LivingEntity livingEntity, FlaskInstance... flaskInstances)
    {
        for (FlaskInstance flaskInstance : flaskInstances)
        {
            MutableInt durationRemaining = this.activeFlaskInstances.remove(flaskInstance);
            if (durationRemaining != null) // If the duration remaining is not null, there was a successful removal from the map.
            {
                flaskInstance.getFlask().getFlaskEffects().forEach(
                        (flaskEffect -> flaskEffect.onRemove(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), durationRemaining.get())));
                livingEntity.removePotionEffect(flaskInstance.getFlask().getGuiRenderEffect());
            }
        }
    }

    // Removes all effect instances.
    public void removeAllFlasks(LivingEntity livingEntity)
    {
        for (Map.Entry<FlaskInstance, MutableInt> activeFlaskInstance : activeFlaskInstances.entrySet())
        {
            FlaskInstance flaskInstance = activeFlaskInstance.getKey();
            MutableInt durationRemaining = activeFlaskInstance.getValue();

            flaskInstance.getFlask().getFlaskEffects().forEach(
                    (flaskEffect -> flaskEffect.onRemove(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), durationRemaining.get())));
            livingEntity.removePotionEffect(flaskInstance.getFlask().getGuiRenderEffect());
        }
        activeFlaskInstances.clear();
    }


    public CompoundNBT serializeNBT()
    {
        return new CompoundNBT(); // TODO
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        // TODO
    }

    @Nullable
    public static FlaskHandler getFlaskHandlerOf(LivingEntity livingEntity)
    {
        return livingEntity.getCapability(CapabilityFlaskHandler.FLASK_HANDLER_CAPABILITY).orElse(null);
    }

    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();
        FlaskHandler flaskHandler = livingEntity.getCapability(CapabilityFlaskHandler.FLASK_HANDLER_CAPABILITY).orElse(null);
        if (flaskHandler != null)
        {
            flaskHandler.tickFlasks(livingEntity);
        }
    }
}
