package eastonium.nuicraft.menu;

import eastonium.nuicraft.core.NuiCraftRegistration;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.crafting.RecipePropertySet;
import net.minecraft.world.item.crafting.RecipeType;

public class PurifierMenu extends AbstractFurnaceMenu {

    /** Server-side: opened from the block entity. */
    public PurifierMenu(int containerId, Inventory playerInventory,
                        Container container, ContainerData data) {
        super(NuiCraftRegistration.PURIFIER_MENU.get(), RecipeType.SMELTING,
                RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE,
                containerId, playerInventory, container, data);
    }

    /** Client-side: reconstructed from network packet. */
    public PurifierMenu(int containerId, Inventory playerInventory) {
        super(NuiCraftRegistration.PURIFIER_MENU.get(), RecipeType.SMELTING,
                RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE,
                containerId, playerInventory);
    }
}
