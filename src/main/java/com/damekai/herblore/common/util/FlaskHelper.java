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
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.text.DecimalFormat;
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
        if (stack.getOrCreateTag().contains("flask_sips"))
        {
            int amountRemaining = stack.getOrCreateTag().getInt("flask_sips");
            lores.add(new TranslationTextComponent("text.herblore.amount_remaining")
                    .append(new StringTextComponent(": " + Math.round(100f * (float) amountRemaining / (float) ItemFlask.INITIAL_FLASK_SIPS) + "%")).mergeStyle(TextFormatting.BLUE));
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
