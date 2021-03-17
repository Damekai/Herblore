package com.damekai.herblore.common.util.reagent;

import com.google.common.collect.ImmutableMap;

public class TierNames
{
    public static final ImmutableMap<Integer, String> TIER_NAMES =
            new ImmutableMap.Builder<Integer, String>()
                    .put(1, "perennial")
                    .put(2, "milled")
                    .put(3, "concentrated")
                    .put(4, "refined")
                    .put(5, "alchemical")
                    .build();

}
