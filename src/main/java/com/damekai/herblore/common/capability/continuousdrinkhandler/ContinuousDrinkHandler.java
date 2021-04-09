package com.damekai.herblore.common.capability.continuousdrinkhandler;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.util.IContinuousDrinkItem;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.fml.common.thread.EffectiveSide;

import javax.annotation.Nullable;
import java.util.Random;

public class ContinuousDrinkHandler
{
    private static final Random RANDOM = new Random();

    private int drinkTime;

    public ContinuousDrinkHandler()
    {
        drinkTime = 0;
    }

    public boolean drink(int maxDrinkTime)
    {
        if (drinkTime == maxDrinkTime)
        {
            return false;
        }

        drinkTime++;
        return true;
    }

    public int getDrinkTime()
    {
        return drinkTime;
    }

    public int finishDrink()
    {
        int finalDrinkTime = drinkTime;
        drinkTime = 0;
        return finalDrinkTime;
    }

    @Nullable
    public static ContinuousDrinkHandler getContinuousDrinkHandlerOf(LivingEntity livingEntity)
    {
        if (livingEntity == null)
        {
            return null;
        }
        return livingEntity.getCapability(CapabilityContinuousDrinkHandler.CONTINUOUS_DRINK_HANDLER_CAPABILITY).orElse(null);
    }

    public static void onUseItem(LivingEntityUseItemEvent event)
    {
        ItemStack stack = event.getItem();
        Item item = stack.getItem();
        if (item instanceof IContinuousDrinkItem)
        {
            LivingEntity livingEntity = event.getEntityLiving();
            ContinuousDrinkHandler continuousDrinkHandler = ContinuousDrinkHandler.getContinuousDrinkHandlerOf(livingEntity);
            if (continuousDrinkHandler != null)
            {
                // Increment the drink counter.
                if (!continuousDrinkHandler.drink(((IContinuousDrinkItem) item).getMaxDrinkTime(stack)))
                {
                    // Force finish drink, exit.
                    event.setDuration(1);
                    return;
                }

                // Hack the animation so it keeps going.
                if (event.getDuration() == 1)
                {
                    event.setDuration(5);
                }

                // Hack the sound effect in, if it would be needed (testing indicates that use durations of 10 or below require this).
                if (item.getUseDuration(stack) <= 10 && continuousDrinkHandler.getDrinkTime() % 4 == 0)
                {
                    livingEntity.playSound(SoundEvents.ENTITY_GENERIC_DRINK, 0.5f, RANDOM.nextFloat() * 0.1F + 0.9f);
                }
            }
        }
    }
}
