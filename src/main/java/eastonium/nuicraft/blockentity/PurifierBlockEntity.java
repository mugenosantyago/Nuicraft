package eastonium.nuicraft.blockentity;

import eastonium.nuicraft.core.NuiCraftRegistration;
import eastonium.nuicraft.menu.PurifierMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class PurifierBlockEntity extends AbstractFurnaceBlockEntity {

    public PurifierBlockEntity(BlockPos pos, BlockState state) {
        super(NuiCraftRegistration.PURIFIER_BE.get(), pos, state, RecipeType.SMELTING);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.nuicraft.purifier");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inventory) {
        return new PurifierMenu(id, inventory, this, this.dataAccess);
    }
}
