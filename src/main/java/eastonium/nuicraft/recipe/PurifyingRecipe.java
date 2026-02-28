package eastonium.nuicraft.recipe;

import eastonium.nuicraft.core.NuiCraftRegistration;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.ItemStack;

/**
 * Recipe type for the Purifier machine. Identical in structure to smelting but
 * processed only by the Purifier block entity, not vanilla furnaces.
 */
public class PurifyingRecipe extends AbstractCookingRecipe {

    public PurifyingRecipe(String group, CookingBookCategory category,
                           Ingredient ingredient, ItemStack result,
                           float experience, int cookingTime) {
        super(group, category, ingredient, result, experience, cookingTime);
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return NuiCraftRegistration.PURIFYING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return NuiCraftRegistration.PURIFYING_TYPE.get();
    }
}
