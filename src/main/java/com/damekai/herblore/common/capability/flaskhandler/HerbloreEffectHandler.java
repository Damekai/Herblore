package com.damekai.herblore.common.capability.flaskhandler;

import com.damekai.herblore.common.capability.toxicityhandler.ToxicityHandler;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.network.HerblorePacketHandler;
import com.damekai.herblore.common.network.MessageHerbloreEffectHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.*;

public class HerbloreEffectHandler implements IHerbloreEffectHandler
{
    /* A map of all currently active Flask Effect Instances. */
    private final List<HerbloreEffectInstance> activeHerbloreEffectInstances;

    public HerbloreEffectHandler()
    {
        activeHerbloreEffectInstances = new ArrayList<>();
    }

    public void applyHerbloreEffectInstance(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        HerbloreEffect herbloreEffect = herbloreEffectInstance.getHerbloreEffect();

        if (herbloreEffect == null) // In the case of an untagged flask.
        {
            return;
        }

        // If there is a Herblore Effect Instance with this Flask Effect already, expire it.
        HerbloreEffectInstance existingInstance = getHerbloreEffectInstance(herbloreEffect);
        if (existingInstance != null)
        {
            removeHerbloreEffectInstance(existingInstance, livingEntity);
        }

        // Add Herblore Effect Instance to list of active Instances.
        activeHerbloreEffectInstances.add(herbloreEffectInstance);

        // Always add toxicity first so that it shows up first in the GUI (most of the time, at least; can't control vanilla).
        ToxicityHandler toxicityHandler = ToxicityHandler.getToxicityHandlerOf(livingEntity);
        if (toxicityHandler != null)
        {
            toxicityHandler.addToxicity(livingEntity, 1); // TODO: Implement Toxicity values per Herblore Effect.
        }

        // Call onApply if the Herblore Effect is applicable.
        if (herbloreEffect instanceof HerbloreEffect.IApplicable)
        {
            ((HerbloreEffect.IApplicable) herbloreEffect).onApply(herbloreEffectInstance, livingEntity);
        }

        // Add the GUI Effect, if there is one.
        Effect guiEffect = herbloreEffect.getGuiEffect();
        if (guiEffect != null)
        {
            livingEntity.addPotionEffect(new EffectInstance(guiEffect, herbloreEffectInstance.getDurationFull()));
        }

        // Sync Herblore Effect Handler from server to client, since there was a change.
        if (livingEntity instanceof ServerPlayerEntity) // Only syncs if the Herblore Effect Handler belongs to a player.
        {
            sendSyncPacketToClient((ServerPlayerEntity) livingEntity);
        }
    }

    public HerbloreEffectInstance getHerbloreEffectInstance(HerbloreEffect herbloreEffect)
    {
        return activeHerbloreEffectInstances.stream()
                .filter((activeHerbloreEffectInstance) -> activeHerbloreEffectInstance.getHerbloreEffect() == herbloreEffect)
                .findAny()
                .orElse(null);
    }

    private void tickHerbloreEffectInstances(LivingEntity livingEntity)
    {
        boolean changed = false;
        Iterator<HerbloreEffectInstance> iter = activeHerbloreEffectInstances.iterator(); // Use iterator for in-place removal.
        while (iter.hasNext())
        {
            HerbloreEffectInstance herbloreEffectInstance = iter.next();
            HerbloreEffect herbloreEffect = herbloreEffectInstance.getHerbloreEffect();

            // Call onTick if the Herblore Effect is tickable.
            if (herbloreEffect instanceof HerbloreEffect.ITickable)
            {
                ((HerbloreEffect.ITickable) herbloreEffect).onTick(herbloreEffectInstance, livingEntity);
            }

            // Decrement the duration remaining on the Herblore Effect Instance, and handle the case of expiry for Expirable Flask Effects.
            if (herbloreEffectInstance.decrementDuration())
            {
                // Handle expiry for Expirable Herblore Effects.
                if (herbloreEffect instanceof HerbloreEffect.IExpirable)
                {
                    ((HerbloreEffect.IExpirable) herbloreEffect).onExpire(herbloreEffectInstance, livingEntity);
                }

                // Remove from list of active Herblore Effects.
                iter.remove();

                // Remove effect from GUI, which may or may not be redundant, but is here just in case.
                Effect guiEffect = herbloreEffect.getGuiEffect();
                if (guiEffect != null)
                {
                    livingEntity.removePotionEffect(guiEffect);
                }
                changed = true;
            }
        }
        if (changed)
        {
            // Sync Herblore Effect Handler from server to client, since there was a change.
            if (livingEntity instanceof ServerPlayerEntity) // Only syncs if the Herblore Effect Handler belongs to a player.
            {
                sendSyncPacketToClient((ServerPlayerEntity) livingEntity);
            }
        }
    }

