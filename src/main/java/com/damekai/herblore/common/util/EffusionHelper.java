package com.damekai.herblore.common.util;

import com.damekai.herblore.effusion.base.EffusionInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class EffusionHelper
{
    public static void writeEffusionTag(@Nonnull EffusionInstance effusionInstance, ItemStack stack)
    {
        CompoundNBT stackTag = stack.getOrCreateTag();

        CompoundNBT blockEntityTag = new CompoundNBT();
        blockEntityTag.put("effusion", effusionInstance.write(new CompoundNBT()));

        stackTag.put("BlockEntityTag", blockEntityTag);
    }

    public static int getRenderedEffusionAmount(EffusionInstance effusionInstance)
    {
        return (int) Math.ceil(10f * effusionInstance.getDurationRemaining() / effusionInstance.getDurationFull());
    }
}
