package com.damekai.herblore.common.util;

import com.damekai.herblore.common.effect.HerbloreEffect;
import com.damekai.herblore.common.effect.HerbloreEffectInstance;
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
    public static ListNBT makeFlaskNBTList(ItemReagent... reagents)
    {
        ListNBT nbtList = new ListNBT();

        Map<HerbloreEffect, MutableInt> combinedHerbloreEffectPointsMap = new HashMap<>();

        for (ItemReagent reagent : reagents)
        {
            Map<RegistryObject<HerbloreEffect>, Integer> herbloreEffectPointsMap = reagent.getHerbloreEffectPoints();

            for (Map.Entry<RegistryObject<HerbloreEffect>, Integer> entry : herbloreEffectPointsMap.entrySet())
            {
                HerbloreEffect herbloreEffect = entry.getKey().get();
                int herbloreEffectPoints = entry.getValue();

                if (!combinedHerbloreEffectPointsMap.containsKey(herbloreEffect))
                {
                    combinedHerbloreEffectPointsMap.put(herbloreEffect, new MutableInt(herbloreEffectPoints));
                }
                else
                {
                    combinedHerbloreEffectPointsMap.get(herbloreEffect).value += herbloreEffectPoints;
                }
            }
        }

        for (Map.Entry<HerbloreEffect, MutableInt> entry : combinedHerbloreEffectPointsMap.entrySet())
        {
            if (entry.getValue().value > 1)
            {
                HerbloreEffectInstance herbloreEffectInstance = new HerbloreEffectInstance(entry.getKey(), entry.getValue().value, 40); // TODO: Calculate duration.
                nbtList.add(herbloreEffectInstance.write(new CompoundNBT()));
            }
        }

        return nbtList;
    }

    // Modified version of PotionUtils.addPotionTooltip() from vanilla.
    @OnlyIn(Dist.CLIENT)
    public static void addFlaskTooltip(ItemStack stack, List<ITextComponent> lores)
    {
        ListNBT nbtList = stack.getOrCreateTag().getList("flask_effects", Constants.NBT.TAG_COMPOUND);

        for (INBT tag : nbtList)
        {
            HerbloreEffectInstance herbloreEffectInstance = HerbloreEffectInstance.read((CompoundNBT) tag);

            IFormattableTextComponent lore = new TranslationTextComponent(herbloreEffectInstance.getHerbloreEffect().getTranslationKey());
            lore.appendString(" (");
            lore.appendString(String.valueOf(herbloreEffectInstance.getPotency()));
            lore.appendString(") : ");
            lore.appendString(StringUtils.ticksToElapsedTime(herbloreEffectInstance.getDuration()));
            lore.mergeStyle(TextFormatting.GREEN);

            lores.add(lore);
        }
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
