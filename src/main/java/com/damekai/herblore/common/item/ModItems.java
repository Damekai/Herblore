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

    public static final RegistryObject<ItemReagent> PEACEBLOOM = ITEMS.register("peacebloom", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.STRIDER);
                add(ModFlaskEffects.QUENCH);
            }}
    ));

    public static final RegistryObject<ItemReagent> SILVERLEAF = ITEMS.register("silverleaf", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.HIGH_NOON);
                add(ModFlaskEffects.QUENCH);
                add(ModFlaskEffects.DREDGE);
            }}
    ));

    public static final RegistryObject<ItemReagent> EARTHROOT = ITEMS.register("earthroot", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.DREDGE);
                add(ModFlaskEffects.QUENCH);
            }}
    ));

    public static final RegistryObject<ItemReagent> MAGEROYAL = ITEMS.register("mageroyal", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.WITCHING_HOUR);
                add(ModFlaskEffects.HIGH_NOON);
            }}
    ));

    public static final RegistryObject<ItemReagent> BRIARTHORN = ITEMS.register("briarthorn", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.STRIDER);
                add(ModFlaskEffects.QUENCH);
            }}
    ));

    public static final RegistryObject<ItemReagent> WILDBLOOM = ITEMS.register("wildbloom", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.HIGH_NOON);
                add(ModFlaskEffects.STRIDER);
            }}
    ));

    public static final RegistryObject<ItemReagent> FADELEAF = ITEMS.register("fadeleaf", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.WITCHING_HOUR);
                add(ModFlaskEffects.QUENCH);
            }}
    ));

    public static final RegistryObject<ItemReagent> GOLDTHORN = ITEMS.register("goldthorn", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.PENANCE);
            }}
    ));

    public static final RegistryObject<ItemReagent> SUNGRASS = ITEMS.register("sungrass", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.PENANCE);
            }}
    ));

    public static final RegistryObject<ItemReagent> DREAMFOIL = ITEMS.register("dreamfoil", () -> new ItemReagent(
            ReagentPotencyProbabilitySets.WILD_REAGENT_POTENCY_PROBABILITIES,
            new ArrayList<RegistryObject<FlaskEffect>>()
            {{
                add(ModFlaskEffects.PENANCE);
            }}
    ));

    public static final RegistryObject<Item> EMPTY_FLASK = ITEMS.register("empty_flask", () -> new ItemEmptyFlask(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK_OF_WATER = ITEMS.register("flask_of_water", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> CRUDE_FLASK = ITEMS.register("crude_flask", () -> new Item(defaultItemProperties()));
    public static final RegistryObject<Item> FLASK = ITEMS.register("flask", ItemFlask::new);

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
