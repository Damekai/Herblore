package com.damekai.herblore.common.util;

import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.*;

public class FlaskHelper
{
    public static int getFlaskColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("flask_effect_color"); // Returns 0 if the flask_effect_color key is not present.
    }

    public static int getFlaskDoses(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("flask_doses");
    }

    @OnlyIn(Dist.CLIENT)
    public static void addFlaskTooltip(ItemStack stack, List<ITextComponent> lores)
    {
        if (!stack.getOrCreateTag().contains("flask_effect_instance"))
        {
            return;
        }

        HerbloreEffectInstance herbloreEffectInstance = HerbloreEffectInstance.read(stack.getOrCreateTag().getCompound("flask_effect_instance"));


    }
}
