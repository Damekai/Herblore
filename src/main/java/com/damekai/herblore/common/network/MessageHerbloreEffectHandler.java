package com.damekai.herblore.common.network;

import net.minecraft.nbt.CompoundNBT;

public class MessageHerbloreEffectHandler
{
    private final CompoundNBT nbt;

    public MessageHerbloreEffectHandler(CompoundNBT nbt)
    {
        this.nbt = nbt;
    }

    public CompoundNBT getNbt()
    {
        return nbt;
    }
}
