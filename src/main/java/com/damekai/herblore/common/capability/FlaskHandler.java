package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.util.MutableInt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.event.entity.living.LivingEvent;

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
        for (FlaskInstance flaskInstance : flaskInstances)
        {
            activeFlaskInstances.put(flaskInstance, new MutableInt(flaskInstance.getDuration()));
            flaskInstance.getFlask().getFlaskEffects().forEach(
                    (flaskEffect) -> flaskEffect.onApply(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), flaskInstance.getDuration()));
            livingEntity.addPotionEffect(new EffectInstance(flaskInstance.getFlask().getGuiRenderEffect(), flaskInstance.getDuration()));
        }
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
                    (flaskEffect -> flaskEffect.onTick(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), durationRemaining.value)));
            durationRemaining.value--;

            if (durationRemaining.value == 0)
            {
                flaskInstance.getFlask().getFlaskEffects().forEach(
                        (flaskEffect -> flaskEffect.onExpire(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), durationRemaining.value)));
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
                        (flaskEffect -> flaskEffect.onRemove(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), durationRemaining.value)));
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
                    (flaskEffect -> flaskEffect.onRemove(livingEntity, flaskInstance.getPotency(), flaskInstance.getDuration(), durationRemaining.value)));
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
