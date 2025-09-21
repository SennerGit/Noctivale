package net.sen.noctivale.data.recipes.builders;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.NonNullList;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;

import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class ChemicalSynthesizerRecipeBuilder implements RecipeBuilder {
    private final RecipeCategory category;
    private final Item result;
    private final int count;
    private final ItemStack resultStack; // Neo: add stack result support
    private final NonNullList<Ingredient> ingredients = NonNullList.create();
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();
    @javax.annotation.Nullable
    private String group;

    public ChemicalSynthesizerRecipeBuilder(RecipeCategory category, ItemLike result, int count) {
        this(category, new ItemStack(result, count));
    }

    public ChemicalSynthesizerRecipeBuilder(RecipeCategory p_250837_, ItemStack result) {
        this.category = p_250837_;
        this.result = result.getItem();
        this.count = result.getCount();
        this.resultStack = result;
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static ChemicalSynthesizerRecipeBuilder chemicalSynthesizer(RecipeCategory category, ItemLike result) {
        return new ChemicalSynthesizerRecipeBuilder(category, result, 1);
    }

    /**
     * Creates a new builder for a shapeless recipe.
     */
    public static ChemicalSynthesizerRecipeBuilder chemicalSynthesizer(RecipeCategory category, ItemLike result, int count) {
        return new ChemicalSynthesizerRecipeBuilder(category, result, count);
    }

    public static ChemicalSynthesizerRecipeBuilder chemicalSynthesizer(RecipeCategory p_252339_, ItemStack result) {
        return new ChemicalSynthesizerRecipeBuilder(p_252339_, result);
    }

    /**
     * Adds an ingredient that can be any item in the given tag.
     */
    public ChemicalSynthesizerRecipeBuilder requires(TagKey<Item> tag) {
        return this.requires(Ingredient.of(tag));
    }

    /**
     * Adds an ingredient of the given item.
     */
    public ChemicalSynthesizerRecipeBuilder requires(ItemLike item) {
        return this.requires(item, 1);
    }

    /**
     * Adds the given ingredient multiple times.
     */
    public ChemicalSynthesizerRecipeBuilder requires(ItemLike item, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.requires(Ingredient.of(item));
        }

        return this;
    }

    /**
     * Adds an ingredient.
     */
    public ChemicalSynthesizerRecipeBuilder requires(Ingredient ingredient) {
        return this.requires(ingredient, 1);
    }

    /**
     * Adds an ingredient multiple times.
     */
    public ChemicalSynthesizerRecipeBuilder requires(Ingredient ingredient, int quantity) {
        for (int i = 0; i < quantity; i++) {
            this.ingredients.add(ingredient);
        }

        return this;
    }

    @Override
    public ChemicalSynthesizerRecipeBuilder unlockedBy(String name, Criterion<?> criterion) {
        this.criteria.put(name, criterion);
        return this;
    }

    @Override
    public ChemicalSynthesizerRecipeBuilder group(@Nullable String groupName) {
        this.group = groupName;
        return this;
    }

    @Override
    public Item getResult() {
        return this.result;
    }

    @Override
    public void save(RecipeOutput recipeOutput, ResourceLocation id) {
        this.ensureValid(id);
        Advancement.Builder advancement$builder = recipeOutput.advancement()
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(AdvancementRequirements.Strategy.OR);
        this.criteria.forEach(advancement$builder::addCriterion);
        ShapelessRecipe shapelessrecipe = new ShapelessRecipe(
                Objects.requireNonNullElse(this.group, ""),
                RecipeBuilder.determineBookCategory(this.category),
                this.resultStack,
                this.ingredients
        );
        recipeOutput.accept(id, shapelessrecipe, advancement$builder.build(id.withPrefix("recipes/" + this.category.getFolderName() + "/")));
    }

    /**
     * Makes sure that this recipe is valid and obtainable.
     */
    private void ensureValid(ResourceLocation id) {
        if (this.criteria.isEmpty()) {
            throw new IllegalStateException("No way of obtaining recipe " + id);
        }
    }
}
