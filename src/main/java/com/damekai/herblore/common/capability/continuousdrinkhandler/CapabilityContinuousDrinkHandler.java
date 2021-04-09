package com.damekai.herblore.common.capability.continuousdrinkhandler;

import com.damekai.herblore.common.Herblore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class CapabilityContinuousDrinkHandler
{
    @CapabilityInject(ContinuousDrinkHandler.class)
    public static Capability<ContinuousDrinkHandler> CONTINUOUS_DRINK_HANDLER_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(ContinuousDrinkHandler.class, new ContinuousDrinkHandlerStorage(), ContinuousDrinkHandler::new);
    }

    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            event.addCapability(new ResourceLocation(Herblore.MOD_ID, "continuous_drink_handler_capability"), new ContinuousDrinkHandlerProvider());
        }
    }
}
