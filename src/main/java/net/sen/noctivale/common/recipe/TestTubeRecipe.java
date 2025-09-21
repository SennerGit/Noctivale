package net.sen.noctivale.common.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import net.sen.noctivale.common.registries.NoctivaleRecipes;

public record TestTubeRecipe (Ingredient inputItem, ItemStack output) implements Recipe<TestTubeRecipeInput> {
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem);
        return list;
    }

    @Override
    public boolean matches(TestTubeRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        boolean testSlot1 = inputItem.test(input.getItem(0));
        return testSlot1;
    }

    @Override
    public ItemStack assemble(TestTubeRecipeInput input, HolderLookup.Provider registries) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NoctivaleRecipes.CHEMICAL_SYNTHESIZER_SERIALIZERS.get();
    }

    @Override
    public RecipeType<?> getType() {
        return NoctivaleRecipes.CHEMICAL_SYNTHESIZER_TYPES.get();
    }

    public static class Serializer implements RecipeSerializer<TestTubeRecipe> {
        public static final MapCodec<TestTubeRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient1").forGetter(TestTubeRecipe::inputItem),
                ItemStack.CODEC.fieldOf("output").forGetter(TestTubeRecipe::output)
        ).apply(inst, TestTubeRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, TestTubeRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, TestTubeRecipe::inputItem,
                ItemStack.STREAM_CODEC, TestTubeRecipe::output,
                TestTubeRecipe::new
        );

        @Override
        public MapCodec<TestTubeRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, TestTubeRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
