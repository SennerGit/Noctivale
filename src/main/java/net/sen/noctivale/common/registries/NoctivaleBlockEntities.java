package net.sen.noctivale.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.noctivale.common.block.entity.*;
import net.sen.noctivale.common.utils.ModUtils;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class NoctivaleBlockEntities {
    private static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, ModUtils.getModId());

    public static final Supplier<BlockEntityType<BloodMarkerBlockEntity>> BLOOD_MARKER_BLOCK = createBlockEntity("blood_marker_block", BloodMarkerBlockEntity::new, NoctivaleBlocks.BLOOD_MARKER_BLOCK);
    public static final Supplier<BlockEntityType<SolventExtractorBlockEntity>> SOLVENT_EXTRACTOR = createBlockEntity("solvent_extractor", SolventExtractorBlockEntity::new, NoctivaleBlocks.SOLVENT_EXTRACTOR);
    public static final Supplier<BlockEntityType<TestTubeRackBlockEntity>> TEST_TUBE_RACK = createBlockEntity("test_tube_rack", TestTubeRackBlockEntity::new, NoctivaleBlocks.TEST_TUBE_RACK);
    public static final Supplier<BlockEntityType<DistillationApparatusBlockEntity>> DISTILLATION_APPARATUS = createBlockEntity("distillation_apparatus", DistillationApparatusBlockEntity::new, NoctivaleBlocks.DISTILLATION_APPARATUS);
    public static final Supplier<BlockEntityType<ChemicalSynthesizerBlockEntity>> CHEMICAL_SYNTHESIZER = createBlockEntity("chemical_synthesizer", ChemicalSynthesizerBlockEntity::new, NoctivaleBlocks.CHEMICAL_SYNTHESIZER);

    @SuppressWarnings("DataFlowIssue")
    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> createBlockEntity(String name, BlockEntityType.BlockEntitySupplier<T> supplier, Collection<? extends Supplier<? extends Block>> blocks) {
        return BLOCK_ENTITIES.register(name, () -> BlockEntityType.Builder.of(supplier, blocks.stream().map(Supplier::get).toList().toArray(new Block[0])).build(null));
    }
    @SafeVarargs
    private static <T extends BlockEntity> Supplier<BlockEntityType<T>> createBlockEntity(String name, BlockEntityType.BlockEntitySupplier<T> supplier, Supplier<? extends Block>... blocks) {
        return createBlockEntity(name, supplier, List.of(blocks));
    }

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
