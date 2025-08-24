package net.sen.noctivale.data.recipes;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.Tags;
import net.sen.noctivale.common.utils.ModUtils;

import java.util.concurrent.CompletableFuture;

public abstract class ModRecipeHelper extends RecipeProvider {
    public ModRecipeHelper(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        miscRecipes(pRecipeOutput);
        stoneRecipes(pRecipeOutput);
        foodRecipes(pRecipeOutput);
        metalRecipes(pRecipeOutput);
        woodRecipes(pRecipeOutput);
        flowerRecipes(pRecipeOutput);
        vanilla(pRecipeOutput);
    }

    abstract void miscRecipes(RecipeOutput pRecipeOutput);
    abstract void stoneRecipes(RecipeOutput pRecipeOutput);
    abstract void foodRecipes(RecipeOutput pRecipeOutput);
    abstract void metalRecipes(RecipeOutput pRecipeOutput);
    abstract void woodRecipes(RecipeOutput pRecipeOutput);
    abstract void flowerRecipes(RecipeOutput pRecipeOutput);

    abstract void vanilla(RecipeOutput output);

    protected final void stairsBlockRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .pattern("#  ")
                .pattern("## ")
                .pattern("###")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("stairs/" + ModUtils.getItemLikeId(result)));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .pattern("  #")
                .pattern(" ##")
                .pattern("###")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("stairs/" + ModUtils.getItemLikeId(result) + "_reverse"));
    }

    protected final void fenceBlockRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 6)
                .pattern("#X#")
                .pattern("#X#")
                .define('#', Ingredient.of(ingredient))
                .define('M', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("fence/" + ModUtils.getItemLikeId(result)));
    }

    protected final void fenceGateBlockRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result)
                .pattern("X#X")
                .pattern("X#X")
                .define('#', Ingredient.of(ingredient))
                .define('M', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("fence_gate/" + ModUtils.getItemLikeId(result)));
    }

    protected final void smallCompressBlockRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 4)
                .pattern("##")
                .pattern("##")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("small_compress/small_compress_" + ModUtils.getItemLikeId(ingredient) + "_into_" + ModUtils.getItemLikeId(result)));
    }

    protected final void largeCompressBlockRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result, 1)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("large_compress/large_compress_" + ModUtils.getItemLikeId(ingredient) + "_into_" + ModUtils.getItemLikeId(result)));
    }

    protected final void breakdownCompressRecipe(RecipeOutput output, ItemLike result, ItemLike ingredient) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result, 9)
                .requires(ingredient)
                .unlockedBy("has_item", has(ingredient))
                .save(output, ModUtils.getModPath("breakdown/breakdown_" + ModUtils.getItemLikeId(ingredient) + "_into_" + ModUtils.getItemLikeId(result)));
    }

    protected final void nineBlock(RecipeOutput output, ItemLike ingot, ItemLike block) {
        largeCompressBlockRecipe(output, block, ingot);
        breakdownCompressRecipe(output, ingot, block);
    }

    protected final void helmetRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("# #")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("helmet/" + ModUtils.getItemLikeId(result)));
    }

    protected final void chestplateRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("# #")
                .pattern("###")
                .pattern("###")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("chestplate/" + ModUtils.getItemLikeId(result)));
    }

    protected final void leggingsRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("# #")
                .pattern("# #")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("leggings/" + ModUtils.getItemLikeId(result)));
    }

    protected final void bootsRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("# #")
                .pattern("# #")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("boots/" + ModUtils.getItemLikeId(result)));
    }

    protected final void swordRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#")
                .pattern("#")
                .pattern("X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("sword/" + ModUtils.getItemLikeId(result)));
    }

    protected final void pickaxeRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .pattern("###")
                .pattern(" X ")
                .pattern(" X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("pickaxe/" + ModUtils.getItemLikeId(result)));
    }

    protected final void axeRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .pattern("##")
                .pattern("#X")
                .pattern(" X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("axe/" + ModUtils.getItemLikeId(result)));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .pattern("##")
                .pattern("X#")
                .pattern("X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("axe/" + ModUtils.getItemLikeId(result) + "_reverse"));
    }

    protected final void shovelRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .pattern("#")
                .pattern("X")
                .pattern("X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("shovel/" + ModUtils.getItemLikeId(result)));
    }

    protected final void shieldRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .pattern("X#X")
                .pattern("XXX")
                .pattern(" X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(ItemTags.PLANKS))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("shield/" + ModUtils.getItemLikeId(result)));
    }

    protected final void hoeRecipe(RecipeOutput output, ItemLike result,ItemLike ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .pattern("##")
                .pattern(" X")
                .pattern(" X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("hoe/" + ModUtils.getItemLikeId(result)));

        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result, 1)
                .pattern("##")
                .pattern("X ")
                .pattern("X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("hoe/" + ModUtils.getItemLikeId(result) + "_reverse"));
    }

    protected final void oreCookingRecipe(RecipeOutput output, ItemLike result, ItemLike ingredients) {
        smeltingRecipe(output, result, ingredients, 0.1F);
        blastingRecipe(output, result, ingredients, 0.1F);

    }

    protected final void smeltingRecipe(RecipeOutput output, ItemLike result, ItemLike ingredient, float exp) {
        smeltingRecipe(output, result, ingredient, exp, 1);
    }

    protected final void smeltingRecipe(RecipeOutput output, ItemLike result, ItemLike ingredient, float exp, int count) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 200)
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("smelting/smelting_" + ModUtils.getItemLikeId(result) + "_with_" + ModUtils.getItemLikeId(ingredient)));
    }

    protected final void blastingRecipe(RecipeOutput output, ItemLike result, ItemLike ingredient, float exp) {
        blastingRecipe(output, result, ingredient, exp, 1);
    }

    protected final void blastingRecipe(RecipeOutput output, ItemLike result, ItemLike ingredient, float exp, int count) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(new ItemStack(ingredient, count)), RecipeCategory.MISC, result, exp, 100)
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("blasting/blasting_" + ModUtils.getItemLikeId(result) + "_with_" + ModUtils.getItemLikeId(ingredient)));
    }

    protected final void makeAndBreakRecipe(RecipeOutput output, ItemLike compressed, ItemLike singleItem) {
        largeCompressBlockRecipe(output, compressed, singleItem);
        breakdownCompressRecipe(output, singleItem, compressed);
    }

    protected final void metalRecipes(RecipeOutput output, ItemLike ore, ItemLike raw, ItemLike raw_block, ItemLike ingot, ItemLike metal_block, ItemLike nugget, ItemLike dust,
                                      ItemLike sword, ItemLike axe, ItemLike shovel, ItemLike pickaxe, ItemLike hoe, ItemLike shield, ItemLike boots, ItemLike leggings, ItemLike chestplate, ItemLike helmet,
                                      ItemLike club, ItemLike flail, ItemLike rapier, ItemLike greatsword, ItemLike hammer, ItemLike katana, ItemLike knife, ItemLike mace, ItemLike pike, ItemLike pole, ItemLike scythe, ItemLike spear, ItemLike trident, ItemLike twinBlade, ItemLike warAxe, ItemLike warHammer) {
        oreCookingRecipe(output, ingot, ore);
        oreCookingRecipe(output, ingot, dust);
        oreCookingRecipe(output, ingot, raw);
        makeAndBreakRecipe(output, raw_block, raw);
        makeAndBreakRecipe(output, metal_block, ingot);
        makeAndBreakRecipe(output, ingot, nugget);
        swordRecipe(output, sword, ingot);
        axeRecipe(output, axe, ingot);
        shovelRecipe(output, shovel, ingot);
        pickaxeRecipe(output, pickaxe, ingot);
        hoeRecipe(output, hoe, ingot);
        shieldRecipe(output, shield, ingot);
        bootsRecipe(output, boots, ingot);
        leggingsRecipe(output, leggings, ingot);
        chestplateRecipe(output, chestplate, ingot);
        helmetRecipe(output, helmet, ingot);
        modToolRecipes(output, ingot, club, flail, rapier, greatsword, hammer, katana, knife, mace, pike, pole, scythe, spear, trident, twinBlade, warAxe, warHammer);
    }

    protected final void metalRecipes(RecipeOutput output, ItemLike ore, ItemLike raw, ItemLike raw_block, ItemLike pure_raw, ItemLike pure_raw_block, ItemLike ingot, ItemLike metal_block, ItemLike refined_ingot, ItemLike refined_metal_block, ItemLike nugget, ItemLike refined_nugget, ItemLike dust, ItemLike refined_dust) {
        oreCookingRecipe(output, ingot, ore);
        oreCookingRecipe(output, ingot, dust);
        oreCookingRecipe(output, ingot, raw);
        oreCookingRecipe(output, refined_ingot, refined_dust);
        oreCookingRecipe(output, refined_ingot, pure_raw);
        makeAndBreakRecipe(output, raw_block, raw);
        makeAndBreakRecipe(output, pure_raw_block, pure_raw);
        makeAndBreakRecipe(output, metal_block, ingot);
        makeAndBreakRecipe(output, refined_metal_block, refined_ingot);
        makeAndBreakRecipe(output, ingot, nugget);
        makeAndBreakRecipe(output, refined_ingot, refined_nugget);
    }

    protected final void metalRecipes(RecipeOutput output, ItemLike ore, ItemLike raw, ItemLike raw_block, ItemLike ingot, ItemLike metal_block, ItemLike nugget, ItemLike dust) {
        oreCookingRecipe(output, ingot, ore);
        oreCookingRecipe(output, ingot, dust);
        oreCookingRecipe(output, ingot, raw);
        makeAndBreakRecipe(output, raw_block, raw);
        makeAndBreakRecipe(output, metal_block, ingot);
        makeAndBreakRecipe(output, ingot, nugget);
    }

    //Single
    protected final void modToolRecipes(RecipeOutput output, ItemLike ingredient, ItemLike club, ItemLike flail, ItemLike rapier, ItemLike greatsword, ItemLike hammer, ItemLike katana, ItemLike knife, ItemLike mace, ItemLike pike, ItemLike pole, ItemLike scythe, ItemLike spear, ItemLike trident, ItemLike twinBlade, ItemLike warAxe, ItemLike warHammer) {
        clubRecipe(output, ingredient, club);
        flailRecipe(output, ingredient, flail);
        rapierRecipe(output, ingredient, rapier);
        greatswordRecipe(output, ingredient, greatsword);
        hammerRecipe(output, ingredient, hammer);
        katanaRecipe(output, ingredient, katana);
        knifeRecipe(output, ingredient, knife);
        maceRecipe(output, ingredient, mace);
        pikeRecipe(output, ingredient, pike);
        poleRecipe(output, ingredient, pole);
        scytheRecipe(output, ingredient, scythe);
        spearRecipe(output, ingredient, spear);
        tridentRecipe(output, ingredient, trident);
        twinBladeRecipe(output, ingredient, twinBlade);
        warAxeRecipe(output, ingredient, warAxe);
        warHammerRecipe(output, ingredient, warHammer);
    }

    protected final void clubRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("##")
                .pattern("##")
                .pattern("X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("club/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("##")
                .pattern("##")
                .pattern(" X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("club/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void flailRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" XS")
                .pattern("X #")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .define('S', Ingredient.of(Items.STRING))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("flail/" + ModUtils.getItemLikeId(result)));
    }

    protected final void rapierRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern(" # ")
                .pattern("X#X")
                .pattern(" X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("rapier/" + ModUtils.getItemLikeId(result)));
    }

    protected final void greatswordRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern(" ##")
                .pattern("###")
                .pattern("X# ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("greatsword/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("## ")
                .pattern("###")
                .pattern(" #X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("greatsword/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void hammerRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("#X#")
                .pattern(" X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("hammer/" + ModUtils.getItemLikeId(result)));
    }

    protected final void katanaRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" # ")
                .pattern("X  ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("greatsword/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#  ")
                .pattern(" # ")
                .pattern("  X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("greatsword/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void knifeRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#")
                .pattern("X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("knife/" + ModUtils.getItemLikeId(result)));
    }

    protected final void maceRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern(" ##")
                .pattern(" X#")
                .pattern("X  ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("mace/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("## ")
                .pattern("#X ")
                .pattern("  X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("mace/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void pikeRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern(" X#")
                .pattern(" XX")
                .pattern("X  ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("pike/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#X ")
                .pattern("XX ")
                .pattern("  X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("pike/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void poleRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" # ")
                .pattern("#  ")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("pole/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#  ")
                .pattern(" # ")
                .pattern("  #")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("pole/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void scytheRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" X#")
                .pattern("X##")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("scythe/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#  ")
                .pattern("#X ")
                .pattern("##X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("scythe/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void spearRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" X ")
                .pattern("X  ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("pole/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#  ")
                .pattern(" X ")
                .pattern("  X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("pole/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void tridentRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("# #")
                .pattern("#X#")
                .pattern(" X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("trident/" + ModUtils.getItemLikeId(result)));
    }

    protected final void twinBladeRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#")
                .pattern("X")
                .pattern("#")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("twin_blade/" + ModUtils.getItemLikeId(result)));
    }

    protected final void warAxeRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("##X")
                .pattern("#X#")
                .pattern("X##")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("war_axe/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("X##")
                .pattern("#X#")
                .pattern("##X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("war_axe/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void warHammerRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("#X#")
                .pattern("X##")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("war_axe/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("#X#")
                .pattern("##X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemLikeId(ingredient))
                .save(output, ModUtils.getModPath("war_axe/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    //Tag
    protected final void modToolRecipes(RecipeOutput output, TagKey<Item> ingredient, ItemLike club, ItemLike flail, ItemLike rapier, ItemLike greatsword, ItemLike hammer, ItemLike katana, ItemLike knife, ItemLike mace, ItemLike pike, ItemLike pole, ItemLike scythe, ItemLike spear, ItemLike trident, ItemLike twinBlade, ItemLike warAxe, ItemLike warHammer) {
        clubRecipe(output, ingredient, club);
        flailRecipe(output, ingredient, flail);
        rapierRecipe(output, ingredient, rapier);
        greatswordRecipe(output, ingredient, greatsword);
        hammerRecipe(output, ingredient, hammer);
        katanaRecipe(output, ingredient, katana);
        knifeRecipe(output, ingredient, knife);
        maceRecipe(output, ingredient, mace);
        pikeRecipe(output, ingredient, pike);
        poleRecipe(output, ingredient, pole);
        scytheRecipe(output, ingredient, scythe);
        spearRecipe(output, ingredient, spear);
        tridentRecipe(output, ingredient, trident);
        twinBladeRecipe(output, ingredient, twinBlade);
        warAxeRecipe(output, ingredient, warAxe);
        warHammerRecipe(output, ingredient, warHammer);
    }

    protected final void clubRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("##")
                .pattern("##")
                .pattern("X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("club/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("##")
                .pattern("##")
                .pattern(" X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("club/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void flailRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" XS")
                .pattern("X #")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .define('S', Ingredient.of(Items.STRING))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("flail/" + ModUtils.getItemLikeId(result)));
    }

    protected final void rapierRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern(" # ")
                .pattern("X#X")
                .pattern(" X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("rapier/" + ModUtils.getItemLikeId(result)));
    }

    protected final void greatswordRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern(" ##")
                .pattern("###")
                .pattern("X# ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("greatsword/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("## ")
                .pattern("###")
                .pattern(" #X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("greatsword/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void hammerRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("#X#")
                .pattern(" X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("hammer/" + ModUtils.getItemLikeId(result)));
    }

    protected final void katanaRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" # ")
                .pattern("X  ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("greatsword/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#  ")
                .pattern(" # ")
                .pattern("  X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("greatsword/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void knifeRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#")
                .pattern("X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("knife/" + ModUtils.getItemLikeId(result)));
    }

    protected final void maceRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern(" ##")
                .pattern(" X#")
                .pattern("X  ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("mace/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("## ")
                .pattern("#X ")
                .pattern("  X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("mace/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void pikeRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern(" X#")
                .pattern(" XX")
                .pattern("X  ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("pike/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#X ")
                .pattern("XX ")
                .pattern("  X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("pike/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void poleRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" # ")
                .pattern("#  ")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("pole/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#  ")
                .pattern(" # ")
                .pattern("  #")
                .define('#', Ingredient.of(ingredient))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("pole/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void scytheRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" X#")
                .pattern("X##")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("scythe/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#  ")
                .pattern("#X ")
                .pattern("##X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("scythe/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void spearRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("  #")
                .pattern(" X ")
                .pattern("X  ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("pole/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#  ")
                .pattern(" X ")
                .pattern("  X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("pole/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void tridentRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("# #")
                .pattern("#X#")
                .pattern(" X ")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("trident/" + ModUtils.getItemLikeId(result)));
    }

    protected final void twinBladeRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("#")
                .pattern("X")
                .pattern("#")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("twin_blade/" + ModUtils.getItemLikeId(result)));
    }

    protected final void warAxeRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("##X")
                .pattern("#X#")
                .pattern("X##")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("war_axe/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("X##")
                .pattern("#X#")
                .pattern("##X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("war_axe/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    protected final void warHammerRecipe(RecipeOutput output, TagKey<Item> ingredient, ItemLike result) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("#X#")
                .pattern("X##")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("war_axe/" + ModUtils.getItemLikeId(result) + "_right"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result, 1)
                .pattern("###")
                .pattern("#X#")
                .pattern("##X")
                .define('#', Ingredient.of(ingredient))
                .define('X', Ingredient.of(Tags.Items.RODS_WOODEN))
                .unlockedBy("has_item", has(ingredient))
                .group(ModUtils.getItemTagId(ingredient))
                .save(output, ModUtils.getModPath("war_axe/" + ModUtils.getItemLikeId(result) + "_left"));
    }

    //Netherite
    protected final void modEnhancedToolRecipes(RecipeOutput output, ItemLike club, ItemLike enhancedClub, ItemLike flail, ItemLike enhancedFlail,
                                                ItemLike rapier, ItemLike enhancedRapier, ItemLike greatsword, ItemLike enhancedGreatsword, ItemLike hammer, ItemLike enhancedHammer, ItemLike katana,
                                                ItemLike enhancedKatana, ItemLike knife, ItemLike enhancedKnife, ItemLike mace, ItemLike enhancedMace, ItemLike pike, ItemLike enhancedPike,
                                                ItemLike pole, ItemLike enhancedPole, ItemLike scythe, ItemLike enhancedScythe, ItemLike spear, ItemLike enhancedSpear, ItemLike trident,
                                                ItemLike enhancedTrident, ItemLike twinBlade, ItemLike enhancedTwinBlade, ItemLike warAxe, ItemLike enhancedWarAxe, ItemLike warHammer,
                                                ItemLike enhancedWarHammer) {
        netheriteSmithingRecipe(output, club, enhancedClub);
        netheriteSmithingRecipe(output, flail, enhancedFlail);
        netheriteSmithingRecipe(output, rapier, enhancedRapier);
        netheriteSmithingRecipe(output, greatsword, enhancedGreatsword);
        netheriteSmithingRecipe(output, hammer, enhancedHammer);
        netheriteSmithingRecipe(output, katana, enhancedKatana);
        netheriteSmithingRecipe(output, knife, enhancedKnife);
        netheriteSmithingRecipe(output, mace, enhancedMace);
        netheriteSmithingRecipe(output, pike, enhancedPike);
        netheriteSmithingRecipe(output, pole, enhancedPole);
        netheriteSmithingRecipe(output, scythe, enhancedScythe);
        netheriteSmithingRecipe(output, spear, enhancedSpear);
        netheriteSmithingRecipe(output, trident, enhancedTrident);
        netheriteSmithingRecipe(output, twinBlade, enhancedTwinBlade);
        netheriteSmithingRecipe(output, warAxe, enhancedWarAxe);
        netheriteSmithingRecipe(output, warHammer, enhancedWarHammer);
    }

    protected final void netheriteSmithingRecipe(RecipeOutput output, ItemLike ingredient, ItemLike result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ingredient), Ingredient.of(Items.NETHERITE_INGOT), RecipeCategory.COMBAT, result.asItem())
                .unlocks("has_item", has(Items.NETHERITE_INGOT))
                .save(output, ModUtils.getModPath("smithing/netherite/" + ModUtils.getItemLikeId(result) + "_smithing"));
    }
}
