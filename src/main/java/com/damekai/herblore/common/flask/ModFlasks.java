package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.herbloreeffect.ModHerbloreEffects;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffect;
import com.damekai.herblore.common.herbloreeffect.base.HerbloreEffectInstance;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModFlasks
{
    public static final DeferredRegister<Flask> FLASKS = DeferredRegister.create(Flask.class, Herblore.MOD_ID);

    public static final RegistryObject<Flask> DREDGE = FLASKS.register("dredge", () -> new Flask(
            "flask.herblore.dredge",
            new HerbloreEffectInstance(ModHerbloreEffects.DREDGE, 0, 2400),
            4,
            0x6B4A46));
}