    public void removeHerbloreEffectInstance(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity)
    {
        if (activeHerbloreEffectInstances.remove(herbloreEffectInstance))
        {
            HerbloreEffect herbloreEffect = herbloreEffectInstance.getHerbloreEffect();

            // Handle removal for Expirable Herblore Effects.
            if (herbloreEffect instanceof HerbloreEffect.IExpirable)
            {
                ((HerbloreEffect.IExpirable) herbloreEffect).onExpire(herbloreEffectInstance, livingEntity);
            }

            // Remove effect from GUI.
            Effect guiEffect = herbloreEffect.getGuiEffect();
            if (guiEffect != null)
            {
                livingEntity.removePotionEffect(guiEffect);
            }

            // Sync Herblore Effect Handler from server to client, since there was a change.
            if (livingEntity instanceof ServerPlayerEntity) // Only syncs if the Herblore EFfect Handler belongs to a player.
            {
                sendSyncPacketToClient((ServerPlayerEntity) livingEntity);
            }
        }
    }

    public void removeAllHerbloreEffectInstances(LivingEntity livingEntity)
    {
        activeHerbloreEffectInstances.forEach(
                (herbloreEffectInstance) ->
                {
                    HerbloreEffect herbloreEffect = herbloreEffectInstance.getHerbloreEffect();

                    // Handle removal for Expirable Flask Effects.
                    if (herbloreEffect instanceof HerbloreEffect.IExpirable)
                    {
                        ((HerbloreEffect.IExpirable) herbloreEffect).onExpire(herbloreEffectInstance, livingEntity);
                    }

                    // Remove effect from GUI.
                    Effect guiEffect = herbloreEffect.getGuiEffect();
                    if (guiEffect != null)
                    {
                        livingEntity.removePotionEffect(guiEffect);
                    }
                }
        );
        activeHerbloreEffectInstances.clear();

        // Sync Flask Handler from server to client, since there (maybe) was a change.
        if (livingEntity instanceof ServerPlayerEntity) // Only syncs if the Flask Handler belongs to a player.
        {
            sendSyncPacketToClient((ServerPlayerEntity) livingEntity);
        }
    }


    public CompoundNBT serializeNBT()
    {
        ListNBT nbtList = new ListNBT();
        activeHerbloreEffectInstances.forEach((herbloreEffectInstance) -> nbtList.add(herbloreEffectInstance.write(new CompoundNBT())));

        CompoundNBT nbt = new CompoundNBT();
        nbt.put("active_herblore_effect_instances", nbtList);

        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        activeHerbloreEffectInstances.clear(); // Start fresh. Also avoids conflict when syncing from server to client.

        if (nbt.contains("active_herblore_effect_instances"))
        {
            ListNBT nbtList = nbt.getList("active_herblore_effect_instances", Constants.NBT.TAG_COMPOUND);
            nbtList.forEach((inbt) -> activeHerbloreEffectInstances.add(HerbloreEffectInstance.read((CompoundNBT) inbt)));
        }
    }

    private void sendSyncPacketToClient(ServerPlayerEntity player)
    {
        HerblorePacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MessageHerbloreEffectHandler(serializeNBT()));
    }

    @OnlyIn(Dist.CLIENT)
    public static void syncHerbloreEffectHandlerFromServer(CompoundNBT nbt)
    {
        HerbloreEffectHandler herbloreEffectHandler = getHerbloreEffectHandlerOf(Minecraft.getInstance().player);
        if (herbloreEffectHandler != null)
        {
            herbloreEffectHandler.deserializeNBT(nbt);
        }
    }

    @Nullable
    public static HerbloreEffectHandler getHerbloreEffectHandlerOf(LivingEntity livingEntity)
    {
        if (livingEntity == null)
        {
            return null;
        }
        return livingEntity.getCapability(CapabilityHerbloreEffectHandler.HERBLORE_EFFECT_HANDLER_CAPABILITY).orElse(null);
    }

    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();

        // Only tick on server side.
        if (!livingEntity.getEntityWorld().isRemote)
        {
            HerbloreEffectHandler flaskHandler = getHerbloreEffectHandlerOf(livingEntity);
            if (flaskHandler != null)
            {
                flaskHandler.tickHerbloreEffectInstances(livingEntity);
            }
        }
    }
}
