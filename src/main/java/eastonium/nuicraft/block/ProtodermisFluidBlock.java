package eastonium.nuicraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.level.redstone.Orientation;
import net.neoforged.neoforge.fluids.FluidInteractionRegistry;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Base class for all protodermis fluid blocks. Extends {@link LiquidBlock} but resolves the
 * block/fluid circular-registration problem by accepting a lazy {@link Supplier} for the
 * flowing fluid rather than the actual instance. {@code Fluids.WATER} is passed to the
 * superclass constructor as an inert placeholder; every method that would use the stored
 * {@code fluid} field is overridden here to use the supplier instead.
 */
public class ProtodermisFluidBlock extends LiquidBlock {

    protected final Supplier<FlowingFluid> fluidSupplier;

    public ProtodermisFluidBlock(Supplier<FlowingFluid> fluidSupplier, Properties props) {
        super(Fluids.WATER, props);   // placeholder — overridden below
        this.fluidSupplier = fluidSupplier;
    }

    // ---- Override every LiquidBlock method that accesses the stored fluid field ----

    @Override
    public FluidState getFluidState(BlockState state) {
        FlowingFluid fluid = fluidSupplier.get();
        // Mirror vanilla LiquidBlock.stateCache logic:
        //   level 0       → source
        //   level 1–7     → getFlowing(8 - level, false)  [amounts 7..1]
        //   level 8+      → getFlowing(8, true)           [falling]
        int level = Math.min(state.getValue(LEVEL), 8);
        if (level == 0) return fluid.getSource(false);
        if (level == 8) return fluid.getFlowing(8, true);
        return fluid.getFlowing(8 - level, false);
    }

    // ---- BucketPickup — use the supplier so the correct filled bucket is returned ----

    @Override
    public ItemStack pickupBlock(@Nullable LivingEntity entity, LevelAccessor level,
                                 BlockPos pos, BlockState state) {
        if (state.getValue(LEVEL) == 0) {
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11);
            return new ItemStack(fluidSupplier.get().getBucket());
        }
        return ItemStack.EMPTY;
    }

    @Override
    public Optional<SoundEvent> getPickupSound() {
        return fluidSupplier.get().getPickupSound();
    }

    // ---- Tick scheduling — mirrors LiquidBlock but uses the supplier for correct tick rates ----

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!FluidInteractionRegistry.canInteract(level, pos)) {
            FlowingFluid fluid = fluidSupplier.get();
            level.scheduleTick(pos, state.getFluidState().getType(), fluid.getTickDelay(level));
        }
    }

    @Override
    protected void neighborChanged(BlockState state, Level level, BlockPos pos, Block block,
                                   @Nullable Orientation orientation, boolean isMoving) {
        if (!FluidInteractionRegistry.canInteract(level, pos)) {
            FlowingFluid fluid = fluidSupplier.get();
            level.scheduleTick(pos, state.getFluidState().getType(), fluid.getTickDelay(level));
        }
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader levelReader,
                                     ScheduledTickAccess tickAccess, BlockPos pos,
                                     Direction direction, BlockPos neighborPos,
                                     BlockState neighborState, RandomSource random) {
        if (state.getFluidState().isSource() || neighborState.getFluidState().isSource()) {
            FlowingFluid fluid = fluidSupplier.get();
            tickAccess.scheduleTick(pos, state.getFluidState().getType(), fluid.getTickDelay(levelReader));
        }
        return super.updateShape(state, levelReader, tickAccess, pos, direction, neighborPos, neighborState, random);
    }
}
