package com.damekai.herblore.common;

import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

public class ModRegistries
{
    public static final IForgeRegistry<HerbloreEffect> HERBLORE_EFFECTS = RegistryManager.ACTIVE.getRegistry(HerbloreEffect.class);
    public static final IForgeRegistry<Flask> FLASKS = RegistryManager.ACTIVE.getRegistry(Flask.class);

    public static void onNewRegistry(RegistryEvent.NewRegistry event)
    {
        ResourceLocation herbloreEffectResourceLocation = new ResourceLocation(Herblore.MOD_ID, "herblore_effect");
        RegistryBuilder<HerbloreEffect> herbloreEffectRegistryBuilder = new RegistryBuilder<>();
        herbloreEffectRegistryBuilder.setType(HerbloreEffect.class);
        herbloreEffectRegistryBuilder.setName(herbloreEffectResourceLocation);
        herbloreEffectRegistryBuilder.setDefaultKey(herbloreEffectResourceLocation);
        herbloreEffectRegistryBuilder.create();

        ResourceLocation flaskResourceLocation = new ResourceLocation(Herblore.MOD_ID, "flask");
        RegistryBuilder<Flask> flaskRegistryBuilder = new RegistryBuilder<>();
        flaskRegistryBuilder.setType(Flask.class);
        flaskRegistryBuilder.setName(flaskResourceLocation);
        flaskRegistryBuilder.setDefaultKey(flaskResourceLocation);
        flaskRegistryBuilder.create();

        Herblore.LOGGER.debug("Created Herblore registries.");
    }
}
