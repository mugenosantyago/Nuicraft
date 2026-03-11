package eastonium.mnogii.blockentity;

import eastonium.mnogii.core.MnogiiRegistration;
import eastonium.mnogii.menu.PurifierMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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
}
