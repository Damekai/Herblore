package com.damekai.herblore.common.item;

import com.damekai.herblore.common.effect.HerbloreEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;

import java.util.Map;

public class ItemMilledReagent extends Item
{
    private final RegistryObject<ItemReagent> BASE_REAGENT;

    public ItemMilledReagent(RegistryObject<ItemReagent> baseReagent)
    {
        super(ModItems.defaultItemProperties());
        BASE_REAGENT = baseReagent;
    }

    public RegistryObject<ItemReagent> getBaseReagent()
    {
        return BASE_REAGENT;
    }
}
