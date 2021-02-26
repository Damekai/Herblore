package com.damekai.herblore.common.recipe;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.ItemMilledReagent;
import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.util.FlaskHelper;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class CrudeFlaskRecipe extends SpecialRecipe
{
    public static SpecialRecipeSerializer<CrudeFlaskRecipe> SERIALIZER = new SpecialRecipeSerializer<>(CrudeFlaskRecipe::new);

    public CrudeFlaskRecipe(ResourceLocation id)
    {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world)
    {
        ArrayList<Item> inputItems = getInputItems(inventory);
        if (!hasUniqueInputItems(inputItems) || inputItems.size() != 4)
        {
            return false; // Has duplicate reagents or more than four inputs.
        }

        ArrayList<ItemReagent> inputReagents = getInputReagents(inventory);
        if (inputReagents.size() != 3)
        {
            return false; // Does not have exactly three reagents.
        }

        return inputItems.contains(ModItems.EMPTY_FLASK.get()); // TODO: Change this to look for flask of water.
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inventory)
    {
        ItemStack crudeFlaskOutput = new ItemStack(ModItems.CRUDE_FLASK.get());

        ArrayList<ItemReagent> inputReagents = getInputReagents(inventory);
        crudeFlaskOutput.getOrCreateTag().put("flask_effects", FlaskHelper.makeFlaskNBTList(inputReagents.toArray(new ItemReagent[0])));

        Herblore.LOGGER.debug(crudeFlaskOutput.getOrCreateTag().toString());

        return crudeFlaskOutput;
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return width >= 2 && height >= 2;
    }

    @Override
    public ItemStack getRecipeOutput()
    {
        return new ItemStack(ModItems.CRUDE_FLASK.get());
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }

    private ArrayList<Item> getInputItems(CraftingInventory inventory)
    {
        ArrayList<Item> inputItems = new ArrayList<>();
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack inputStack = inventory.getStackInSlot(i);
            if (inputStack != ItemStack.EMPTY)
            {
                inputItems.add(inputStack.getItem());
            }
        }
        return inputItems;
    }

    private ArrayList<ItemReagent> getInputReagents(CraftingInventory inventory)
    {
        ArrayList<ItemReagent> inputReagents = new ArrayList<>();
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack inputStack = inventory.getStackInSlot(i);
            if (inputStack != ItemStack.EMPTY && inputStack.getItem() instanceof ItemMilledReagent)
            {
                inputReagents.add(((ItemMilledReagent)inputStack.getItem()).getBaseReagent().get());
            }
        }
        return inputReagents;
    }

    private boolean hasUniqueInputItems(ArrayList<Item> inputs)
    {
        Set<Item> inputSet = new HashSet<>(inputs);
        return inputSet.size() == inputs.size();
    }
}
