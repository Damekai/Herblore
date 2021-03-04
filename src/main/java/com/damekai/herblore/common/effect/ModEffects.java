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
}
