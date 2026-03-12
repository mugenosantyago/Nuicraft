package eastonium.mnogii.block;

import com.mojang.serialization.MapCodec;
import eastonium.mnogii.blockentity.PurifierBlockEntity;
import eastonium.mnogii.core.MnogiiRegistration;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractFurnaceBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class BlockPurifier extends AbstractFurnaceBlock {

    public static final MapCodec<BlockPurifier> CODEC = simpleCodec(BlockPurifier::new);

    public BlockPurifier(Properties properties) {
        super(properties);
    }

    @Override
    public MapCodec<BlockPurifier> codec() {
        return CODEC;
    }

    @Override
    protected void openContainer(Level level, BlockPos pos, Player player) {
        BlockEntity be = level.getBlockEntity(pos);
        if (be instanceof PurifierBlockEntity purifier) {
            player.openMenu(purifier);
        }
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new PurifierBlockEntity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state,
                                                                   BlockEntityType<T> type) {
        return level instanceof net.minecraft.server.level.ServerLevel serverLevel
                ? createTickerHelper(type, MnogiiRegistration.PURIFIER_BE.get(),
                        (l, pos, s, be) -> PurifierBlockEntity.fuelFreeTick(serverLevel, pos, s, be))
                : null;
    }
}
