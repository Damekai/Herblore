package com.damekai.herblore.effusion.base;

import com.damekai.herblore.common.ModRegistries;
import com.damekai.herblore.common.block.tile.TileEffusion;
import net.minecraft.util.Util;
import net.minecraftforge.registries.ForgeRegistryEntry;

public abstract class Effusion extends ForgeRegistryEntry<Effusion>
{
    private String translationKey = null;

    private final int color;

    public Effusion(int color)
    {
        this.color = color;
    }

    public String getTranslationKey()
    {
        if (translationKey == null)
        {
            translationKey = Util.makeDescriptionId("effusion", ModRegistries.EFFUSIONS.getKey(this));
        }
        return translationKey;
    }

    public int getColor()
    {
        return color;
    }

    public abstract void onTick(TileEffusion effusionTile);
}
