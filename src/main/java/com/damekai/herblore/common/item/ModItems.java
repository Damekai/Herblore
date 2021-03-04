package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.effect.FlaskEffect;
import com.damekai.herblore.common.effect.ModFlaskEffects;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.ModFlasks;
import com.damekai.herblore.common.util.WeightedSet;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Herblore.MOD_ID);

    public static final RegistryObject<ItemReagent> REAGENT_A = ITEMS.register("reagent_a", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.DEBUG_CDE, 2);
                add(ModFlasks.STRIDER, 1);
            }}
            ));

    public static final RegistryObject<ItemReagent> REAGENT_B = ITEMS.register("reagent_b", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.HIGH_NOON, 7);
            }}
    ));

    public static final RegistryObject<ItemReagent> REAGENT_C = ITEMS.register("reagent_c", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.WITCHING_HOUR, 5);
            }}
    ));

    public static final RegistryObject<ItemMilledReagent> REAGENT_A_MILLED = ITEMS.register("reagent_a_milled", () -> new ItemMilledReagent(REAGENT_A));
    public static final RegistryObject<ItemMilledReagent> REAGENT_B_MILLED = ITEMS.register("reagent_b_milled", () -> new ItemMilledReagent(REAGENT_B));
    public static final RegistryObject<ItemMilledReagent> REAGENT_C_MILLED = ITEMS.register("reagent_c_milled", () -> new ItemMilledReagent(REAGENT_C));

    public static final RegistryObject<ItemGrimyHerb> GRIMY_DEBUG = ITEMS.register("grimy_debug", () -> new ItemGrimyHerb(
            new WeightedSet<RegistryObject<ItemReagent>>() {{
                add(ModItems.REAGENT_A, 1);
                add(ModItems.REAGENT_B, 1);
                add(ModItems.REAGENT_C, 1);
            }}
    ));

    public static final RegistryObject<Item> EMPTY_FLASK = ITEMS.register("empty_flask", () -> new ItemEmptyFlask(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK_OF_WATER = ITEMS.register("flask_of_water", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> CRUDE_FLASK = ITEMS.register("crude_flask", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK = ITEMS.register("flask", ItemFlask::new);

    public static final RegistryObject<Item> PESTLE_AND_MORTAR = ITEMS.register("pestle_and_mortar", ItemPestleAndMortar::new);

    public static final RegistryObject<BlockItem> ATHANOR = ITEMS.register("athanor", () -> new BlockItem(ModBlocks.ATHANOR.get(), defaultItemProperties()));

    public static final RegistryObject<BlockItem> PERENNIAL_PATCH_DEBUG = ITEMS.register("perennial_patch_debug", () -> new BlockItem(ModBlocks.PERENNIAL_PATCH_DEBUG.get(), defaultItemProperties()));

    public static Item.Properties defaultItemProperties()
    {
        return (new Item.Properties())
                .group(ModItemGroups.HERBLORE);
    }

    public static Item getItemFromRegistry(String name)
    {
        RegistryObject<Item> match = ITEMS.getEntries().stream().filter((itemSupplier) -> itemSupplier.get().getRegistryName().toString().equals(name)).findAny().orElse(null);
        return match != null ? match.get() : null;
    }
}
