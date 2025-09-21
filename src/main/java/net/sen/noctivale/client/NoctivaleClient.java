package net.sen.noctivale.client;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.sen.noctivale.Noctivale;
import net.sen.noctivale.common.registries.NoctivaleMenuTypes;
import net.sen.noctivale.common.screen.*;

@Mod(value = Noctivale.MODID, dist = Dist.CLIENT)
public class NoctivaleClient {
    public NoctivaleClient(ModContainer container) {
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }

    public static void NoctivaleClientRegistry(IEventBus eventBus) {
        eventBus.addListener(NoctivaleClient::onClientSetup);
        eventBus.addListener(NoctivaleClient::renderEntities);
        eventBus.addListener(NoctivaleClient::registerLayer);
        eventBus.addListener(NoctivaleClient::registerScreens);
    }

    public static void onClientSetup(FMLClientSetupEvent event)
    {
        event.enqueueWork(() -> {
        });
    }

    private static void renderEntities(EntityRenderersEvent.RegisterRenderers  event) {
    }

    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
    }

    public static void registerScreens(RegisterMenuScreensEvent event) {
        event.register(NoctivaleMenuTypes.SOLVENT_EXTRACTOR_MENU.get(), SolventExtractorScreen::new);
        event.register(NoctivaleMenuTypes.DISTILLATION_APPARATUS_MENU.get(), DistillationApparatusScreen::new);
        event.register(NoctivaleMenuTypes.CHEMICAL_SYNTHESIZER_MENU.get(), ChemicalSynthesizerScreen::new);
    }
}
