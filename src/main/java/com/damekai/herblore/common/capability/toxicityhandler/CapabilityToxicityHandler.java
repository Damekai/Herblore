package com.damekai.herblore.common.capability.toxicityhandler;

import com.damekai.herblore.common.Herblore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class CapabilityToxicityHandler
{
    @CapabilityInject(ToxicityHandler.class)
    public static Capability<ToxicityHandler> TOXICITY_HANDLER_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(ToxicityHandler.class, new ToxicityHandlerStorage(), ToxicityHandler::new);
    }

    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (!event.getObject().getEntityWorld().isRemote && event.getObject() instanceof PlayerEntity)
        {
            event.addCapability(new ResourceLocation(Herblore.MOD_ID, "toxicity_handler_capability"), new ToxicityHandlerProvider());
        }
    }
}
