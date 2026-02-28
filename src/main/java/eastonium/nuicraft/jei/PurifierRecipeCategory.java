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
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * JEI recipe category for the Purifier machine.
 * Shows purifying recipes under a dedicated Purifier heading, separate from the vanilla furnace.
 */
public class PurifierRecipeCategory implements IRecipeCategory<PurifyingRecipe> {

    public static final RecipeType<PurifyingRecipe> TYPE =
            RecipeType.create(NuiCraft.MODID, "purifying", PurifyingRecipe.class);

    private static final ResourceLocation PURIFIER_GUI =
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/gui/purifier_gui.png");

    /** Crop the same input-slot / arrow / output-slot region used by the furnace GUI. */
    private final IDrawable background;
    private final IDrawable icon;

    public PurifierRecipeCategory(IGuiHelper guiHelper) {
        this.background = guiHelper.createDrawable(PURIFIER_GUI, 55, 16, 82, 54);
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
        builder.addSlot(RecipeIngredientRole.INPUT, 1, 17)
               .addItemStacks(recipe.getInputStacks());

        builder.addSlot(RecipeIngredientRole.OUTPUT, 61, 17)
               .addItemStack(recipe.getResultCopy());
    }
}
