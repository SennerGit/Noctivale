package net.sen.noctivale.common.registries;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.sen.noctivale.common.recipe.ChemicalSynthesizerRecipe;
import net.sen.noctivale.common.recipe.DistillationApparatusRecipe;
import net.sen.noctivale.common.recipe.SolventExtractorRecipe;
import net.sen.noctivale.common.recipe.TestTubeRecipe;
import net.sen.noctivale.common.utils.ModUtils;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class NoctivaleRecipes {
    private static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(Registries.RECIPE_SERIALIZER, ModUtils.getModId());
    private static final DeferredRegister<RecipeType<?>> RECIPE_TYPES = DeferredRegister.create(Registries.RECIPE_TYPE, ModUtils.getModId());

    public static final Supplier<RecipeSerializer<SolventExtractorRecipe>> SOLVENT_EXTRACTOR_SERIALIZERS = createRecipeSerializer("solvent_extractor", SolventExtractorRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<TestTubeRecipe>> TEST_TUBE_RACK_SERIALIZERS = createRecipeSerializer("test_tube_rack", TestTubeRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<DistillationApparatusRecipe>> DISTILLATION_APPARATUS_SERIALIZERS = createRecipeSerializer("distillation_apparatus", DistillationApparatusRecipe.Serializer::new);
    public static final Supplier<RecipeSerializer<ChemicalSynthesizerRecipe>> CHEMICAL_SYNTHESIZER_SERIALIZERS = createRecipeSerializer("chemical_synthesizer", ChemicalSynthesizerRecipe.Serializer::new);

    public static final Supplier<RecipeType<SolventExtractorRecipe>> SOLVENT_EXTRACTOR_TYPES = createRecipeType("solvent_extractor");
    public static final Supplier<RecipeType<TestTubeRecipe>> TEST_TUBE_RACK_TYPES = createRecipeType("test_tube_rack");
    public static final Supplier<RecipeType<DistillationApparatusRecipe>> DISTILLATION_APPARATUS_TYPES = createRecipeType("distillation_apparatus");
    public static final Supplier<RecipeType<ChemicalSynthesizerRecipe>> CHEMICAL_SYNTHESIZER_TYPES = createRecipeType("chemical_synthesizer");

    @SuppressWarnings("DataFlowIssue")
    private static <T extends RecipeSerializer<?>> Supplier<T> createRecipeSerializer(String name, Supplier<T> supplier) {
        return RECIPE_SERIALIZERS.register(name, supplier);
    }

    private static <T extends RecipeType<?>> Supplier<T> createRecipeType(String name) {
        return (Supplier<T>) createRecipe(name);
    }

    private static <T extends Recipe<?>> Supplier<RecipeType<T>> createRecipe(String name) {
        return RECIPE_TYPES.register(name, () -> new RecipeType<T>() {
            @Override
            public String toString() {
                return name;
            }
        });
    }

    public static void register(IEventBus eventBus) {
        RECIPE_SERIALIZERS.register(eventBus);
        RECIPE_TYPES.register(eventBus);
    }
}
