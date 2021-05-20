package com.damekai.herblore.common.item.effusion;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.data.ModAssetManagers;
import com.damekai.herblore.common.item.effusion.base.ItemEffusion;
import com.damekai.herblore.common.util.EffusionHelper;
import com.google.common.collect.ImmutableMap;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SoundType;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
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
                ImmutableMap<Block, Result> results = ModAssetManagers.CRUMBLEMIST_LOOT_TABLE.getResultsMap();

                // Reduce positions to only include valid blocks.
                List<BlockPos> validPos = posInRange.stream()
                        .filter((pos) -> results.containsKey(world.getBlockState(pos).getBlock()))
                        .collect(Collectors.toList());

                if (!validPos.isEmpty())
                {
                    // Choose a random valid block to crumble.
                    BlockPos crumblePos = validPos.get(ThreadLocalRandom.current().nextInt(0, validPos.size()));

                    Result result = results.get(world.getBlockState(crumblePos).getBlock());
                    Item item = result.item.get();
                    int count = ThreadLocalRandom.current().nextInt(result.minCount, result.maxCount);

                    SoundType soundType = world.getBlockState(crumblePos).getSoundType();
                    world.playSound(null, crumblePos, soundType.getBreakSound(), SoundCategory.BLOCKS, (soundType.getVolume() + 1.0F) / 2.0F, soundType.getPitch() * 0.8F);

                    world.setBlockAndUpdate(crumblePos, Blocks.AIR.defaultBlockState());

                    world.addFreshEntity(new ItemEntity(world, crumblePos.getX() + 0.5d, crumblePos.getY() + 0.5d, crumblePos.getZ() + 0.5d, new ItemStack(item, count)));
                }
            }


        }
    }

    public static class Result
    {
        private final Supplier<Block> block;
        private final Supplier<Item> item;
        private final int minCount;
        private final int maxCount;

        public Result(Supplier<Block> block, Supplier<Item> item, int minCount, int maxCount)
        {
            this.block = block;
            this.item = item;
            this.minCount = minCount;
            this.maxCount = maxCount;
        }

        public Supplier<Block> getBlock()
        {
            return block;
        }
    }
}
