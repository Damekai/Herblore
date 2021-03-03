package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.Herblore;
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
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
        for (ItemReagent reagent : reagentKnowledge.keySet())
        {
            CompoundNBT reagentKnowledgeNbt = new CompoundNBT();

            reagentKnowledgeNbt.putString("reagent_name", reagent.getRegistryName().toString()); // "key"

            ListNBT flasksNbt = new ListNBT();
            for (Flask flask : reagentKnowledge.get(reagent))
            {
                CompoundNBT flaskNbt = new CompoundNBT();

                flaskNbt.putString("flask_name", flask.getRegistryName().toString());
                flasksNbt.add(flaskNbt);
            }

            reagentKnowledgeNbt.put("flasks", flasksNbt); // "value"

            reagentKnowledgeMapNbt.add(reagentKnowledgeNbt);
        }

        nbt.put("reagent_knowledge", reagentKnowledgeMapNbt);

        return nbt;
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        ListNBT reagentKnowledgeMapNbt = nbt.getList("reagent_knowledge", Constants.NBT.TAG_COMPOUND);
        for (INBT reagentKnowledgeInbt : reagentKnowledgeMapNbt)
        {
            CompoundNBT reagentKnowledgeNbt = (CompoundNBT) reagentKnowledgeInbt;

            Item item = ModItems.getItemFromRegistry(reagentKnowledgeNbt.getString("reagent_name"));
            if (item instanceof ItemReagent) // Also does null check, apparently.
            {
                ItemReagent reagent = (ItemReagent) item;

                Collection<Flask> knownFlasks = new ArrayList<>();

                ListNBT flasksNbt = reagentKnowledgeNbt.getList("flasks", Constants.NBT.TAG_COMPOUND);
                for (INBT flaskInbt : flasksNbt)
                {
                    CompoundNBT flaskNbt = (CompoundNBT) flaskInbt;

                    Flask flask = ModFlasks.getFlaskFromRegistry(flaskNbt.getString("flask_name"));
                    if (flask != null)
                    {
                        knownFlasks.add(flask);
                    }
                }

                reagentKnowledge.put(reagent, knownFlasks);
            }
        }
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
