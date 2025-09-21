package net.sen.noctivale.data.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.sen.noctivale.common.registries.*;

import java.util.concurrent.CompletableFuture;

import static net.sen.noctivale.common.registries.NoctivaleItems.*;
import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;

public class ModRecipeProvider extends ModRecipeHelper {

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries);
    }

    @Override
    void miscRecipes(RecipeOutput recipeOutput) {

    }

    @Override
    void pestleAndMotarRecipes(RecipeOutput recipeOutput) {
        this.pestalAndMotorRecipe(recipeOutput, Items.BONE, Items.BONE_MEAL);
    }

    @Override
    void stoneRecipes(RecipeOutput recipeOutput) {

    }

    @Override
    void foodRecipes(RecipeOutput recipeOutput) {

    }

    @Override
    void metalRecipes(RecipeOutput recipeOutput) {

    }

    @Override
    void woodRecipes(RecipeOutput recipeOutput) {

    }

    @Override
    void flowerRecipes(RecipeOutput recipeOutput) {

    }

    @Override
    void vanilla(RecipeOutput output) {

    }
}
