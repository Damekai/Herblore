package com.damekai.herblore.common.flask.base;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.ModFlaskEffects;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public abstract class FlaskEffect extends ForgeRegistryEntry<FlaskEffect>
{
    private final String translationKey;
    private final int color;
    private final RegistryObject<Effect> guiEffect;

    public FlaskEffect(FlaskEffect.Properties properties)
    {
        translationKey = properties.translationKey;
        color = properties.color;
        guiEffect = properties.guiEffect;
    }

    public int getColor()
    {
        return color;
    }

    public String getTranslationKey()
    {
        return translationKey;
    }

    @Nullable
    public Effect getGuiEffect()
    {
        return guiEffect != null ? guiEffect.get() : null;
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

    public static final class Properties
    {
        private String translationKey = "no_translation_key";
        private int color;
        private RegistryObject<Effect> guiEffect;

        public FlaskEffect.Properties translationName(String translationName)
        {
            translationKey = "flask_effect." + Herblore.MOD_ID + "." + translationName;
            return this;
        }

        public FlaskEffect.Properties color(int color)
        {
            this.color = color;
            return this;
        }

        public FlaskEffect.Properties guiEffect(RegistryObject<Effect> guiEffect)
        {
            this.guiEffect = guiEffect;
            return this;
        }
    }
}
