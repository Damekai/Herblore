package com.damekai.herblore.common.util;

import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class EffusionHelper
{
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
