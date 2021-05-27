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

    public static List<BlockPos> getBlockPosInRadius(BlockPos origin, int radius, boolean includeOrigin)
    {
        List<BlockPos> positions = new ArrayList<>();

        int j = origin.getY();

        for (int i = origin.getX() - radius; i <= origin.getX() + radius; i++)
        {
            for (int k = origin.getZ() - radius; k <= origin.getZ() + radius; k++)
            {
                BlockPos blockPos = new BlockPos(i, j, k);
                if (!includeOrigin && blockPos.equals(origin))
                {
                    continue;
                }
                positions.add(blockPos);
            }
        }

        return positions;
    }
}
