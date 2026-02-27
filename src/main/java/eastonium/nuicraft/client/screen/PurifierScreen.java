package eastonium.nuicraft.client.screen;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.menu.PurifierMenu;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.recipebook.SmeltingRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class PurifierScreen extends AbstractFurnaceScreen<PurifierMenu> {

    private static final ResourceLocation TEXTURE =
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/gui/purifier_gui.png");

    public PurifierScreen(PurifierMenu menu, Inventory playerInventory, Component title) {
        super(menu, new SmeltingRecipeBookComponent(), playerInventory, title, TEXTURE);
    }
}
