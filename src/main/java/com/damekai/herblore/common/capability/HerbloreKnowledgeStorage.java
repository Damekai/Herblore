package com.damekai.herblore.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import javax.annotation.Nullable;

public class HerbloreKnowledgeStorage implements Capability.IStorage<HerbloreKnowledge>
{

    @Nullable
    @Override
    public INBT writeNBT(Capability<HerbloreKnowledge> capability, HerbloreKnowledge instance, Direction side)
    {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<HerbloreKnowledge> capability, HerbloreKnowledge instance, Direction side, INBT nbt)
    {
        instance.deserializeNBT((CompoundNBT)nbt);
    }
}
