package com.damekai.herblore.effusion.base;

import com.damekai.herblore.common.ModRegistries;
import com.damekai.herblore.common.block.tile.TileEffusion;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import java.util.function.Supplier;

public class EffusionInstance
{
    private final Supplier<Effusion> effusion;
    private final int durationFull;
    private int durationRemaining;

    public EffusionInstance(Supplier<Effusion> effusion, int durationFull, int durationRemaining)
    {
        this.effusion = effusion;
        this.durationFull = durationFull;
        this.durationRemaining = durationRemaining;
    }

    public EffusionInstance(Supplier<Effusion> effusion, int duration)
    {
        this(effusion, duration, duration);
    }

    public Effusion getEffusion()
    {
        return effusion.get();
    }

    public int getDurationFull()
    {
        return durationFull;
    }

    public int getDurationRemaining()
    {
        return durationRemaining;
    }

    public boolean tick(TileEffusion effusionTile)
    {
        effusion.get().onTick(effusionTile);
        durationRemaining--;
        return durationRemaining <= 0;
    }

    public CompoundNBT write(CompoundNBT nbt)
    {
        nbt.putString("effusion", effusion.get().getRegistryName().toString());
        nbt.putInt("duration_full", durationFull);
        nbt.putInt("duration_remaining", durationRemaining);

        return nbt;
    }

    public static EffusionInstance read(CompoundNBT nbt)
    {
        return new EffusionInstance(
                () -> ModRegistries.EFFUSIONS.getValue(new ResourceLocation(nbt.getString("effusion"))),
                nbt.getInt("duration_full"),
                nbt.getInt("duration_remaining")
        );
    }
}
