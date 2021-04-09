package com.damekai.herblore.common.capability.continuousdrinkhandler;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class ContinuousDrinkHandlerStorage implements Capability.IStorage<ContinuousDrinkHandler>
{
    @Nullable
    @Override
    public INBT writeNBT(Capability<ContinuousDrinkHandler> capability, ContinuousDrinkHandler instance, Direction side)
    {
        return null;
    }

    @Override
    public void readNBT(Capability<ContinuousDrinkHandler> capability, ContinuousDrinkHandler instance, Direction side, INBT nbt)
    {

    }
}
