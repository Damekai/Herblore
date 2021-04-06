package com.damekai.herblore.common.herbloreeffect.base;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.ModRegistries;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class HerbloreEffectInstance
{
    private final Supplier<HerbloreEffect> herbloreEffect;
    private final int amplifier;
    private int durationFull;
    private int durationRemaining;
    private CompoundNBT tag;

    public HerbloreEffectInstance(Supplier<HerbloreEffect> herbloreEffect)
    {
        this.herbloreEffect = herbloreEffect;
        this.amplifier = 0;
        this.durationFull = 0;
        this.durationRemaining = 0;
        this.tag = null;
    }

    /**
     * Used for Herblore Effects not intended to have a duration (e.g. instant "on apply" effects).
     */
    public HerbloreEffectInstance(Supplier<HerbloreEffect> herbloreEffect, int amplifier)
    {
        this.herbloreEffect = herbloreEffect;
        this.amplifier = amplifier;
        this.durationFull = 0;
        this.durationRemaining = 0;
        this.tag = null;
    }

    public HerbloreEffectInstance(Supplier<HerbloreEffect> herbloreEffect, int amplifier, int duration)
    {
        this.herbloreEffect = herbloreEffect;
        this.amplifier = amplifier;
        this.durationFull = duration;
        this.durationRemaining = duration;
        this.tag = null;
    }

    /**
     * Private constructor for use when deserializing. Includes parameter for remaining duration and serialized data.
     */
    private HerbloreEffectInstance(Supplier<HerbloreEffect> herbloreEffect, int amplifier, int durationFull, int durationRemaining, CompoundNBT tag)
    {
        this.herbloreEffect = herbloreEffect;
        this.amplifier = amplifier;
        this.durationFull = durationFull;
        this.durationRemaining = durationRemaining;
        this.tag = tag;
    }

    public HerbloreEffect getHerbloreEffect()
    {
        return herbloreEffect.get();
    }

    public int getAmplifier()
    {
        return amplifier;
    }

    /**
     * Sets both the full and remaining duration to the specified value.
     */
    public void setDuration(int duration)
    {
        durationFull = duration;
        durationRemaining = duration;
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

    public HerbloreEffectInstance copy()
    {
        return new HerbloreEffectInstance(herbloreEffect, amplifier, durationFull, durationRemaining, tag != null ? tag.copy() : null);
    }

    public CompoundNBT write(CompoundNBT nbt)
    {
        nbt.putString("herblore_effect", ModRegistries.HERBLORE_EFFECTS.getKey(herbloreEffect.get()).toString());
        nbt.putInt("amplifier", amplifier);
        nbt.putInt("duration_full", durationFull);
        nbt.putInt("duration_remaining", durationRemaining);
        if (tag != null)
        {
            nbt.put("tag", tag);
        }

        return nbt;
    }

    public static HerbloreEffectInstance read(CompoundNBT nbt)
    {
        return new HerbloreEffectInstance(
                () -> ModRegistries.HERBLORE_EFFECTS.getValue(new ResourceLocation(nbt.getString("herblore_effect"))),
                nbt.getInt("amplifier"),
                nbt.getInt("duration_full"),
                nbt.getInt("duration_remaining"),
                (CompoundNBT) nbt.get("tag"));
    }
}
