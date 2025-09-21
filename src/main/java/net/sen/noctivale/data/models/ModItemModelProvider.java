package net.sen.noctivale.data.models;

import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.sen.noctivale.common.item.TestTubeEmptyItem;

import java.util.Collection;

import static net.sen.noctivale.common.registries.NoctivaleItems.*;
import static net.sen.noctivale.common.registries.NoctivaleTestTubeItems.*;
import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;

public class ModItemModelProvider extends ModItemModelHelper {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void generateItems() {
        this.simpleItem(DRAWING_KNIFE);
        this.simpleItem(PESTLE_AND_MORTAR);

        createTestTubeItemsAlphabetical();
    }

    @Override
    protected void generateBlocks() {
    }

    private void createTestTubeItemsAlphabetical() {
        Collection<? extends Item> test_tube_items = TEST_TUBE_ITEMS.getEntries().stream().map(DeferredHolder::value).toList();
        for (Item item : test_tube_items) {
            this.basicItem(item);
        }
    }
}