package com.damekai.herblore.common.capability.continuousdrinkhandler;

import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ContinuousDrinkHandlerProvider implements ICapabilityProvider
{
    private final ContinuousDrinkHandler instance = new ContinuousDrinkHandler();

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
    {
        if (cap == CapabilityContinuousDrinkHandler.CONTINUOUS_DRINK_HANDLER_CAPABILITY)
        {
            return LazyOptional.of(() -> (T) this.instance);
        }
        return LazyOptional.empty();
    }
}
