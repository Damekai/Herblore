package com.damekai.herblore.common.capability.flaskhandler;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.capability.herbloreknowledge.HerbloreKnowledge;
import com.damekai.herblore.common.capability.toxicityhandler.ToxicityHandler;
import com.damekai.herblore.common.flask.base.*;
import com.damekai.herblore.common.flask.perk.base.FlaskPerk;
import com.damekai.herblore.common.network.HerblorePacketHandler;
import com.damekai.herblore.common.network.MessageFlaskHandler;
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

public class FlaskHandler implements IFlaskHandler
{
    /* A map of all currently active Flask Effect Instances. */
    private final List<FlaskEffectInstance> activeFlaskEffectInstances;

    public FlaskHandler()
    {
        activeFlaskEffectInstances = new ArrayList<>();
    }

    public void applyFlaskEffectInstance(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity, FlaskPerk... flaskPerks)
    {
        FlaskEffect flaskEffect = flaskEffectInstance.getFlaskEffect();

        if (flaskEffect == null) // In the case of an untagged flask.
        {
            return;
        }

        // If there is a Flask Effect Instance with this Flask Effect already, expire it.
        FlaskEffectInstance existingInstance = getFlaskEffectInstance(flaskEffect);
        if (existingInstance != null)
        {
            removeFlaskEffectInstance(existingInstance, livingEntity);
        }

        // Apply perks onto the Flask Effect Instance before applying it.
        for (FlaskPerk flaskPerk : flaskPerks)
        {
            flaskPerk.applyPerk(flaskEffectInstance, livingEntity);
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

        // Sync Flask Handler from server to client, since there was a change.
        if (livingEntity instanceof ServerPlayerEntity) // Only syncs if the Flask Handler belongs to a player.
        {
            sendSyncPacketToClient((ServerPlayerEntity) livingEntity);
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
        boolean changed = false;
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
                changed = true;
            }
        }
        if (changed)
        {
            // Sync Flask Handler from server to client, since there was a change.
            if (livingEntity instanceof ServerPlayerEntity) // Only syncs if the Flask Handler belongs to a player.
            {
                sendSyncPacketToClient((ServerPlayerEntity) livingEntity);
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

            // Sync Flask Handler from server to client, since there was a change.
            if (livingEntity instanceof ServerPlayerEntity) // Only syncs if the Flask Handler belongs to a player.
            {
                sendSyncPacketToClient((ServerPlayerEntity) livingEntity);
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

        // Sync Flask Handler from server to client, since there (maybe) was a change.
        if (livingEntity instanceof ServerPlayerEntity) // Only syncs if the Flask Handler belongs to a player.
        {
            sendSyncPacketToClient((ServerPlayerEntity) livingEntity);
        }
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
        activeFlaskEffectInstances.clear(); // Start fresh.

        if (nbt.contains("active_flask_effect_instances"))
        {
            ListNBT nbtList = nbt.getList("active_flask_effect_instances", Constants.NBT.TAG_COMPOUND);
            nbtList.forEach((inbt) -> activeFlaskEffectInstances.add(FlaskEffectInstance.read((CompoundNBT) inbt)));
        }
    }

    private void sendSyncPacketToClient(ServerPlayerEntity player)
    {
        HerblorePacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> player), new MessageFlaskHandler(serializeNBT()));
    }

    @OnlyIn(Dist.CLIENT)
    public static void syncFlaskHandlerFromServer(CompoundNBT nbt)
    {
        FlaskHandler flaskHandler = getFlaskHandlerOf(Minecraft.getInstance().player);
        if (flaskHandler != null)
        {
            flaskHandler.deserializeNBT(nbt);
        }
    }

    @Nullable
    public static FlaskHandler getFlaskHandlerOf(LivingEntity livingEntity)
    {
        if (livingEntity == null)
        {
            return null;
        }
        return livingEntity.getCapability(CapabilityFlaskHandler.FLASK_HANDLER_CAPABILITY).orElse(null);
    }

    public static void onLivingUpdate(LivingEvent.LivingUpdateEvent event)
    {
        LivingEntity livingEntity = event.getEntityLiving();

        // Only tick on server side.
        if (!livingEntity.getEntityWorld().isRemote)
        {
            FlaskHandler flaskHandler = getFlaskHandlerOf(livingEntity);
            if (flaskHandler != null)
            {
                flaskHandler.tickFlaskEffectInstances(livingEntity);
            }
        }
    }
}
