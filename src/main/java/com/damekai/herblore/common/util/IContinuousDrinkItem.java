package com.damekai.herblore.common.util;

import com.damekai.herblore.common.Herblore;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;

public interface IContinuousDrinkItem
{
    int getInitialSips();

    static void onUseItem(LivingEntityUseItemEvent event)
    {
        ItemStack stack = event.getItem();
        Item item = stack.getItem();
        if (item instanceof IContinuousDrinkItem)
        {
            if (stack.getOrCreateTag().getInt("flask_sips") == 0)
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
            if (item.getUseDuration(stack) <= 10 && stack.getOrCreateTag().getInt("drink_time") % 4 == 0)
            {
                LivingEntity livingEntity = event.getEntityLiving();
                livingEntity.playSound(SoundEvents.GENERIC_DRINK, 0.5f, livingEntity.level.getRandom().nextFloat() * 0.1F + 0.9f);
            }

            stack.getOrCreateTag().putInt("flask_sips", stack.getOrCreateTag().getInt("flask_sips") - 1);
            stack.getOrCreateTag().putInt("drink_time", stack.getOrCreateTag().getInt("drink_time") + 1);
        }
    }
}
