package com.damekai.herblore.common.block;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.tile.TileAthanor;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class BlockAthanor extends Block
{
    public static final IntegerProperty STAGE = IntegerProperty.create("stage", 0, 3);

    public BlockAthanor()
    {
        super(Block.Properties.create(Material.ROCK).sound(SoundType.STONE).hardnessAndResistance(2.0f).notSolid());
        this.setDefaultState(this.getStateContainer().getBaseState().with(STAGE, 0));
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    public TileEntity createTileEntity(BlockState blockState, IBlockReader world)
    {
        return new TileAthanor();
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
    {
        super.animateTick(state, world, pos, rand);

        if (state.get(STAGE) >= 1)
        {
            double blockPosX = pos.getX();
            double blockPosY = pos.getY();
            double blockPosZ = pos.getZ();

            // Fire particle spawn.
            double fireParticleSpawnX = blockPosX + 0.5d + nextDoubleInRange(rand, -0.15d, 0.15d);
            double fireParticleSpawnY = blockPosY + 0.3d;
            double fireParticleSpawnZ = blockPosZ + 0.5d + nextDoubleInRange(rand, -0.15d, 0.15d);
            world.addParticle(ParticleTypes.FLAME, fireParticleSpawnX, fireParticleSpawnY, fireParticleSpawnZ, 0, 0.0075, 0);

            // Bubble particle spawn when this brew is finished.
            if (state.get(STAGE) == 3) {
                if (rand.nextDouble() < 0.4d)
                {
                    world.playSound(blockPosX + 0.5d, blockPosY + 1d, blockPosZ + 0.5d, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.AMBIENT, 2.5f, 0.5f, false);
                }
                double bubbleParticleSpawnX = blockPosX + 0.5d + nextDoubleInRange(rand, -0.05d, 0.05d);
                double bubbleParticleSpawnY = blockPosY + 1.2d + nextDoubleInRange(rand, -0.05, 0.1);
                double bubbleParticleSpawnZ = blockPosZ + 0.5d + nextDoubleInRange(rand, -0.05d, 0.05d);
                world.addParticle(ParticleTypes.BUBBLE_POP, bubbleParticleSpawnX, bubbleParticleSpawnY, bubbleParticleSpawnZ, 0, 0, 0);
            }
        }
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit)
    {
        if (!world.isRemote)
        {
            Herblore.LOGGER.debug("onBlockActivated");
            TileEntity tileEntity = world.getTileEntity(pos);
            if (tileEntity instanceof TileAthanor)
            {
                return ((TileAthanor)tileEntity).attemptUse(state, player, player.getHeldItem(hand));
            }
        }
        return ActionResultType.CONSUME;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
    {
        super.fillStateContainer(builder);
        builder.add(STAGE);
    }

    private static double nextDoubleInRange(Random rand, double min, double max)
    {
        return min + (max - min) * rand.nextDouble();
    }

}
