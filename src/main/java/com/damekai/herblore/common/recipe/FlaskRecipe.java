package com.damekai.herblore.common.recipe;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.ModRegistries;
import com.damekai.herblore.common.flask.Flask;
import com.damekai.herblore.common.item.ItemFlask;
import com.damekai.herblore.common.item.ItemReagent;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.util.FlaskStationInventory;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class FlaskRecipe implements IRecipe<FlaskStationInventory>
{
    public static final IRecipeType<FlaskRecipe> FLASK_RECIPE = IRecipeType.register("herblore:flask");

    private final ResourceLocation recipeId;
    private final ImmutableMap<Supplier<ItemReagent>, Integer> requiredReagentQuantities;
    private final Supplier<Flask> result;

    public FlaskRecipe(ResourceLocation recipeId, ImmutableMap<Supplier<ItemReagent>, Integer> requiredReagentQuantities, Supplier<Flask> result)
    {
        this.recipeId = recipeId;
        this.requiredReagentQuantities = requiredReagentQuantities;
        this.result = result;
    }

    @Override
    public boolean matches(FlaskStationInventory inventory, World world)
    {
        if (inventory.getItem(0).getItem() != ModItems.FLASK_OF_WATER.get())
        {
            return false;
        }

        if (!inventory.isValidPuzzleSolution())
        {
            return false;
        }

        HashMap<ItemReagent, Integer> inputReagents = new HashMap<>();

        for (int i = 1; i < 18; i++)
        {
            ItemStack stack = inventory.getItem(i);
            if (stack != ItemStack.EMPTY && stack.getItem() instanceof ItemReagent)
            {
                ItemReagent reagent = (ItemReagent) stack.getItem();
                if (inputReagents.containsKey(reagent))
                {
                    inputReagents.put(reagent, inputReagents.get(reagent) + 1);
                }
                else
                {
                    inputReagents.put(reagent, 1);
                }
            }
        }

        // Check if maps are equal.
        if (inputReagents.size() != requiredReagentQuantities.size())
        {
            return false;
        }
        for (Map.Entry<Supplier<ItemReagent>, Integer> entry : requiredReagentQuantities.entrySet())
        {
            ItemReagent reagent = entry.getKey().get();

            if (!inputReagents.containsKey(reagent) || inputReagents.get(reagent).intValue() != entry.getValue().intValue())
            {
                return false;
            }
        }

        return true;
    }

    @Override
    public ItemStack assemble(FlaskStationInventory inventory)
    {
        ItemStack flask = new ItemStack(ModItems.FLASK::get);
        flask.getOrCreateTag().putString("flask", ModRegistries.FLASKS.getKey(result.get()).toString());
        flask.getOrCreateTag().putInt("flask_sips", ((ItemFlask) flask.getItem()).getInitialSips());
        return flask;
    }

    @Override
    public ItemStack getResultItem()
    {
        ItemStack flask = new ItemStack(ModItems.FLASK::get);
        flask.getOrCreateTag().putString("flask", ModRegistries.FLASKS.getKey(result.get()).toString());
        flask.getOrCreateTag().putInt("flask_sips", ((ItemFlask) flask.getItem()).getInitialSips());
        return flask;
    }

    @Override
    public boolean canCraftInDimensions(int p_194133_1_, int p_194133_2_)
    {
        return true;
    }

    @Override
    public ResourceLocation getId()
    {
        return recipeId;
    }

    @Override
    public IRecipeSerializer<?> getSerializer()
    {
        return ModRecipeSerializers.FLASK.get();
    }

    @Override
    public IRecipeType<?> getType()
    {
        return FLASK_RECIPE;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<FlaskRecipe>
    {
        @Override
        public FlaskRecipe fromJson(ResourceLocation recipeId, JsonObject json)
        {
            HashMap<Supplier<ItemReagent>, Integer> reagentQuantities = new HashMap<>();

            JsonObject reagentQuantitiesJson = JSONUtils.getAsJsonObject(json, "reagents");
            for (Map.Entry<String, JsonElement> entry : reagentQuantitiesJson.entrySet())
            {
                Supplier<ItemReagent> key = () -> (ItemReagent) ForgeRegistries.ITEMS.getValue(new ResourceLocation(entry.getKey()));
                Integer value = entry.getValue().getAsInt();
                reagentQuantities.put(key, value);
            }

            String resultLocation = JSONUtils.getAsString(json, "result");
            Herblore.LOGGER.debug(resultLocation);
            Supplier<Flask> result = () -> ModRegistries.FLASKS.getValue(new ResourceLocation(resultLocation));

            return new FlaskRecipe(recipeId, ImmutableMap.copyOf(reagentQuantities), result);
        }

        @Nullable
        @Override
        public FlaskRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer)
        {
            HashMap<Supplier<ItemReagent>, Integer> reagentQuantities = new HashMap<>();

            int numEntries = buffer.readVarInt();
            for (int i = 0; i < numEntries; i++)
            {
                Supplier<ItemReagent> key = () -> (ItemReagent) ForgeRegistries.ITEMS.getValue(buffer.readResourceLocation());
                Integer value = buffer.readVarInt();
                reagentQuantities.put(key, value);
            }

            Supplier<Flask> result = () -> ModRegistries.FLASKS.getValue(buffer.readResourceLocation());

            return new FlaskRecipe(recipeId, ImmutableMap.copyOf(reagentQuantities), result);
        }

        @Override
        public void toNetwork(PacketBuffer buffer, FlaskRecipe recipe)
        {
            // Write the reagent quantity map, quick and dirty.
            buffer.writeVarInt(recipe.requiredReagentQuantities.size());
            recipe.requiredReagentQuantities.entrySet().forEach((entry) ->
            {
                buffer.writeResourceLocation(ForgeRegistries.ITEMS.getKey(entry.getKey().get()));
                buffer.writeVarInt(entry.getValue());
            });

            buffer.writeResourceLocation(ModRegistries.FLASKS.getKey(recipe.result.get()));
        }
    }
}
