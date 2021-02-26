package com.damekai.herblore.common.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class HerbloreEffectInstance
{
    private final HerbloreEffect herbloreEffect;
    private final int potency;
    private final int duration;

    public HerbloreEffectInstance(HerbloreEffect herbloreEffect, int potency, int duration)
    {
        this.herbloreEffect = herbloreEffect;
        this.potency = potency;
        this.duration = duration;
    }

    public HerbloreEffect getHerbloreEffect() { return herbloreEffect; }

    public int getPotency() { return potency; }

    public int getDuration() { return duration; }

    public CompoundNBT write(CompoundNBT nbt)
    {
        nbt.putString("effect", herbloreEffect.getRegistryName().toString());
        nbt.putInt("potency", potency);
        nbt.putInt("duration", duration);

        return nbt;
    }

    public static HerbloreEffectInstance read(CompoundNBT nbt)
    {
        HerbloreEffect herbloreEffect = HerbloreEffect.getHerbloreEffectFromRegistry(new ResourceLocation(nbt.getString("effect")));
        int potency = nbt.getInt("potency");
        int duration = nbt.getInt("duration");

        return new HerbloreEffectInstance(herbloreEffect, potency, duration);
    }
}
