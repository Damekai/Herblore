package com.damekai.herblore.common.network;

import com.damekai.herblore.common.capability.herbloreknowledge.HerbloreKnowledge;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketSyncKnowledge
{
    public PacketSyncKnowledge() {}

    public static BiConsumer<CompoundNBT, PacketBuffer> encoder()
    {
        return (nbt, buffer) -> buffer.writeString(nbt.toString());
    }

    public static Function<PacketBuffer, CompoundNBT> decoder()
    {
        return (buffer) ->
        {
            try
            {
                return JsonToNBT.getTagFromJson(buffer.readString());
            }
            catch (CommandSyntaxException e)
            {
                e.printStackTrace();
                return null;
            }
        };
    }

    public static BiConsumer<CompoundNBT, Supplier<NetworkEvent.Context>> handler()
    {
        return (nbt, context) ->
        {
            context.get().enqueueWork(() -> HerbloreKnowledge.syncKnowledgeFromServer(nbt));
            context.get().setPacketHandled(true);
        };
    }
}
