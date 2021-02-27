package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.FlaskEffect;
import com.google.common.collect.ImmutableList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.stream.Collectors;

public class Flask extends net.minecraftforge.registries.ForgeRegistryEntry<Flask>
{
    private final String translationKey;
    private final ImmutableList<RegistryObject<FlaskEffect>> flaskEffects;

    public Flask(String translationName, RegistryObject<FlaskEffect>... flaskEffects)
    {
        translationKey = "flask." + Herblore.MOD_ID + "." + translationName;
        this.flaskEffects = ImmutableList.copyOf(flaskEffects);
    }

    public String getTranslationKey()
    {
        return translationKey;
    }

    public ImmutableList<FlaskEffect> getFlaskEffects()
    {
        return ImmutableList.copyOf(
                flaskEffects.stream()
                        .map(RegistryObject::get)
                        .collect(Collectors.toList()));
    }

    public static Flask getFlaskFromRegistry(ResourceLocation name)
    {
        for (RegistryObject<Flask> registeredEffect : ModFlasks.FLASKS.getEntries())
        {
            if (name.equals(registeredEffect.get().getRegistryName()))
            {
                return registeredEffect.get();
            }
        }
        return null;
    }
}
