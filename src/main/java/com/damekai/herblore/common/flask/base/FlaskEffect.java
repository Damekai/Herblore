package com.damekai.herblore.common.flask.base;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.ModFlaskEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public abstract class FlaskEffect extends ForgeRegistryEntry<FlaskEffect>
{
    private final String translationKey;
    private final int baseDuation;
    private final int color;
    private final RegistryObject<Effect> guiEffect;

    public FlaskEffect(FlaskEffect.Properties properties)
    {
        translationKey = properties.translationKey;
        baseDuation = properties.baseDuration;
        color = properties.color;
        guiEffect = properties.guiEffect;
    }

    public int getBaseDuation()
    {
        return baseDuation;
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
        private int baseDuration;
        private int color;
        private RegistryObject<Effect> guiEffect;

        public FlaskEffect.Properties translationName(String translationName)
        {
            translationKey = "flask_effect." + Herblore.MOD_ID + "." + translationName;
            return this;
        }

        public FlaskEffect.Properties baseDuration(int baseDuration)
        {
            this.baseDuration = baseDuration;
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

    /**
     * Implies that a Flask Effect has functionality when it is applied.
     */
    public interface IApplicable
    {
        void onApply(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity);
    }

    /**
     * Implies that a Flask Effect has functionality every tick.
     */
    public interface ITickable
    {
        void onTick(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity);
    }

    /**
     * Implies that a Flask Effect has functionality when it expires.
     */
    public interface IExpirable
    {
        void onExpire(FlaskEffectInstance flaskEffectInstance, LivingEntity livingEntity);
    }
}
