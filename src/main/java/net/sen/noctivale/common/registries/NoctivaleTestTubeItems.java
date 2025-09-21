package net.sen.noctivale.common.registries;

import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.noctivale.common.item.*;
import net.sen.noctivale.common.utils.ModUtils;
import net.sen.noctivale.common.utils.alchemy.AlchemyTypes;

import java.util.function.Supplier;

public class NoctivaleTestTubeItems {
    public static final DeferredRegister.Items TEST_TUBE_ITEMS = DeferredRegister.createItems(ModUtils.getModId());

    public static final Supplier<Item> TEST_TUBE = TEST_TUBE_ITEMS.register("test_tube", () -> new TestTubeEmptyItem(""));
    public static final Supplier<Item> TEST_TUBE_WATER = TEST_TUBE_ITEMS.register("test_tube_water", TestTubeWaterItem::new);

    private static Supplier<Item> createItem(AlchemyTypes alchemyTypes) {
        return TEST_TUBE_ITEMS.register(alchemyTypes.getTestTube(), () -> new TestTubeItem(alchemyTypes.getName(), alchemyTypes));
    }

    public static void register(IEventBus eventBus) {
        TEST_TUBE_ITEMS.register(eventBus);
    }
}
