package com.damekai.herblore.common.util;

import com.damekai.herblore.common.Herblore;
import com.google.common.collect.ImmutableMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class ProbabilitySet<T>
{
    private static final Random RANDOM = new Random();

    private final ImmutableMap<T, Float> probabilityMap;

    public ProbabilitySet(ImmutableMap<T, Float> probabilityMap)
    {
        // Throw exception if probabilities do not total to 1.
        if ((float) probabilityMap.values().stream().mapToDouble(Float::doubleValue).sum() != 1.0f)
        {
            throw new IllegalArgumentException("Probability set requires the sum of all values to be 1 in order to function properly.");
        }
        this.probabilityMap = probabilityMap;
    }

    public T roll()
    {
        float roll = RANDOM.nextFloat();

        float current = 0f;
        for (Map.Entry<T, Float> weight : probabilityMap.entrySet())
        {
            current += weight.getValue();
            if (current >= roll)
            {
                return weight.getKey();
            }
        }
        return null; // Only ever reached if this set is empty.
    }
}
