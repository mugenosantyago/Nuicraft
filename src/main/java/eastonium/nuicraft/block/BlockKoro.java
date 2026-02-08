// NOTE: This block originally had 16 variants using metadata (see BlockKoro.java.TODO)
// For now, simplified to a single block. TODO: Split into separate blocks per variant

package eastonium.nuicraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

// Simplified version - originally had 16 variants via metadata
// TODO: Split into separate block types for each variant
public class BlockKoro extends Block {
    public BlockKoro(BlockBehaviour.Properties properties) {
        super(properties);
    }
    
    public static BlockBehaviour.Properties createProperties() {
        return BlockBehaviour.Properties.of()
                .strength(1.5F, 5.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE);
    }
}
