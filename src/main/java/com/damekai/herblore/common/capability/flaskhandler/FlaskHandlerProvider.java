package com.damekai.herblore.common.capability.flaskhandler;

import com.damekai.herblore.common.capability.flaskhandler.CapabilityFlaskHandler;
import com.damekai.herblore.common.capability.flaskhandler.FlaskHandler;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FlaskHandlerProvider implements ICapabilitySerializable<CompoundNBT>
{
    private final FlaskHandler instance = new FlaskHandler();

    public CompoundNBT serializeNBT()
    {
        return instance.serializeNBT();
    }

    public void deserializeNBT(CompoundNBT nbt)
    {
        instance.deserializeNBT(nbt);
    }


    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == CapabilityFlaskHandler.FLASK_HANDLER_CAPABILITY)
        {
            return LazyOptional.of(() -> (T) this.instance);
        }
        return LazyOptional.empty();
    }
}
