package eastonium.nuicraft.block;

import com.mojang.serialization.MapCodec;
import eastonium.nuicraft.core.NuiCraftBlocks;
import eastonium.nuicraft.core.NuiCraftDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;
import java.util.Map;
import java.util.Set;

/**
 * Portal block to the Mata Nui dimension. Frame is built from protodermis blocks;
 * ignite with a Toa stone. Step inside to travel.
 */
public class BlockMataNuiPortal extends Block implements Portal {
    public static final MapCodec<BlockMataNuiPortal> CODEC = simpleCodec(BlockMataNuiPortal::new);
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    private static final Map<Direction.Axis, VoxelShape> SHAPES = Shapes.rotateHorizontalAxis(Block.column(4.0, 16.0, 0.0, 16.0));

    public BlockMataNuiPortal(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    public MapCodec<? extends Block> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPES.get(state.getValue(AXIS));
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity, net.minecraft.world.entity.InsideBlockEffectApplier insideBlockEffectApplier) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, pos);
        }
    }

    @Override
    public int getPortalTransitionTime(ServerLevel level, Entity entity) {
        return 80;
    }

    @Nullable
    @Override
    public TeleportTransition getPortalDestination(ServerLevel sourceLevel, Entity entity, BlockPos portalPos) {
        ServerLevel mataNui = sourceLevel.getServer().getLevel(NuiCraftDimensions.MATA_NUI);
        if (mataNui == null) return null;
        WorldBorder border = mataNui.getWorldBorder();
        double scale = 1.0;
        BlockPos destPos = border.clampToBounds(entity.getX() * scale, entity.getY(), entity.getZ() * scale);
        Vec3 destVec = Vec3.atBottomCenterOf(destPos).add(0, 0.5, 0);
        return new TeleportTransition(mataNui, destVec, entity.getDeltaMovement(), entity.getYRot(), entity.getXRot(), false, false, Set.of(), TeleportTransition.PLAY_PORTAL_SOUND);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduler, BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, net.minecraft.util.RandomSource random) {
        Direction.Axis axis = state.getValue(AXIS);
        boolean axisHorizontal = direction.getAxis() != Direction.Axis.Y && direction.getAxis() != axis;
        if (axisHorizontal && !neighborState.is(this) && !hasAdjacentFrameOrPortal(level, pos, axis)) {
            return Blocks.AIR.defaultBlockState();
        }
        return super.updateShape(state, level, scheduler, pos, direction, neighborPos, neighborState, random);
    }

    private static boolean hasAdjacentFrameOrPortal(BlockGetter level, BlockPos pos, Direction.Axis axis) {
        for (Direction dir : Direction.Plane.HORIZONTAL) {
            if (dir.getAxis() == axis) continue;
            BlockState adjacent = level.getBlockState(pos.relative(dir));
            if (adjacent.is(NuiCraftBlocks.BLOCK_PROTODERMIS.get()) || adjacent.is(NuiCraftBlocks.MATA_NUI_PORTAL.get())) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }
}
