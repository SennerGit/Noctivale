package net.sen.noctivale.data.models;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.sen.noctivale.common.registries.NoctivaleItems;

import static net.sen.noctivale.common.registries.NoctivaleItems.*;
import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;

public class ModItemModelProvider extends ModItemModelHelper {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, existingFileHelper);
    }

    @Override
    protected void generateItems() {
        this.simpleItem(DRAWING_KNIFE);
    }

    @Override
    protected void generateBlocks() {
    }
}