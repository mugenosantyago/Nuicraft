package eastonium.nuicraft.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class BlockNuvaCube extends Block {
    public BlockNuvaCube(BlockBehaviour.Properties properties) {
        super(properties);
    }
    
    public static BlockBehaviour.Properties createProperties() {
        return BlockBehaviour.Properties.of()
                .strength(-1.0F, 3600000.0F)
                .lightLevel((state) -> 10)
                .sound(SoundType.STONE)
                .noLootTable();
    }
}
