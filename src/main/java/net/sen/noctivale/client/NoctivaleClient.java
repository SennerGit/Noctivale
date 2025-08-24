package net.sen.noctivale.client;

import net.minecraft.client.renderer.Sheets;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.sen.noctivale.Noctivale;

@Mod(value = Noctivale.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Noctivale.MODID, value = Dist.CLIENT)
public class NoctivaleClient {
    public NoctivaleClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    public static void NoctivaleClientRegistry(IEventBus eventBus) {
        eventBus.addListener(NoctivaleClient::renderEntities);
        eventBus.addListener(NoctivaleClient::registerLayer);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
        });
    }

    private static void renderEntities(EntityRenderersEvent.RegisterRenderers  event) {
    }

    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }
}
