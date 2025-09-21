package net.sen.noctivale.common.registries;

import com.mojang.serialization.Codec;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.noctivale.Noctivale;

import java.util.function.UnaryOperator;

public class NoctivaleDataComponents {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPES = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, Noctivale.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<String>> BLOOD_ENTITY = register("blood_entity", builder -> builder.persistent(Codec.STRING));
    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> BLOOD_AMOUNT = register("blood_amount", builder -> builder.persistent(Codec.INT));

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<Integer>> TEST_TUBE_FLUID_AMOUNT = register("test_tube_fluid_amount", builder -> builder.persistent(Codec.INT));

    private static <T>DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        return DATA_COMPONENT_TYPES.register(name, () -> builder.apply(DataComponentType.builder()).build());
    }

    public static void register(IEventBus eventBus) {
        DATA_COMPONENT_TYPES.register(eventBus);
    }
}
