package com.damekai.herblore.common.util;

import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.FlaskInstance;
import com.damekai.herblore.common.flask.ModFlasks;
import com.damekai.herblore.common.item.ItemReagent;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import java.util.*;

public class FlaskHelper
{
    public static CompoundNBT makeFlaskInstanceNBT(ItemReagent... reagents)
    {
        CompoundNBT nbt = new CompoundNBT();

        Flask resultingFlask = null;
        int resultingFlaskPoints = 0;

        ItemReagent firstReagent = reagents[0];

        for (RegistryObject<Flask> flask : firstReagent.getFlaskPoints().keySet())
        {
            boolean sharedBetweenAllReagents = true;
            int sumPoints = firstReagent.getFlaskPoints().get(flask);
            for (int i = 1; i < reagents.length; i++)
            {
                ItemReagent reagent = reagents[i];
                if (!reagent.getFlaskPoints().containsKey(flask))
                {
                    sharedBetweenAllReagents = false;
                    break;
                }
                else
                {
                    sumPoints += reagent.getFlaskPoints().get(flask);
                }
            }
            if (sharedBetweenAllReagents && sumPoints > resultingFlaskPoints)
            {
                resultingFlask = flask.get();
                resultingFlaskPoints = sumPoints;
            }
        }

        FlaskInstance resultingFlaskInstance;
        if (resultingFlask != null)
        {
            resultingFlaskInstance = new FlaskInstance(resultingFlask, resultingFlaskPoints, 40); // TODO: Calculate duration.
        }
        else
        {
            resultingFlaskInstance = new FlaskInstance(ModFlasks.DEBUG_AB.get(), 0, 0); // TODO: Put some empty Flask thing here.
        }

        return resultingFlaskInstance.write(new CompoundNBT());
    }

    public static int getFlaskColor(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("flask_color"); // Returns 0 if the flask_color key is not present.
    }

    public static int getFlaskDoses(ItemStack stack)
    {
        return stack.getOrCreateTag().getInt("flask_doses");
    }

    // Modified version of PotionUtils.addPotionTooltip() from vanilla.
    @OnlyIn(Dist.CLIENT)
    public static void addFlaskTooltip(ItemStack stack, List<ITextComponent> lores)
    {
        if (!stack.getOrCreateTag().contains("flask_instance"))
        {
            return;
        }

        FlaskInstance flaskInstance = FlaskInstance.read(stack.getOrCreateTag().getCompound("flask_instance"));

        // Write doses.
        int doses = stack.getOrCreateTag().getInt("flask_doses");
        lores.add(new StringTextComponent(doses + (doses == 1 ? " Dose" : " Doses")).mergeStyle(TextFormatting.BLUE));

        // Write potency and duration.
        lores.add((new StringTextComponent(String.format("%d : %s", flaskInstance.getPotency(), StringUtils.ticksToElapsedTime(flaskInstance.getDuration())))).mergeStyle(TextFormatting.BLUE));

        // Write each FlaskEffect from this Flask.
        flaskInstance.getFlask().getFlaskEffects().forEach(
                (flaskEffect) -> lores.add((new TranslationTextComponent(flaskEffect.getTranslationKey()).mergeStyle(TextFormatting.GREEN))));
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
