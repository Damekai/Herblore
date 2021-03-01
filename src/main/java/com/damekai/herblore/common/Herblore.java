package com.damekai.herblore.common;

import com.damekai.herblore.client.ModItemPropertyGetters;
import com.damekai.herblore.client.ModRenderTypeSetter;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.block.tile.ModTiles;
import com.damekai.herblore.common.capability.CapabilityFlaskHandler;
import com.damekai.herblore.common.capability.FlaskHandler;
import com.damekai.herblore.common.data.FlaskRecipeProvider;
import com.damekai.herblore.common.data.MilledReagentRecipeProvider;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.effect.ModFlaskEffects;
import com.damekai.herblore.common.flask.ModFlasks;
import com.damekai.herblore.common.item.ModItemColors;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.recipe.CrudeFlaskRecipe;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Random;


@Mod(Herblore.MOD_ID)
public class Herblore
{
    public static final String MOD_ID = "herblore";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final Random RANDOM = new Random();

    public Herblore()
    {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(Herblore::onCommonSetup);
        modBus.addListener(ModRenderTypeSetter::onClientSetup);
        modBus.addListener(ModItemPropertyGetters::onClientSetup);

        modBus.addListener(Herblore::onGatherData);
        modBus.addListener(ModRegistries::onNewRegistry);
        modBus.addListener(ModItemColors::onLoadComplete);

        modBus.addGenericListener(IRecipeSerializer.class, Herblore::onRegisterRecipeSerializers);

        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CapabilityFlaskHandler::onAttachCapabilities);

        ModBlocks.BLOCKS.register(modBus);
        ModTiles.TILES.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModEffects.EFFECTS.register(modBus);
        ModFlaskEffects.FLASK_EFFECTS.register(modBus);
        ModFlasks.FLASKS.register(modBus);

        MinecraftForge.EVENT_BUS.addListener(FlaskHandler::onLivingUpdate);
    }

    public static void onCommonSetup(FMLCommonSetupEvent event)
    {
        CapabilityFlaskHandler.register();
    }

    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();

        generator.addProvider(new MilledReagentRecipeProvider(generator));
        generator.addProvider(new FlaskRecipeProvider(generator));
    }

    public static void onRegisterRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event)
    {
        IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();
        registry.register(CrudeFlaskRecipe.SERIALIZER.setRegistryName(new ResourceLocation(Herblore.MOD_ID, "crude_flask")));
    }
}
