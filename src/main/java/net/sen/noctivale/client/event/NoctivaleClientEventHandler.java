package net.sen.noctivale.client.event;

import com.mojang.blaze3d.platform.Window;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.ComputeFovModifierEvent;
import net.neoforged.neoforge.client.event.RegisterGuiLayersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.sen.noctivale.Noctivale;
import net.sen.noctivale.common.event.NoctivaleEventHandler;
import net.sen.noctivale.common.item.TestTubeItem;
import net.sen.noctivale.common.item.TestTubeWaterItem;
import net.sen.noctivale.common.registries.NoctivaleCreativeModeTabs;
import net.sen.noctivale.common.registries.NoctivaleDataComponents;
import net.sen.noctivale.common.registries.NoctivaleTestTubeItems;

public class NoctivaleClientEventHandler {
    public static void NoctivaleClientEventHandlerRegistry(IEventBus eventBus) {
        eventBus.addListener(NoctivaleClientEventHandler::registerOverlays);
    }

    private static void registerOverlays(RegisterGuiLayersEvent event) {
    }

    private static void registerPortalVisual(RegisterGuiLayersEvent event, String name, Block portalBlock, float animPreTime, float animTime) {
        event.registerAboveAll(ResourceLocation.fromNamespaceAndPath(Noctivale.MODID, name), (guiGraphics, deltaTracker) -> {
            Minecraft minecraft = Minecraft.getInstance();
            Window window = minecraft.getWindow();
            LocalPlayer player = minecraft.player;

            if (player != null) {
                renderPortalOverlay(guiGraphics, minecraft, window, deltaTracker.getGameTimeDeltaPartialTick(true), animPreTime, animTime, portalBlock);
            }
        });
    }

    private static void renderPortalOverlay(GuiGraphics graphics, Minecraft minecraft, Window window, float partialTicks, float animPreTime, float animTime, Block portalBlock) {
        float alpha = Mth.lerp(partialTicks, animPreTime, animTime);
        if (alpha > 0.0F) {
            if (alpha < 1.0F) {
                alpha = alpha * alpha;
                alpha = alpha * alpha;
                alpha = alpha * 0.8F + 0.2F;
            }

            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            graphics.setColor(1.0F, 1.0F, 1.0F, alpha);
            TextureAtlasSprite textureatlassprite = minecraft.getBlockRenderer().getBlockModelShaper().getParticleIcon(portalBlock.defaultBlockState());
            graphics.blit(0, 0, -90, window.getGuiScaledWidth(), window.getGuiScaledHeight(), textureatlassprite);
            RenderSystem.disableBlend();
            RenderSystem.depthMask(true);
            RenderSystem.enableDepthTest();
            graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
        }
    }
}
