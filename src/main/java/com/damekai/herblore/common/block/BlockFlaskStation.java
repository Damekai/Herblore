package com.damekai.herblore.common.block;

import com.damekai.herblore.common.block.tile.TileFlaskStation;
import com.damekai.herblore.common.container.ContainerFlaskStation;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.monster.piglin.PiglinTasks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;
import java.util.Random;

public class BlockFlaskStation extends Block
{
    protected BlockFlaskStation()
    {
        super(Block.Properties.of(Material.WOOD).sound(SoundType.WOOD).strength(1.0f).noOcclusion());
    }

    @Override
    public boolean hasTileEntity(BlockState state)
    {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world)
    {
        return new TileFlaskStation();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult hit)
    {
        if (!world.isClientSide)
        {
            TileEntity tile = world.getBlockEntity(pos);
            if (tile instanceof TileFlaskStation)
            {
                INamedContainerProvider containerProvider = new INamedContainerProvider()
                {
                    @Override
                    public ITextComponent getDisplayName()
                    {
                        return new TranslationTextComponent("block.herblore.flask_station");
                    }

                    @Nullable
                    @Override
                    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity playerEntity)
                    {
                        return new ContainerFlaskStation(i, world, pos, inventory);
                    }
                };
                NetworkHooks.openGui((ServerPlayerEntity) playerEntity, containerProvider, tile.getBlockPos());
            }
            else
            {
                throw new IllegalStateException("Missing named container provider.");
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState state, World world, BlockPos pos, Random rand)
    {
        super.animateTick(state, world, pos, rand);

        TileFlaskStation flaskStationTile = (TileFlaskStation) world.getBlockEntity(pos);

        if (flaskStationTile != null)
        {
            double blockPosX = pos.getX();
            double blockPosY = pos.getY();
            double blockPosZ = pos.getZ();

            if (flaskStationTile.getElapsedCookTime() > 0)
            {
                if (rand.nextDouble() < 0.4d)
                {
                    world.playLocalSound(blockPosX + 0.5d, blockPosY + 1d, blockPosZ + 0.5d, SoundEvents.BUBBLE_COLUMN_BUBBLE_POP, SoundCategory.BLOCKS, 2f, rand.nextFloat() * 0.1F + 0.5f, false);
                }
            }
        }
    }
}
