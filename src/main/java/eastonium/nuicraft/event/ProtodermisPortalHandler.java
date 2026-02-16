package eastonium.nuicraft.event;

import eastonium.nuicraft.core.NuiCraftBlocks;
import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.portal.PortalShape;
import net.neoforged.bus.api.SubscribeEvent;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * When a player uses a Toa stone on a protodermis block that is part of a valid empty frame,
 * fill the frame with Mata Nui portal blocks.
 */
public class ProtodermisPortalHandler {

    @SubscribeEvent
    public static void onRightClickBlock(net.neoforged.neoforge.event.entity.player.PlayerInteractEvent.RightClickBlock event) {
        if (event.getLevel().isClientSide()) return;
        if (event.getHand() != net.minecraft.world.InteractionHand.MAIN_HAND) return;
        ItemStack stack = event.getEntity().getItemInHand(event.getHand());
        if (!isToaStone(stack)) return;
        if (event.getLevel().getBlockState(event.getPos()).is(NuiCraftBlocks.BLOCK_PROTODERMIS.get())) {
            Level level = event.getLevel();
            BlockPos pos = event.getPos();
            Optional<PortalShape> optX = PortalShape.findEmptyPortalShape(level, pos, Direction.Axis.X);
            Optional<PortalShape> optZ = optX.isPresent() ? optX : PortalShape.findEmptyPortalShape(level, pos, Direction.Axis.Z);
            if (optZ.isPresent()) {
                fillPortalFromShape(level, optZ.get());
                if (level instanceof ServerLevel serverLevel) {
                    serverLevel.playSound(null, pos, SoundEvents.FLINTANDSTEEL_USE, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                if (!event.getEntity().getAbilities().instabuild) {
                    stack.shrink(1);
                }
                event.setCanceled(true);
                event.setCancellationResult(InteractionResult.SUCCESS);
            }
        }
    }

    private static boolean isToaStone(ItemStack stack) {
        return stack.is(NuiCraftItems.WATER_TOA_STONE.get()) || stack.is(NuiCraftItems.EARTH_TOA_STONE.get())
                || stack.is(NuiCraftItems.AIR_TOA_STONE.get()) || stack.is(NuiCraftItems.FIRE_TOA_STONE.get())
                || stack.is(NuiCraftItems.ICE_TOA_STONE.get()) || stack.is(NuiCraftItems.ROCK_TOA_STONE.get());
    }

    private static void fillPortalFromShape(Level level, PortalShape shape) {
        try {
            Field bottomLeft = PortalShape.class.getDeclaredField("bottomLeft");
            bottomLeft.setAccessible(true);
            Field height = PortalShape.class.getDeclaredField("height");
            height.setAccessible(true);
            Field width = PortalShape.class.getDeclaredField("width");
            width.setAccessible(true);
            Field rightDir = PortalShape.class.getDeclaredField("rightDir");
            rightDir.setAccessible(true);
            Field axis = PortalShape.class.getDeclaredField("axis");
            axis.setAccessible(true);

            BlockPos min = (BlockPos) bottomLeft.get(shape);
            int h = height.getInt(shape);
            int w = width.getInt(shape);
            Direction right = (Direction) rightDir.get(shape);
            Direction.Axis ax = (Direction.Axis) axis.get(shape);

            BlockState portalState = NuiCraftBlocks.MATA_NUI_PORTAL.get().defaultBlockState().setValue(BlockStateProperties.HORIZONTAL_AXIS, ax);
            BlockPos max = min.relative(Direction.UP, h - 1).relative(right, w - 1);
            BlockPos.betweenClosed(min, max).forEach(p -> level.setBlock(p, portalState, 18));
        } catch (ReflectiveOperationException e) {
            eastonium.nuicraft.NuiCraft.LOGGER.error("Failed to fill Mata Nui portal from shape", e);
        }
    }
}
