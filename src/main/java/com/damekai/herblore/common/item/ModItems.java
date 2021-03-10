package com.damekai.herblore.common.item;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.flask.ModFlaskEffects;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.util.ReagentPotencyProbabilitySets;
import com.damekai.herblore.common.util.WeightedSet;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.ArrayList;

public class ModItems
{
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Herblore.MOD_ID);

    public static final RegistryObject<ItemReagent> WINDY_LICHEN = ITEMS.register("windy_lichen", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.NOMAD);
                add(ModFlaskEffects.STRIDER);
                add(ModFlaskEffects.FLEET);
            }}
    ));

    public static final RegistryObject<ItemReagent> SUNSPECKLE = ITEMS.register("sunspeckle", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.HIGH_NOON);
                add(ModFlaskEffects.VERDURE);
                add(ModFlaskEffects.QUENCH);
            }}
    ));

    public static final RegistryObject<ItemReagent> MOONSPECKLE = ITEMS.register("moonspeckle", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.WITCHING_HOUR);
                add(ModFlaskEffects.COMET);
                add(ModFlaskEffects.RUBBLE);
            }}
    ));

    public static final RegistryObject<ItemReagent> STONESTEM = ITEMS.register("stonestem", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.RUBBLE);
                add(ModFlaskEffects.HAPTIC);
                add(ModFlaskEffects.COMET);
            }}
    ));

    public static final RegistryObject<ItemReagent> WILLOW_WORT = ITEMS.register("willow_wort", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.WITCHING_HOUR);
                add(ModFlaskEffects.QUENCH);
                add(ModFlaskEffects.FLEET);
            }}
    ));

    public static final RegistryObject<ItemReagent> RUMBLEROOT = ITEMS.register("rumbleroot", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.RUBBLE);
                add(ModFlaskEffects.VERDURE);
                add(ModFlaskEffects.FLEET);
            }}
    ));

    public static final RegistryObject<ItemReagent> PHANTOM_FROND = ITEMS.register("phantom_frond", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.WITCHING_HOUR);
                add(ModFlaskEffects.PENANCE);
                add(ModFlaskEffects.HAPTIC);
            }}
    ));

    public static final RegistryObject<ItemReagent> BREEZEBLOOM = ITEMS.register("breezebloom", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.NOMAD);
                add(ModFlaskEffects.VERDURE);
                add(ModFlaskEffects.STRIDER);
            }}
    ));

    public static final RegistryObject<ItemReagent> DESERTS_THIRST = ITEMS.register("deserts_thirst", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.HIGH_NOON);
                add(ModFlaskEffects.QUENCH);
                add(ModFlaskEffects.PENANCE);
            }}
    ));

    public static final RegistryObject<ItemReagent> THUNDERSTAR = ITEMS.register("thunderstar", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.HAPTIC);
                add(ModFlaskEffects.COMET);
                add(ModFlaskEffects.NOMAD);
            }}
    ));

    public static final RegistryObject<ItemReagent> SUNSTRIDERS_SIN = ITEMS.register("sunstriders_sin", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.HIGH_NOON);
                add(ModFlaskEffects.STRIDER);
                add(ModFlaskEffects.PENANCE);
            }}
    ));

    public static final RegistryObject<Item> EMPTY_FLASK = ITEMS.register("empty_flask", () -> new ItemEmptyFlask(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK_OF_WATER = ITEMS.register("flask_of_water", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> CRUDE_FLASK = ITEMS.register("crude_flask", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK = ITEMS.register("flask", ItemFlask::new);

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
