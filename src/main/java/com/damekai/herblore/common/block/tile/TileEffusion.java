package com.damekai.herblore.common.block.tile;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.BlockEffusion;
import com.damekai.herblore.common.util.EffusionHelper;
import com.damekai.herblore.effusion.base.EffusionInstance;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

import java.util.ArrayList;
import java.util.List;

public class TileEffusion extends TileEntity implements ITickableTileEntity
{
    private EffusionInstance effusionInstance;

    public TileEffusion()
    {
        super(ModTiles.EFFUSION.get());
    }

    @Override
    public void tick()
    {
        if (getBlockState().getValue(BlockEffusion.OPEN) && effusionInstance != null)
        {
            boolean finished = effusionInstance.tick(this);
            if (finished)
            {
                if (level != null && !level.isClientSide)
                {
                    level.destroyBlock(getBlockPos(), false);
                }
            }
            else
            {
                updateRenderedFluidAmount();
            }
        }
    }

    public EffusionInstance getEffusionInstance()
    {
        return effusionInstance;
    }

    public List<BlockPos> getBlockPosInRadius(int radius, boolean includeOrigin)
    {
        BlockPos origin = getBlockPos();

        List<BlockPos> positions = new ArrayList<>();

        int j = origin.getY();

        for (int i = origin.getX() - radius; i <= origin.getX() + radius; i++)
        {
            for (int k = origin.getZ() - radius; k <= origin.getZ() + radius; k++)
            {
                BlockPos blockPos = new BlockPos(i, j, k);
                if (!includeOrigin && blockPos.equals(origin))
                {
                    continue;
                }
                positions.add(blockPos);
            }
        }

        return positions;
    }

    private void updateRenderedFluidAmount()
    {
        if (level != null)
        {
            int amount = EffusionHelper.getRenderedEffusionAmount(effusionInstance);

            BlockState blockState = getBlockState();
            if (blockState.getValue(BlockEffusion.AMOUNT) != amount)
            {
                level.setBlockAndUpdate(getBlockPos(), blockState.setValue(BlockEffusion.AMOUNT, amount));
            }
        }
    }

    @Override
    public CompoundNBT getUpdateTag()
    {
        return save(new CompoundNBT());
    }

    @Override
    public CompoundNBT save(CompoundNBT nbt)
    {
        super.save(nbt);

        if (effusionInstance != null)
        {
            nbt.put("effusion", effusionInstance.write(new CompoundNBT()));
        }

        return nbt;
    }

    @Override
    public void load(BlockState state, CompoundNBT nbt)
    {
        super.load(state, nbt);

        if (nbt.contains("effusion"))
        {
            effusionInstance = EffusionInstance.read(nbt.getCompound("effusion"));
        }
    }
}
