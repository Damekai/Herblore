package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.herbloreeffect.ModHerbloreEffects;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects
{
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Herblore.MOD_ID);

    public static RegistryObject<Effect> TOXICITY_RENDER = EFFECTS.register("toxicity", GuiToxicityEffect::new);

    public static final RegistryObject<Effect> DEBUG_ALPHA_RENDER = EFFECTS.register("debug_alpha", () -> new GuiFlaskEffect(ModHerbloreEffects.DEBUG_ALPHA));

    public static final RegistryObject<Effect> STRIDER_RENDER = EFFECTS.register("strider", () -> new GuiFlaskEffect(ModHerbloreEffects.STRIDER));

    public static final RegistryObject<Effect> QUENCH_RENDER = EFFECTS.register("quench", () -> new GuiFlaskEffect(ModHerbloreEffects.QUENCH));

    public static final RegistryObject<Effect> HIGH_NOON_RENDER = EFFECTS.register("high_noon", () -> new GuiFlaskEffect(ModHerbloreEffects.HIGH_NOON));
    public static final RegistryObject<Effect> WITCHING_HOUR_RENDER = EFFECTS.register("witching_hour", () -> new GuiFlaskEffect(ModHerbloreEffects.WITCHING_HOUR));

    public static final RegistryObject<Effect> DREDGE_RENDER = EFFECTS.register("dredge", () -> new GuiFlaskEffect(ModHerbloreEffects.DREDGE));

    public static final RegistryObject<Effect> PENANCE_RENDER = EFFECTS.register("penance", () -> new GuiFlaskEffect(ModHerbloreEffects.PENANCE));

    public static final RegistryObject<Effect> COMET_RENDER = EFFECTS.register("comet", () -> new GuiFlaskEffect(ModHerbloreEffects.COMET));

    public static final RegistryObject<Effect> HAPTIC_RENDER = EFFECTS.register("haptic", () -> new GuiFlaskEffect(ModHerbloreEffects.HAPTIC));

    public static final RegistryObject<Effect> NOMAD_RENDER = EFFECTS.register("nomad", () -> new GuiFlaskEffect(ModHerbloreEffects.NOMAD));

    public static final RegistryObject<Effect> FLEET_RENDER = EFFECTS.register("fleet", () -> new GuiFlaskEffect(ModHerbloreEffects.FLEET));

    public static final RegistryObject<Effect> RIGOR_RENDER = EFFECTS.register("rigor", () -> new GuiFlaskEffect(ModHerbloreEffects.RIGOR));

    public static final RegistryObject<Effect> FALLARBOR_RENDER = EFFECTS.register("fallarbor", () -> new GuiFlaskEffect(ModHerbloreEffects.FALLARBOR));

    public static final RegistryObject<Effect> AMBIT_RENDER = EFFECTS.register("ambit", () -> new GuiFlaskEffect(ModHerbloreEffects.AMBIT));
}
