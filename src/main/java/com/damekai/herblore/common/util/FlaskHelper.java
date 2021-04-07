package com.damekai.herblore.common.util;

import com.damekai.herblore.common.ModRegistries;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.ModFlasks;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.*;

public class FlaskHelper
{
    public static int getFlaskColor(ItemStack stack)
    {
        Flask flask = ModRegistries.FLASKS.getValue(new ResourceLocation(stack.getOrCreateTag().getString("flask")));
        if (flask != null)
        {
            return flask.getColor();
        }
        return 0;
    }

    public static int getFlaskDoses(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("flask_doses");
    }

    @OnlyIn(Dist.CLIENT)
    public static void addFlaskTooltip(ItemStack stack, List<ITextComponent> lores)
    {
        if (stack.getOrCreateTag().contains("flask_doses"))
        {
            int dosesRemaining = stack.getOrCreateTag().getInt("flask_doses");
            lores.add(new TranslationTextComponent("text.herblore.doses_remaining").append(new StringTextComponent(": " + dosesRemaining)).mergeStyle(TextFormatting.BLUE));
        }

        Flask flask = ModRegistries.FLASKS.getValue(new ResourceLocation(stack.getOrCreateTag().getString("flask")));
        if (flask != null)
        {
            HerbloreEffectInstance herbloreEffectInstance = flask.getHerbloreEffectInstance();
            HerbloreEffect herbloreEffect = herbloreEffectInstance.getHerbloreEffect();

            lores.add(new TranslationTextComponent(herbloreEffect.getTranslationKey()).mergeStyle(TextFormatting.GREEN));
        }
    }
}
