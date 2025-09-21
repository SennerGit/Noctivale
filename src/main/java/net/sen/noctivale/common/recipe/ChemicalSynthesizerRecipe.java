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

public record ChemicalSynthesizerRecipe (Ingredient inputItem1, Ingredient inputItem2, ItemStack output) implements Recipe<ChemicalSynthesizerRecipeInput> {
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem1);
        list.add(inputItem2);
        return list;
    }

    @Override
    public boolean matches(ChemicalSynthesizerRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        boolean testSlot1 = inputItem1.test(input.getItem(0));
        boolean testSlot2 = inputItem2.test(input.getItem(1));
        return testSlot1 && testSlot2;
    }

    @Override
    public ItemStack assemble(ChemicalSynthesizerRecipeInput input, HolderLookup.Provider registries) {
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

    public static class Serializer implements RecipeSerializer<ChemicalSynthesizerRecipe> {
        public static final MapCodec<ChemicalSynthesizerRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient1").forGetter(ChemicalSynthesizerRecipe::inputItem1),
            Ingredient.CODEC_NONEMPTY.fieldOf("ingredient2").forGetter(ChemicalSynthesizerRecipe::inputItem2),
            ItemStack.CODEC.fieldOf("output").forGetter(ChemicalSynthesizerRecipe::output)
        ).apply(inst, ChemicalSynthesizerRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, ChemicalSynthesizerRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, ChemicalSynthesizerRecipe::inputItem1,
                Ingredient.CONTENTS_STREAM_CODEC, ChemicalSynthesizerRecipe::inputItem2,
                ItemStack.STREAM_CODEC, ChemicalSynthesizerRecipe::output,
                ChemicalSynthesizerRecipe::new
        );

        @Override
        public MapCodec<ChemicalSynthesizerRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, ChemicalSynthesizerRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
