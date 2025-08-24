package net.sen.noctivale.data.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.sen.noctivale.common.registries.*;

import java.util.concurrent.CompletableFuture;

import static net.sen.noctivale.common.registries.NoctivaleItems.*;
import static net.sen.noctivale.common.registries.NoctivaleBlocks.*;

public class ModRecipeProvider extends ModRecipeHelper {

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> registries) {
        super(pOutput, registries);
    }

    @Override
    void miscRecipes(RecipeOutput pRecipeOutput) {

    }

    @Override
    void stoneRecipes(RecipeOutput pRecipeOutput) {

    }

    @Override
    void foodRecipes(RecipeOutput pRecipeOutput) {

    }

    @Override
    void metalRecipes(RecipeOutput pRecipeOutput) {

    }

    @Override
    void woodRecipes(RecipeOutput pRecipeOutput) {

    }

    @Override
    void flowerRecipes(RecipeOutput pRecipeOutput) {

    }

    @Override
    void vanilla(RecipeOutput output) {

    }
}
