package com.damekai.herblore.common.flask.perk.base;

import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class FlaskPerk extends ForgeRegistryEntry<FlaskPerk>
{
    private final String translationKey;

    public FlaskPerk(String translationName)
    {
        translationKey = "flask_perk.herblore." + translationName;
    }

    public abstract void applyPerk(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity);

    public String getTranslationKey()
    {
        return translationKey;
    }
}
