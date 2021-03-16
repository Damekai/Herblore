package com.damekai.herblore.common.util;

import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.item.ModItems;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import net.minecraftforge.fml.RegistryObject;

public class ReagentDatabase
{
    public static final ReagentData WINDY_LICHEN = new ReagentData("windy_lichen",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_WINDY_LICHEN)
                    .put(2, ModItems.MILLED_WINDY_LICHEN)
                    .put(3, ModItems.CONCENTRATED_WINDY_LICHEN)
                    .put(4, ModItems.REFINED_WINDY_LICHEN)
                    .put(5, ModItems.ALCHEMICAL_WINDY_LICHEN)
                    .build());

    public static final ReagentData SUNSPECKLE = new ReagentData("sunspeckle",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_SUNSPECKLE)
                    .put(2, ModItems.MILLED_SUNSPECKLE)
                    .put(3, ModItems.CONCENTRATED_SUNSPECKLE)
                    .put(4, ModItems.REFINED_SUNSPECKLE)
                    .put(5, ModItems.ALCHEMICAL_SUNSPECKLE)
                    .build());

    public static final ReagentData MOONSPECKLE = new ReagentData("moonspeckle",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_MOONSPECKLE)
                    .put(2, ModItems.MILLED_MOONSPECKLE)
                    .put(3, ModItems.CONCENTRATED_MOONSPECKLE)
                    .put(4, ModItems.REFINED_MOONSPECKLE)
                    .put(5, ModItems.ALCHEMICAL_MOONSPECKLE)
                    .build());

    public static final ReagentData STONESTEM = new ReagentData("stonestem",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_STONESTEM)
                    .put(2, ModItems.MILLED_STONESTEM)
                    .put(3, ModItems.CONCENTRATED_STONESTEM)
                    .put(4, ModItems.REFINED_STONESTEM)
                    .put(5, ModItems.ALCHEMICAL_STONESTEM)
                    .build());

    public static final ReagentData WILLOW_WORT = new ReagentData("willow_wort",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_WILLOW_WORT)
                    .put(2, ModItems.MILLED_WILLOW_WORT)
                    .put(3, ModItems.CONCENTRATED_WILLOW_WORT)
                    .put(4, ModItems.REFINED_WILLOW_WORT)
                    .put(5, ModItems.ALCHEMICAL_WILLOW_WORT)
                    .build());

    public static final ReagentData RUMBLEROOT = new ReagentData("rumbleroot",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_RUMBLEROOT)
                    .put(2, ModItems.MILLED_RUMBLEROOT)
                    .put(3, ModItems.CONCENTRATED_RUMBLEROOT)
                    .put(4, ModItems.REFINED_RUMBLEROOT)
                    .put(5, ModItems.ALCHEMICAL_RUMBLEROOT)
                    .build());

    public static final ReagentData PHANTOM_FROND = new ReagentData("phantom_frond",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_PHANTOM_FROND)
                    .put(2, ModItems.MILLED_PHANTOM_FROND)
                    .put(3, ModItems.CONCENTRATED_PHANTOM_FROND)
                    .put(4, ModItems.REFINED_PHANTOM_FROND)
                    .put(5, ModItems.ALCHEMICAL_PHANTOM_FROND)
                    .build());

    public static final ReagentData BREEZEBLOOM = new ReagentData("breezebloom",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_BREEZEBLOOM)
                    .put(2, ModItems.MILLED_BREEZEBLOOM)
                    .put(3, ModItems.CONCENTRATED_BREEZEBLOOM)
                    .put(4, ModItems.REFINED_BREEZEBLOOM)
                    .put(5, ModItems.ALCHEMICAL_BREEZEBLOOM)
                    .build());

    public static final ReagentData DESERTS_THIRST = new ReagentData("deserts_thirst",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_DESERTS_THIRST)
                    .put(2, ModItems.MILLED_DESERTS_THIRST)
                    .put(3, ModItems.CONCENTRATED_DESERTS_THIRST)
                    .put(4, ModItems.REFINED_DESERTS_THIRST)
                    .put(5, ModItems.ALCHEMICAL_DESERTS_THIRST)
                    .build());

    public static final ReagentData THUNDERSTAR = new ReagentData("thunderstar",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_THUNDERSTAR)
                    .put(2, ModItems.MILLED_THUNDERSTAR)
                    .put(3, ModItems.CONCENTRATED_THUNDERSTAR)
                    .put(4, ModItems.REFINED_THUNDERSTAR)
                    .put(5, ModItems.ALCHEMICAL_THUNDERSTAR)
                    .build());

    public static final ReagentData SUNSTRIDERS_SIN = new ReagentData("sunstriders_sin",
            new ImmutableMap.Builder<Integer, RegistryObject<ItemReagent>>()
                    .put(1, ModItems.PERENNIAL_SUNSTRIDERS_SIN)
                    .put(2, ModItems.MILLED_SUNSTRIDERS_SIN)
                    .put(3, ModItems.CONCENTRATED_SUNSTRIDERS_SIN)
                    .put(4, ModItems.REFINED_SUNSTRIDERS_SIN)
                    .put(5, ModItems.ALCHEMICAL_SUNSTRIDERS_SIN)
                    .build());


    public static final ImmutableList<ReagentData> REAGENT_DATA =
            new ImmutableList.Builder<ReagentData>()
                    .add(ReagentDatabase.WINDY_LICHEN)
                    .add(ReagentDatabase.SUNSPECKLE)
                    .add(ReagentDatabase.MOONSPECKLE)
                    .add(ReagentDatabase.STONESTEM)
                    .add(ReagentDatabase.WILLOW_WORT)
                    .add(ReagentDatabase.RUMBLEROOT)
                    .add(ReagentDatabase.PHANTOM_FROND)
                    .add(ReagentDatabase.BREEZEBLOOM)
                    .add(ReagentDatabase.DESERTS_THIRST)
                    .add(ReagentDatabase.THUNDERSTAR)
                    .add(ReagentDatabase.SUNSTRIDERS_SIN)
                    .build();

    public static class ReagentData
    {
        public final String name;
        public final ImmutableMap<Integer, RegistryObject<ItemReagent>> tiers;

        public ReagentData(String name, ImmutableMap<Integer, RegistryObject<ItemReagent>> tiers)
        {
            this.name = name;
            this.tiers = tiers;
        }
    }
}
