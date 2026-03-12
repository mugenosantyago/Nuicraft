package eastonium.mnogii.menu;

import eastonium.mnogii.core.MnogiiItems;
import eastonium.mnogii.core.MnogiiRegistration;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;

public class PurifierMenu extends AbstractFurnaceMenu {

    /** Server-side: opened from the block entity. */
    public PurifierMenu(int containerId, Inventory playerInventory,
                        Container container, ContainerData data) {
        super(MnogiiRegistration.PURIFIER_MENU.get(), MnogiiRegistration.PURIFYING_TYPE.get(),
                RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE,
                containerId, playerInventory, container, data);
    }

    /** Client-side: reconstructed from network packet. */
    public PurifierMenu(int containerId, Inventory playerInventory) {
        super(MnogiiRegistration.PURIFIER_MENU.get(), MnogiiRegistration.PURIFYING_TYPE.get(),
                RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE,
                containerId, playerInventory);
    }

    /**
     * Override shift-click routing: items are sent to the input slot only when
     * they are valid purifying ingredients. Works on both client and server.
     */
    @Override
    protected boolean canSmelt(ItemStack stack) {
        return stack.is(MnogiiItems.PROTODERMIS_BUCKET.get())
                || stack.is(MnogiiItems.MOLTEN_PROTODERMIS_BUCKET.get());
    }
}
