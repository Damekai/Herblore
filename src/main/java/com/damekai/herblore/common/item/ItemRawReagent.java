package com.damekai.herblore.common.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;

public class ItemRawReagent extends Item
{
    private static final Random RANDOM = new Random();

    private final RegistryObject<ItemReagent> outputReagent;
    private final int minPotency;
    private final int maxPotency;

    public ItemRawReagent(RegistryObject<ItemReagent> outputReagent, int minPotency, int maxPotency)
    {
        super(ModItems.defaultItemProperties());
        this.outputReagent = outputReagent;
        this.minPotency = minPotency;
        this.maxPotency = maxPotency;
    }

    public RegistryObject<ItemReagent> getOutputReagent()
    {
        return outputReagent;
    }

    public ItemStack getInstanceWithRandomPotency() // TODO: Use weighted set to set probabilities for different potencies (i.e. make higher potencies rarer).
    {
        ItemStack result = new ItemStack(this);
        result.getOrCreateTag().putInt("potency", minPotency + RANDOM.nextInt(maxPotency - minPotency + 1));
        return result;
    }
}
