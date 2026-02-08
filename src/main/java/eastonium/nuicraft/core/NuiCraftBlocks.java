package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.block.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NuiCraftBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NuiCraft.MODID);

    // Fluid blocks - will be registered by fluid system
    public static final DeferredBlock<Block> FLUID_PROTODERMIS = BLOCKS.register("fluid_protodermis",
            () -> new Block(BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable()));
    
    public static final DeferredBlock<Block> FLUID_PROTODERMIS_PURE = BLOCKS.register("fluid_protodermis_pure",
            () -> new Block(BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable()));
    
    public static final DeferredBlock<Block> FLUID_PROTODERMIS_MOLTEN = BLOCKS.register("fluid_protodermis_molten",
            () -> new Block(BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable()));
    
    public static final DeferredBlock<Block> FLUID_PROTODERMIS_PURE_MOLTEN = BLOCKS.register("fluid_protodermis_pure_molten",
            () -> new Block(BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable()));

    // Machines - TODO: Need to port BlockMaskForge and BlockPurifier
    public static final DeferredBlock<Block> MASK_FORGE = BLOCKS.register("mask_forge",
            () -> new Block(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.METAL)));
    
    public static final DeferredBlock<Block> PURIFIER = BLOCKS.register("purifier",
            () -> new Block(BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.METAL)));

    // Decorative blocks - TODO: Need to port BlockKoro
    public static final DeferredBlock<Block> KORO_BLOCK = BLOCKS.register("koro_block",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.STONE)));

    // Special blocks
    public static final DeferredBlock<BlockNuvaCube> NUVA_CUBE = BLOCKS.register("nuva_cube",
            () -> new BlockNuvaCube(BlockNuvaCube.createProperties()));
    
    public static final DeferredBlock<BlockBionicleStone> MATANUI_STONE = BLOCKS.register("matanui_stone",
            () -> new BlockBionicleStone(BlockBionicleStone.createProperties()));
    
    public static final DeferredBlock<BlockBionicleStone> MAKUTA_STONE = BLOCKS.register("makuta_stone",
            () -> new BlockBionicleStone(BlockBionicleStone.createProperties()));
    
    // Lightstone
    public static final DeferredBlock<BlockLightstone> LIGHTSTONE = BLOCKS.register("lightstone",
            () -> new BlockLightstone(BlockLightstone.createProperties()));

    // Ores - TODO: Port BlockOre and BlockProtodermisDeposit
    public static final DeferredBlock<Block> LIGHTSTONE_ORE = BLOCKS.register("lightstone_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3.0F, 5.0F)
                    .lightLevel((state) -> 10)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));
    
    public static final DeferredBlock<Block> HEATSTONE_ORE = BLOCKS.register("heatstone_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3.0F, 5.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));
    
    public static final DeferredBlock<Block> PROTODERMIS_ORE = BLOCKS.register("protodermis_ore",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(3.0F, 5.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)));

    // Plants - TODO: Port BlockBamboo
    public static final DeferredBlock<Block> BAMBOO = BLOCKS.register("bamboo",
            () -> new Block(BlockBehaviour.Properties.of().strength(1.0F, 9001.0F).sound(SoundType.WOOD)));

    // Metal blocks
    public static final DeferredBlock<BlockMetal> BLOCK_PROTODERMIS = BLOCKS.register("block_protodermis",
            () -> new BlockMetal(BlockMetal.createProperties()));
    
    public static final DeferredBlock<BlockMetal> BLOCK_PROTOSTEEL = BLOCKS.register("block_protosteel",
            () -> new BlockMetal(BlockMetal.createProperties()));
}
