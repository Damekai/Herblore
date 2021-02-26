package com.damekai.herblore.common;

import com.damekai.herblore.common.effect.HerbloreEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

public class ModRegistries
{
    public static final IForgeRegistry<HerbloreEffect> HERBLORE_EFFECTS = RegistryManager.ACTIVE.getRegistry(HerbloreEffect.class);

    public static void onNewRegistry(RegistryEvent.NewRegistry event)
    {
        ResourceLocation herbloreEffectResourceLocation = new ResourceLocation(Herblore.MOD_ID, "herblore_effects");
        RegistryBuilder<HerbloreEffect> herbloreEffectRegistryBuilder = new RegistryBuilder<>();
        herbloreEffectRegistryBuilder.setType(HerbloreEffect.class);
        herbloreEffectRegistryBuilder.setName(herbloreEffectResourceLocation);
        herbloreEffectRegistryBuilder.setDefaultKey(herbloreEffectResourceLocation);
        herbloreEffectRegistryBuilder.create();

        Herblore.LOGGER.debug("Created Herblore registries.");
    }
}
