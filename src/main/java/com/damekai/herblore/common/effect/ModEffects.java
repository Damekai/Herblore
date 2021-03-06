package com.damekai.herblore.common.effect;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.ModFlasks;
import net.minecraft.potion.Effect;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEffects
{
    public static final DeferredRegister<Effect> EFFECTS = DeferredRegister.create(ForgeRegistries.POTIONS, Herblore.MOD_ID);

    public static RegistryObject<Effect> TOXICITY_RENDER = EFFECTS.register("toxicity", GuiToxicityEffect::new);

    public static final RegistryObject<Effect> DEBUG_BONANZA_RENDER = EFFECTS.register("debug_bonanza", () -> new GuiFlaskEffect(ModFlasks.DEBUG_BONANZA));

    public static final RegistryObject<Effect> STRIDER_RENDER = EFFECTS.register("strider", () -> new GuiFlaskEffect(ModFlasks.STRIDER));

    public static final RegistryObject<Effect> QUENCH_RENDER = EFFECTS.register("quench", () -> new GuiFlaskEffect(ModFlasks.QUENCH));

    public static final RegistryObject<Effect> HIGH_NOON_RENDER = EFFECTS.register("high_noon", () -> new GuiFlaskEffect(ModFlasks.HIGH_NOON));
    public static final RegistryObject<Effect> WITCHING_HOUR_RENDER = EFFECTS.register("witching_hour", () -> new GuiFlaskEffect(ModFlasks.WITCHING_HOUR));

    public static final RegistryObject<Effect> DREDGE_RENDER = EFFECTS.register("dredge", () -> new GuiFlaskEffect(ModFlasks.DREDGE));

    public static final RegistryObject<Effect> PENANCE_RENDER = EFFECTS.register("penance", () -> new GuiFlaskEffect(ModFlasks.PENANCE));
}
