package com.damekai.herblore.common.util;

import com.damekai.herblore.common.block.tile.TileEffusion;
import com.damekai.herblore.effusion.base.EffusionInstance;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockDisplayReader;
import net.minecraft.world.World;

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

    public static float getEffusionItemFillPredicate(ItemStack stack, ClientWorld clientWorld, LivingEntity livingEntity)
    {
        CompoundNBT nbt = stack.getOrCreateTag();
        if (nbt.contains("BlockEntityTag"))
        {
            CompoundNBT blockEntityTag = nbt.getCompound("BlockEntityTag");
            if (blockEntityTag.contains("effusion"))
            {
                EffusionInstance effusionInstance = EffusionInstance.read(blockEntityTag.getCompound("effusion"));
                return getRenderedEffusionAmount(effusionInstance);
            }
        }
        return 10f; // Default return.
    }

    public static int getEffusionBlockColor(BlockState blockState, IBlockDisplayReader blockDisplayReader, BlockPos blockPos, int tintIndex)
    {
        if (blockPos != null && tintIndex == 1)
        {
            World world = Minecraft.getInstance().level;
            if (world != null)
            {
                TileEffusion effusionTile = (TileEffusion) world.getBlockEntity(blockPos);
                if (effusionTile != null)
                {
                    EffusionInstance effusionInstance = effusionTile.getEffusionInstance();
                    if (effusionInstance != null)
                    {
                        return effusionInstance.getEffusion().getColor();
                    }
                }
            }
        }
        return 0xFFFFFF; // Default return (no tint).
    }

    public static int getEffusionItemColor(ItemStack stack, int tintIndex)
    {
        if (tintIndex == 0)
        {
            CompoundNBT nbt = stack.getOrCreateTag();
            if (nbt.contains("BlockEntityTag"))
            {
                CompoundNBT blockEntityTag = nbt.getCompound("BlockEntityTag");
                if (blockEntityTag.contains("effusion"))
                {
                    EffusionInstance effusionInstance = EffusionInstance.read(blockEntityTag.getCompound("effusion"));
                    return effusionInstance.getEffusion().getColor();
                }
            }
        }
        return 0xFFFFFF; // Default return (no tint).
    }
}
