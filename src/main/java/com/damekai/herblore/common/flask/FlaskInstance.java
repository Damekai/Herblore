package com.damekai.herblore.common.flask;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class FlaskInstance
{
    private final Flask flask;
    private final int potency;
    private final int durationFull;
    private int durationRemaining;
    private CompoundNBT tag;

    /**
     * Used for Flasks with no duration.
     */
    public FlaskInstance(Flask flask, int potency)
    {
        this.flask = flask;
        this.potency = potency;
        this.durationFull = 0;
        this.durationRemaining = 0;
        this.tag = null;
    }

    public FlaskInstance(Flask flask, int potency, int duration)
    {
        this.flask = flask;
        this.potency = potency;
        this.durationFull = duration;
        this.durationRemaining = duration;
        this.tag = null;
    }

    /**
     * Private constructor for use when deserializing. Includes parameter for remaining duration and serialized data.
     */
    private FlaskInstance(Flask flask, int potency, int durationFull, int durationRemaining, CompoundNBT tag)
    {
        this.flask = flask;
        this.potency = potency;
        this.durationFull = durationFull;
        this.durationRemaining = durationRemaining;
        this.tag = tag;
    }

    public Flask getFlask()
    {
        return flask;
    }

    public int getPotency()
    {
        return potency;
    }

    public int getDurationFull()
    {
        return durationFull;
    }

    public int getDurationRemaining()
    {
        return durationRemaining;
    }

    /**
     * @return True if the duration remaining is decreased to 0, false otherwise.
     */
    public boolean decrementDuration()
    {
        durationRemaining = Math.max(0, durationRemaining - 1);
        return durationRemaining == 0;
    }

    @Nonnull
    public CompoundNBT getOrCreateTag()
    {
        if (tag == null)
        {
            tag = new CompoundNBT();
        }
        return tag;
    }

    public CompoundNBT write(CompoundNBT nbt)
    {
        nbt.putString("flask", flask.getRegistryName().toString());
        nbt.putInt("potency", potency);
        nbt.putInt("duration_full", durationFull);
        nbt.putInt("duration_remaining", durationRemaining);
        if (tag != null)
        {
            nbt.put("tag", tag);
        }

        return nbt;
    }

    public static FlaskInstance read(CompoundNBT nbt)
    {
        return new FlaskInstance(
                Flask.getFlaskFromRegistry(new ResourceLocation(nbt.getString("flask"))),
                nbt.getInt("potency"),
                nbt.getInt("duration_full"),
                nbt.getInt("duration_remaining"),
                (CompoundNBT) nbt.get("tag"));
    }
}
