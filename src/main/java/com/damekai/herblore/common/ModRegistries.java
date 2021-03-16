package com.damekai.herblore.common;

import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.perk.base.FlaskPerk;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryBuilder;
import net.minecraftforge.registries.RegistryManager;

public class ModRegistries
{
    public static final IForgeRegistry<FlaskEffect> FLASK_EFFECTS = RegistryManager.ACTIVE.getRegistry(FlaskEffect.class);
    public static final IForgeRegistry<FlaskPerk> FLASK_PERKS = RegistryManager.ACTIVE.getRegistry(FlaskPerk.class);

    public static void onNewRegistry(RegistryEvent.NewRegistry event)
    {
        ResourceLocation flaskEffectResourceLocation = new ResourceLocation(Herblore.MOD_ID, "flask_effects");
        RegistryBuilder<FlaskEffect> flaskEffectRegistryBuilder = new RegistryBuilder<>();
        flaskEffectRegistryBuilder.setType(FlaskEffect.class);
        flaskEffectRegistryBuilder.setName(flaskEffectResourceLocation);
        flaskEffectRegistryBuilder.setDefaultKey(flaskEffectResourceLocation);
        flaskEffectRegistryBuilder.create();

        ResourceLocation flaskPerkResourceLocation = new ResourceLocation(Herblore.MOD_ID, "flask_perks");
        RegistryBuilder<FlaskPerk> flaskPerkRegistryBuilder = new RegistryBuilder<>();
        flaskPerkRegistryBuilder.setType(FlaskPerk.class);
        flaskPerkRegistryBuilder.setName(flaskPerkResourceLocation);
        flaskPerkRegistryBuilder.setDefaultKey(flaskPerkResourceLocation);
        flaskPerkRegistryBuilder.create();

        Herblore.LOGGER.debug("Created Herblore registries.");
    }
}
