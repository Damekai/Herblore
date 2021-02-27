package com.damekai.herblore.common.util;

import com.damekai.herblore.common.effect.HerbloreEffect;
import com.damekai.herblore.common.effect.HerbloreEffectInstance;
import com.damekai.herblore.common.effect.ModHerbloreEffects;
import com.damekai.herblore.common.item.ItemFlask;
import com.damekai.herblore.common.item.ItemReagent;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.RegistryObject;

import java.lang.reflect.Array;
import java.util.*;

public class FlaskHelper
{
    public static CompoundNBT makeFlaskNBT(ItemReagent... reagents)
    {
        CompoundNBT nbt = new CompoundNBT();

        HerbloreEffect resultingEffect = null;
        int resultingEffectPoints = 0;

        ItemReagent firstReagent = reagents[0];

        for (RegistryObject<HerbloreEffect> herbloreEffect : firstReagent.getHerbloreEffectPoints().keySet())
        {
            boolean sharedBetweenAllReagents = true;
            int sumPoints = 0;
            for (int i = 1; i < reagents.length; i++)
            {
                ItemReagent reagent = reagents[i];
                if (!reagent.getHerbloreEffectPoints().containsKey(herbloreEffect))
                {
                    sharedBetweenAllReagents = false;
                    break;
                }
                else
                {
                    sumPoints += reagent.getHerbloreEffectPoints().get(herbloreEffect);
                }
            }
            if (sharedBetweenAllReagents && sumPoints > resultingEffectPoints)
            {
                resultingEffect = herbloreEffect.get();
                resultingEffectPoints = sumPoints;
            }
        }

        HerbloreEffectInstance resultingEffectInstance;
        if (resultingEffect != null)
        {
            resultingEffectInstance = new HerbloreEffectInstance(resultingEffect, resultingEffectPoints, 40); // TODO: Calculate duration.
        }
        else
        {
            resultingEffectInstance = new HerbloreEffectInstance(ModHerbloreEffects.DEBUG_ALPHA.get(), 0, 0); // TODO: Put some empty effect thing here.
        }

        return resultingEffectInstance.write(new CompoundNBT());
    }

    // Modified version of PotionUtils.addPotionTooltip() from vanilla.
    @OnlyIn(Dist.CLIENT)
    public static void addFlaskTooltip(ItemStack stack, List<ITextComponent> lores)
    {
        if (!stack.getOrCreateTag().contains("flask_effect"))
        {
            return;
        }

        HerbloreEffectInstance herbloreEffectInstance = HerbloreEffectInstance.read(stack.getOrCreateTag().getCompound("flask_effect"));

        IFormattableTextComponent lore = new TranslationTextComponent(herbloreEffectInstance.getHerbloreEffect().getTranslationKey());
        lore.appendString(" (");
        lore.appendString(String.valueOf(herbloreEffectInstance.getPotency()));
        lore.appendString(") : ");
        lore.appendString(StringUtils.ticksToElapsedTime(herbloreEffectInstance.getDuration()));
        lore.mergeStyle(TextFormatting.GREEN);

        lores.add(lore);
    }

    private static String intToNumerals(int value)
    {
        StringBuilder numeralBuilder = new StringBuilder();

        while (value != 0)
        {
            if (value >= 10)
            {
                for (int i = 0; i < value / 10; i++)
                {
                    numeralBuilder.append("X");
                }
                value %= 10;
            }
            else if (value >= 5)
            {
                if (value < 9)
                {
                    for (int i = 0; i < value / 5; i++)
                    {
                        numeralBuilder.append("I");
                    }
                    value %= 5;
                }
                else
                {
                    numeralBuilder.append("IX");
                    value = 0;
                }
            }
            else if (value >= 1)
            {
                if (value < 4)
                {
                    for (int i = 0; i < value; i++)
                    {
                        numeralBuilder.append("I");
                    }
                }
                else
                {
                    numeralBuilder.append("IV");
                }
                value = 0;
            }
        }

        return numeralBuilder.toString();
    }
}
