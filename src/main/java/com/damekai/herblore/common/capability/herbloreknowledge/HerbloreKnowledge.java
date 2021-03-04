package com.damekai.herblore.common.capability.herbloreknowledge;

import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.ModFlasks;
import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.network.HerblorePacketHandler;
import com.google.common.collect.ImmutableList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

public class HerbloreKnowledge implements IHerbloreKnowledge
{
    private final Map<ItemReagent, Collection<Flask>> reagentKnowledge;

    public HerbloreKnowledge()
    {
        reagentKnowledge = new HashMap<>();
    }

    @Override
    @Nullable
    public ImmutableList<Flask> getKnownFlasks(ItemReagent reagent)
    {
        if (!reagentKnowledge.containsKey(reagent))
        {
            return null;
        }
        return ImmutableList.copyOf(reagentKnowledge.get(reagent));
    }

    @Override
    public void setFlaskKnown(PlayerEntity playerEntity, ItemReagent reagent, Flask flask)
    {
        if (!reagentKnowledge.containsKey(reagent))
        {
            Collection<Flask> knownFlasks = new ArrayList<>();
            knownFlasks.add(flask);
            reagentKnowledge.put(reagent, knownFlasks);
        }
        else if (!reagentKnowledge.get(reagent).contains(flask))
        {
            reagentKnowledge.get(reagent).add(flask);
        }
        else
        {
            return; // No update to map required, so no packet needs to be sent.
        }

        if (playerEntity instanceof ServerPlayerEntity)
        {
            HerblorePacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity)playerEntity), serializeNBT());
        }
    }

    public CompoundNBT serializeNBT()
    {
        CompoundNBT nbt = new CompoundNBT();

        ListNBT reagentKnowledgeMapNbt = new ListNBT();
        reagentKnowledge.keySet().forEach((reagent) ->
        {
            CompoundNBT reagentKnowledgeNbt = new CompoundNBT();

            reagentKnowledgeNbt.putString("reagent_name", reagent.getRegistryName().toString()); // "key"

            ListNBT flasksNbt = new ListNBT();
            reagentKnowledge.get(reagent).forEach((flask) ->
            {
                CompoundNBT flaskNbt = new CompoundNBT();
                flaskNbt.putString("flask_name", flask.getRegistryName().toString());
                flasksNbt.add(flaskNbt);
            });
            reagentKnowledgeNbt.put("flasks", flasksNbt); // "value"

            reagentKnowledgeMapNbt.add(reagentKnowledgeNbt);
        });
        nbt.put("reagent_knowledge", reagentKnowledgeMapNbt);

        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        nbt.getList("reagent_knowledge", Constants.NBT.TAG_COMPOUND)
                .stream().map((inbt) -> (CompoundNBT) inbt) // Cast all elements to CompoundNBT.
                .forEach((reagentKnowledgeNbt) ->
                {
                    Item item = ModItems.getItemFromRegistry(reagentKnowledgeNbt.getString("reagent_name"));
                    if (item instanceof ItemReagent)
                    {
                        ItemReagent reagent = (ItemReagent) item;

                         reagentKnowledge.put(reagent, reagentKnowledgeNbt.getList("flasks", Constants.NBT.TAG_COMPOUND)
                                 .stream().map((inbt) -> (CompoundNBT) inbt) // Cast all elements to CompoundNBT.
                                 .map((flaskNbt) -> ModFlasks.getFlaskFromRegistry(flaskNbt.getString("flask_name"))) // Convert to Flasks using registry.
                                 .filter(Objects::nonNull) // Filter out null entries.
                                 .collect(Collectors.toList())); // Transform to collection.
                    }
                });
    }

    public static void syncKnowledgeFromServer(CompoundNBT nbt, PlayerEntity playerEntity)
    {
        HerbloreKnowledge knowledge = getHerbloreKnowledgeOf(playerEntity);
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
}
