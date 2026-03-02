package eastonium.nuicraft.item;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

/**
 * Sluice tool used to extract protodermis nuggets from a protodermis fluid source block.
 *
 * The sluice uses a fluid-aware ray trace (ClipContext.Fluid.SOURCE_ONLY) so the player
 * can click directly on the surface of the fluid block. Without this, fluid blocks are
 * invisible to the default ray trace (ClipContext.Fluid.NONE) and the interaction would
 * never reach the block.
 */
public class ItemSluice extends Item {

    public ItemSluice(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        // Use SOURCE_ONLY fluid tracing so the player can target the fluid surface directly.
        BlockHitResult hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY);
        if (hit.getType() != HitResult.Type.BLOCK) {
            return InteractionResult.PASS;
        }

        BlockPos pos = hit.getBlockPos();
        if (!level.mayInteract(player, pos)) {
            return InteractionResult.PASS;
        }

        BlockState state = level.getBlockState(pos);
        ItemStack sluice = player.getItemInHand(hand);

        // Delegate to the block's useItemOn — this calls BlockLiquidProtodermis.useItemOn()
        // which checks for the sluice item and harvests the nugget.
        InteractionResult result = state.useItemOn(sluice, level, player, hand, hit);
        if (result.consumesAction()) {
            return result;
        }
        return InteractionResult.PASS;
    }
}
