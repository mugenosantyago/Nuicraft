package eastonium.nuicraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;

// NOTE: Drop behavior now handled by loot tables (src/main/resources/data/nuicraft/loot_tables/blocks/)
// This class just defines the block properties
public class BlockOre extends Block {
    private final UniformInt xpRange;
    
    public BlockOre(BlockBehaviour.Properties properties, UniformInt xpRange) {
        super(properties);
        this.xpRange = xpRange;
    }
    
    public BlockOre(BlockBehaviour.Properties properties) {
        this(properties, UniformInt.of(3, 7)); // Default XP range
    }
    
    public static BlockBehaviour.Properties createProperties() {
        return BlockBehaviour.Properties.of()
                .strength(3.0F, 5.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE);
    }
    
    @Override
    protected void spawnAfterBreak(BlockState state, ServerLevel level, BlockPos pos, ItemStack stack, boolean dropExperience) {
        super.spawnAfterBreak(state, level, pos, stack, dropExperience);
        if (dropExperience && this.xpRange != null) {
            this.popExperience(level, pos, this.xpRange.sample(level.random));
        }
    }
}