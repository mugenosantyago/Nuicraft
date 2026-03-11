package eastonium.mnogii.client.screen;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.menu.PurifierMenu;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.SearchRecipeBookCategory;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeBookCategories;

import java.util.List;

public class PurifierScreen extends AbstractFurnaceScreen<PurifierMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/gui/purifier_gui.png");
    private static final ResourceLocation LIT_PROGRESS_SPRITE =
            ResourceLocation.withDefaultNamespace("container/furnace/lit_progress");
    private static final ResourceLocation BURN_PROGRESS_SPRITE =
            ResourceLocation.withDefaultNamespace("container/furnace/burn_progress");
    private static final Component FILTER_NAME =
            Component.translatable("gui.recipebook.toggleRecipes.smeltable");

    private static final List<RecipeBookComponent.TabInfo> TABS = List.of(
            new RecipeBookComponent.TabInfo(SearchRecipeBookCategory.FURNACE),
            new RecipeBookComponent.TabInfo(Items.PORKCHOP,  RecipeBookCategories.FURNACE_FOOD),
            new RecipeBookComponent.TabInfo(Items.STONE,     RecipeBookCategories.FURNACE_BLOCKS),
            new RecipeBookComponent.TabInfo(Items.IRON_INGOT, RecipeBookCategories.FURNACE_MISC)
    );

    public PurifierScreen(PurifierMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title, FILTER_NAME,
                TEXTURE, LIT_PROGRESS_SPRITE, BURN_PROGRESS_SPRITE, TABS);
    }

    @Override
    public void init() {
        super.init();
        // Left-align the title so it doesn't overlap the centered fuel slot.
        // inventoryLabelY sits in the separator strip between the purifier slots
        // and the player inventory grid (imageHeight=166, inventory starts at ~81).
        this.titleLabelX = 8;
        this.titleLabelY = 6;
        this.inventoryLabelX = 8;
        this.inventoryLabelY = 68;
    }
}
