package eastonium.nuicraft.recipe;

import eastonium.nuicraft.core.NuiCraftRegistration;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeBookCategory;
import net.minecraft.world.item.crafting.RecipeBookCategories;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

import java.util.List;

/**
 * Recipe type for the Purifier machine. Structurally identical to smelting but
 * processed only by the Purifier block entity, not vanilla furnaces.
 */
public class PurifyingRecipe extends AbstractCookingRecipe {

    public PurifyingRecipe(String group, CookingBookCategory category,
                           Ingredient ingredient, ItemStack result,
                           float experience, int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
    }

    @Override
    public RecipeSerializer<PurifyingRecipe> getSerializer() {
        return NuiCraftRegistration.PURIFYING_SERIALIZER.get();
    }

    @Override
    public RecipeType<PurifyingRecipe> getType() {
        return NuiCraftRegistration.PURIFYING_TYPE.get();
    }

    @Override
    protected Item furnaceIcon() {
        return Items.FURNACE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.FURNACE_MISC;
    }

    /** Public copy of the output stack for JEI display (result() is protected in SingleItemRecipe). */
    public ItemStack getResultCopy() {
        return result().copy();
    }

    /** Expanded input stacks for JEI slot display. */
    @SuppressWarnings("deprecation")
    public List<ItemStack> getInputStacks() {
        return input().items()
                .map(holder -> new ItemStack(holder.value()))
                .toList();
    }
}
