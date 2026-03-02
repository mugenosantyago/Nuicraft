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
 * Pure liquid protodermis. Sluicing the source block (level=0) yields 12
 * protodermis nuggets and removes the source — pure protodermis is far more
 * concentrated than regular.
 */
public class BlockPureProtodermis extends ProtodermisFluidBlock {

    private static final int NUGGET_COUNT = 12;

    public BlockPureProtodermis(Supplier<FlowingFluid> fluidSupplier, Properties props) {
        super(fluidSupplier, props);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(NuiCraftItems.SLUICE.get())) {
            return InteractionResult.PASS;
        }
        if (state.getValue(LEVEL) != 0) {
            return InteractionResult.PASS;
        }

        Vec3 hitVec = hitResult.getLocation();

        if (level.isClientSide) {
            for (int i = 0; i < 10; ++i) {
                level.addParticle(
                    new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(NuiCraftItems.NUGGET_PROTODERMIS.get())),
                    hitVec.x, hitVec.y, hitVec.z,
                    (level.random.nextFloat() - 0.5D) * 0.5D,
                    level.random.nextFloat() * 0.4D,
                    (level.random.nextFloat() - 0.5D) * 0.5D
                );
            }
        } else {
            ItemStack drop = new ItemStack(NuiCraftItems.NUGGET_PROTODERMIS.get(), NUGGET_COUNT);
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
