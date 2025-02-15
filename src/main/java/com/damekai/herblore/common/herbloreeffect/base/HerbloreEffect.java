package com.damekai.herblore.common.herbloreeffect.base;

import com.damekai.herblore.common.ModRegistries;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.util.Util;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.function.Supplier;

public abstract class HerbloreEffect extends ForgeRegistryEntry<HerbloreEffect>
{
    private String translationKey = null;
    private final Supplier<Effect> guiEffect;

    public HerbloreEffect(Supplier<Effect> guiEffect)
    {
        this.guiEffect = guiEffect;
    }

    public String getTranslationKey()
    {
        if (translationKey == null)
        {
            translationKey = Util.makeDescriptionId("herbloreeffect", ModRegistries.HERBLORE_EFFECTS.getKey(this));
        }
        return translationKey;
    }

    @Nullable
    public Effect getGuiEffect()
    {
        return guiEffect != null ? guiEffect.get() : null;
    }

    @Nullable
    public boolean combineInstances(HerbloreEffectInstance left, HerbloreEffectInstance right)
    {
        if (left.getHerbloreEffect() != right.getHerbloreEffect())
        {
            return false;
        }

        left.setAmplifier(Math.min(left.getAmplifier(), right.getAmplifier()));
        left.setDurationFull(left.getDurationFull() + right.getDurationFull());
        left.setDurationRemaining(left.getDurationRemaining() + right.getDurationRemaining());

        return true;
    }

    /**
     * Implies that a Flask Effect has functionality when it is applied.
     */
    public interface IApplicable
    {
        void onApply(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity);
    }

    /**
     * Implies that a Flask Effect has functionality every tick.
     */
    public interface ITickable
    {
        void onTick(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity);
    }

    /**
     * Implies that a Flask Effect has functionality when it expires.
     */
    public interface IExpirable
    {
        void onExpire(HerbloreEffectInstance herbloreEffectInstance, LivingEntity livingEntity);
    }
}
