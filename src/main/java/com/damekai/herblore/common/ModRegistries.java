package com.damekai.herblore.common;

import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.effusion.base.Effusion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;

public class ModRegistries
{
    public static IForgeRegistry<HerbloreEffect> HERBLORE_EFFECTS;
    public static IForgeRegistry<Flask> FLASKS;
    public static IForgeRegistry<Effusion> EFFUSIONS;

    public static void onNewRegistry(RegistryEvent.NewRegistry event)
    {
        ResourceLocation herbloreEffectResourceLocation = new ResourceLocation(Herblore.MOD_ID, "herblore_effect");
        RegistryBuilder<HerbloreEffect> herbloreEffectRegistryBuilder = new RegistryBuilder<>();
        herbloreEffectRegistryBuilder.setType(HerbloreEffect.class);
        herbloreEffectRegistryBuilder.setName(herbloreEffectResourceLocation);
        herbloreEffectRegistryBuilder.setDefaultKey(herbloreEffectResourceLocation);
        HERBLORE_EFFECTS = herbloreEffectRegistryBuilder.create();

        ResourceLocation flaskResourceLocation = new ResourceLocation(Herblore.MOD_ID, "flask");
        RegistryBuilder<Flask> flaskRegistryBuilder = new RegistryBuilder<>();
        flaskRegistryBuilder.setType(Flask.class);
        flaskRegistryBuilder.setName(flaskResourceLocation);
        flaskRegistryBuilder.setDefaultKey(flaskResourceLocation);
        FLASKS = flaskRegistryBuilder.create();

        ResourceLocation effusionResourceLocation = new ResourceLocation(Herblore.MOD_ID, "effusion");
        RegistryBuilder<Effusion> effusionRegistryBuilder = new RegistryBuilder<>();
        effusionRegistryBuilder.setType(Effusion.class);
        effusionRegistryBuilder.setName(effusionResourceLocation);
        effusionRegistryBuilder.setDefaultKey(effusionResourceLocation);
        EFFUSIONS = effusionRegistryBuilder.create();

        Herblore.LOGGER.debug("Created Herblore registries.");
    }
}
