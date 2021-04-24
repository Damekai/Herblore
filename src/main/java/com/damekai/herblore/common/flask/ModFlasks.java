package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.ModRegistries;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.herbloreeffect.ModHerbloreEffects;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.DeferredRegister;

public class ModFlasks
{
    public static final DeferredRegister<Flask> FLASKS = DeferredRegister.create(Flask.class, Herblore.MOD_ID);

    public static final RegistryObject<Flask> STRIDER = FLASKS.register("strider", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.STRIDER, 0, 6000), 0x52DE23));

    public static final RegistryObject<Flask> QUENCH = FLASKS.register("quench", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.QUENCH, 0, 3600), 0xFF7C3F));

    public static final RegistryObject<Flask> HIGH_NOON = FLASKS.register("high_noon", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.HIGH_NOON, 0, 3600), 0xFDFBD3));

    public static final RegistryObject<Flask> WITCHING_HOUR = FLASKS.register("witching_hour", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.WITCHING_HOUR, 0, 3600), 0x27055E));

    public static final RegistryObject<Flask> DREDGE = FLASKS.register("dredge", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.DREDGE, 0, 4800), 0x6B4A46));

    public static final RegistryObject<Flask> PENANCE = FLASKS.register("penance", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.PENANCE, 0, 3600), 0x9E0313));

    public static final RegistryObject<Flask> COMET = FLASKS.register("comet", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.COMET, 0, 4800), 0xACF5EF));

    public static final RegistryObject<Flask> HAPTIC = FLASKS.register("haptic", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.HAPTIC, 0, 6000), 0xF75AFA));

    public static final RegistryObject<Flask> NOMAD = FLASKS.register("nomad", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.NOMAD, 0, 4800), 0xD99B0B));

    public static final RegistryObject<Flask> FLEET = FLASKS.register("fleet", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.FLEET, 0, 3600), 0xF7F80C));

    public static final RegistryObject<Flask> RIGOR = FLASKS.register("rigor", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.RIGOR, 0, 3600), 0xE8420E));

    public static final RegistryObject<Flask> FALLARBOR = FLASKS.register("fallarbor", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.FALLARBOR, 0, 6000), 0x164706));

    public static final RegistryObject<Flask> AMBIT = FLASKS.register("ambit", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.AMBIT, 0, 4800), 0x7A0069));

    public static final RegistryObject<Flask> SHADOWSTEP = FLASKS.register("shadowstep", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.SHADOWSTEP, 0, 200), 0x7600F5));

    public static final RegistryObject<Flask> GLACIAL = FLASKS.register("glacial", () -> new Flask(
            new HerbloreEffectInstance(ModHerbloreEffects.GLACIAL, 0, 4800), 0xD5FDEC));
}