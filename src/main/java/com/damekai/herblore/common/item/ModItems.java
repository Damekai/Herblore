package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Herblore.MOD_ID);

    public static final RegistryObject<Item> WINDY_LICHEN = ITEMS.register("windy_lichen", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> SUNSPECKLE = ITEMS.register("sunspeckle", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> MOONSPECKLE = ITEMS.register("moonspeckle", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> STONESTEM = ITEMS.register("stonestem", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> WILLOW_WORT = ITEMS.register("willow_wort", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> RUMBLEROOT = ITEMS.register("rumbleroot", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> PHANTOM_FROND = ITEMS.register("phantom_frond", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> BREEZEBLOOM = ITEMS.register("breezebloom", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> DESERTS_THIRST = ITEMS.register("deserts_thirst", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> THUNDERSTAR = ITEMS.register("thunderstar", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> SUNSTRIDERS_SIN = ITEMS.register("sunstriders_sin", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> SLAKEMOSS = ITEMS.register("slakemoss", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> SLAG_RIND = ITEMS.register("slag_rind", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> VENGERVINE = ITEMS.register("vengervine", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> SKYGLOM = ITEMS.register("skyglom", () -> new Item(defaultItemProperties()));

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
    public static final RegistryObject<Item> FLASK_OF_WATER = ITEMS.register("flask_of_water", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> CRUDE_FLASK = ITEMS.register("crude_flask", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK = ITEMS.register("flask", ItemFlask::new);

    public static final RegistryObject<BlockItem> PERENNIAL_PATCH = ITEMS.register("perennial_patch", () -> new BlockItem(ModBlocks.PERENNIAL_PATCH.get(), defaultItemProperties()));

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
