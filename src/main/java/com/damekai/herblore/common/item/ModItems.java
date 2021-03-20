package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.flask.ModFlaskEffects;
import com.damekai.herblore.common.flask.perk.ModFlaskPerks;
import com.damekai.herblore.common.util.reagent.Reagent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Herblore.MOD_ID);

    public static final RegistryObject<Item> MARROW = ITEMS.register("marrow", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.HEAL_LARGE));
    public static final RegistryObject<Item> POWDERY_SINEW = ITEMS.register("powdery_sinew", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.ADD_DURATION_SMALL));
    public static final RegistryObject<Item> ENDER_DUST = ITEMS.register("ender_dust", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.MULTIPLY_DURATION_LARGE));
    public static final RegistryObject<Item> SPIDER_GRIT = ITEMS.register("spider_grit", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.NIGHT_VISION));
    public static final RegistryObject<Item> EERIE_MUCOUS = ITEMS.register("eerie_mucous", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.INVISIBILITY));
    public static final RegistryObject<Item> POPPY_EXTRACT = ITEMS.register("poppy_extract", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.ABSORPTION));
    public static final RegistryObject<Item> CRUSHED_PUMPKIN_SEEDS = ITEMS.register("crushed_pumpkin_seeds", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.ADD_DURATION_MEDIUM));
    public static final RegistryObject<Item> PUREED_BERRIES = ITEMS.register("pureed_berries", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.HEAL_SMALL));
    public static final RegistryObject<Item> COCOA_FLOUR = ITEMS.register("cocoa_flour", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.ADD_DURATION_LARGE));
    public static final RegistryObject<Item> KELP_POWDER = ITEMS.register("kelp_powder", () -> new ItemCatalyst(defaultItemProperties(), ModFlaskPerks.MULTIPLY_DURATION_SMALL));

    public static final Reagent WINDY_LICHEN = new Reagent(
            new Reagent.Initializer(ITEMS, "windy_lichen")
                    .flaskEffect(ModFlaskEffects.STRIDER)
                    .seeds(ModBlocks.WINDY_LICHEN_CROP)
                    .tiers(1, 5));

    public static final Reagent SUNSPECKLE = new Reagent(
            new Reagent.Initializer(ITEMS, "sunspeckle")
                    .flaskEffect(ModFlaskEffects.HIGH_NOON)
                    .seeds(ModBlocks.SUNSPECKLE_CROP)
                    .tiers(1, 5));

    public static final Reagent MOONSPECKLE = new Reagent(
            new Reagent.Initializer(ITEMS, "moonspeckle")
                    .flaskEffect(ModFlaskEffects.WITCHING_HOUR)
                    .seeds(ModBlocks.MOONSPECKLE_CROP)
                    .tiers(1, 5));

    public static final Reagent STONESTEM = new Reagent(
            new Reagent.Initializer(ITEMS, "stonestem")
                    .flaskEffect(ModFlaskEffects.RUBBLE)
                    .seeds(ModBlocks.STONESTEM_CROP)
                    .tiers(1, 5));

    public static final Reagent WILLOW_WORT = new Reagent(
            new Reagent.Initializer(ITEMS, "willow_wort")
                    .flaskEffect(ModFlaskEffects.QUENCH)
                    .seeds(ModBlocks.WILLOW_WORT_CROP)
                    .tiers(1, 5));

    public static final Reagent RUMBLEROOT = new Reagent(
            new Reagent.Initializer(ITEMS, "rumbleroot")
                    .flaskEffect(ModFlaskEffects.HAPTIC)
                    .seeds(ModBlocks.RUMBLEROOT_CROP)
                    .tiers(1, 5));

    public static final Reagent PHANTOM_FROND = new Reagent(
            new Reagent.Initializer(ITEMS, "phantom_frond")
                    .flaskEffect(ModFlaskEffects.FLEET)
                    .seeds(ModBlocks.PHANTOM_FROND_CROP)
                    .tiers(1, 5));

    public static final Reagent BREEZEBLOOM = new Reagent(
            new Reagent.Initializer(ITEMS, "breezebloom")
                    .flaskEffect(ModFlaskEffects.VERDURE)
                    .seeds(ModBlocks.BREEZEBLOOM_CROP)
                    .tiers(1, 5));

    public static final Reagent DESERTS_THIRST = new Reagent(
            new Reagent.Initializer(ITEMS, "deserts_thirst")
                    .flaskEffect(ModFlaskEffects.NOMAD)
                    .seeds(ModBlocks.DESERTS_THIRST_CROP)
                    .tiers(1, 5));

    public static final Reagent THUNDERSTAR = new Reagent(
            new Reagent.Initializer(ITEMS, "thunderstar")
                    .flaskEffect(ModFlaskEffects.COMET)
                    .seeds(ModBlocks.THUNDERSTAR_CROP)
                    .tiers(1, 5));

    public static final Reagent SUNSTRIDERS_SIN = new Reagent(
            new Reagent.Initializer(ITEMS, "sunstriders_sin")
                    .flaskEffect(ModFlaskEffects.PENANCE)
                    .seeds(ModBlocks.SUNSTRIDERS_SIN_CROP)
                    .tiers(1, 5));

    public static final Reagent SLAKEMOSS = new Reagent(
            new Reagent.Initializer(ITEMS, "slakemoss")
                    .flaskEffect(ModFlaskEffects.RIGOR)
                    .seeds(ModBlocks.SLAKEMOSS_CROP)
                    .tiers(1, 5));

    public static final Reagent SLAG_RIND = new Reagent(
            new Reagent.Initializer(ITEMS, "slag_rind")
                    .flaskEffect(ModFlaskEffects.DREDGE)
                    .seeds(ModBlocks.SLAG_RIND_CROP)
                    .tiers(1, 5));

    public static final Reagent VENGERVINE = new Reagent(
            new Reagent.Initializer(ITEMS, "vengervine")
                    .flaskEffect(ModFlaskEffects.FALLARBOR)
                    .seeds(ModBlocks.VENGERVINE_CROP)
                    .tiers(1, 5));

    public static final Reagent SKYGLOM = new Reagent(
            new Reagent.Initializer(ITEMS, "skyglom")
                    .flaskEffect(ModFlaskEffects.AMBIT)
                    .seeds(ModBlocks.SKYGLOM_CROP)
                    .tiers(1, 5));

    public static final RegistryObject<Item> EMPTY_FLASK = ITEMS.register("empty_flask", () -> new ItemEmptyFlask(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK_OF_WATER = ITEMS.register("flask_of_water", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> CRUDE_FLASK = ITEMS.register("crude_flask", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK = ITEMS.register("flask", ItemFlask::new);

    public static final RegistryObject<Item> PESTLE_AND_MORTAR = ITEMS.register("pestle_and_mortar", ItemPestleAndMortar::new);

    public static final RegistryObject<Item> SUNFLOWER_OIL = ITEMS.register("sunflower_oil", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> FERMENTING_PASTE = ITEMS.register("fermenting_paste", () -> new Item(defaultItemProperties()));

    public static final RegistryObject<BlockItem> ATHANOR = ITEMS.register("athanor", () -> new BlockItem(ModBlocks.ATHANOR.get(), defaultItemProperties()));

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
