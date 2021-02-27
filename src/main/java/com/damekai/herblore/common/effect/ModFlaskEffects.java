package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.Herblore;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModFlaskEffects
{
    public static final DeferredRegister<FlaskEffect> FLASK_EFFECTS = DeferredRegister.create(FlaskEffect.class, Herblore.MOD_ID);

    public static final RegistryObject<FlaskEffect> DEBUG_ALPHA = FLASK_EFFECTS.register("debug_alpha", () -> new FlaskEffectDebug("debug_alpha", "alpha"));
    public static final RegistryObject<FlaskEffect> DEBUG_BETA = FLASK_EFFECTS.register("debug_beta", () -> new FlaskEffectDebug("debug_beta", "beta"));
    public static final RegistryObject<FlaskEffect> DEBUG_GAMMA = FLASK_EFFECTS.register("debug_gamma", () -> new FlaskEffectDebug("debug_gamma", "gamma"));
    public static final RegistryObject<FlaskEffect> DEBUG_DELTA = FLASK_EFFECTS.register("debug_delta", () -> new FlaskEffectDebug("debug_delta", "delta"));
    public static final RegistryObject<FlaskEffect> DEBUG_EPSILON = FLASK_EFFECTS.register("debug_epsilon", () -> new FlaskEffectDebug("debug_epsilon", "epsilon"));

    public static final RegistryObject<FlaskEffect> HEALTH_REGENERATION = FLASK_EFFECTS.register("health_regeneration", FlaskEffectHealthRegeneration::new);
}
