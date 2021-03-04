package com.damekai.herblore.common.capability.toxicityhandler;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ToxicityHandlerStorage implements Capability.IStorage<ToxicityHandler>
{
    @Nullable
    @Override
    public INBT writeNBT(Capability<ToxicityHandler> capability, ToxicityHandler instance, Direction side)
    {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<ToxicityHandler> capability, ToxicityHandler instance, Direction side, INBT nbt)
    {
        instance.deserializeNBT((CompoundNBT)nbt);
    }
}
