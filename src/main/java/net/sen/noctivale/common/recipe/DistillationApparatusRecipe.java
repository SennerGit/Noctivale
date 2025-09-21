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

public record DistillationApparatusRecipe (Ingredient inputItem1, Ingredient inputItem2, ItemStack output) implements Recipe<DistillationApparatusRecipeInput> {
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        list.add(inputItem1);
        list.add(inputItem2);
        return list;
    }

    @Override
    public boolean matches(DistillationApparatusRecipeInput input, Level level) {
        if (level.isClientSide()) return false;
        boolean testSlot1 = inputItem1.test(input.getItem(0));
        boolean testSlot2 = inputItem2.test(input.getItem(1));
        return testSlot1 && testSlot2;
    }

    @Override
    public ItemStack assemble(DistillationApparatusRecipeInput input, HolderLookup.Provider registries) {
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

    public static class Serializer implements RecipeSerializer<DistillationApparatusRecipe> {
        public static final MapCodec<DistillationApparatusRecipe> CODEC = RecordCodecBuilder.mapCodec(inst -> inst.group(
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient1").forGetter(DistillationApparatusRecipe::inputItem1),
                Ingredient.CODEC_NONEMPTY.fieldOf("ingredient2").forGetter(DistillationApparatusRecipe::inputItem2),
                ItemStack.CODEC.fieldOf("output").forGetter(DistillationApparatusRecipe::output)
        ).apply(inst, DistillationApparatusRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, DistillationApparatusRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC, DistillationApparatusRecipe::inputItem1,
                Ingredient.CONTENTS_STREAM_CODEC, DistillationApparatusRecipe::inputItem2,
                ItemStack.STREAM_CODEC, DistillationApparatusRecipe::output,
                DistillationApparatusRecipe::new
        );

        @Override
        public MapCodec<DistillationApparatusRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, DistillationApparatusRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
