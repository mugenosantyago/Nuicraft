package eastonium.nuicraft.block;

import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class BlockProtodermisDeposit extends BlockOre {
    public static final IntegerProperty DROPS = IntegerProperty.create("drops", 0, 4);

    public BlockProtodermisDeposit(BlockBehaviour.Properties properties) {
        super(properties, UniformInt.of(3, 7));
        this.registerDefaultState(this.stateDefinition.any().setValue(DROPS, 0));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, 
                                         Player player, InteractionHand hand, BlockHitResult hitResult) {
        // Check if player is using sluice item
        if (stack.is(NuiCraftItems.SLUICE.get())) {
            if (level.isClientSide) {
                // Spawn particles on client side
                Vec3 hitVec = hitResult.getLocation();
                for (int i = 0; i < 5; ++i) {
                    level.addParticle(
                        new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(NuiCraftItems.GENERIC_ITEM.get())),
                        hitVec.x, hitVec.y, hitVec.z,
                        (level.random.nextFloat() - 0.5D) * 0.2D,
                        level.random.nextFloat() * 0.2D,
                        (level.random.nextFloat() - 0.5D) * 0.2D
                    );
                }
            } else {
                // Server-side logic
                int drops = state.getValue(DROPS);
                
                // Drop item at hit location
                Vec3 hitVec = hitResult.getLocation();
                ItemStack dropStack = new ItemStack(NuiCraftItems.GENERIC_ITEM.get(), 1); // TODO: Get proto blob item
                ItemEntity itemEntity = new ItemEntity(level, hitVec.x, hitVec.y, hitVec.z, dropStack);
                itemEntity.setDeltaMovement(
                    level.random.nextGaussian() * 0.06D,
                    level.random.nextGaussian() * 0.06D + 0.2D,
                    level.random.nextGaussian() * 0.06D
                );
                level.addFreshEntity(itemEntity);
                
                // Update block state or convert to stone
                if (drops <= 0) {
                    level.setBlockAndUpdate(pos, Blocks.STONE.defaultBlockState());
                } else {
                    level.setBlock(pos, state.setValue(DROPS, drops - 1), 3);
                }
            }
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }

    @Override
    protected void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
        if (!oldState.is(this) && state.getValue(DROPS) == 0) {
            level.setBlock(pos, state.setValue(DROPS, 1 + level.random.nextInt(3)), 3);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(DROPS);
    }
}
