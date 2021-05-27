package com.damekai.herblore.common.block;

import com.damekai.herblore.common.block.tile.TileEffusion;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class BlockEffusion extends Block
{
    public BlockEffusion()
    {
        super(Block.Properties.of(Material.GLASS).sound(SoundType.GLASS).strength(0.3f).noOcclusion());
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
}
