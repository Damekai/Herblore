package com.damekai.herblore.common.capability.herbloreknowledge;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.ModFlaskEffects;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.network.HerblorePacketHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class HerbloreKnowledge implements IHerbloreKnowledge
{
    private final List<ItemReagent> reagentKnowledge;

    public HerbloreKnowledge()
    {
        reagentKnowledge = new ArrayList<>();
    }

    @Override
    public boolean isReagentKnown(ItemReagent reagent)
    {
        return reagentKnowledge.contains(reagent);
    }

    @Override
    public void setReagentKnown(PlayerEntity playerEntity, ItemReagent reagent)
    {
        if (!reagentKnowledge.contains(reagent))
        {
            reagentKnowledge.add(reagent);
            if (playerEntity instanceof ServerPlayerEntity)
            {
                HerblorePacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)playerEntity), serializeNBT());
            }
        }
    }

    public CompoundNBT serializeNBT()
    {
        CompoundNBT nbt = new CompoundNBT();

        ListNBT reagentKnowledgeNbt = new ListNBT();
        reagentKnowledge.forEach((reagent) ->
        {
            CompoundNBT nbtReagent = new CompoundNBT();
            nbtReagent.putString("reagent_registry_name", reagent.getRegistryName().toString());
            reagentKnowledgeNbt.add(nbtReagent);
        });

        nbt.put("reagent_knowledge", reagentKnowledgeNbt);

        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        reagentKnowledge.clear(); // Start fresh for deserialization.
        reagentKnowledge.addAll(
                nbt.getList("reagent_knowledge", Constants.NBT.TAG_COMPOUND)
                        .stream()
                        .map((inbt) -> (ItemReagent) ModItems.getItemFromRegistry(((CompoundNBT) inbt).getString("reagent_registry_name")))
                        .collect(Collectors.toList()));
    }

    @OnlyIn(Dist.CLIENT)
    public static void syncKnowledgeFromServer(CompoundNBT nbt)
    {
        HerbloreKnowledge knowledge = getHerbloreKnowledgeOf(Minecraft.getInstance().player);
        if (knowledge != null)
        {
            knowledge.deserializeNBT(nbt);
        }
    }

    @Nullable
    public static HerbloreKnowledge getHerbloreKnowledgeOf(PlayerEntity playerEntity)
    {
        if (playerEntity == null)
        {
            return null;
        }
        return playerEntity.getCapability(CapabilityHerbloreKnowledge.HERBLORE_KNOWLEDGE_CAPABILITY).orElse(null);
    }

    public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event)
    {
        PlayerEntity playerEntity = event.getPlayer();
        if (playerEntity instanceof ServerPlayerEntity)
        {
            HerbloreKnowledge herbloreKnowledge = getHerbloreKnowledgeOf(playerEntity);
            if (herbloreKnowledge != null)
            {
                HerblorePacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerEntity), herbloreKnowledge.serializeNBT());
            }

        }
    }

    public static void onPlayerClone(PlayerEvent.Clone event)
    {
        PlayerEntity clone = event.getPlayer();
        if (clone instanceof ServerPlayerEntity)
        {
            if (event.isWasDeath())
            {
                HerbloreKnowledge oldHerbloreKnowledge = getHerbloreKnowledgeOf(event.getOriginal());
                if (oldHerbloreKnowledge != null)
                {
                    HerbloreKnowledge newHerbloreKnowledge = getHerbloreKnowledgeOf(clone);
                    if (newHerbloreKnowledge != null)
                    {
                        newHerbloreKnowledge.deserializeNBT(oldHerbloreKnowledge.serializeNBT()); // Carry over from old to new.
                    }
                }
            }
        }
    }

    public static void onPlayerRespawn(PlayerEvent.PlayerRespawnEvent event)
    {
        PlayerEntity playerEntity = event.getPlayer();
        if (playerEntity instanceof ServerPlayerEntity)
        {
            HerbloreKnowledge herbloreKnowledge = getHerbloreKnowledgeOf(playerEntity);
            if (herbloreKnowledge != null)
            {
                HerblorePacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) playerEntity), herbloreKnowledge.serializeNBT());
            }
        }
    }
}
