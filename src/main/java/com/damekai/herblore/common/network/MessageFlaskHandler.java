package com.damekai.herblore.common.network;

import net.minecraft.nbt.CompoundNBT;

public class MessageFlaskHandler
{
    private final CompoundNBT nbt;

    public MessageFlaskHandler(CompoundNBT nbt)
    {
        this.nbt = nbt;
    }

    public CompoundNBT getNbt()
    {
        return nbt;
    }
}
