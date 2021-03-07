package com.damekai.herblore.common.flask.base;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;

public class FlaskEffectInstance
{
    private final FlaskEffect flaskEffect;
    private final int potency;
    private final int durationFull;
    private int durationRemaining;
    private CompoundNBT tag;

    /**
     * Used for Flasks with no duration.
     */
    public FlaskEffectInstance(FlaskEffect flaskEffect, int potency)
    {
        this.flaskEffect = flaskEffect;
        this.potency = potency;
        this.durationFull = 0;
        this.durationRemaining = 0;
        this.tag = null;
    }

    public FlaskEffectInstance(FlaskEffect flaskEffect, int potency, int duration)
    {
        this.flaskEffect = flaskEffect;
        this.potency = potency;
        this.durationFull = duration;
        this.durationRemaining = duration;
        this.tag = null;
    }

    /**
     * Private constructor for use when deserializing. Includes parameter for remaining duration and serialized data.
     */
    private FlaskEffectInstance(FlaskEffect flaskEffect, int potency, int durationFull, int durationRemaining, CompoundNBT tag)
    {
        this.flaskEffect = flaskEffect;
        this.potency = potency;
        this.durationFull = durationFull;
        this.durationRemaining = durationRemaining;
        this.tag = tag;
    }

    public FlaskEffect getFlaskEffect()
    {
        return flaskEffect;
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
        nbt.putString("flask_effect", flaskEffect.getRegistryName().toString());
        nbt.putInt("potency", potency);
        nbt.putInt("duration_full", durationFull);
        nbt.putInt("duration_remaining", durationRemaining);
        if (tag != null)
        {
            nbt.put("tag", tag);
        }

        return nbt;
    }

    public static FlaskEffectInstance read(CompoundNBT nbt)
    {
        return new FlaskEffectInstance(
                FlaskEffect.getFlaskEffectFromRegistry(new ResourceLocation(nbt.getString("flask_effect"))),
                nbt.getInt("potency"),
                nbt.getInt("duration_full"),
                nbt.getInt("duration_remaining"),
                (CompoundNBT) nbt.get("tag"));
    }
}
