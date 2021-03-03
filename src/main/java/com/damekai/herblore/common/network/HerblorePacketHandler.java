package com.damekai.herblore.common.network;

import com.damekai.herblore.common.Herblore;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;

public class HerblorePacketHandler
{
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(Herblore.MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public static void registerPackets()
    {
        INSTANCE.messageBuilder(CompoundNBT.class, 0)
                .encoder(PacketSyncKnowledge.encoder())
                .decoder(PacketSyncKnowledge.decoder())
                .consumer(PacketSyncKnowledge.handler())
                .add();

    }
}
