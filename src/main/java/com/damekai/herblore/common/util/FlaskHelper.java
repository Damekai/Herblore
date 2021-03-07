package com.damekai.herblore.common.util;

import com.damekai.herblore.common.flask.ModFlaskEffects;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.item.ItemReagent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import java.util.*;

public class FlaskHelper
{
    public static FlaskEffectInstance makeFlaskEffectInstance(ItemReagent... reagents)
    {
        FlaskEffect resultingFlaskEffect = null;
        int resultingFlaskEffectPoints = 0;

        ItemReagent firstReagent = reagents[0];

        for (RegistryObject<FlaskEffect> flaskEffect : firstReagent.getFlaskEffectWeights().getElements())
        {
            boolean sharedBetweenAllReagents = true;
            int sumPoints = firstReagent.getFlaskEffectWeights().getWeight(flaskEffect);
            for (int i = 1; i < reagents.length; i++)
            {
                ItemReagent reagent = reagents[i];
                if (!reagent.getFlaskEffectWeights().contains(flaskEffect))
                {
                    sharedBetweenAllReagents = false;
                    break;
                }
                else
                {
                    sumPoints += reagent.getFlaskEffectWeights().getWeight(flaskEffect);
                }
            }
            if (sharedBetweenAllReagents && sumPoints > resultingFlaskEffectPoints)
            {
                resultingFlaskEffect = flaskEffect.get();
                resultingFlaskEffectPoints = sumPoints;
            }
        }

        FlaskEffectInstance resultingFlaskEffectInstance;
        if (resultingFlaskEffect != null)
        {
            resultingFlaskEffectInstance = new FlaskEffectInstance(resultingFlaskEffect, resultingFlaskEffectPoints, 100); // TODO: Calculate duration.
        }
        else
        {
            resultingFlaskEffectInstance = new FlaskEffectInstance(ModFlaskEffects.DEBUG_ALPHA.get(), 0, 0); // TODO: Put some empty Flask thing here.
        }

        return resultingFlaskEffectInstance;
    }

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

        FlaskEffectInstance flaskEffectInstance = FlaskEffectInstance.read(stack.getOrCreateTag().getCompound("flask_effect_instance"));

        // Write doses.
        int doses = stack.getOrCreateTag().getInt("flask_doses");
        lores.add(new StringTextComponent(doses + (doses == 1 ? " Dose" : " Doses")).mergeStyle(TextFormatting.BLUE));

        // Write potency and duration.
        lores.add((new StringTextComponent(String.format("Potency %d (%s)", flaskEffectInstance.getPotency(), StringUtils.ticksToElapsedTime(flaskEffectInstance.getDurationFull())))).mergeStyle(TextFormatting.BLUE));
    }
}
