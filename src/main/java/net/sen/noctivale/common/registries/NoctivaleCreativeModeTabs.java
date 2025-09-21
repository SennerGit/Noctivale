package net.sen.noctivale.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.noctivale.common.utils.ModUtils;

import java.util.Collection;
import java.util.Locale;

import static net.sen.noctivale.common.registries.NoctivaleEntityTypes.SPAWN_EGGS;
import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;
import static net.sen.noctivale.common.registries.NoctivaleItems.*;
import static net.sen.noctivale.common.registries.NoctivaleTestTubeItems.*;

public class NoctivaleCreativeModeTabs {
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ModUtils.getModId());

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> NOCTIVALE_TAB = TABS.register("noctivale_tab", () -> {
        return CreativeModeTab.builder()
                .icon(() -> new ItemStack(Items.STICK))
                .title(Component.translatable(generateName("noctivale_tab")))
                .withSearchBar()
                .displayItems(((itemDisplayParameters, output) -> {
                    output.accept(DRAWING_KNIFE.get());
                    output.accept(PESTLE_AND_MORTAR.get());

                    output.accept(SOLVENT_EXTRACTOR.get());
                    output.accept(OPERATING_TABLE.get());
                    output.accept(LABORATORY_COUNTER.get());
                    output.accept(LABORATORY_SHELF.get());
                    output.accept(LABORATORY_SINK.get());
                    output.accept(LABORATORY_JAR.get());
                    output.accept(STUDY_TABLE.get());
                    output.accept(PIPE_BLOCK.get());
                    output.accept(TEST_TUBE_RACK.get());
                    output.accept(DISTILLATION_APPARATUS.get());
                    output.accept(CHEMICAL_SYNTHESIZER.get());
                    output.accept(TEST_TUBE.get());

                    //Generated Lists
//                    createTestTubeItemsAlphabetical(output);
                    createSpawnEggsAlphabetical(output);
                })
                )
                .build();
    });

    private static String generateName(String id) {
        return ("itemgroup." + ModUtils.getModId() + "." + id).toLowerCase(Locale.ROOT);
    }

    private static void createSpawnEggsAlphabetical(CreativeModeTab.Output output) {
        Collection<? extends Item> eggs = SPAWN_EGGS.getEntries().stream().map(DeferredHolder::value).toList();
        eggs.forEach(output::accept);
    }

    private static void createTestTubeItemsAlphabetical(CreativeModeTab.Output output) {
        Collection<? extends Item> test_tube_items = TEST_TUBE_ITEMS.getEntries().stream().map(DeferredHolder::value).toList();
        test_tube_items.forEach(output::accept);
    }

    public static void register(IEventBus eventBus) {
        TABS.register(eventBus);
    }
}
