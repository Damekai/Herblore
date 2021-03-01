package com.damekai.herblore.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class WeightedSet<T>
{
    private static final Random RANDOM = new Random();

    private final Map<T, MutableInt> weights;

    public WeightedSet()
    {
        weights = new HashMap<>();
    }

    public int add(T element, int weight)
    {
        MutableInt value = new MutableInt(weight);
        MutableInt replaced = weights.put(element, value);
        return replaced != null ? replaced.get() : value.get();
    }

    public boolean contains(T element)
    {
        return weights.containsKey(element);
    }

    public int getWeight(T element)
    {
        MutableInt retrieved = weights.get(element);
        return retrieved != null ? retrieved.get() : 0;
    }

    public Set<T> getElements()
    {
        return weights.keySet();
    }

    public int remove(T element)
    {
        MutableInt removed = weights.remove(element);
        return removed != null ? removed.get() : 0;
    }

    public int getTotalWeight()
    {
        return weights.values().stream().mapToInt(MutableInt::get).sum();
    }

    public T getWeightedRandomEntry()
    {
        int roll = RANDOM.nextInt(getTotalWeight());

        int current = 0;
        for (Map.Entry<T, MutableInt> weight: weights.entrySet())
        {
            current += weight.getValue().get();
            if (current > roll)
            {
                return weight.getKey();
            }
        }
        return null; // Only ever reached if this set is empty.
    }
}
