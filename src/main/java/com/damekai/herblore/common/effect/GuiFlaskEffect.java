package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;

/**
 * "Dummy" effect used as a means to display an active Flask with the GUI in the same way
 * that Effects are displayed in vanilla Minecraft.
 */
public class GuiFlaskEffect extends Effect
{
    private final RegistryObject<HerbloreEffect> flaskEffect;

    protected GuiFlaskEffect(RegistryObject<HerbloreEffect> flaskEffect)
    {
        super(EffectType.BENEFICIAL, 0);

        this.flaskEffect = flaskEffect;
    }

    @Override
    public ITextComponent getDisplayName()
    {
        return new TranslationTextComponent(flaskEffect.get().getTranslationKey());
    }

    @Override
    public String getDescriptionId()
    {
        return flaskEffect.get().getTranslationKey();
    }

    @Override
    public void applyEffectTick(LivingEntity livingEntity, int amplifier) { }


    @Override
    public void applyInstantenousEffect(@Nullable Entity source, @Nullable Entity indirectSource, LivingEntity livingEntity, int amplifier, double health) { }
}
