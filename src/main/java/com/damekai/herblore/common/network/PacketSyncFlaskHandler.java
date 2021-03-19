package com.damekai.herblore.common.network;

import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class PacketSyncFlaskHandler
{
    public PacketSyncFlaskHandler() {}

    public static BiConsumer<MessageFlaskHandler, PacketBuffer> encoder()
    {
        return (message, buffer) -> buffer.writeCompoundTag(message.getNbt());
    }

    public static Function<PacketBuffer, MessageFlaskHandler> decoder()
    {
        return (buffer) -> new MessageFlaskHandler(buffer.readCompoundTag());
    }

    public static BiConsumer<MessageFlaskHandler, Supplier<NetworkEvent.Context>> handler()
    {
        return (message, context) ->
        {
            context.get().enqueueWork(() -> FlaskHandler.syncFlaskHandlerFromServer(message.getNbt()));
            context.get().setPacketHandled(true);
        };
    }
}
