package net.sen.noctivale.data.loottable;

import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.sen.noctivale.common.registries.NoctivaleBlocks;
import net.sen.noctivale.common.registries.NoctivaleItems;

import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;
import static net.sen.noctivale.common.registries.NoctivaleItems.*;

public class ModBlockLootTables extends BlockLootTableHelper {
    private static final LootItemCondition.Builder SHEARS = MatchTool.toolMatches(ItemPredicate.Builder.item().of(Items.SHEARS));

    public ModBlockLootTables(HolderLookup.Provider registries) {
        super(registries);
    }

    @Override
    protected void generate() {

    }
}
