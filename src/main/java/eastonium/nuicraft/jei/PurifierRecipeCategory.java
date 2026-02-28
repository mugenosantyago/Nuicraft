package eastonium.nuicraft.jei;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.core.NuiCraftBlocks;
import eastonium.nuicraft.recipe.PurifyingRecipe;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * JEI recipe category for the Purifier machine.
 * Displays purifying recipes (input → output with a progress arrow) under a dedicated
 * Purifier category rather than the vanilla furnace category.
 */
public class PurifierRecipeCategory implements IRecipeCategory<PurifyingRecipe> {

    public static final RecipeType<PurifyingRecipe> TYPE =
            RecipeType.create(NuiCraft.MODID, "purifying", PurifyingRecipe.class);

    /** Vanilla furnace GUI texture reused so the look is consistent with PurifierScreen. */
    private static final ResourceLocation FURNACE_GUI =
            ResourceLocation.withDefaultNamespace("textures/gui/container/furnace.png");

    /** 82×34 slice of the vanilla furnace GUI: input slot, arrow, output slot. */
    private final IDrawable background;
    private final IDrawable icon;

    public PurifierRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(FURNACE_GUI, 55, 16, 82, 54);
        this.icon = guiHelper.createDrawableItemStack(new ItemStack(NuiCraftBlocks.PURIFIER.get()));
    }

    @Override
    public RecipeType<PurifyingRecipe> getRecipeType() {
        return TYPE;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.nuicraft.purifier");
    }

    @Override
    public IDrawable getBackground() {
        return background;
    }

    @Override
    public IDrawable getIcon() {
        return icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, PurifyingRecipe recipe, IFocusGroup focuses) {
        // Input slot — aligned with the furnace GUI slot position
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 17)
               .addIngredients(recipe.getIngredients().get(0));

        // Output slot
        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 17)
               .addItemStack(recipe.getResultItem(HolderLookup.Provider.create(java.util.stream.Stream.empty())));
    }
}
