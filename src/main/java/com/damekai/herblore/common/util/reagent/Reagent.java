package com.damekai.herblore.common.util.reagent;

import com.damekai.herblore.common.block.BlockPerennialCrop;
import com.damekai.herblore.common.flask.base.FlaskEffect;
import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.item.ItemReagentSeeds;
import com.google.common.collect.ImmutableMap;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.Map;

public class Reagent
{
    private final String name;

    private final RegistryObject<FlaskEffect> flaskEffect;
    private final RegistryObject<ItemReagentSeeds> seeds;
    private final ImmutableMap<Integer, RegistryObject<ItemReagent>> tiers;

    public Reagent(Reagent.Initializer initializer)
    {
        name = initializer.name;

        flaskEffect = initializer.flaskEffect;
        seeds = initializer.seeds;
        tiers = ImmutableMap.copyOf(initializer.tiers);
    }

    public String getName()
    {
        return name;
    }

    public RegistryObject<FlaskEffect> getFlaskEffect()
    {
        return flaskEffect;
    }

    public RegistryObject<ItemReagentSeeds> getSeeds()
    {
        return seeds;
    }

    public ImmutableMap<Integer, RegistryObject<ItemReagent>> getTiers()
    {
        return tiers;
    }

    public static class Initializer
    {
        private final DeferredRegister<Item> register;
        private final String name;

        private RegistryObject<FlaskEffect> flaskEffect;
        private RegistryObject<ItemReagentSeeds> seeds;
        private Map<Integer, RegistryObject<ItemReagent>> tiers;

        public Initializer(DeferredRegister<Item> register, String name)
        {
            this.register = register;
            this.name = name;
        }

        public Initializer flaskEffect(RegistryObject<FlaskEffect> flaskEffect)
        {
            this.flaskEffect = flaskEffect;
            return this;
        }

        public Initializer seeds(RegistryObject<BlockPerennialCrop> crop)
        {
            seeds = register.register(name + "_seeds", () -> new ItemReagentSeeds(crop.get()));
            return this;
        }

        public Initializer tiers(int lowestTier, int highestTier)
        {
            if (flaskEffect == null)
            {
                throw new IllegalStateException("Reagent requires Flask Effect to be added before tiers are initialized.");
            }

            tiers = new HashMap<>();

            for (int i = lowestTier; i <= highestTier; i++)
            {
                String tierName = TierNames.TIER_NAMES.get(i);

                final int potency = i; // Needs to be final for use in lambda.
                tiers.put(potency, register.register(tierName + "_" + name, () -> new ItemReagent(potency, flaskEffect)));
            }

            return this;
        }
    }
}
