package net.sen.noctivale;

import com.google.common.reflect.Reflection;
import net.neoforged.api.distmarker.Dist;
import net.sen.noctivale.client.NoctivaleClient;
import net.sen.noctivale.client.event.NoctivaleClientEventHandler;
import net.sen.noctivale.common.config.Config;
import net.sen.noctivale.common.event.NoctivaleEventHandler;
import net.sen.noctivale.common.registries.*;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

@Mod(Noctivale.MODID)
public class Noctivale {
    public static final String MODID = "noctivale";
    public static final Logger LOGGER = LogUtils.getLogger();

    public Noctivale(IEventBus eventBus, ModContainer modContainer, Dist dist) {
        if (dist.isClient()) {
            NoctivaleClient.NoctivaleClientRegistry(eventBus);
            NoctivaleClientEventHandler.NoctivaleClientEventHandlerRegistry(eventBus);
        }

        NoctivaleEventHandler.NoctivaleEventHandlerRegistry(eventBus);

        NoctivaleBlocks.register(eventBus);
        NoctivaleItems.register(eventBus);
        NoctivaleBlockEntities.register(eventBus);
        NoctivaleEntityTypes.register(eventBus);
        NoctivaleArmourMaterials.register(eventBus);
        NoctivaleCreativeModeTabs.register(eventBus);

        eventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        preInit(event);
        init(event);
        postInit(event);
    }

    private void preInit(FMLCommonSetupEvent event) {
        Reflection.initialize(
        );
    }

    private void init(FMLCommonSetupEvent event) {
    }

    private void postInit(FMLCommonSetupEvent event) {
    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
    }
}
