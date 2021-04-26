package com.damekai.herblore.common.network;

import com.damekai.herblore.common.capability.herbloreeffecthandler.HerbloreEffectHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketSyncHerbloreEffectHandler
{
    public PacketSyncHerbloreEffectHandler() {}

    public static BiConsumer<MessageHerbloreEffectHandler, PacketBuffer> encoder()
    {
        return (message, buffer) -> buffer.writeNbt(message.getNbt());
    }

    public static Function<PacketBuffer, MessageHerbloreEffectHandler> decoder()
    {
        return (buffer) -> new MessageHerbloreEffectHandler(buffer.readNbt());
    }

    public static BiConsumer<MessageHerbloreEffectHandler, Supplier<NetworkEvent.Context>> handler()
    {
        return (message, context) ->
        {
            context.get().enqueueWork(() -> HerbloreEffectHandler.syncHerbloreEffectHandlerFromServer(message.getNbt()));
            context.get().setPacketHandled(true);
        };
    }
}
