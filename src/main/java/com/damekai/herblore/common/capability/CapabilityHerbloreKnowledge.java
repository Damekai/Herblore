package com.damekai.herblore.common.capability;

import com.damekai.herblore.common.Herblore;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class CapabilityHerbloreKnowledge
{
    @CapabilityInject(HerbloreKnowledge.class)
    public static Capability<HerbloreKnowledge> HERBLORE_KNOWLEDGE_CAPABILITY = null;

    public static void register()
    {
        CapabilityManager.INSTANCE.register(HerbloreKnowledge.class, new HerbloreKnowledgeStorage(), HerbloreKnowledge::new);
    }

    public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event)
    {
        if (event.getObject() instanceof PlayerEntity)
        {
            event.addCapability(new ResourceLocation(Herblore.MOD_ID, "herblore_knowledge_capability"), new HerbloreKnowledgeProvider());
        }
    }
}
