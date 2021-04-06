package com.damekai.herblore.common.recipe;

import com.damekai.herblore.common.Herblore;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipeSerializers
{
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Herblore.MOD_ID);

    public static final RegistryObject<IRecipeSerializer<FlaskRecipe>> FLASK = RECIPE_SERIALIZERS.register("flask", FlaskRecipe.Serializer::new);
}
