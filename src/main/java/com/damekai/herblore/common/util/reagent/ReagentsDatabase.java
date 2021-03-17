package com.damekai.herblore.common.util.reagent;

import com.damekai.herblore.common.item.ModItems;
import com.google.common.collect.ImmutableList;

public class ReagentsDatabase
{
    public static ImmutableList<Reagent> REAGENTS = new ImmutableList.Builder<Reagent>()
            .add(ModItems.WINDY_LICHEN)
            .add(ModItems.SUNSPECKLE)
            .add(ModItems.MOONSPECKLE)
            .add(ModItems.STONESTEM)
            .add(ModItems.WILLOW_WORT)
            .add(ModItems.RUMBLEROOT)
            .add(ModItems.PHANTOM_FROND)
            .add(ModItems.BREEZEBLOOM)
            .add(ModItems.DESERTS_THIRST)
            .add(ModItems.THUNDERSTAR)
            .add(ModItems.SUNSTRIDERS_SIN)
            .add(ModItems.SLAKEMOSS)
            .build();
}
