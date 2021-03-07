package com.damekai.herblore.common.recipe;

import com.damekai.herblore.common.item.ItemPestleAndMortar;
import com.damekai.herblore.common.item.ItemRawReagent;
import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.item.ModItems;
import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.SpecialRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import java.util.ArrayList;

public class MilledReagentRecipe extends SpecialRecipe
{
    public static SpecialRecipeSerializer<MilledReagentRecipe> SERIALIZER = new SpecialRecipeSerializer<>(MilledReagentRecipe::new);

    public MilledReagentRecipe(ResourceLocation id)
    {
        super(id);
    }

    @Override
    public boolean matches(CraftingInventory inventory, World world)
    {
        ArrayList<ItemStack> inputItemStacks = getInputItemStacks(inventory);
        if (inputItemStacks.size() == 2)
        {
            ItemStack pestleAndMortar = inputItemStacks.stream()
                    .filter((itemStack) -> itemStack.getItem() instanceof ItemPestleAndMortar)
                    .findAny()
                    .orElse(null);

            ItemStack rawReagent = inputItemStacks.stream()
                    .filter((itemStack) -> itemStack.getItem() instanceof ItemRawReagent)
                    .findAny()
                    .orElse(null);

            return pestleAndMortar != null && rawReagent != null;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(CraftingInventory inventory)
    {
        ItemStack rawReagent = getInputItemStacks(inventory).stream()
                .filter((itemStack) -> itemStack.getItem() instanceof ItemRawReagent)
                .findAny()
                .orElse(null);

        if (rawReagent != null)
        {
            ItemStack result = new ItemStack(((ItemRawReagent) rawReagent.getItem()).getOutputReagent().get());
            result.getOrCreateTag().putInt("potency", rawReagent.getOrCreateTag().getInt("potency"));
            return result;
        }

        return null;
    }

    @Override
    public boolean canFit(int width, int height)
    {
        return width * height >= 2;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return SERIALIZER;
    }

    private ArrayList<ItemStack> getInputItemStacks(CraftingInventory inventory)
    {
        ArrayList<ItemStack> inputItemStacks = new ArrayList<>();
        for (int i = 0; i < inventory.getSizeInventory(); i++)
        {
            ItemStack inputStack = inventory.getStackInSlot(i);
            if (inputStack != ItemStack.EMPTY)
            {
                inputItemStacks.add(inputStack);
            }
        }
        return inputItemStacks;
    }
}
