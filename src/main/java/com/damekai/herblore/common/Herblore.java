package com.damekai.herblore.common;

import com.damekai.herblore.client.ModItemPropertyGetters;
import com.damekai.herblore.client.ModRenderTypeSetter;
import com.damekai.herblore.common.block.ModBlocks;
import com.damekai.herblore.common.block.tile.ModTiles;
import com.damekai.herblore.common.capability.herbloreeffecthandler.CapabilityHerbloreEffectHandler;
import com.damekai.herblore.common.capability.herbloreeffecthandler.HerbloreEffectHandler;
import com.damekai.herblore.common.capability.toxicityhandler.CapabilityToxicityHandler;
import com.damekai.herblore.common.capability.toxicityhandler.ToxicityHandler;
import com.damekai.herblore.client.ModColorHandlers;
import com.damekai.herblore.common.container.ModContainers;
import com.damekai.herblore.common.data.ModAssetManagers;
import com.damekai.herblore.common.data.ModRecipeProvider;
import com.damekai.herblore.common.effect.ModEffects;
import com.damekai.herblore.common.flask.ModFlasks;
import com.damekai.herblore.common.herbloreeffect.*;
import com.damekai.herblore.common.item.ModItemColors;
import com.damekai.herblore.common.item.ModItems;
import com.damekai.herblore.common.network.HerblorePacketHandler;
import com.damekai.herblore.common.recipe.ModRecipeSerializers;
import com.damekai.herblore.common.screen.ScreenFlaskStation;
import com.damekai.herblore.common.util.IContinuousDrinkItem;
import com.damekai.herblore.common.world.ModFeatures;
import com.damekai.herblore.effusion.ModEffusions;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.entity.Entity;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

        modBus.addListener(Herblore::onClientSetup);
        modBus.addListener(Herblore::onCommonSetup);
        modBus.addListener(ModRenderTypeSetter::onClientSetup);
        modBus.addListener(ModItemPropertyGetters::onClientSetup);

        modBus.addListener(ModRecipeProvider::onGatherData);

        modBus.addListener(ModRegistries::onNewRegistry);
        modBus.addListener(ModItemColors::onLoadComplete);

        modBus.addListener(ModColorHandlers::onRegisterBlockColors);

        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, ModFeatures::onBiomeLoading);

        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CapabilityHerbloreEffectHandler::onAttachCapabilities);
        MinecraftForge.EVENT_BUS.addGenericListener(Entity.class, CapabilityToxicityHandler::onAttachCapabilities);

        ModBlocks.BLOCKS.register(modBus);
        ModTiles.TILES.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModFeatures.FEATURES.register(modBus);
        ModEffects.EFFECTS.register(modBus);
        ModContainers.CONTAINERS.register(modBus);
        ModRecipeSerializers.RECIPE_SERIALIZERS.register(modBus);
        ModHerbloreEffects.HERBLORE_EFFECTS.register(modBus);
        ModFlasks.FLASKS.register(modBus);
        ModEffusions.EFFUSIONS.register(modBus);

        MinecraftForge.EVENT_BUS.addListener(ModAssetManagers::onAddReloadListener);

        MinecraftForge.EVENT_BUS.addListener(IContinuousDrinkItem::onUseItem);

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

    // TODO: Move this somewhere else.
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        ScreenManager.register(ModContainers.FLASK_STATION.get(), ScreenFlaskStation::new);
    }
}
