package com.damekai.herblore.common.block;

import com.damekai.herblore.common.block.tile.TileDiffuser;
import com.damekai.herblore.common.block.tile.TileFlaskStation;
import com.damekai.herblore.common.container.ContainerDiffuser;
import com.damekai.herblore.common.container.ContainerFlaskStation;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.network.NetworkHooks;

import javax.annotation.Nullable;

public class BlockDiffuser extends Block
{
    public BlockDiffuser()
    {
        super(AbstractBlock.Properties.of(Material.METAL)
                .sound(SoundType.METAL)
                .harvestTool(ToolType.PICKAXE)
                .strength(1.0f)
                .noOcclusion());
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
        return new TileDiffuser();
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult)
    {
        if (!world.isClientSide)
        {
            TileEntity tile = world.getBlockEntity(blockPos);
            if (tile instanceof TileDiffuser)
            {
                INamedContainerProvider containerProvider = new INamedContainerProvider()
                {
                    @Override
                    public ITextComponent getDisplayName()
                    {
                        return new TranslationTextComponent("block.herblore.diffuser");
                    }

                    @Nullable
                    @Override
                    public Container createMenu(int i, PlayerInventory inventory, PlayerEntity playerEntity)
                    {
                        return new ContainerDiffuser(i, world, blockPos, inventory);
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
}
