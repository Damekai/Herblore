package com.damekai.herblore.effusion;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.effusion.base.Effusion;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

public class ModEffusions
{
    public static final DeferredRegister<Effusion> EFFUSIONS = DeferredRegister.create(Effusion.class, Herblore.MOD_ID);

    public static final RegistryObject<Effusion> CRUMBLEMIST = EFFUSIONS.register("crumblemist", EffusionCrumblemist::new);
    public static final RegistryObject<Effusion> VERDURE = EFFUSIONS.register("verdure", EffusionVerdure::new);
    public static final RegistryObject<Effusion> PHEROMONE = EFFUSIONS.register("pheromone", EffusionPheromone::new);
    public static final RegistryObject<Effusion> TERRASMOG = EFFUSIONS.register("terrasmog", EffusionTerrasmog::new);
}
