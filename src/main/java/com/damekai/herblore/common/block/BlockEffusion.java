package com.damekai.herblore.common.block;

import com.damekai.herblore.common.block.tile.TileEffusion;
import com.damekai.herblore.common.util.EffusionHelper;
import com.damekai.herblore.effusion.base.EffusionInstance;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockEffusion extends Block
{
    public static final BooleanProperty OPEN = BooleanProperty.create("effusion_open");
    public static final IntegerProperty AMOUNT = IntegerProperty.create("effusion_amount", 1, 10);

    public BlockEffusion()
    {
        super(Block.Properties.of(Material.GLASS).sound(SoundType.GLASS).strength(0.3f).noOcclusion());
        this.registerDefaultState(this.stateDefinition.any().setValue(OPEN, false).setValue(AMOUNT, 10));
    }

    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState blockState, World world, BlockPos blockPos, PlayerEntity playerEntity, Hand hand, BlockRayTraceResult blockRayTraceResult)
    {
        world.setBlockAndUpdate(blockPos, blockState.setValue(OPEN, !blockState.getValue(OPEN)));

        if (blockState.getValue(OPEN))
        {
            world.playSound(playerEntity, blockPos, SoundEvents.BOTTLE_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }
        else
        {
            world.playSound(playerEntity, blockPos, SoundEvents.BOTTLE_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
        }

        return ActionResultType.sidedSuccess(world.isClientSide);
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
        return new TileEffusion();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
    {
        super.createBlockStateDefinition(builder);
        builder.add(OPEN, AMOUNT);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext blockItemUseContext)
    {
        BlockState blockState = defaultBlockState();

        CompoundNBT nbt = blockItemUseContext.getItemInHand().getOrCreateTag();
        if (nbt.contains("BlockEntityTag"))
        {
            CompoundNBT blockEntityTag = nbt.getCompound("BlockEntityTag");
            if (blockEntityTag.contains("effusion"))
            {
                EffusionInstance effusionInstance = EffusionInstance.read(blockEntityTag.getCompound("effusion"));
                blockState = blockState.setValue(AMOUNT, EffusionHelper.getRenderedEffusionAmount(effusionInstance));
            }
        }

        return blockState;
    }
}
