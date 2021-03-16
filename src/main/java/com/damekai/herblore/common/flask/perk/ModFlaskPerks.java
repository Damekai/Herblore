package com.damekai.herblore.common.flask.perk;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.perk.base.FlaskPerk;
import net.minecraft.potion.Effects;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModFlaskPerks
{
    public static final DeferredRegister<FlaskPerk> FLASK_PERKS = DeferredRegister.create(FlaskPerk.class, Herblore.MOD_ID);

    public static final RegistryObject<FlaskPerk> ADD_DURATION_SMALL = FLASK_PERKS.register("add_duration_small", () -> new FlaskPerkAddDuration("add_duration_small", 200));
    public static final RegistryObject<FlaskPerk> ADD_DURATION_MEDIUM = FLASK_PERKS.register("add_duration_medium", () -> new FlaskPerkAddDuration("add_duration_medium", 400));
    public static final RegistryObject<FlaskPerk> ADD_DURATION_LARGE = FLASK_PERKS.register("add_duration_large", () -> new FlaskPerkAddDuration("add_duration_large", 600));

    public static final RegistryObject<FlaskPerk> MULTIPLY_DURATION_SMALL = FLASK_PERKS.register("multiply_duration_small", () -> new FlaskPerkMultiplyDuration("multiply_duration_small", 1.1f));
    public static final RegistryObject<FlaskPerk> MULTIPLY_DURATION_LARGE = FLASK_PERKS.register("multiply_duration_large", () -> new FlaskPerkMultiplyDuration("multiply_duration_large", 1.2f));

    public static final RegistryObject<FlaskPerk> HEAL_SMALL = FLASK_PERKS.register("heal_small", () -> new FlaskPerkHeal("heal_small", 0.5f));
    public static final RegistryObject<FlaskPerk> HEAL_LARGE = FLASK_PERKS.register("heal_large", () -> new FlaskPerkHeal("heal_large", 1f));

    public static final RegistryObject<FlaskPerk> NIGHT_VISION = FLASK_PERKS.register("night_vision", () -> new FlaskPerkVanillaEffect("night_vision", Effects.NIGHT_VISION, 200, 0));
    public static final RegistryObject<FlaskPerk> INVISIBILITY = FLASK_PERKS.register("invisibility", () -> new FlaskPerkVanillaEffect("invisibility", Effects.INVISIBILITY, 100, 0));
    public static final RegistryObject<FlaskPerk> ABSORPTION = FLASK_PERKS.register("absorption", () -> new FlaskPerkVanillaEffect("absorption", Effects.ABSORPTION, 200, 0));

    public static FlaskPerk getFlaskPerkFromRegistry(String name)
    {
        RegistryObject<FlaskPerk> match = FLASK_PERKS.getEntries().stream().filter((flaskPerkSupplier) -> flaskPerkSupplier.get().getRegistryName().toString().equals(name)).findAny().orElse(null);
        return match != null ? match.get() : null;
    }
}
