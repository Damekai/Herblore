package com.damekai.herblore.common;

import com.damekai.herblore.common.flask.base.FlaskEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

public class ModRegistries
{
    public static final IForgeRegistry<FlaskEffect> FLASK_EFFECTS = RegistryManager.ACTIVE.getRegistry(FlaskEffect.class);

    public static void onNewRegistry(RegistryEvent.NewRegistry event)
    {
        ResourceLocation flaskEffectResourceLocation = new ResourceLocation(Herblore.MOD_ID, "flask_effects");
        RegistryBuilder<FlaskEffect> flaskEffectRegistryBuilder = new RegistryBuilder<>();
        flaskEffectRegistryBuilder.setType(FlaskEffect.class);
        flaskEffectRegistryBuilder.setName(flaskEffectResourceLocation);
        flaskEffectRegistryBuilder.setDefaultKey(flaskEffectResourceLocation);
        flaskEffectRegistryBuilder.create();

        Herblore.LOGGER.debug("Created Herblore registries.");
    }
}
