package com.damekai.herblore.common.util;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.ModRegistries;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.ModFlasks;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import com.damekai.herblore.common.item.ItemFlask;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.DecimalFormat;
import java.util.*;

public class FlaskHelper
{
    public static int getFlaskItemColor(ItemStack stack, int tintIndex)
    {
        if (tintIndex == 0)
        {
            Flask flask = ModRegistries.FLASKS.getValue(new ResourceLocation(stack.getOrCreateTag().getString("flask")));
            if (flask != null)
            {
                return flask.getColor();
            }
        }
        return 0xFFFFFF;
    }

    public static int getFlaskFillPredicate(ItemStack stack)
    {
        int flaskSips = stack.getOrCreateTag().getInt("flask_sips");
        if (flaskSips >= 180)
        {
            return 10;
        }
        else if (flaskSips >= 160)
        {
            return 9;
        }
        else if (flaskSips >= 140)
        {
            return 8;
        }
        else if (flaskSips >= 120)
        {
            return 7;
        }
        else if (flaskSips >= 100)
        {
            return 6;
        }
        else if (flaskSips >= 80)
        {
            return 5;
        }
        else if (flaskSips >= 60)
        {
            return 4;
        }
        else if (flaskSips >= 40)
        {
            return 3;
        }
        else if (flaskSips >= 20)
        {
            return 2;
        }
        else
        {
            return 1;
        }
    }

    @OnlyIn(Dist.CLIENT)
    public static void addFlaskTooltip(ItemStack stack, List<ITextComponent> lores)
    {
        Flask flask = ModRegistries.FLASKS.getValue(new ResourceLocation(stack.getOrCreateTag().getString("flask")));
        int amountRemaining = stack.getOrCreateTag().getInt("flask_sips");

        if (flask != null)
        {
            lores.add(new TranslationTextComponent("text.herblore.duration_remaining")
                    .append(new StringTextComponent(": " + StringUtils.formatTickDuration(Math.round(flask.getHerbloreEffectInstance().getDurationFull() * (float) amountRemaining / ((ItemFlask) stack.getItem()).getInitialSips())))).withStyle(TextFormatting.BLUE));

            HerbloreEffectInstance herbloreEffectInstance = flask.getHerbloreEffectInstance();
            HerbloreEffect herbloreEffect = herbloreEffectInstance.getHerbloreEffect();

            lores.add(new TranslationTextComponent(herbloreEffect.getTranslationKey()).withStyle(TextFormatting.GREEN));
        }
    }
}
