package com.damekai.herblore.common.flask;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.common.Tags;
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

    public static final RegistryObject<FlaskEffect> HAPTIC = FLASK_EFFECTS.register("haptic", () -> new FlaskEffectHaptic(new FlaskEffect.Properties().translationName("haptic").color(0xF75AFA).guiEffect(ModEffects.HAPTIC_RENDER)));

    public static final RegistryObject<FlaskEffect> VERDURE = FLASK_EFFECTS.register("verdure", () -> new FlaskEffectDominion(new FlaskEffect.Properties().translationName("verdure").color(0x089C33).guiEffect(ModEffects.VERDURE_RENDER),
            UUID.fromString("db641c15-704d-44aa-90be-aa2df654aff5"),
            new ImmutableList.Builder<Block>()
                    .add(Blocks.GRASS_BLOCK)
                    .add(Blocks.DIRT)
                    .build()));

    public static final RegistryObject<FlaskEffect> RUBBLE = FLASK_EFFECTS.register("rubble", () -> new FlaskEffectDominion(new FlaskEffect.Properties().translationName("rubble").color(0x7A746B).guiEffect(ModEffects.RUBBLE_RENDER),
            UUID.fromString("757d4083-3c6a-4ffc-94c7-5ea5ad59039c"),
            new ImmutableList.Builder<Block>()
                    .add(Blocks.STONE)
                    .add(Blocks.COBBLESTONE)
                    .build()));

    public static FlaskEffect getFlaskEffectFromRegistry(String name)
    {
        RegistryObject<FlaskEffect> match = FLASK_EFFECTS.getEntries().stream().filter((flaskEffectSupplier) -> flaskEffectSupplier.get().getRegistryName().toString().equals(name)).findAny().orElse(null);
        return match != null ? match.get() : null;
    }
}
