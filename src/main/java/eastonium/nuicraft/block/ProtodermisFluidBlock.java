package eastonium.nuicraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

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
        int level = state.getValue(LEVEL);
        return level == 0 ? fluid.getSource(false) : fluid.getFlowing(15 - level, level == 8);
    }

    @Override
    public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (state.getFluidState().isSource()) {
            FlowingFluid fluid = fluidSupplier.get();
            level.scheduleTick(pos, fluid, fluid.getTickDelay(level));
        }
    }

    // neighborChanged is intentionally not overridden: its signature changed in MC 1.21.4
    // (added BlockChangedPayload). The initial flow tick is scheduled via onPlace; subsequent
    // propagation is driven by the FlowingFluid tick system itself.
}
