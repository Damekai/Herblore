package com.damekai.herblore.common.flask;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.util.INBTSerializable;

public class FlaskInstance
{
    private final Flask flask;
    private final int potency;
    private final int duration;

    public FlaskInstance(Flask flask, int potency, int duration)
    {
        this.flask = flask;
        this.potency = potency;
        this.duration = duration;
    }

    public Flask getFlask()
    {
        return flask;
    }

    public int getPotency()
    {
        return potency;
    }

    public int getDuration()
    {
        return duration;
    }

    public CompoundNBT write(CompoundNBT nbt)
    {
        nbt.putString("flask", flask.getRegistryName().toString());
        nbt.putInt("potency", potency);
        nbt.putInt("duration", duration);

        return nbt;
    }

    public static FlaskInstance read(CompoundNBT nbt)
    {
        Flask flask = Flask.getFlaskFromRegistry(new ResourceLocation(nbt.getString("flask")));
        return new FlaskInstance(flask, nbt.getInt("potency"), nbt.getInt("duration"));
    }
}
