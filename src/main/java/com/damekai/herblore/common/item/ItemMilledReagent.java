package com.damekai.herblore.common.item;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

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
