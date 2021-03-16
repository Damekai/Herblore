package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.flask.ModFlaskEffects;
import com.damekai.herblore.common.flask.perk.ModFlaskPerks;
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

    public static final RegistryObject<ItemReagent> PERENNIAL_WINDY_LICHEN = ITEMS.register("perennial_windy_lichen", () -> new ItemReagent(1, ModFlaskEffects.STRIDER));
    public static final RegistryObject<ItemReagent> MILLED_WINDY_LICHEN = ITEMS.register("milled_windy_lichen", () -> new ItemReagent(2, ModFlaskEffects.STRIDER));
    public static final RegistryObject<ItemReagent> CONCENTRATED_WINDY_LICHEN = ITEMS.register("concentrated_windy_lichen", () -> new ItemReagent(3, ModFlaskEffects.STRIDER));
    public static final RegistryObject<ItemReagent> REFINED_WINDY_LICHEN = ITEMS.register("refined_windy_lichen", () -> new ItemReagent(4, ModFlaskEffects.STRIDER));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_WINDY_LICHEN = ITEMS.register("alchemical_windy_lichen", () -> new ItemReagent(5, ModFlaskEffects.STRIDER));

    public static final RegistryObject<ItemReagent> PERENNIAL_SUNSPECKLE = ITEMS.register("perennial_sunspeckle", () -> new ItemReagent(1, ModFlaskEffects.HIGH_NOON));
    public static final RegistryObject<ItemReagent> MILLED_SUNSPECKLE = ITEMS.register("milled_sunspeckle", () -> new ItemReagent(2, ModFlaskEffects.HIGH_NOON));
    public static final RegistryObject<ItemReagent> CONCENTRATED_SUNSPECKLE = ITEMS.register("concentrated_sunspeckle", () -> new ItemReagent(3, ModFlaskEffects.HIGH_NOON));
    public static final RegistryObject<ItemReagent> REFINED_SUNSPECKLE = ITEMS.register("refined_sunspeckle", () -> new ItemReagent(4, ModFlaskEffects.HIGH_NOON));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_SUNSPECKLE = ITEMS.register("alchemical_sunspeckle", () -> new ItemReagent(5, ModFlaskEffects.HIGH_NOON));

    public static final RegistryObject<ItemReagent> PERENNIAL_MOONSPECKLE = ITEMS.register("perennial_moonspeckle", () -> new ItemReagent(1, ModFlaskEffects.WITCHING_HOUR));
    public static final RegistryObject<ItemReagent> MILLED_MOONSPECKLE = ITEMS.register("milled_moonspeckle", () -> new ItemReagent(2, ModFlaskEffects.WITCHING_HOUR));
    public static final RegistryObject<ItemReagent> CONCENTRATED_MOONSPECKLE = ITEMS.register("concentrated_moonspeckle", () -> new ItemReagent(3, ModFlaskEffects.WITCHING_HOUR));
    public static final RegistryObject<ItemReagent> REFINED_MOONSPECKLE = ITEMS.register("refined_moonspeckle", () -> new ItemReagent(4, ModFlaskEffects.WITCHING_HOUR));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_MOONSPECKLE = ITEMS.register("alchemical_moonspeckle", () -> new ItemReagent(5, ModFlaskEffects.WITCHING_HOUR));

    public static final RegistryObject<ItemReagent> PERENNIAL_STONESTEM = ITEMS.register("perennial_stonestem", () -> new ItemReagent(1, ModFlaskEffects.RUBBLE));
    public static final RegistryObject<ItemReagent> MILLED_STONESTEM = ITEMS.register("milled_stonestem", () -> new ItemReagent(2, ModFlaskEffects.RUBBLE));
    public static final RegistryObject<ItemReagent> CONCENTRATED_STONESTEM = ITEMS.register("concentrated_stonestem", () -> new ItemReagent(3, ModFlaskEffects.RUBBLE));
    public static final RegistryObject<ItemReagent> REFINED_STONESTEM = ITEMS.register("refined_stonestem", () -> new ItemReagent(4, ModFlaskEffects.RUBBLE));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_STONESTEM = ITEMS.register("alchemical_stonestem", () -> new ItemReagent(5, ModFlaskEffects.RUBBLE));

    public static final RegistryObject<ItemReagent> PERENNIAL_WILLOW_WORT = ITEMS.register("perennial_willow_wort", () -> new ItemReagent(1, ModFlaskEffects.QUENCH));
    public static final RegistryObject<ItemReagent> MILLED_WILLOW_WORT = ITEMS.register("milled_willow_wort", () -> new ItemReagent(2, ModFlaskEffects.QUENCH));
    public static final RegistryObject<ItemReagent> CONCENTRATED_WILLOW_WORT = ITEMS.register("concentrated_willow_wort", () -> new ItemReagent(3, ModFlaskEffects.QUENCH));
    public static final RegistryObject<ItemReagent> REFINED_WILLOW_WORT = ITEMS.register("refined_willow_wort", () -> new ItemReagent(4, ModFlaskEffects.QUENCH));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_WILLOW_WORT = ITEMS.register("alchemical_willow_wort", () -> new ItemReagent(5, ModFlaskEffects.QUENCH));

    public static final RegistryObject<ItemReagent> PERENNIAL_RUMBLEROOT = ITEMS.register("perennial_rumbleroot", () -> new ItemReagent(1, ModFlaskEffects.HAPTIC));
    public static final RegistryObject<ItemReagent> MILLED_RUMBLEROOT = ITEMS.register("milled_rumbleroot", () -> new ItemReagent(2, ModFlaskEffects.HAPTIC));
    public static final RegistryObject<ItemReagent> CONCENTRATED_RUMBLEROOT = ITEMS.register("concentrated_rumbleroot", () -> new ItemReagent(3, ModFlaskEffects.HAPTIC));
    public static final RegistryObject<ItemReagent> REFINED_RUMBLEROOT = ITEMS.register("refined_rumbleroot", () -> new ItemReagent(4, ModFlaskEffects.HAPTIC));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_RUMBLEROOT = ITEMS.register("alchemical_rumbleroot", () -> new ItemReagent(5, ModFlaskEffects.HAPTIC));

    public static final RegistryObject<ItemReagent> PERENNIAL_PHANTOM_FROND = ITEMS.register("perennial_phantom_frond", () -> new ItemReagent(1, ModFlaskEffects.FLEET));
    public static final RegistryObject<ItemReagent> MILLED_PHANTOM_FROND = ITEMS.register("milled_phantom_frond", () -> new ItemReagent(2, ModFlaskEffects.FLEET));
    public static final RegistryObject<ItemReagent> CONCENTRATED_PHANTOM_FROND = ITEMS.register("concentrated_phantom_frond", () -> new ItemReagent(3, ModFlaskEffects.FLEET));
    public static final RegistryObject<ItemReagent> REFINED_PHANTOM_FROND = ITEMS.register("refined_phantom_frond", () -> new ItemReagent(4, ModFlaskEffects.FLEET));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_PHANTOM_FROND = ITEMS.register("alchemical_phantom_frond", () -> new ItemReagent(5, ModFlaskEffects.FLEET));

    public static final RegistryObject<ItemReagent> PERENNIAL_BREEZEBLOOM = ITEMS.register("perennial_breezebloom", () -> new ItemReagent(1, ModFlaskEffects.VERDURE));
    public static final RegistryObject<ItemReagent> MILLED_BREEZEBLOOM = ITEMS.register("milled_breezebloom", () -> new ItemReagent(2, ModFlaskEffects.VERDURE));
    public static final RegistryObject<ItemReagent> CONCENTRATED_BREEZEBLOOM = ITEMS.register("concentrated_breezebloom", () -> new ItemReagent(3, ModFlaskEffects.VERDURE));
    public static final RegistryObject<ItemReagent> REFINED_BREEZEBLOOM = ITEMS.register("refined_breezebloom", () -> new ItemReagent(4, ModFlaskEffects.VERDURE));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_BREEZEBLOOM = ITEMS.register("alchemical_breezebloom", () -> new ItemReagent(5, ModFlaskEffects.VERDURE));

    public static final RegistryObject<ItemReagent> PERENNIAL_DESERTS_THIRST = ITEMS.register("perennial_deserts_thirst", () -> new ItemReagent(1, ModFlaskEffects.NOMAD));
    public static final RegistryObject<ItemReagent> MILLED_DESERTS_THIRST = ITEMS.register("milled_deserts_thirst", () -> new ItemReagent(2, ModFlaskEffects.NOMAD));
    public static final RegistryObject<ItemReagent> CONCENTRATED_DESERTS_THIRST = ITEMS.register("concentrated_deserts_thirst", () -> new ItemReagent(3, ModFlaskEffects.NOMAD));
    public static final RegistryObject<ItemReagent> REFINED_DESERTS_THIRST = ITEMS.register("refined_deserts_thirst", () -> new ItemReagent(4, ModFlaskEffects.NOMAD));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_DESERTS_THIRST = ITEMS.register("alchemical_deserts_thirst", () -> new ItemReagent(5, ModFlaskEffects.NOMAD));

    public static final RegistryObject<ItemReagent> PERENNIAL_THUNDERSTAR = ITEMS.register("perennial_thunderstar", () -> new ItemReagent(1, ModFlaskEffects.COMET));
    public static final RegistryObject<ItemReagent> MILLED_THUNDERSTAR = ITEMS.register("milled_thunderstar", () -> new ItemReagent(2, ModFlaskEffects.COMET));
    public static final RegistryObject<ItemReagent> CONCENTRATED_THUNDERSTAR = ITEMS.register("concentrated_thunderstar", () -> new ItemReagent(3, ModFlaskEffects.COMET));
    public static final RegistryObject<ItemReagent> REFINED_THUNDERSTAR = ITEMS.register("refined_thunderstar", () -> new ItemReagent(4, ModFlaskEffects.COMET));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_THUNDERSTAR = ITEMS.register("alchemical_thunderstar", () -> new ItemReagent(5, ModFlaskEffects.COMET));

    public static final RegistryObject<ItemReagent> PERENNIAL_SUNSTRIDERS_SIN = ITEMS.register("perennial_sunstriders_sin", () -> new ItemReagent(1, ModFlaskEffects.PENANCE));
    public static final RegistryObject<ItemReagent> MILLED_SUNSTRIDERS_SIN = ITEMS.register("milled_sunstriders_sin", () -> new ItemReagent(2, ModFlaskEffects.PENANCE));
    public static final RegistryObject<ItemReagent> CONCENTRATED_SUNSTRIDERS_SIN = ITEMS.register("concentrated_sunstriders_sin", () -> new ItemReagent(3, ModFlaskEffects.PENANCE));
    public static final RegistryObject<ItemReagent> REFINED_SUNSTRIDERS_SIN = ITEMS.register("refined_sunstriders_sin", () -> new ItemReagent(4, ModFlaskEffects.PENANCE));
    public static final RegistryObject<ItemReagent> ALCHEMICAL_SUNSTRIDERS_SIN = ITEMS.register("alchemical_sunstriders_sin", () -> new ItemReagent(5, ModFlaskEffects.PENANCE));

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
