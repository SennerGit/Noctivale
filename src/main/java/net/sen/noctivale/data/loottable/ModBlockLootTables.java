package net.sen.noctivale.data.loottable;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;

import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;

public class ModBlockLootTables extends BlockLootTableHelper {
    private static final LootItemCondition.Builder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));

    public ModBlockLootTables(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(SOLVENT_EXTRACTOR);
        this.dropSelf(OPERATING_TABLE);
        this.dropSelf(LABORATORY_COUNTER);
        this.dropSelf(LABORATORY_SHELF);
        this.dropSelf(LABORATORY_SINK);
        this.dropSelf(LABORATORY_JAR);
        this.dropSelf(STUDY_TABLE);
        this.dropSelf(PIPE_BLOCK);
        this.dropSelf(TEST_TUBE_RACK);
        this.dropSelf(DISTILLATION_APPARATUS);
        this.dropSelf(CHEMICAL_SYNTHESIZER);
    }
}
