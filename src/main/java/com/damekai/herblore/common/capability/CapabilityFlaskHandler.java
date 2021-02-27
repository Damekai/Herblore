package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.Herblore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class CapabilityFlaskHandler
{
    @CapabilityInject(FlaskHandler.class)
    public static Capability<FlaskHandler> FLASK_HANDLER_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(FlaskHandler.class, new FlaskHandlerStorage(), FlaskHandler::new);
    }

    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            event.addCapability(new ResourceLocation(Herblore.MOD_ID, "flask_handler_capability"), new FlaskHandlerProvider());
        }
    }
}
