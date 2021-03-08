package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.UUID;

public class ModFlaskEffects
{
    public static final DeferredRegister<FlaskEffect> FLASK_EFFECTS = DeferredRegister.create(FlaskEffect.class, Herblore.MOD_ID);

    public static final RegistryObject<FlaskEffect> DEBUG_ALPHA = FLASK_EFFECTS.register("debug_alpha", () -> new FlaskEffectDebug(new FlaskEffect.Properties().translationName("debug_alpha").color(0).guiEffect(ModEffects.DEBUG_ALPHA_RENDER), "alpha"));

    public static final RegistryObject<FlaskEffect> STRIDER = FLASK_EFFECTS.register("strider", () -> new FlaskEffectStrider(new FlaskEffect.Properties().translationName("strider").color(0x52DE23).guiEffect(ModEffects.STRIDER_RENDER)));

    public static final RegistryObject<FlaskEffect> QUENCH = FLASK_EFFECTS.register("quench", () -> new FlaskEffectQuench(new FlaskEffect.Properties().translationName("quench").color(0xFF7C3F).guiEffect(ModEffects.QUENCH_RENDER)));

    public static final RegistryObject<FlaskEffect> HIGH_NOON = FLASK_EFFECTS.register("high_noon", () -> new FlaskEffectPowerHour(new FlaskEffect.Properties().translationName("high_noon").color(0xFDFBD3).guiEffect(ModEffects.HIGH_NOON_RENDER),
            6000, UUID.fromString("d6baf902-b953-447f-bcaa-d9618c7474e8")));
    public static final RegistryObject<FlaskEffect> WITCHING_HOUR = FLASK_EFFECTS.register("witching_hour", () -> new FlaskEffectPowerHour(new FlaskEffect.Properties().translationName("witching_hour").color(0x27055E).guiEffect(ModEffects.WITCHING_HOUR_RENDER),
            18000, UUID.fromString("b7bff02f-54f4-4589-8917-956c669e376a")));

    public static final RegistryObject<FlaskEffect> DREDGE = FLASK_EFFECTS.register("dredge", () -> new FlaskEffectDredge(new FlaskEffect.Properties().translationName("dredge").color(0x6B4A46).guiEffect(ModEffects.DREDGE_RENDER), 20));

    public static final RegistryObject<FlaskEffect> PENANCE = FLASK_EFFECTS.register("penance", () -> new FlaskEffectPenance(new FlaskEffect.Properties().translationName("penance").color(0x9E0313).guiEffect(ModEffects.PENANCE_RENDER)));

    public static final RegistryObject<FlaskEffect> COMET = FLASK_EFFECTS.register("comet", () -> new FlaskEffectComet(new FlaskEffect.Properties().translationName("comet").color(0xACF5EF).guiEffect(ModEffects.COMET_RENDER)));

    public static FlaskEffect getFlaskEffectFromRegistry(String name)
    {
        RegistryObject<FlaskEffect> match = FLASK_EFFECTS.getEntries().stream().filter((flaskEffectSupplier) -> flaskEffectSupplier.get().getRegistryName().toString().equals(name)).findAny().orElse(null);
        return match != null ? match.get() : null;
    }
}
