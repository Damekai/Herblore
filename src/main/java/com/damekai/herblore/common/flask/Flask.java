package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.ModRegistries;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraft.util.Util;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class Flask extends ForgeRegistryEntry<Flask>
{
    private String translationKey = null;
    private final HerbloreEffectInstance herbloreEffectInstance;
    private final int baseDoses;
    private final int color;

    public Flask(HerbloreEffectInstance herbloreEffectInstance, int baseDoses, int color)
    {
        this.herbloreEffectInstance = herbloreEffectInstance;
        this.baseDoses = baseDoses;
        this.color = color;
    }

    public String getTranslationKey()
    {
        if (translationKey == null)
        {
            translationKey = Util.makeTranslationKey("flask", ModRegistries.FLASKS.getKey(this));
        }
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
