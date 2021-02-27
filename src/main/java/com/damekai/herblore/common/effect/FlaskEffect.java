package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.Herblore;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class FlaskEffect extends ForgeRegistryEntry<FlaskEffect>
{
    private final String translationKey;

    public FlaskEffect(String translationName)
    {
        translationKey = "flask_effect." + Herblore.MOD_ID + "." + translationName;
    }

    public void onApply(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {

    }

    public void onTick(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {

    }

    public void onExpire(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {

    }

    public void onRemove(LivingEntity livingEntity, int potency, int durationFull, int durationRemaining)
    {

    }

    public String getTranslationKey()
    {
        return translationKey;
    }

    public static FlaskEffect getFlaskEffectFromRegistry(ResourceLocation name)
    {
        for (RegistryObject<FlaskEffect> registeredEffect : ModFlaskEffects.FLASK_EFFECTS.getEntries())
        {
            if (name.equals(registeredEffect.get().getRegistryName()))
            {
                return registeredEffect.get();
            }
        }
        return null;
    }
}
