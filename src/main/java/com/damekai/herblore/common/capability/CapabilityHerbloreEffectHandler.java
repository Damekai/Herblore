package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.Herblore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;

import javax.annotation.Nullable;
import java.util.concurrent.Callable;

public class CapabilityHerbloreEffectHandler
{
    @CapabilityInject(HerbloreEffectHandler.class)
    public static Capability<HerbloreEffectHandler> HERBLORE_EFFECT_HANDLER_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(HerbloreEffectHandler.class, new HerbloreEffectHandlerStorage(), HerbloreEffectHandler::new);
    }

    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            event.addCapability(new ResourceLocation(Herblore.MOD_ID, "herblore_effect_handler_capability"), new HerbloreEffectHandlerProvider());
        }
    }
}
