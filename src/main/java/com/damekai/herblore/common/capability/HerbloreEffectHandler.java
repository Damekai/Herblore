package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.HerbloreEffect;
import com.damekai.herblore.common.effect.HerbloreEffectInstance;
import com.damekai.herblore.common.util.MutableInt;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.event.entity.living.LivingEvent;

import java.util.*;

public class HerbloreEffectHandler implements IHerbloreEffectHandler
{
    /* A map of all currently active Herblore Effect Instances (key) and their elapsed durations (value). */
    private final Map<HerbloreEffectInstance, MutableInt> activeHerbloreEffectInstances;

    public HerbloreEffectHandler()
    {
        activeHerbloreEffectInstances = new HashMap<>();
    }

    public void applyHerbloreEffects(LivingEntity livingEntity, HerbloreEffectInstance... herbloreEffectInstances)
    {
        for (HerbloreEffectInstance herbloreEffectInstance : herbloreEffectInstances)
        {
            activeHerbloreEffectInstances.put(herbloreEffectInstance, new MutableInt(herbloreEffectInstance.getDuration()));
            herbloreEffectInstance.getHerbloreEffect().onApply(livingEntity, herbloreEffectInstance.getPotency(), herbloreEffectInstance.getDuration(), herbloreEffectInstance.getDuration());
        }
    }

    public void tickHerbloreEffects(LivingEntity livingEntity)
    {
        Iterator<Map.Entry<HerbloreEffectInstance, MutableInt>> iter = activeHerbloreEffectInstances.entrySet().iterator();

        while (iter.hasNext())
        {
            Map.Entry<HerbloreEffectInstance, MutableInt> activeHerbloreEffectInstance = iter.next();
            HerbloreEffectInstance herbloreEffectInstance = activeHerbloreEffectInstance.getKey();
            MutableInt durationRemaining = activeHerbloreEffectInstance.getValue();

            herbloreEffectInstance.getHerbloreEffect().onTick(livingEntity, herbloreEffectInstance.getPotency(), herbloreEffectInstance.getDuration(), durationRemaining.value);
            durationRemaining.value--;

            if (durationRemaining.value == 0)
            {
                herbloreEffectInstance.getHerbloreEffect().onExpire(livingEntity, herbloreEffectInstance.getPotency(), herbloreEffectInstance.getDuration(), durationRemaining.value);
                iter.remove();
            }
        }
    }

    // Removes one or more specific effect instances.
    public void removeHerbloreEffects(LivingEntity livingEntity, HerbloreEffectInstance... herbloreEffectInstances)
    {
        for (HerbloreEffectInstance herbloreEffectInstance : herbloreEffectInstances)
        {
            MutableInt durationRemaining = this.activeHerbloreEffectInstances.remove(herbloreEffectInstance);
            if (durationRemaining != null) // If the duration remaining is not null, there was a successful removal from the map.
            {
                herbloreEffectInstance.getHerbloreEffect().onRemove(livingEntity, herbloreEffectInstance.getPotency(), herbloreEffectInstance.getDuration(), durationRemaining.value);
            }
        }
    }

    // Removes all instances of the given effect types.
    public void removeHerbloreEffectTypes(LivingEntity livingEntity, HerbloreEffect... herbloreEffects)
    {
        List<HerbloreEffect> herbloreEffectList = Arrays.asList(herbloreEffects);

        Iterator<Map.Entry<HerbloreEffectInstance, MutableInt>> iter = activeHerbloreEffectInstances.entrySet().iterator();
        while (iter.hasNext())
        {
            Map.Entry<HerbloreEffectInstance, MutableInt> activeHerbloreEffectInstance = iter.next();
            HerbloreEffectInstance herbloreEffectInstance = activeHerbloreEffectInstance.getKey();
            MutableInt durationRemaining = activeHerbloreEffectInstance.getValue();

            if (herbloreEffectList.contains(herbloreEffectInstance.getHerbloreEffect()))
            {
                herbloreEffectInstance.getHerbloreEffect().onRemove(livingEntity, herbloreEffectInstance.getPotency(), herbloreEffectInstance.getDuration(), durationRemaining.value);
                iter.remove();
            }
        }
    }

    // Removes all effect instances.
    public void removeAllHerbloreEffects(LivingEntity livingEntity)
    {
        for (Map.Entry<HerbloreEffectInstance, MutableInt> activeHerbloreEffectInstance : activeHerbloreEffectInstances.entrySet())
        {
            HerbloreEffectInstance herbloreEffectInstance = activeHerbloreEffectInstance.getKey();
            MutableInt durationRemaining = activeHerbloreEffectInstance.getValue();

            herbloreEffectInstance.getHerbloreEffect().onRemove(livingEntity, herbloreEffectInstance.getPotency(), herbloreEffectInstance.getDuration(), durationRemaining.value);
        }
        activeHerbloreEffectInstances.clear();
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
        HerbloreEffectHandler herbloreEffectHandler = livingEntity.getCapability(CapabilityHerbloreEffectHandler.HERBLORE_EFFECT_HANDLER_CAPABILITY).orElse(null);
        if (herbloreEffectHandler != null)
        {
            herbloreEffectHandler.tickHerbloreEffects(livingEntity);
        }
    }
}
