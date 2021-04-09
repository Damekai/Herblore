package com.damekai.herblore.client;

import com.damekai.herblore.common.Herblore;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.util.FlaskHelper;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModItemPropertyGetters
{
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(ModItemPropertyGetters::registerPropertyGetters);
    }

    private static void registerPropertyGetter(IItemProvider itemProvider, ResourceLocation id, IItemPropertyGetter itemPropertyGetter)
    {
        ItemModelsProperties.registerProperty(itemProvider.asItem(), id, itemPropertyGetter);
    }

    private static void registerPropertyGetters()
    {
        registerPropertyGetter(ModItems.FLASK::get, new ResourceLocation(Herblore.MOD_ID, "flask_fill"), (stack, clientWorld, livingEntity) -> FlaskHelper.getFlaskFillPredicate(stack));
    }
}
