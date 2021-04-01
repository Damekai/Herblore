package com.damekai.herblore.common.capability.herbloreeffecthandler;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class HerbloreEffectHandlerStorage implements Capability.IStorage<HerbloreEffectHandler>
{

    @Nullable
    @Override
    public INBT writeNBT(Capability<HerbloreEffectHandler> capability, HerbloreEffectHandler instance, Direction side)
    {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<HerbloreEffectHandler> capability, HerbloreEffectHandler instance, Direction side, INBT nbt)
    {
        instance.deserializeNBT((CompoundNBT)nbt);
    }
}
