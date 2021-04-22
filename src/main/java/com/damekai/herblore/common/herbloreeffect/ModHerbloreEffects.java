package com.damekai.herblore.common.herbloreeffect;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.UUID;

public class ModHerbloreEffects
{
    public static final DeferredRegister<HerbloreEffect> HERBLORE_EFFECTS = DeferredRegister.create(HerbloreEffect.class, Herblore.MOD_ID);

    public static final RegistryObject<HerbloreEffect> DEBUG_ALPHA = HERBLORE_EFFECTS.register("debug_alpha", () -> new HerbloreEffectDebug(ModEffects.DEBUG_ALPHA_RENDER, "alpha"));
    public static final RegistryObject<HerbloreEffect> STRIDER = HERBLORE_EFFECTS.register("strider", () -> new HerbloreEffectStrider(ModEffects.STRIDER_RENDER));
    public static final RegistryObject<HerbloreEffect> QUENCH = HERBLORE_EFFECTS.register("quench", () -> new HerbloreEffectQuench(ModEffects.QUENCH_RENDER));
    public static final RegistryObject<HerbloreEffect> HIGH_NOON = HERBLORE_EFFECTS.register("high_noon", () -> new HerbloreEffectPowerHour(ModEffects.HIGH_NOON_RENDER, 6000, UUID.fromString("d6baf902-b953-447f-bcaa-d9618c7474e8")));
    public static final RegistryObject<HerbloreEffect> WITCHING_HOUR = HERBLORE_EFFECTS.register("witching_hour", () -> new HerbloreEffectPowerHour(ModEffects.WITCHING_HOUR_RENDER, 18000, UUID.fromString("b7bff02f-54f4-4589-8917-956c669e376a")));
    public static final RegistryObject<HerbloreEffect> DREDGE = HERBLORE_EFFECTS.register("dredge", () -> new HerbloreEffectDredge(ModEffects.DREDGE_RENDER));
    public static final RegistryObject<HerbloreEffect> PENANCE = HERBLORE_EFFECTS.register("penance", () -> new HerbloreEffectPenance(ModEffects.PENANCE_RENDER));
    public static final RegistryObject<HerbloreEffect> COMET = HERBLORE_EFFECTS.register("comet", () -> new HerbloreEffectComet(ModEffects.COMET_RENDER));
    public static final RegistryObject<HerbloreEffect> HAPTIC = HERBLORE_EFFECTS.register("haptic", () -> new HerbloreEffectHaptic(ModEffects.HAPTIC_RENDER));
    public static final RegistryObject<HerbloreEffect> VERDURE = HERBLORE_EFFECTS.register("verdure", () -> new HerbloreEffectDominion(ModEffects.VERDURE_RENDER, UUID.fromString("db641c15-704d-44aa-90be-aa2df654aff5"), new ImmutableList.Builder<Block>().add(Blocks.GRASS_BLOCK).add(Blocks.DIRT).build()));
    public static final RegistryObject<HerbloreEffect> RUBBLE = HERBLORE_EFFECTS.register("rubble", () -> new HerbloreEffectDominion(ModEffects.RUBBLE_RENDER, UUID.fromString("757d4083-3c6a-4ffc-94c7-5ea5ad59039c"), new ImmutableList.Builder<Block>().add(Blocks.STONE).add(Blocks.COBBLESTONE).build()));
    public static final RegistryObject<HerbloreEffect> NOMAD = HERBLORE_EFFECTS.register("nomad", () -> new HerbloreEffectNomad(ModEffects.NOMAD_RENDER, UUID.fromString("da140e6b-25a3-40ae-85d6-e72bdb1dda71")));
    public static final RegistryObject<HerbloreEffect> FLEET = HERBLORE_EFFECTS.register("fleet", () -> new HerbloreEffectFleet(ModEffects.FLEET_RENDER, UUID.fromString("98f25338-e60e-472d-9e24-4ad3bbaa5f67")));
    public static final RegistryObject<HerbloreEffect> RIGOR = HERBLORE_EFFECTS.register("rigor", () -> new HerbloreEffectRigor(ModEffects.RIGOR_RENDER));
    public static final RegistryObject<HerbloreEffect> FALLARBOR = HERBLORE_EFFECTS.register("fallarbor", () -> new HerbloreEffectFallarbor(ModEffects.FALLARBOR_RENDER));
    public static final RegistryObject<HerbloreEffect> AMBIT = HERBLORE_EFFECTS.register("ambit", () -> new HerbloreEffectAmbit(ModEffects.AMBIT_RENDER, UUID.fromString("d5c45518-e921-4193-a5c2-dd1274524b47")));
    public static final RegistryObject<HerbloreEffect> SHADOWSTEP = HERBLORE_EFFECTS.register("shadowstep", () -> new HerbloreEffectShadowstep(ModEffects.DEBUG_ALPHA_RENDER));
    public static final RegistryObject<HerbloreEffect> GLACIAL = HERBLORE_EFFECTS.register("glacial", () -> new HerbloreEffectGlacial(ModEffects.DEBUG_ALPHA_RENDER));
}
