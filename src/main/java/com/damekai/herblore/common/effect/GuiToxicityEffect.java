package com.damekai.herblore.common.effect;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import javax.annotation.Nullable;

public class GuiToxicityEffect extends Effect
{
    protected GuiToxicityEffect()
    {
        super(EffectType.HARMFUL, 0);
    }

    @Override
    public void applyEffectTick(LivingEntity entityLivingBaseIn, int amplifier) { }

    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity entityLivingBaseIn, int amplifier, double health) { }
}
