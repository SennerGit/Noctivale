package net.sen.noctivale.common.event;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;
import net.sen.noctivale.Noctivale;

public class NoctivaleEventHandler {

    public static void NoctivaleEventHandlerRegistry(IEventBus eventBus) {
        eventBus.addListener(NoctivaleEventHandler::registerPackets);

        NeoForge.EVENT_BUS.addListener(NoctivaleEventHandler::tickPortalLogic);
    }

    private static void registerPackets(RegisterPayloadHandlersEvent event) {
        PayloadRegistrar registrar = event.registrar(Noctivale.MODID).versioned("0.1.0").optional();
    }

    private static void tickPortalLogic(PlayerTickEvent.Pre event) {
        if (event.getEntity().level().isClientSide()) {
        }
    }
}
