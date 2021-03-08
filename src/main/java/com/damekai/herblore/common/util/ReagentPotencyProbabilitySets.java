package com.damekai.herblore.common.util;

import com.google.common.collect.ImmutableMap;

public abstract class ReagentPotencyProbabilitySets
{
    public static final ProbabilitySet<Integer> WILD_REAGENT_POTENCY_PROBABILITIES = new ProbabilitySet<>(
            new ImmutableMap.Builder<Integer, Float>()
                    .put(1, 0.20f)
                    .put(2, 0.20f)
                    .put(3, 0.20f)
                    .put(4, 0.15f)
                    .put(5, 0.1f)
                    .put(6, 0.05f)
                    .put(7, 0.04f)
                    .put(8, 0.03f)
                    .put(9, 0.02f)
                    .put(10, 0.01f)
                    .build()
    );
}
