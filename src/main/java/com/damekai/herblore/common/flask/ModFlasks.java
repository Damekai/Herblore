package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.effect.ModFlaskEffects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModFlasks
{
    public static final DeferredRegister<Flask> FLASKS = DeferredRegister.create(Flask.class, Herblore.MOD_ID);

    public static final RegistryObject<Flask> DEBUG_AB = FLASKS.register("debug_ab", () -> new Flask("debug_ab", ModEffects.DEBUG_BONANZA_RENDER,
            ModFlaskEffects.DEBUG_ALPHA,
            ModFlaskEffects.DEBUG_BETA));

    public static final RegistryObject<Flask> DEBUG_CDE = FLASKS.register("debug_cde", () -> new Flask("debug_cde", ModEffects.DEBUG_BONANZA_RENDER,
            ModFlaskEffects.DEBUG_GAMMA,
            ModFlaskEffects.DEBUG_DELTA,
            ModFlaskEffects.DEBUG_EPSILON));

    public static final RegistryObject<Flask> DEBUG_BONANZA = FLASKS.register("debug_bonanza", () -> new Flask("debug_bonanza", ModEffects.DEBUG_BONANZA_RENDER,
                    ModFlaskEffects.DEBUG_ALPHA,
                    ModFlaskEffects.DEBUG_BETA,
                    ModFlaskEffects.DEBUG_GAMMA,
                    ModFlaskEffects.DEBUG_DELTA,
                    ModFlaskEffects.DEBUG_EPSILON));
}
