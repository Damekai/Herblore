package com.damekai.herblore.common.item.effusion;

import com.damekai.herblore.common.data.ModAssetManagers;
import com.damekai.herblore.common.data.effusion.EffusionItemResult;
import com.damekai.herblore.common.item.effusion.base.ItemEffusion;
import com.damekai.herblore.common.util.EffusionHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class ItemEffusionCrumblemist extends ItemEffusion
{
    private static final int RADIUS = 4;
    private static final float CRUMBLE_CHANCE = 0.05f;

    public ItemEffusionCrumblemist()
    {
        super(2400);
    }

    @Override
    public void tick(ItemStack itemStack, World world, BlockPos blockPos)
    {
        List<BlockPos> posInRange = EffusionHelper.getBlockPosInRadius(blockPos, RADIUS, false);
        if (posInRange.size() == 0)
        {
            return;
        }

        if (!world.isClientSide)
        {
            // Roll to see if a block gets affected.
            if (world.random.nextFloat() <= CRUMBLE_CHANCE)
            {
                // Get map of valid blocks.
                ImmutableMap<Block, ImmutableList<EffusionItemResult>> table = ModAssetManagers.CRUMBLEMIST_TABLE.getTable();

                // Reduce positions to only include valid blocks.
                List<BlockPos> validPos = posInRange.stream()
                        .filter((pos) -> table.containsKey(world.getBlockState(pos).getBlock()))
                        .collect(Collectors.toList());

                if (!validPos.isEmpty())
                {
                    // Choose a random valid block to crumble.
                    BlockPos crumblePos = validPos.get(ThreadLocalRandom.current().nextInt(0, validPos.size()));

                    // Get the drops for that block.
                    ImmutableList<EffusionItemResult> outputs = table.get(world.getBlockState(crumblePos).getBlock());

                    // Destroy the block.
                    world.destroyBlock(crumblePos, false);

                    // Drop the appropriate items.
                    outputs.forEach((output) -> world.addFreshEntity(new ItemEntity(world, crumblePos.getX() + 0.5d, crumblePos.getY() + 0.5d, crumblePos.getZ() + 0.5d, output.createItemStack())));
                }
            }
        }
    }
}
