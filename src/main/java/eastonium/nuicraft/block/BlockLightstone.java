package eastonium.nuicraft.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.TorchBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BlockLightstone extends TorchBlock {
    public BlockLightstone(BlockBehaviour.Properties properties) {
        super(ParticleTypes.FLAME, properties);
    }
    
    public static BlockBehaviour.Properties createProperties() {
        return BlockBehaviour.Properties.of()
                .noCollission()
                .strength(0.0F)
                .lightLevel((state) -> 15)
                .sound(SoundType.GLASS);
    }
    
    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        // TODO: Add custom particle effects when particle system is migrated
        // For now, use default torch particles
        super.animateTick(state, level, pos, random);
    }
}