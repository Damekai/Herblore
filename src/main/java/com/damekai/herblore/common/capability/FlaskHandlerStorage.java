package com.damekai.herblore.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class FlaskHandlerStorage implements Capability.IStorage<FlaskHandler>
{

    @Nullable
    @Override
    public INBT writeNBT(Capability<FlaskHandler> capability, FlaskHandler instance, Direction side)
    {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<FlaskHandler> capability, FlaskHandler instance, Direction side, INBT nbt)
    {
        instance.deserializeNBT((CompoundNBT)nbt);
    }
}
