package eastonium.mnogii.blockentity;

import eastonium.mnogii.core.MnogiiRegistration;
import eastonium.mnogii.menu.PurifierMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PurifierBlockEntity extends AbstractFurnaceBlockEntity {

    public PurifierBlockEntity(BlockPos pos, BlockState state) {
        super(MnogiiRegistration.PURIFIER_BE.get(), pos, state, MnogiiRegistration.PURIFYING_TYPE.get());
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.mnogii.purifier");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new PurifierMenu(id, inventory, this, this.dataAccess);
    }

    /**
     * Custom server tick that makes the Purifier fuel-free.
     *
     * The standard {@link AbstractFurnaceBlockEntity#serverTick} only processes when the machine
     * is "lit" (litTimeRemaining > 0), which normally requires a fuel item in slot 1.
     * Here we pre-set the lit timer whenever an ingredient is waiting and the machine is idle,
     * so processing starts automatically without consuming any fuel.
     *
     * The fuel slot (slot 1) still exists in the GUI and can optionally hold fuel, but the machine
     * will also run with an empty fuel slot.
     */
    public static void fuelFreeTick(ServerLevel level, BlockPos pos, BlockState state, PurifierBlockEntity be) {
        // dataAccess index 0 = litTimeRemaining, index 1 = litTotalTime
        boolean machineCold = be.dataAccess.get(0) == 0;
        boolean hasIngredient = !be.items.get(SLOT_INPUT).isEmpty();

        if (machineCold && hasIngredient) {
            // Pretend the machine just received fuel for exactly one cooking cycle (200 ticks).
            // No fuel item is consumed because we set the counters directly rather than
            // going through AbstractFurnaceBlockEntity's normal fuel-consumption path.
            be.dataAccess.set(0, 200);   // litTimeRemaining
            be.dataAccess.set(1, 200);   // litTotalTime
        }

        AbstractFurnaceBlockEntity.serverTick(level, pos, state, be);
    }
}
