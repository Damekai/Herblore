package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.flask.ModFlaskEffects;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.util.WeightedSet;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Herblore.MOD_ID);

    public static final RegistryObject<ItemReagent> MILLED_PEACEBLOOM = ITEMS.register("milled_peacebloom", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.STRIDER, 2);
                add(ModFlaskEffects.QUENCH, 1);
            }}
    ));

    public static final RegistryObject<ItemReagent> MILLED_SILVERLEAF = ITEMS.register("milled_silverleaf", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.HIGH_NOON, 1);
                add(ModFlaskEffects.QUENCH, 1);
                add(ModFlaskEffects.DREDGE, 1);
            }}
    ));

    public static final RegistryObject<ItemReagent> MILLED_EARTHROOT = ITEMS.register("milled_earthroot", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.DREDGE, 2);
                add(ModFlaskEffects.QUENCH, 1);
            }}
    ));

    public static final RegistryObject<ItemReagent> MILLED_MAGEROYAL = ITEMS.register("milled_mageroyal", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.WITCHING_HOUR, 2);
                add(ModFlaskEffects.HIGH_NOON, 1);
            }}
    ));

    public static final RegistryObject<ItemReagent> MILLED_BRIARTHORN = ITEMS.register("milled_briarthorn", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.STRIDER, 1);
                add(ModFlaskEffects.QUENCH, 2);
            }}
    ));

    public static final RegistryObject<ItemReagent> MILLED_WILDBLOOM = ITEMS.register("milled_wildbloom", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.HIGH_NOON, 1);
                add(ModFlaskEffects.STRIDER, 2);
            }}
    ));

    public static final RegistryObject<ItemReagent> MILLED_FADELEAF = ITEMS.register("milled_fadeleaf", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.WITCHING_HOUR, 1);
                add(ModFlaskEffects.QUENCH, 2);
            }}
    ));

    public static final RegistryObject<ItemReagent> MILLED_GOLDTHORN = ITEMS.register("milled_goldthorn", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.PENANCE, 2);
            }}
    ));

    public static final RegistryObject<ItemReagent> MILLED_SUNGRASS = ITEMS.register("milled_sungrass", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.PENANCE, 2);
            }}
    ));

    public static final RegistryObject<ItemReagent> MILLED_DREAMFOIL = ITEMS.register("milled_dreamfoil", () -> new ItemReagent(
            new WeightedSet<RegistryObject<FlaskEffect>>() {{
                add(ModFlaskEffects.PENANCE, 1);
            }}
    ));

    public static final RegistryObject<Item> PEACEBLOOM = ITEMS.register("peacebloom", () -> new ItemRawReagent(MILLED_PEACEBLOOM, 1, 10));
    public static final RegistryObject<Item> SILVERLEAF = ITEMS.register("silverleaf", () -> new ItemRawReagent(MILLED_SILVERLEAF, 1, 10));
    public static final RegistryObject<Item> EARTHROOT = ITEMS.register("earthroot", () -> new ItemRawReagent(MILLED_EARTHROOT, 1, 10));
    public static final RegistryObject<Item> MAGEROYAL = ITEMS.register("mageroyal", () -> new ItemRawReagent(MILLED_MAGEROYAL, 1, 10));
    public static final RegistryObject<Item> BRIARTHORN = ITEMS.register("briarthorn", () -> new ItemRawReagent(MILLED_BRIARTHORN, 1, 10));
    public static final RegistryObject<Item> WILDBLOOM = ITEMS.register("wildbloom", () -> new ItemRawReagent(MILLED_WILDBLOOM, 1, 10));
    public static final RegistryObject<Item> FADELEAF = ITEMS.register("fadeleaf", () -> new ItemRawReagent(MILLED_FADELEAF, 1, 10));
    public static final RegistryObject<Item> GOLDTHORN = ITEMS.register("goldthorn", () -> new ItemRawReagent(MILLED_GOLDTHORN, 1, 10));
    public static final RegistryObject<Item> SUNGRASS = ITEMS.register("sungrass", () -> new ItemRawReagent(MILLED_SUNGRASS, 1, 10));
    public static final RegistryObject<Item> DREAMFOIL = ITEMS.register("dreamfoil", () -> new ItemRawReagent(MILLED_DREAMFOIL, 1, 10));

    public static final RegistryObject<ItemGrimyHerb> GRIMY_HERB = ITEMS.register("grimy_herb", () -> new ItemGrimyHerb(
            new WeightedSet<RegistryObject<Item>>() {{
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
