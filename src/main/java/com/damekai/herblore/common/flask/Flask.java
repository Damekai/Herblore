package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class Flask extends ForgeRegistryEntry<Flask>
{
    private final String translationKey;
    private final HerbloreEffectInstance herbloreEffectInstance;
    private final int baseDoses;
    private final int color;

    public Flask(String translationKey, HerbloreEffectInstance herbloreEffectInstance, int baseDoses, int color)
    {
        this.translationKey = translationKey;
        this.herbloreEffectInstance = herbloreEffectInstance;
        this.baseDoses = baseDoses;
        this.color = color;
    }

    public String getTranslationKey()
    {
        return translationKey;
    }

    public HerbloreEffectInstance getHerbloreEffectInstance()
    {
        return herbloreEffectInstance.copy();
    }

    public int getBaseDoses()
    {
        return baseDoses;
    }

    public int getColor()
    {
        return color;
    }
}
