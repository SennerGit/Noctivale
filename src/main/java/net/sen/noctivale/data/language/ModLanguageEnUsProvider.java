package net.sen.noctivale.data.language;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.sen.noctivale.common.item.TestTubeEmptyItem;
import net.sen.noctivale.common.registries.NoctivaleCreativeModeTabs;

import java.util.Collection;

import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;
import static net.sen.noctivale.common.registries.NoctivaleItems.*;
import static net.sen.noctivale.common.registries.NoctivaleTestTubeItems.*;

public class ModLanguageEnUsProvider extends LanguageProviderHelper {
    public ModLanguageEnUsProvider(PackOutput output, String locale) {
        super(output, locale);
    }

    @Override
    void spawnEggs() {

    }

    @Override
    void blocks() {
        this.addBlock(BLOOD_MARKER_BLOCK, "Blood Marker");
        this.addBlock(OPERATING_TABLE, "Operating Table");
        this.addBlock(LABORATORY_COUNTER, "Laboratory Counter");
        this.addBlock(LABORATORY_SHELF, "Laboratory Shelf");
        this.addBlock(LABORATORY_SINK, "Laboratory Sink");
        this.addBlock(LABORATORY_JAR, "Laboratory Jar");
        this.addBlock(STUDY_TABLE, "Study Table");
        this.addBlock(PIPE_BLOCK, "Pipe");
        this.addBlock(SOLVENT_EXTRACTOR, "Solvent Extractor");
        this.addBlock(TEST_TUBE_RACK, "Test Tube Rack");
        this.addBlock(DISTILLATION_APPARATUS, "Distillation Apparatus");
        this.addBlock(CHEMICAL_SYNTHESIZER, "Chemical Synthesizer");
    }

    @Override
    void items() {
        this.addItem(DRAWING_KNIFE, "Drawing Knife");
        this.addItem(PESTLE_AND_MORTAR, "Pestle and Mortar");
//        this.addItem(TEST_TUBE, "Test Tube");

        createTestTubeItemsAlphabetical();
    }

    @Override
    void paintings() {

    }

    @Override
    void effects() {

    }

    @Override
    void potions() {

    }

    @Override
    void sounds() {

    }

    @Override
    void custom() {

    }

    @Override
    void config() {

    }

    @Override
    void creativeTab() {
        addCreativeTab(NoctivaleCreativeModeTabs.NOCTIVALE_TAB, "Noctivale Tab");
    }

    @Override
    void baseAdvancements() {

    }

    private void createTestTubeItemsAlphabetical() {
        Collection<? extends Item> test_tube_items = TEST_TUBE_ITEMS.getEntries().stream().map(DeferredHolder::value).toList();
        for (Item item : test_tube_items) {
            if (item instanceof TestTubeEmptyItem testTubeItem) {
                this.add(item, testTubeItem.getName() != "" ? "Test Tube " + testTubeItem.getName() : "Test Tube");
            }
        }
    }
}
