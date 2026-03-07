package eastonium.nuicraft.block;

import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.function.Supplier;

/**
 * Flowing liquid protodermis. Sluicing the source block (level=0) yields one
 * protodermis nugget and removes the source, causing the pool to drain naturally.
 */
public class BlockLiquidProtodermis extends ProtodermisFluidBlock {

    public BlockLiquidProtodermis(Supplier<FlowingFluid> fluidSupplier, Properties props) {
        super(fluidSupplier, props);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(NuiCraftItems.SLUICE.get())) {
            return InteractionResult.PASS;
        }
        // Only sluiceable on source blocks
        if (state.getValue(LEVEL) != 0) {
            return InteractionResult.PASS;
        }

        Vec3 hitVec = hitResult.getLocation();

        if (level.isClientSide) {
            for (int i = 0; i < 5; ++i) {
                level.addParticle(
                    new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(NuiCraftItems.NUGGET_PROTODERMIS.get())),
                    hitVec.x, hitVec.y, hitVec.z,
                    (level.random.nextFloat() - 0.5D) * 0.3D,
                    level.random.nextFloat() * 0.3D,
                    (level.random.nextFloat() - 0.5D) * 0.3D
                );
            }
        } else {
            int nuggets = 3 + level.random.nextInt(5); // 3–7 nuggets
            ItemStack drop = new ItemStack(NuiCraftItems.NUGGET_PROTODERMIS.get(), nuggets);
            ItemEntity item = new ItemEntity(level, hitVec.x, hitVec.y, hitVec.z, drop);
            item.setDeltaMovement(
                level.random.nextGaussian() * 0.06D,
                level.random.nextGaussian() * 0.06D + 0.2D,
                level.random.nextGaussian() * 0.06D
            );
            level.addFreshEntity(item);
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }

        return InteractionResult.SUCCESS;
    }
}
