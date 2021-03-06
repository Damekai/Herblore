package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flaskeffect.base.ApplicableFlaskEffect;
import com.damekai.herblore.common.flaskeffect.base.DurationFlaskEffect;
import com.damekai.herblore.common.flaskeffect.base.FlaskEffect;
import com.damekai.herblore.common.flaskeffect.base.TickingFlaskEffect;
import com.google.common.collect.ImmutableList;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.stream.Collectors;

public class Flask extends net.minecraftforge.registries.ForgeRegistryEntry<Flask>
{
    private final String translationKey;
    private final int color;
    private final RegistryObject<Effect> guiRenderEffect;
    private final ImmutableList<RegistryObject<FlaskEffect>> flaskEffects;

    public Flask(String translationName, int color, @Nullable RegistryObject<Effect> guiRenderEffect, RegistryObject<FlaskEffect>... flaskEffects)
    {
        translationKey = "flask." + Herblore.MOD_ID + "." + translationName;
        this.color = color;
        this.guiRenderEffect = guiRenderEffect;
        this.flaskEffects = ImmutableList.copyOf(flaskEffects);
    }

    public String getTranslationKey()
    {
        return translationKey;
    }

    public int getColor()
    {
        return color;
    }

    public Effect getGuiRenderEffect()
    {
        return guiRenderEffect != null ? guiRenderEffect.get() : null;
    }

    public ImmutableList<FlaskEffect> getFlaskEffects()
    {
        return ImmutableList.copyOf(
                flaskEffects.stream()
                        .map(RegistryObject::get)
                        .collect(Collectors.toList()));
    }

    public ImmutableList<ApplicableFlaskEffect> getApplicableFlaskEffects()
    {
        return ImmutableList.copyOf(
                flaskEffects.stream()
                        .map(RegistryObject::get)
                        .filter((flaskEffect) -> flaskEffect instanceof ApplicableFlaskEffect)
                        .map((flaskEffect) -> (ApplicableFlaskEffect) flaskEffect)
                        .collect(Collectors.toList()));
    }

    public ImmutableList<DurationFlaskEffect> getDurationFlaskEffects()
    {
        return ImmutableList.copyOf(
                flaskEffects.stream()
                        .map(RegistryObject::get)
                        .filter((flaskEffect) -> flaskEffect instanceof DurationFlaskEffect)
                        .map((flaskEffect) -> (DurationFlaskEffect) flaskEffect)
                        .collect(Collectors.toList()));
    }

    public ImmutableList<TickingFlaskEffect> getTickingFlaskEffects()
    {
        return ImmutableList.copyOf(
                flaskEffects.stream()
                        .map(RegistryObject::get)
                        .filter((flaskEffect) -> flaskEffect instanceof TickingFlaskEffect)
                        .map((flaskEffect) -> (TickingFlaskEffect) flaskEffect)
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
