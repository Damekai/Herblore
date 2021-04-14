package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.awt.*;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Herblore.MOD_ID);

    private static final Color MAROON = new Color(0x861F41);
    private static final Color ORANGE = new Color(0xE87722);

    public static final RegistryObject<ItemReagent> WINDY_LICHEN = ITEMS.register("windy_lichen", () -> new ItemReagent(MAROON, ORANGE, ORANGE, MAROON));
    public static final RegistryObject<ItemReagent> SUNSPECKLE = ITEMS.register("sunspeckle", () -> new ItemReagent(ORANGE, ORANGE, ORANGE, ORANGE));
    public static final RegistryObject<ItemReagent> MOONSPECKLE = ITEMS.register("moonspeckle", () -> new ItemReagent(MAROON, MAROON, MAROON, MAROON));
    public static final RegistryObject<ItemReagent> STONESTEM = ITEMS.register("stonestem", () -> new ItemReagent(MAROON, MAROON, ORANGE, ORANGE));
    public static final RegistryObject<ItemReagent> WILLOW_WORT = ITEMS.register("willow_wort", () -> new ItemReagent(MAROON, ORANGE, MAROON, MAROON));
    public static final RegistryObject<ItemReagent> RUMBLEROOT = ITEMS.register("rumbleroot", () -> new ItemReagent(ORANGE, MAROON, MAROON, ORANGE));
    public static final RegistryObject<ItemReagent> PHANTOM_FROND = ITEMS.register("phantom_frond", () -> new ItemReagent(ORANGE, MAROON, ORANGE, ORANGE));
    public static final RegistryObject<ItemReagent> BREEZEBLOOM = ITEMS.register("breezebloom", () -> new ItemReagent(ORANGE, MAROON, MAROON, ORANGE));
    public static final RegistryObject<ItemReagent> DESERTS_THIRST = ITEMS.register("deserts_thirst", () -> new ItemReagent(MAROON, MAROON, ORANGE, MAROON));
    public static final RegistryObject<ItemReagent> THUNDERSTAR = ITEMS.register("thunderstar", () -> new ItemReagent(ORANGE, MAROON, MAROON, MAROON));
    public static final RegistryObject<ItemReagent> SUNSTRIDERS_SIN = ITEMS.register("sunstriders_sin", () -> new ItemReagent(MAROON, MAROON, MAROON, ORANGE));
    public static final RegistryObject<ItemReagent> SLAKEMOSS = ITEMS.register("slakemoss", () -> new ItemReagent(ORANGE, ORANGE, MAROON, MAROON));
    public static final RegistryObject<ItemReagent> SLAG_RIND = ITEMS.register("slag_rind", () -> new ItemReagent(ORANGE, ORANGE, MAROON, ORANGE));
    public static final RegistryObject<ItemReagent> VENGERVINE = ITEMS.register("vengervine", () -> new ItemReagent(ORANGE, ORANGE, ORANGE, MAROON));
    public static final RegistryObject<ItemReagent> SKYGLOM = ITEMS.register("skyglom", () -> new ItemReagent(ORANGE, MAROON, ORANGE, ORANGE));

    public static final RegistryObject<ItemReagentSeeds> WINDY_LICHEN_SEEDS = ITEMS.register("windy_lichen_seeds", () -> new ItemReagentSeeds(ModBlocks.WINDY_LICHEN_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> SUNSPECKLE_SEEDS = ITEMS.register("sunspeckle_seeds", () -> new ItemReagentSeeds(ModBlocks.SUNSPECKLE_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> MOONSPECKLE_SEEDS = ITEMS.register("moonspeckle_seeds", () -> new ItemReagentSeeds(ModBlocks.MOONSPECKLE_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> STONESTEM_SEEDS = ITEMS.register("stonestem_seeds", () -> new ItemReagentSeeds(ModBlocks.STONESTEM_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> WILLOW_WORT_SEEDS = ITEMS.register("willow_wort_seeds", () -> new ItemReagentSeeds(ModBlocks.WILLOW_WORT_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> RUMBLEROOT_SEEDS = ITEMS.register("rumbleroot_seeds", () -> new ItemReagentSeeds(ModBlocks.RUMBLEROOT_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> PHANTOM_FROND_SEEDS = ITEMS.register("phantom_frond_seeds", () -> new ItemReagentSeeds(ModBlocks.PHANTOM_FROND_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> BREEZEBLOOM_SEEDS = ITEMS.register("breezebloom_seeds", () -> new ItemReagentSeeds(ModBlocks.BREEZEBLOOM_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> DESERTS_THIRST_SEEDS = ITEMS.register("deserts_thirst_seeds", () -> new ItemReagentSeeds(ModBlocks.DESERTS_THIRST_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> THUNDERSTAR_SEEDS = ITEMS.register("thunderstar_seeds", () -> new ItemReagentSeeds(ModBlocks.THUNDERSTAR_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> SUNSTRIDERS_SIN_SEEDS = ITEMS.register("sunstriders_sin_seeds", () -> new ItemReagentSeeds(ModBlocks.SUNSTRIDERS_SIN_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> SLAKEMOSS_SEEDS = ITEMS.register("slakemoss_seeds", () -> new ItemReagentSeeds(ModBlocks.SLAKEMOSS_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> SLAG_RIND_SEEDS = ITEMS.register("slag_rind_seeds", () -> new ItemReagentSeeds(ModBlocks.SLAG_RIND_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> VENGERVINE_SEEDS = ITEMS.register("vengervine_seeds", () -> new ItemReagentSeeds(ModBlocks.VENGERVINE_CROP.get()));
    public static final RegistryObject<ItemReagentSeeds> SKYGLOM_SEEDS = ITEMS.register("skyglom_seeds", () -> new ItemReagentSeeds(ModBlocks.SKYGLOM_CROP.get()));

    public static final RegistryObject<Item> EMPTY_FLASK = ITEMS.register("empty_flask", () -> new ItemEmptyFlask(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK_OF_WATER = ITEMS.register("flask_of_water", () -> new Item(defaultItemProperties().maxStackSize(1)));
    public static final RegistryObject<Item> CRUDE_FLASK = ITEMS.register("crude_flask", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK = ITEMS.register("flask", ItemFlask::new);

    public static final RegistryObject<BlockItem> PERENNIAL_PATCH = ITEMS.register("perennial_patch", () -> new BlockItem(ModBlocks.PERENNIAL_PATCH.get(), defaultItemProperties()));

    public static final RegistryObject<BlockItem> FLASK_STATION = ITEMS.register("flask_station", () -> new BlockItem(ModBlocks.FLASK_STATION.get(), defaultItemProperties()));

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
