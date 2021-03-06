package com.damekai.herblore.common.capability.flaskhandler;

import com.damekai.herblore.common.capability.toxicityhandler.ToxicityHandler;
import com.damekai.herblore.common.flaskeffect.base.FlaskEffect;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.util.MutableInt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingEvent;

import javax.annotation.Nullable;
import java.util.*;

public class FlaskHandler implements IFlaskHandler
{
    /* A map of all currently active Flask Instances. */
    private final List<FlaskInstance> activeFlaskInstances;

    public FlaskHandler()
    {
        activeFlaskInstances = new ArrayList<>();
    }

    public void applyFlask(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        activeFlaskInstances.add(flaskInstance);

        // Always add toxicity first so that it shows up first in the GUI (most of the time, at least; can't control vanilla).
        ToxicityHandler toxicityHandler = ToxicityHandler.getToxicityHandlerOf(livingEntity);
        if (toxicityHandler != null)
        {
            toxicityHandler.addToxicity(livingEntity, flaskInstance.getPotency());
        }

        // Call onApply on each Applicable Flask.
        flaskInstance.getFlask().getApplicableFlaskEffects().forEach((applicableFlaskEffect) -> applicableFlaskEffect.onApply(flaskInstance, livingEntity));

        // Add the GUI Effect, if there is one.
        if (flaskInstance.getFlask().getGuiRenderEffect() != null)
        {
            livingEntity.addPotionEffect(new EffectInstance(flaskInstance.getFlask().getGuiRenderEffect(), flaskInstance.getDurationFull()));
        }
    }

    public FlaskInstance getFlask(Flask flask)
    {
         return activeFlaskInstances.stream().filter((activeFlaskInstance) -> flask == activeFlaskInstance.getFlask()).findAny().orElse(null);
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
        return activeFlaskInstances.stream()
                .filter((activeFlaskInstance) -> activeFlaskInstance.getFlask().getFlaskEffects().contains(flaskEffect))
                .max(Comparator.comparingInt(FlaskInstance::getPotency))
                .orElse(null);
    }

    private void tickFlasks(LivingEntity livingEntity)
    {
        Iterator<FlaskInstance> iter = activeFlaskInstances.iterator(); // Use iterator for in-place removal.
        while (iter.hasNext())
        {
            FlaskInstance flaskInstance = iter.next();

            // Tick the Tickable Flask Effects.
            flaskInstance.getFlask().getTickingFlaskEffects().forEach((tickingFlaskEffect) -> tickingFlaskEffect.onTick(flaskInstance, livingEntity));

            // Decrement the duration remaining on the FlaskInstance, and handle the case of Flask expiry.
            if (flaskInstance.decrementDuration())
            {
                // Handle expiry for Duration Flask Effects (also includes Tickable Flask Effects).
                flaskInstance.getFlask().getDurationFlaskEffects().forEach((durationFlaskEffect) -> durationFlaskEffect.onExpire(flaskInstance, livingEntity));

                // Remove from list of active flasks.
                iter.remove();

                // Remove effect from GUI, which may or may not be redundant, but is here just in case.
                livingEntity.removePotionEffect(flaskInstance.getFlask().getGuiRenderEffect()); //
            }
        }
    }

    public void removeFlask(FlaskInstance flaskInstance, LivingEntity livingEntity)
    {
        if (activeFlaskInstances.remove(flaskInstance))
        {
            // Handle removal for Duration Flask Effects (also includes Tickable Flask Effects).
            flaskInstance.getFlask().getDurationFlaskEffects().forEach((durationFlaskEffect) -> durationFlaskEffect.onRemove(flaskInstance, livingEntity));

            // Remove effect from GUI.
            livingEntity.removePotionEffect(flaskInstance.getFlask().getGuiRenderEffect());
        }
    }

    public void removeAllFlasks(LivingEntity livingEntity)
    {
        activeFlaskInstances.forEach(
                (flaskInstance) ->
                {
                    // Handle removal for Duration Flask Effects (also includes Tickable Flask Effects).
                    flaskInstance.getFlask().getDurationFlaskEffects().forEach((durationFlaskEffect) -> durationFlaskEffect.onRemove(flaskInstance, livingEntity));

                    // Remove effect from GUI.
                    livingEntity.removePotionEffect(flaskInstance.getFlask().getGuiRenderEffect());
                }
        );
        activeFlaskInstances.clear();
    }


    public CompoundNBT serializeNBT()
    {
        ListNBT nbtList = new ListNBT();
        activeFlaskInstances.forEach((flaskInstance) -> nbtList.add(flaskInstance.write(new CompoundNBT())));

        CompoundNBT nbt = new CompoundNBT();
        nbt.put("active_flask_instances", nbtList);

        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        if (nbt.contains("active_flask_instances"))
        {
            ListNBT nbtList = nbt.getList("active_flask_instances", Constants.NBT.TAG_COMPOUND);
            nbtList.forEach((inbt) -> activeFlaskInstances.add(FlaskInstance.read((CompoundNBT) inbt)));
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
            flaskHandler.tickFlasks(livingEntity);
        }
    }
}
