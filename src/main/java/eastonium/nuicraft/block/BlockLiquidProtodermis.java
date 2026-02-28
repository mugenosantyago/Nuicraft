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
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Placeholder liquid protodermis block that can be sluiced to yield protodermis nuggets.
 * One use of the sluice consumes the entire liquid block and drops 1–3 nuggets.
 */
public class BlockLiquidProtodermis extends Block {

    public BlockLiquidProtodermis(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(NuiCraftItems.SLUICE.get())) {
            return InteractionResult.PASS;
        }

        Vec3 hitVec = hitResult.getLocation();

        if (level.isClientSide) {
            for (int i = 0; i < 8; ++i) {
                level.addParticle(
                    new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(NuiCraftItems.NUGGET_PROTODERMIS.get())),
                    hitVec.x, hitVec.y, hitVec.z,
                    (level.random.nextFloat() - 0.5D) * 0.3D,
                    level.random.nextFloat() * 0.3D,
                    (level.random.nextFloat() - 0.5D) * 0.3D
                );
            }
        } else {
            int count = 1 + level.random.nextInt(3);
            ItemStack dropStack = new ItemStack(NuiCraftItems.NUGGET_PROTODERMIS.get(), count);
            ItemEntity itemEntity = new ItemEntity(level, hitVec.x, hitVec.y, hitVec.z, dropStack);
            itemEntity.setDeltaMovement(
                level.random.nextGaussian() * 0.06D,
                level.random.nextGaussian() * 0.06D + 0.2D,
                level.random.nextGaussian() * 0.06D
            );
            level.addFreshEntity(itemEntity);
            level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        }

        return InteractionResult.SUCCESS;
    }
}
