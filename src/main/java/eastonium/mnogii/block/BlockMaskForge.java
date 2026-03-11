package eastonium.mnogii.block;

import com.mojang.serialization.MapCodec;
import eastonium.mnogii.core.MnogiiBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.CraftingMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * Mask Forge — a themed crafting table.
 * Opens a standard 3×3 crafting grid so players can craft masks using
 * the shapeless recipes defined in data/mnogii/recipes/.
 */
public class BlockMaskForge extends Block {

    public static final MapCodec<BlockMaskForge> CODEC = simpleCodec(BlockMaskForge::new);

    private static final Component TITLE = Component.translatable("block.mnogii.mask_forge");

    public BlockMaskForge(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<BlockMaskForge> codec() {
        return CODEC;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos,
                                               Player player, BlockHitResult hit) {
        if (level.isClientSide) {
            return InteractionResult.SUCCESS;
        }
        ContainerLevelAccess access = ContainerLevelAccess.create(level, pos);
        player.openMenu(new SimpleMenuProvider(
                (id, inv, p) -> new MaskForgeCraftingMenu(id, inv, access),
                TITLE
        ));
        return InteractionResult.SUCCESS;
    }

    /**
     * A CraftingMenu that validates against BlockMaskForge instead of Blocks.CRAFTING_TABLE,
     * preventing the menu from immediately closing when opened at the Mask Forge.
     */
    private static class MaskForgeCraftingMenu extends CraftingMenu {

        private final ContainerLevelAccess access;

        MaskForgeCraftingMenu(int id, Inventory inv, ContainerLevelAccess access) {
            super(id, inv, access);
            this.access = access;
        }

        @Override
        public boolean stillValid(Player player) {
            return stillValid(this.access, player, MnogiiBlocks.MASK_FORGE.get());
        }
    }
}
