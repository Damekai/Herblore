package com.damekai.herblore.common.util;

import com.damekai.herblore.common.flask.ModFlaskEffects;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.item.ItemReagent;
import com.google.common.collect.ImmutableList;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringUtils;
import net.minecraft.util.text.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class FlaskHelper
{
    public static FlaskEffectInstance makeFlaskEffectInstance(ImmutableList<ItemStack> reagents)
    {
        // Attempt to find a shared Flask Effect by generating a map that counts the number of times that Flask Effect shows up between all reagents.
        Map<FlaskEffect, MutableInt> effectCounts = new HashMap<>();
        reagents.forEach((reagent) ->
                ((ItemReagent) reagent.getItem()).getFlaskEffects().forEach((flaskEffectSupplier) ->
                {
                    FlaskEffect flaskEffect = flaskEffectSupplier.get();
                    if (effectCounts.containsKey(flaskEffect))
                    {
                        effectCounts.get(flaskEffect).increment();
                    }
                    else
                    {
                        effectCounts.put(flaskEffect, new MutableInt(1));
                    }
                }));

        // Filter out the shared effect, and if there is one, return an instance of it.
        Map.Entry<FlaskEffect, MutableInt> resultEntry = effectCounts.entrySet().stream()
                .filter((effectCount) -> effectCount.getValue().get() == reagents.size())
                .findAny()
                .orElse(null);
        if (resultEntry != null)
        {
            int potency = (int) Math.round(reagents.stream()
                    .mapToInt((reagent) -> reagent.getOrCreateTag().getInt("potency"))
                    .average()
                    .orElse(0));

            FlaskEffect flaskEffect = resultEntry.getKey();
            return new FlaskEffectInstance(flaskEffect, potency, flaskEffect.getBaseDuation()); // TODO: Design a more interesting way to determine the duration of a Flask.
        }
        else
        {
            return new FlaskEffectInstance(ModFlaskEffects.DEBUG_ALPHA.get(), 0, 0); // TODO: Change this to a "None" Flask Effect.
        }
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
