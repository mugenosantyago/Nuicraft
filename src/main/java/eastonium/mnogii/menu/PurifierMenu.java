package eastonium.mnogii.menu;

import eastonium.mnogii.core.MnogiiItems;
import eastonium.mnogii.core.MnogiiRegistration;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.FurnaceFuelSlot;
import net.minecraft.world.inventory.FurnaceResultSlot;
import net.minecraft.world.inventory.RecipeBookType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipePropertySet;

public class PurifierMenu extends AbstractFurnaceMenu {

    /** Server-side: opened from the block entity. */
    public PurifierMenu(int containerId, Inventory playerInventory,
                        Container container, ContainerData data) {
        super(MnogiiRegistration.PURIFIER_MENU.get(), MnogiiRegistration.PURIFYING_TYPE.get(),
                RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE,
                containerId, playerInventory, container, data);
        fixSlotPositions(playerInventory);
    }

    /** Client-side: reconstructed from network packet. */
    public PurifierMenu(int containerId, Inventory playerInventory) {
        super(MnogiiRegistration.PURIFIER_MENU.get(), MnogiiRegistration.PURIFYING_TYPE.get(),
                RecipePropertySet.FURNACE_INPUT, RecipeBookType.FURNACE,
                containerId, playerInventory);
        fixSlotPositions(playerInventory);
    }

    /**
     * Replaces the three furnace slots with identically-typed slots at positions matching
     * purifier_gui.png (determined by pixel analysis of the texture).
     *
     * AbstractFurnaceMenu adds them at vanilla-furnace positions (56,17), (56,53), (116,35).
     * Slot.x and Slot.y are final, so we replace the slot objects rather than mutating them.
     *
     * Slot 0 – ingredient (input bucket):             (74,  8)
     * Slot 1 – fuel (optional, machine is fuel-free): (94, 30)
     * Slot 2 – result (output bucket):               (134,  8)
     */
    private void fixSlotPositions(Inventory playerInventory) {
        Container container = this.slots.get(0).container;

        Slot ingredient = new Slot(container, 0, 74, 8);
        ingredient.index = 0;
        this.slots.set(0, ingredient);

        Slot fuel = new FurnaceFuelSlot(this, container, 1, 94, 30);
        fuel.index = 1;
        this.slots.set(1, fuel);

        Slot result = new FurnaceResultSlot(playerInventory.player, container, 2, 134, 8);
        result.index = 2;
        this.slots.set(2, result);
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
