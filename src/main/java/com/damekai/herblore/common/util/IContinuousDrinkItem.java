package com.damekai.herblore.common.util;

import net.minecraft.item.ItemStack;

public interface IContinuousDrinkItem
{
    int getMaxDrinkTime(ItemStack stack);
}
