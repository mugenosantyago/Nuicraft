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
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

/**
 * Molten protodermis deposit block.
 * Each sluice use has a 1-in-6 chance to yield one protosteel nugget,
 * making it much rarer than normal liquid protodermis (which always gives a protodermis nugget).
 * The block depletes after MAX_USES sluice attempts regardless of drops.
 */
public class BlockMoltenProtodermis extends Block {

    public static final int MAX_USES = 20;
    public static final IntegerProperty USES = IntegerProperty.create("uses", 0, MAX_USES);

    /** Probability of dropping a protosteel nugget per sluice use (1 in NUGGET_CHANCE). */
    private static final int NUGGET_CHANCE = 6;

    public BlockMoltenProtodermis(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(USES, MAX_USES));
    }

    @Override
    protected InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
                                          Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!stack.is(NuiCraftItems.SLUICE.get())) {
            return InteractionResult.PASS;
        }

        Vec3 hitVec = hitResult.getLocation();

        if (level.isClientSide) {
            for (int i = 0; i < 5; ++i) {
                level.addParticle(
                    new ItemParticleOption(ParticleTypes.ITEM, new ItemStack(NuiCraftItems.NUGGET_PROTOSTEEL.get())),
                    hitVec.x, hitVec.y, hitVec.z,
                    (level.random.nextFloat() - 0.5D) * 0.3D,
                    level.random.nextFloat() * 0.3D,
                    (level.random.nextFloat() - 0.5D) * 0.3D
                );
            }
        } else {
            // 1-in-NUGGET_CHANCE chance to yield a protosteel nugget
            if (level.random.nextInt(NUGGET_CHANCE) == 0) {
                ItemStack dropStack = new ItemStack(NuiCraftItems.NUGGET_PROTOSTEEL.get(), 1);
                ItemEntity itemEntity = new ItemEntity(level, hitVec.x, hitVec.y, hitVec.z, dropStack);
                itemEntity.setDeltaMovement(
                    level.random.nextGaussian() * 0.06D,
                    level.random.nextGaussian() * 0.06D + 0.2D,
                    level.random.nextGaussian() * 0.06D
                );
                level.addFreshEntity(itemEntity);
            }

            int uses = state.getValue(USES);
            if (uses <= 1) {
                level.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
            } else {
                level.setBlock(pos, state.setValue(USES, uses - 1), 3);
            }
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(USES);
    }
}
