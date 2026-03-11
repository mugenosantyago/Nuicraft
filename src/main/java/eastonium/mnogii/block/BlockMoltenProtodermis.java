package eastonium.mnogii.block;

import eastonium.mnogii.core.MnogiiItems;
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
 * Flowing molten protodermis. Sluicing the source block (level=0) gives a 1-in-6
 * chance of yielding a protosteel nugget, then removes the source.
 */
public class BlockMoltenProtodermis extends ProtodermisFluidBlock {

    private static final int NUGGET_CHANCE = 6;

    public BlockMoltenProtodermis(Supplier<FlowingFluid> fluidSupplier, Properties props) {
        super(fluidSupplier, props);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(MnogiiItems.SLUICE.get())) {
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
                    new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(MnogiiItems.NUGGET_PROTOSTEEL.get())),
                    hitVec.x, hitVec.y, hitVec.z,
                    (level.random.nextFloat() - 0.5D) * 0.3D,
                    level.random.nextFloat() * 0.3D,
                    (level.random.nextFloat() - 0.5D) * 0.3D
                );
            }
        } else {
            if (level.random.nextInt(NUGGET_CHANCE) == 0) {
                ItemStack drop = new ItemStack(MnogiiItems.NUGGET_PROTOSTEEL.get(), 1);
                ItemEntity item = new ItemEntity(level, hitVec.x, hitVec.y, hitVec.z, drop);
                item.setDeltaMovement(
                    level.random.nextGaussian() * 0.06D,
                    level.random.nextGaussian() * 0.06D + 0.2D,
                    level.random.nextGaussian() * 0.06D
                );
                level.addFreshEntity(item);
            }
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }

        return InteractionResult.SUCCESS;
    }
}
