package com.damekai.herblore.common.recipe;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.flask.base.FlaskEffectInstance;
import com.damekai.herblore.common.item.ItemCatalyst;
import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.util.FlaskHelper;
import com.google.common.collect.ImmutableList;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;
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
    public boolean matches(@Nonnull CraftingInventory inventory, @Nonnull World world) // TODO: Clean this up.
    {
        ArrayList<Item> inputs = getInputItems(inventory);
        if (!hasUniqueInputItems(inputs) || inputs.size() != 4)
        {
            return false; // Has duplicate reagents or more than four inputs.
        }

        ItemReagent reagent = getReagent(inventory);
        if (reagent == null)
        {
            return false; // Does not have a reagent.
        }

        ArrayList<ItemCatalyst> catalysts = getCatalysts(inventory);
        if (catalysts.size() != 2)
        {
            return false; // Does not have exactly two catalysts.
        }

        return inputs.contains(ModItems.FLASK_OF_WATER.get());
    }

    @Nonnull
    @Override
    public ItemStack getCraftingResult(@Nonnull CraftingInventory inventory)
    {
        ItemStack crudeFlaskOutput = new ItemStack(ModItems.CRUDE_FLASK.get());

        FlaskEffectInstance flaskEffectInstance = FlaskHelper.makeFlaskEffectInstance(getReagent(inventory));
        crudeFlaskOutput.getOrCreateTag().put("flask_effect_instance", flaskEffectInstance.write(new CompoundNBT()));
        crudeFlaskOutput.getOrCreateTag().putInt("flask_effect_color", flaskEffectInstance.getFlaskEffect().getColor());

        Herblore.LOGGER.debug(crudeFlaskOutput.getOrCreateTag().toString());

        return crudeFlaskOutput;
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return width >= 2 && height >= 2;
    }

    @Nonnull
    @Override
    public ItemStack getRecipeOutput()
    {
        return new ItemStack(ModItems.CRUDE_FLASK.get());
    }

    @Nonnull
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

    private ItemReagent getReagent(CraftingInventory inventory)
    {
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem() instanceof ItemReagent)
            {
                return (ItemReagent) stack.getItem();
            }
        }
        return null;
    }

    private ArrayList<ItemCatalyst> getCatalysts(CraftingInventory inventory)
    {
        ArrayList<ItemCatalyst> catalysts = new ArrayList<>();
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack stack = inventory.getStackInSlot(i);
            if (stack.getItem() instanceof ItemCatalyst)
            {
                catalysts.add((ItemCatalyst) stack.getItem());
            }
        }
        return catalysts;
    }

    private boolean hasUniqueInputItems(ArrayList<Item> inputs)
    {
        Set<Item> inputSet = new HashSet<>(inputs);
        return inputSet.size() == inputs.size();
    }
}
