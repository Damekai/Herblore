package com.damekai.herblore.common.data.effusion;

import com.damekai.herblore.common.data.util.JsonDeserializer;

public class EffusionConversion<I, O>
{
    private final I input;
    private final O output;

    protected EffusionConversion(I input, O output)
    {
        this.input = input;
        this.output = output;
    }

    public I getInput()
    {
        return input;
    }

    public O getOutput()
    {
        return output;
    }
}
