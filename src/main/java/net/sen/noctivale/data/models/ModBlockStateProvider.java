package net.sen.noctivale.data.models;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;

public class ModBlockStateProvider extends ModBlockStateHelper {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.simpleBlock(BLOOD_MARKER_BLOCK);
        this.blockWithItem(SOLVENT_EXTRACTOR);
        this.blockWithItem(OPERATING_TABLE);
        this.blockWithItem(LABORATORY_JAR);
        this.blockWithItem(LABORATORY_COUNTER);
        this.blockWithItem(LABORATORY_SHELF);
        this.blockWithItem(LABORATORY_SINK);
        this.blockWithItem(STUDY_TABLE);
        this.blockWithItem(PIPE_BLOCK);
        this.blockWithItem(TEST_TUBE_RACK);
        this.blockWithItem(DISTILLATION_APPARATUS);
        this.blockWithItem(CHEMICAL_SYNTHESIZER);
    }
}
