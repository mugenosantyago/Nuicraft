package eastonium.nuicraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.common.extensions.IBlockExtension;

/**
 * Protodermis metal block; can be used as a portal frame for the Mata Nui portal.
 */
public class BlockProtodermis extends BlockMetal implements IBlockExtension {

    public BlockProtodermis(BlockBehaviour.Properties properties) {
        super(properties);
    }

    @Override
    public boolean isPortalFrame(BlockState state, BlockGetter level, BlockPos pos) {
        return true;
    }
}
