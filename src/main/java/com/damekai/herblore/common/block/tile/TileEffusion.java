package com.damekai.herblore.common.block.tile;

import com.damekai.herblore.common.Herblore;
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
        if (effusionInstance != null)
        {
            if (effusionInstance.tick(this))
            {
                if (level != null)
                {
                    level.destroyBlock(getBlockPos(), false);
                }
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

    @Override
    public void load(BlockState state, CompoundNBT nbt)
    {
        Herblore.LOGGER.debug("deserializing");

        super.load(state, nbt);

        if (nbt.contains("effusion"))
        {
            effusionInstance = EffusionInstance.read(nbt.getCompound("effusion"));

            Herblore.LOGGER.debug("deserialized");
        }
    }
}
