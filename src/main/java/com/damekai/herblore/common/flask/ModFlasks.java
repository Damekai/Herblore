package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.flaskeffect.ModFlaskEffects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModFlasks
{
    public static final DeferredRegister<Flask> FLASKS = DeferredRegister.create(Flask.class, Herblore.MOD_ID);

    public static final RegistryObject<Flask> DEBUG_AB = FLASKS.register("debug_ab", () -> new Flask("debug_ab", 0x1CDB00, ModEffects.DEBUG_BONANZA_RENDER,
            ModFlaskEffects.DEBUG_ALPHA,
            ModFlaskEffects.DEBUG_BETA));

    public static final RegistryObject<Flask> DEBUG_CDE = FLASKS.register("debug_cde", () -> new Flask("debug_cde", 0x83A02, ModEffects.DEBUG_BONANZA_RENDER,
            ModFlaskEffects.DEBUG_GAMMA,
            ModFlaskEffects.DEBUG_DELTA,
            ModFlaskEffects.DEBUG_EPSILON));

    public static final RegistryObject<Flask> DEBUG_BONANZA = FLASKS.register("debug_bonanza", () -> new Flask("debug_bonanza", 0x6B20E8, ModEffects.DEBUG_BONANZA_RENDER,
            ModFlaskEffects.DEBUG_ALPHA,
            ModFlaskEffects.DEBUG_BETA,
            ModFlaskEffects.DEBUG_GAMMA,
            ModFlaskEffects.DEBUG_DELTA,
            ModFlaskEffects.DEBUG_EPSILON));

    public static final RegistryObject<Flask> STRIDER = FLASKS.register("strider", () -> new Flask("strider", 0x52DE23, ModEffects.STRIDER_RENDER,
            ModFlaskEffects.BOUNDING));

    public static final RegistryObject<Flask> QUENCH = FLASKS.register("quench", () -> new Flask("quench", 0xFF7C3F, ModEffects.QUENCH_RENDER,
            ModFlaskEffects.FIRE_EATER));

    public static final RegistryObject<Flask> HIGH_NOON = FLASKS.register("high_noon", () -> new Flask("high_noon", 0xFDFBD3, ModEffects.HIGH_NOON_RENDER,
            ModFlaskEffects.HOUR_POWER_NOON));
    public static final RegistryObject<Flask> WITCHING_HOUR = FLASKS.register("witching_hour", () -> new Flask("witching_hour", 0x27055E, ModEffects.WITCHING_HOUR_RENDER,
            ModFlaskEffects.HOUR_POWER_MIDNIGHT));

    public static final RegistryObject<Flask> DREDGE = FLASKS.register("dredge", () -> new Flask("dredge", 0x6B4A46, ModEffects.DREDGE_RENDER,
            ModFlaskEffects.SUBTERRANIAN));

    public static Flask getFlaskFromRegistry(String name)
    {
        RegistryObject<Flask> match = FLASKS.getEntries().stream().filter((flaskSupplier) -> flaskSupplier.get().getRegistryName().toString().equals(name)).findAny().orElse(null);
        return match != null ? match.get() : null;
    }
}
