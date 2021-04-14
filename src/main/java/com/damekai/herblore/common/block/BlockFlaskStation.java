package com.damekai.herblore.common.block;

import com.damekai.herblore.common.block.tile.TileFlaskStation;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
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
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockFlaskStation extends ContainerBlock
{
    protected BlockFlaskStation()
    {
        super(Block.Properties.create(Material.WOOD).sound(SoundType.WOOD).hardnessAndResistance(1.0f).notSolid());
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(IBlockReader world)
    {
        return new TileFlaskStation();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult hit)
    {
        if (!world.isRemote)
        {
            TileEntity tile = world.getTileEntity(pos);
            if (tile instanceof TileFlaskStation)
            {
                NetworkHooks.openGui((ServerPlayerEntity) playerEntity, (TileFlaskStation) tile, tile.getPos());
            } else
            {
                throw new IllegalStateException("Our named container provider is missing!");
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    @SuppressWarnings("deprecation")
    public BlockRenderType getRenderType(BlockState state)
    {
        return BlockRenderType.MODEL;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
    {
        super.animateTick(state, world, pos, rand);

        TileFlaskStation flaskStationTile = (TileFlaskStation) world.getTileEntity(pos);

        if (flaskStationTile != null)
        {
            double blockPosX = pos.getX();
            double blockPosY = pos.getY();
            double blockPosZ = pos.getZ();

            if (flaskStationTile.getElapsedCookTime() > 0)
            {
                if (rand.nextDouble() < 0.4d)
                {
                    world.playSound(blockPosX + 0.5d, blockPosY + 1d, blockPosZ + 0.5d, SoundEvents.BLOCK_BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 2f, rand.nextFloat() * 0.1F + 0.5f, false);
                }
            }
        }
    }
}
