package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.ModRegistries;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModHerbloreEffects
{
    public static final DeferredRegister<HerbloreEffect> HERBLORE_EFFECTS = DeferredRegister.create(HerbloreEffect.class, Herblore.MOD_ID);

    public static final RegistryObject<HerbloreEffect> DEBUG_ALPHA = HERBLORE_EFFECTS.register("debug_alpha", () -> new HerbloreEffectDebug("debug_alpha", "alpha"));
    public static final RegistryObject<HerbloreEffect> DEBUG_BETA = HERBLORE_EFFECTS.register("debug_beta", () -> new HerbloreEffectDebug("debug_beta", "beta"));
    public static final RegistryObject<HerbloreEffect> DEBUG_GAMMA = HERBLORE_EFFECTS.register("debug_gamma", () -> new HerbloreEffectDebug("debug_gamma", "gamma"));
    public static final RegistryObject<HerbloreEffect> DEBUG_DELTA = HERBLORE_EFFECTS.register("debug_delta", () -> new HerbloreEffectDebug("debug_delta", "delta"));
    public static final RegistryObject<HerbloreEffect> DEBUG_EPSILON = HERBLORE_EFFECTS.register("debug_epsilon", () -> new HerbloreEffectDebug("debug_epsilon", "epsilon"));

    public static final RegistryObject<HerbloreEffect> HEALTH_REGENERATION = HERBLORE_EFFECTS.register("health_regeneration", HerbloreEffectHealthRegeneration::new);
}
