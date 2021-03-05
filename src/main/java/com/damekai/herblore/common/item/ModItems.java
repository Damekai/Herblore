package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.flask.ModFlasks;
import com.damekai.herblore.common.util.WeightedSet;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Herblore.MOD_ID);

    public static final RegistryObject<ItemReagent> PEACEBLOOM = ITEMS.register("peacebloom", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.STRIDER, 2);
                add(ModFlasks.QUENCH, 1);
            }}
    ));

    public static final RegistryObject<ItemReagent> SILVERLEAF = ITEMS.register("silverleaf", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.HIGH_NOON, 1);
                add(ModFlasks.QUENCH, 1);
                add(ModFlasks.DREDGE, 1);
            }}
    ));

    public static final RegistryObject<ItemReagent> EARTHROOT = ITEMS.register("earthroot", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.DREDGE, 2);
                add(ModFlasks.QUENCH, 1);
            }}
    ));

    public static final RegistryObject<ItemReagent> MAGEROYAL = ITEMS.register("mageroyal", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.WITCHING_HOUR, 2);
                add(ModFlasks.HIGH_NOON, 1);
            }}
    ));

    public static final RegistryObject<ItemReagent> BRIARTHORN = ITEMS.register("briarthorn", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.STRIDER, 1);
                add(ModFlasks.QUENCH, 2);
            }}
    ));

    public static final RegistryObject<ItemReagent> WILDBLOOM = ITEMS.register("wildbloom", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.HIGH_NOON, 1);
                add(ModFlasks.STRIDER, 2);
            }}
    ));

    public static final RegistryObject<ItemReagent> FADELEAF = ITEMS.register("fadeleaf", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.WITCHING_HOUR, 1);
                add(ModFlasks.QUENCH, 2);
            }}
    ));

    public static final RegistryObject<ItemReagent> GOLDTHORN = ITEMS.register("goldthorn", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.HIGH_NOON, 1);
                add(ModFlasks.QUENCH, 1);
                add(ModFlasks.STRIDER, 1);
            }}
    ));

    public static final RegistryObject<ItemReagent> SUNGRASS = ITEMS.register("sungrass", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.HIGH_NOON, 3);
            }}
    ));

    public static final RegistryObject<ItemReagent> DREAMFOIL = ITEMS.register("dreamfoil", () -> new ItemReagent(
            new WeightedSet<RegistryObject<Flask>>() {{
                add(ModFlasks.WITCHING_HOUR, 3);
            }}
    ));

    public static final RegistryObject<ItemMilledReagent> MILLED_PEACEBLOOM = ITEMS.register("milled_peacebloom", () -> new ItemMilledReagent(PEACEBLOOM));
    public static final RegistryObject<ItemMilledReagent> MILLED_SILVERLEAF = ITEMS.register("milled_silverleaf", () -> new ItemMilledReagent(SILVERLEAF));
    public static final RegistryObject<ItemMilledReagent> MILLED_EARTHROOT = ITEMS.register("milled_earthroot", () -> new ItemMilledReagent(EARTHROOT));
    public static final RegistryObject<ItemMilledReagent> MILLED_MAGEROYAL = ITEMS.register("milled_mageroyal", () -> new ItemMilledReagent(MAGEROYAL));
    public static final RegistryObject<ItemMilledReagent> MILLED_BRIARTHORN = ITEMS.register("milled_briarthorn", () -> new ItemMilledReagent(BRIARTHORN));
    public static final RegistryObject<ItemMilledReagent> MILLED_WILDBLOOM = ITEMS.register("milled_wildbloom", () -> new ItemMilledReagent(WILDBLOOM));
    public static final RegistryObject<ItemMilledReagent> MILLED_FADELEAF = ITEMS.register("milled_fadeleaf", () -> new ItemMilledReagent(FADELEAF));
    public static final RegistryObject<ItemMilledReagent> MILLED_GOLDTHORN = ITEMS.register("milled_goldthorn", () -> new ItemMilledReagent(GOLDTHORN));
    public static final RegistryObject<ItemMilledReagent> MILLED_SUNGRASS = ITEMS.register("milled_sungrass", () -> new ItemMilledReagent(SUNGRASS));
    public static final RegistryObject<ItemMilledReagent> MILLED_DREAMFOIL = ITEMS.register("milled_dreamfoil", () -> new ItemMilledReagent(DREAMFOIL));

    public static final RegistryObject<ItemGrimyHerb> GRIMY_HERB = ITEMS.register("grimy_herb", () -> new ItemGrimyHerb(
            new WeightedSet<RegistryObject<ItemReagent>>() {{
                add(ModItems.PEACEBLOOM, 1);
                add(ModItems.SILVERLEAF, 1);
                add(ModItems.EARTHROOT, 1);
                add(ModItems.MAGEROYAL, 1);
                add(ModItems.BRIARTHORN, 1);
                add(ModItems.WILDBLOOM, 1);
                add(ModItems.FADELEAF, 1);
                add(ModItems.GOLDTHORN, 1);
                add(ModItems.SUNGRASS, 1);
                add(ModItems.DREAMFOIL, 1);
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
