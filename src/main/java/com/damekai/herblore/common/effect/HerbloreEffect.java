package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.Herblore;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public abstract class HerbloreEffect extends ForgeRegistryEntry<HerbloreEffect>
{
    private final String translationKey;

    public HerbloreEffect(String translationName)
    {
        translationKey = "herblore_effect." + Herblore.MOD_ID + "." + translationName;
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

    public static HerbloreEffect getHerbloreEffectFromRegistry(ResourceLocation name)
    {
        for (RegistryObject<HerbloreEffect> registeredEffect : ModHerbloreEffects.HERBLORE_EFFECTS.getEntries())
        {
            if (name.equals(registeredEffect.get().getRegistryName()))
            {
                return registeredEffect.get();
            }
        }
        return null;
    }
}
