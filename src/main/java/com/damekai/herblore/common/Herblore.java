package com.damekai.herblore.common;

import com.damekai.herblore.client.ModItemPropertyGetters;
import com.damekai.herblore.client.ModRenderTypeSetter;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.block.tile.ModTiles;
import com.damekai.herblore.common.capability.flaskhandler.CapabilityHerbloreEffectHandler;
import com.damekai.herblore.common.capability.flaskhandler.HerbloreEffectHandler;
import com.damekai.herblore.common.capability.toxicityhandler.CapabilityToxicityHandler;
import com.damekai.herblore.common.capability.toxicityhandler.ToxicityHandler;
import com.damekai.herblore.common.data.ModRecipeProvider;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.flask.ModFlasks;
import com.damekai.herblore.common.herbloreeffect.*;
import com.damekai.herblore.common.item.ModItemColors;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.network.HerblorePacketHandler;
import com.damekai.herblore.common.world.ModFeatures;
import net.minecraft.entity.Entity;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.IForgeRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(Herblore.MOD_ID)
public class Herblore
{
    public static final String MOD_ID = "herblore";

    public static final Logger LOGGER = LogManager.getLogger();

    public Herblore()
    {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();

        modBus.addListener(Herblore::onCommonSetup);
        modBus.addListener(ModRenderTypeSetter::onClientSetup);
        modBus.addListener(ModItemPropertyGetters::onClientSetup);

        modBus.addListener(ModRecipeProvider::onGatherData);
        modBus.addListener(ModRegistries::onNewRegistry);
        modBus.addListener(ModItemColors::onLoadComplete);

        modBus.addGenericListener(IRecipeSerializer.class, Herblore::onRegisterRecipeSerializers);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ModFeatures::onBiomeLoading);

        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CapabilityHerbloreEffectHandler::onAttachCapabilities);
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CapabilityToxicityHandler::onAttachCapabilities);

        ModBlocks.BLOCKS.register(modBus);
        ModTiles.TILES.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModFeatures.FEATURES.register(modBus);
        ModEffects.EFFECTS.register(modBus);
        ModHerbloreEffects.HERBLORE_EFFECTS.register(modBus);
        ModFlasks.FLASKS.register(modBus);

        MinecraftForge.EVENT_BUS.addListener(HerbloreEffectHandler::onLivingUpdate);
        MinecraftForge.EVENT_BUS.addListener(ToxicityHandler::onLivingUpdate);

        MinecraftForge.EVENT_BUS.addListener(HerbloreEffectStrider::onLivingJump);
        MinecraftForge.EVENT_BUS.addListener(HerbloreEffectQuench::onLivingDamage);
        MinecraftForge.EVENT_BUS.addListener(HerbloreEffectComet::onLivingDamage);
        MinecraftForge.EVENT_BUS.addListener(HerbloreEffectHaptic::onLivingDamage);
        MinecraftForge.EVENT_BUS.addListener(HerbloreEffectDredge::onBreakSpeed);
        MinecraftForge.EVENT_BUS.addListener(HerbloreEffectFallarbor::onBreakSpeed);

        HerblorePacketHandler.registerPackets();
    }

    public static void onCommonSetup(FMLCommonSetupEvent event)
    {
        CapabilityHerbloreEffectHandler.register();
        CapabilityToxicityHandler.register();
    }

    public static void onRegisterRecipeSerializers(RegistryEvent.Register<IRecipeSerializer<?>> event)
    {
        IForgeRegistry<IRecipeSerializer<?>> registry = event.getRegistry();
        //registry.register(CrudeFlaskRecipe.SERIALIZER.setRegistryName(new ResourceLocation(Herblore.MOD_ID, "crude_flask")));
    }
}
