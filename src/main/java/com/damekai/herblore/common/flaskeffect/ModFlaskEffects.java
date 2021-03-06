package com.damekai.herblore.common.flaskeffect;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flaskeffect.base.FlaskEffect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.UUID;

public class ModFlaskEffects
{
    public static final DeferredRegister<FlaskEffect> FLASK_EFFECTS = DeferredRegister.create(FlaskEffect.class, Herblore.MOD_ID);

    public static final RegistryObject<FlaskEffect> DEBUG_ALPHA = FLASK_EFFECTS.register("debug_alpha", () -> new FlaskEffectDebug("debug_alpha", "alpha"));
    public static final RegistryObject<FlaskEffect> DEBUG_BETA = FLASK_EFFECTS.register("debug_beta", () -> new FlaskEffectDebug("debug_beta", "beta"));
    public static final RegistryObject<FlaskEffect> DEBUG_GAMMA = FLASK_EFFECTS.register("debug_gamma", () -> new FlaskEffectDebug("debug_gamma", "gamma"));
    public static final RegistryObject<FlaskEffect> DEBUG_DELTA = FLASK_EFFECTS.register("debug_delta", () -> new FlaskEffectDebug("debug_delta", "delta"));
    public static final RegistryObject<FlaskEffect> DEBUG_EPSILON = FLASK_EFFECTS.register("debug_epsilon", () -> new FlaskEffectDebug("debug_epsilon", "epsilon"));

    public static final RegistryObject<FlaskEffect> HEALTH_REGENERATION = FLASK_EFFECTS.register("health_regeneration", FlaskEffectHealthRegeneration::new);

    public static final RegistryObject<FlaskEffect> BOUNDING = FLASK_EFFECTS.register("bounding", FlaskEffectBounding::new);

    public static final RegistryObject<FlaskEffect> FIRE_EATER = FLASK_EFFECTS.register("fire_eater", FlaskEffectFireEater::new);

    public static final RegistryObject<FlaskEffect> POWER_HOUR_NOON = FLASK_EFFECTS.register("power_hour_noon", () -> new FlaskEffectPowerHour("power_hour_noon", 6000, UUID.fromString("d6baf902-b953-447f-bcaa-d9618c7474e8")));
    public static final RegistryObject<FlaskEffect> POWER_HOUR_MIDNIGHT = FLASK_EFFECTS.register("power_hour_midnight", () -> new FlaskEffectPowerHour("power_hour_midnight", 18000, UUID.fromString("b7bff02f-54f4-4589-8917-956c669e376a")));

    public static final RegistryObject<FlaskEffect> SUBTERRANEAN = FLASK_EFFECTS.register("subterranean", FlaskEffectSubterranean::new);

    public static final RegistryObject<FlaskEffect> BLOOD_PACT = FLASK_EFFECTS.register("blood_pact", FlaskEffectBloodPact::new);
}
