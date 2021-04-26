package com.damekai.herblore.common.world;

import com.damekai.herblore.common.block.BlockPerennialPatch;
import com.damekai.herblore.common.block.ModBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;

public class FeaturePerennialPatch extends Feature<NoFeatureConfig>
{
    private final RegistryObject<Block> perennialPatchBlock;

    public FeaturePerennialPatch(Codec<NoFeatureConfig> codec, RegistryObject<Block> perennialPatchBlock)
    {
        super(codec);

        this.perennialPatchBlock = perennialPatchBlock;
    }

    @Override
    public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config)
    {
        int i = 0;

        for(int j = 0; j < 64; ++j)
        {
            BlockPos position = new BlockPos(
                    pos.getX() + rand.nextInt(8) - rand.nextInt(8),
                    pos.getY() + rand.nextInt(4) - rand.nextInt(4),
                    pos.getZ() + rand.nextInt(8) - rand.nextInt(8));
            if (world.getBlockState(position).canBeReplacedByLeaves(world, position) && world.getBlockState(position.below()).getBlock() == Blocks.GRASS_BLOCK)
            {
                world.setBlock(position, perennialPatchBlock.get().defaultBlockState(), 2);
                ++i;
            }
        }

        return i > 0;
    }
}
