package com.damekai.herblore.effusion.base;

import com.damekai.herblore.common.block.tile.TileEffusion;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class Effusion extends ForgeRegistryEntry<Effusion>
{
    private final int color;

    public Effusion(int color)
    {
        this.color = color;
    }

    public int getColor()
    {
        return color;
    }

    public abstract void onTick(TileEffusion effusionTile);
}
