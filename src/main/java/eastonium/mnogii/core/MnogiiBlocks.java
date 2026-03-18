package eastonium.mnogii.core;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.block.*;
import eastonium.mnogii.block.BlockPurifier;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceBlock;
import net.minecraft.world.level.block.LadderBlock;
import net.minecraft.world.level.block.PressurePlateBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MnogiiBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Mnogii.MODID);

    /** Call on BlockBehaviour.Properties before registering (1.21 requires id set). */
    private static BlockBehaviour.Properties withBlockId(String name, BlockBehaviour.Properties props) {
        return props.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, name)));
    }

    // Flowing fluid blocks — backed by real FlowingFluid instances registered in MnogiiRegistration.
    // The DeferredHolder for each fluid is passed as a lazy Supplier so block registration
    // can complete before the fluid registry event fires (registration order is block → fluid).
    public static final DeferredBlock<BlockLiquidProtodermis> FLUID_PROTODERMIS =
        BLOCKS.registerBlock("fluid_protodermis",
            props -> new BlockLiquidProtodermis(MnogiiRegistration.SOURCE_PROTODERMIS, props),
            fluidBlockProps("fluid_protodermis", SoundType.EMPTY));

    public static final DeferredBlock<BlockPureProtodermis> FLUID_PROTODERMIS_PURE =
        BLOCKS.registerBlock("fluid_protodermis_pure",
            props -> new BlockPureProtodermis(MnogiiRegistration.SOURCE_PROTODERMIS_PURE, props),
            fluidBlockProps("fluid_protodermis_pure", SoundType.EMPTY));

    public static final DeferredBlock<BlockMoltenProtodermis> FLUID_PROTODERMIS_MOLTEN =
        BLOCKS.registerBlock("fluid_protodermis_molten",
            props -> new BlockMoltenProtodermis(MnogiiRegistration.SOURCE_PROTODERMIS_MOLTEN, props),
            fluidBlockProps("fluid_protodermis_molten", SoundType.EMPTY));

    public static final DeferredBlock<ProtodermisFluidBlock> FLUID_PROTODERMIS_PURE_MOLTEN =
        BLOCKS.registerBlock("fluid_protodermis_pure_molten",
            props -> new ProtodermisFluidBlock(MnogiiRegistration.SOURCE_PROTODERMIS_PURE_MOLTEN, props),
            fluidBlockProps("fluid_protodermis_pure_molten", SoundType.EMPTY));

    private static BlockBehaviour.Properties fluidBlockProps(String name, SoundType sound) {
        return withBlockId(name, BlockBehaviour.Properties.of()
            .noCollission().replaceable().strength(100.0F).noLootTable()
            .sound(sound));
    }

    // Machines
    public static final DeferredBlock<BlockMaskForge> MASK_FORGE = BLOCKS.registerBlock("mask_forge",
            BlockMaskForge::new, withBlockId("mask_forge", BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.METAL)));
    
    public static final DeferredBlock<BlockPurifier> PURIFIER = BLOCKS.registerBlock("purifier",
            BlockPurifier::new, withBlockId("purifier", BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.METAL)));

    // Decorative blocks
    public static final DeferredBlock<BlockKoro> KORO_BLOCK = BLOCKS.registerBlock("koro_block",
            BlockKoro::new, withBlockId("koro_block", BlockKoro.createProperties()));

    // Special blocks
    public static final DeferredBlock<BlockNuvaCube> NUVA_CUBE = BLOCKS.registerBlock("nuva_cube",
            BlockNuvaCube::new, withBlockId("nuva_cube", BlockNuvaCube.createProperties()));
    
    public static final DeferredBlock<BlockBionicleStone> MATANUI_STONE = BLOCKS.registerBlock("matanui_stone",
            BlockBionicleStone::new, withBlockId("matanui_stone", BlockBionicleStone.createProperties()));
    
    public static final DeferredBlock<BlockBionicleStone> MAKUTA_STONE = BLOCKS.registerBlock("makuta_stone",
            BlockBionicleStone::new, withBlockId("makuta_stone", BlockBionicleStone.createProperties()));
    
    // Lightstone
    public static final DeferredBlock<BlockLightstone> LIGHTSTONE = BLOCKS.registerBlock("lightstone",
            BlockLightstone::new, withBlockId("lightstone", BlockLightstone.createProperties()));

    // Ores
    public static final DeferredBlock<BlockOre> LIGHTSTONE_ORE = BLOCKS.registerBlock("lightstone_ore",
            BlockOre::new, withBlockId("lightstone_ore", BlockOre.createProperties().lightLevel((state) -> 10)));
    
    public static final DeferredBlock<BlockOre> HEATSTONE_ORE = BLOCKS.registerBlock("heatstone_ore",
            BlockOre::new, withBlockId("heatstone_ore", BlockOre.createProperties()));
    
    public static final DeferredBlock<BlockProtodermisDeposit> PROTODERMIS_ORE = BLOCKS.registerBlock("protodermis_ore",
            BlockProtodermisDeposit::new, withBlockId("protodermis_ore", BlockOre.createProperties()));

    public static final DeferredBlock<BlockOre> ONU_WAHI_STONE_ORE = BLOCKS.registerBlock("onu_wahi_stone_ore",
            BlockOre::new, withBlockId("onu_wahi_stone_ore", BlockOre.createProperties()));

    // Plants

    // Metal blocks
    public static final DeferredBlock<BlockProtodermis> BLOCK_PROTODERMIS = BLOCKS.registerBlock("block_protodermis",
            BlockProtodermis::new, withBlockId("block_protodermis", BlockMetal.createProperties()));
    
    public static final DeferredBlock<BlockMetal> BLOCK_PROTOSTEEL = BLOCKS.registerBlock("block_protosteel",
            BlockMetal::new, withBlockId("block_protosteel", BlockMetal.createProperties()));

    // ---- Ga-Koro wood set (seaweed wood) ----
    // Used by gakoro structure templates — ported from QFN 1.20.1.
    public static final DeferredBlock<Block> SEAWEED_WOOD_PLANKS = BLOCKS.registerSimpleBlock("seaweed_wood_planks",
            withBlockId("seaweed_wood_planks", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<RotatedPillarBlock> SEAWEED_WOOD_WOOD = BLOCKS.registerBlock("seaweed_wood_wood",
            RotatedPillarBlock::new, withBlockId("seaweed_wood_wood", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<SlabBlock> SEAWEED_WOOD_SLAB = BLOCKS.registerBlock("seaweed_wood_slab",
            SlabBlock::new, withBlockId("seaweed_wood_slab", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<StairBlock> SEAWEED_WOOD_STAIRS = BLOCKS.registerBlock("seaweed_wood_stairs",
            props -> new StairBlock(SEAWEED_WOOD_PLANKS.get().defaultBlockState(), props),
            withBlockId("seaweed_wood_stairs", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD)));
    // ---- Ga-Koro cured wood (rope/platform frames) ----
    public static final DeferredBlock<RotatedPillarBlock> CURED_WOOD_LOG = BLOCKS.registerBlock("cured_wood_log",
            RotatedPillarBlock::new, withBlockId("cured_wood_log", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<FenceBlock> CURED_WOOD_FENCE = BLOCKS.registerBlock("cured_wood_fence",
            FenceBlock::new, withBlockId("cured_wood_fence", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD)));
    // ---- Ga-Koro decorative blocks ----
    public static final DeferredBlock<Block> LIGHTSTONES_BLOCK = BLOCKS.registerSimpleBlock("lightstones_block",
            withBlockId("lightstones_block", BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.STONE)
                    .lightLevel(state -> 12)));
    public static final DeferredBlock<Block> LIGHT_GRAY_STONE = BLOCKS.registerSimpleBlock("light_gray_stone",
            withBlockId("light_gray_stone", BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    // mata_nui_stone: the old name used in gakoro NBTs, distinct from matanui_stone (which has the face texture)
    public static final DeferredBlock<Block> MATA_NUI_STONE = BLOCKS.registerSimpleBlock("mata_nui_stone",
            withBlockId("mata_nui_stone", BlockBehaviour.Properties.of().strength(3.0F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<LadderBlock> LE_KORO_LADDER = BLOCKS.registerBlock("le_koro_ladder",
            LadderBlock::new, withBlockId("le_koro_ladder", BlockBehaviour.Properties.of().strength(0.4F)
                    .sound(SoundType.LADDER).noOcclusion()));
    // Path/walkway blocks used in gakoro platform surfaces
    public static final DeferredBlock<Block> PATH = BLOCKS.registerSimpleBlock("path",
            withBlockId("path", BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOD)));
    public static final DeferredBlock<Block> PATH2 = BLOCKS.registerSimpleBlock("path2",
            withBlockId("path2", BlockBehaviour.Properties.of().strength(0.8F).sound(SoundType.WOOD)));

    // ---- Black stone set (Ta-Koro walls, Onu-Koro, Ko-Koro) ----
    public static final DeferredBlock<Block> BLACK_STONE_BRICK = BLOCKS.registerSimpleBlock("black_stone_brick",
            withBlockId("black_stone_brick", BlockBehaviour.Properties.of().strength(2.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> BLACK_STONE_SLAB = BLOCKS.registerBlock("black_stone_slab",
            SlabBlock::new, withBlockId("black_stone_slab", BlockBehaviour.Properties.of().strength(2.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<StairBlock> BLACK_STONE_STAIRS = BLOCKS.registerBlock("black_stone_stairs",
            props -> new StairBlock(BLACK_STONE_BRICK.get().defaultBlockState(), props),
            withBlockId("black_stone_stairs", BlockBehaviour.Properties.of().strength(2.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<FenceBlock> BLACK_STONE_FENCE = BLOCKS.registerBlock("black_stone_fence",
            FenceBlock::new, withBlockId("black_stone_fence", BlockBehaviour.Properties.of().strength(2.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> BLACK_STONE_LAMP = BLOCKS.registerSimpleBlock("black_stone_lamp",
            withBlockId("black_stone_lamp", BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.STONE).lightLevel(state -> 15).noOcclusion()));
    public static final DeferredBlock<PressurePlateBlock> BLACK_STONE_PRESSURE_PLATE = BLOCKS.registerBlock("black_stone_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.STONE, props),
            withBlockId("black_stone_pressure_plate", BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.STONE).noCollission()));

    // ---- Cured wood missing variants (Le-Koro, Po-Koro) ----
    public static final DeferredBlock<Block> CURED_WOOD_PLANKS = BLOCKS.registerSimpleBlock("cured_wood_planks",
            withBlockId("cured_wood_planks", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<RotatedPillarBlock> CURED_WOOD_WOOD = BLOCKS.registerBlock("cured_wood_wood",
            RotatedPillarBlock::new, withBlockId("cured_wood_wood", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.WOOD)));
    public static final DeferredBlock<PressurePlateBlock> CURED_WOOD_PRESSURE_PLATE = BLOCKS.registerBlock("cured_wood_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.OAK, props),
            withBlockId("cured_wood_pressure_plate", BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.WOOD).noCollission()));

    // ---- Ice wood set (Ko-Koro bridge & interior) ----
    public static final DeferredBlock<Block> ICE_PLANKS = BLOCKS.registerSimpleBlock("ice_planks",
            withBlockId("ice_planks", BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.GLASS).friction(0.98F)));
    public static final DeferredBlock<RotatedPillarBlock> ICE_WOOD = BLOCKS.registerBlock("ice_wood",
            RotatedPillarBlock::new, withBlockId("ice_wood", BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.GLASS).friction(0.98F)));
    public static final DeferredBlock<SlabBlock> ICE_SLAB = BLOCKS.registerBlock("ice_slab",
            SlabBlock::new, withBlockId("ice_slab", BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.GLASS).friction(0.98F)));
    public static final DeferredBlock<StairBlock> ICE_STAIRS = BLOCKS.registerBlock("ice_stairs",
            props -> new StairBlock(ICE_PLANKS.get().defaultBlockState(), props),
            withBlockId("ice_stairs", BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.GLASS).friction(0.98F)));
    public static final DeferredBlock<FenceBlock> ICE_FENCE = BLOCKS.registerBlock("ice_fence",
            FenceBlock::new, withBlockId("ice_fence", BlockBehaviour.Properties.of().strength(1.5F).sound(SoundType.GLASS).friction(0.98F)));
    public static final DeferredBlock<PressurePlateBlock> ICE_PRESSURE_PLATE = BLOCKS.registerBlock("ice_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.STONE, props),
            withBlockId("ice_pressure_plate", BlockBehaviour.Properties.of().strength(0.5F).sound(SoundType.GLASS).noCollission()));

    // ---- Light gray stone variants (Onu-Koro, Ko-Koro, Po-Koro) ----
    public static final DeferredBlock<Block> LIGHT_GRAY_STONE_BRICK = BLOCKS.registerSimpleBlock("light_gray_stone_brick",
            withBlockId("light_gray_stone_brick", BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<SlabBlock> LIGHT_GRAY_TOA_STONE_SLAB = BLOCKS.registerBlock("light_gray_toa_stone_slab",
            SlabBlock::new, withBlockId("light_gray_toa_stone_slab", BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<StairBlock> LIGHT_GRAY_TOA_STONE_STAIRS = BLOCKS.registerBlock("light_gray_toa_stone_stairs",
            props -> new StairBlock(LIGHT_GRAY_STONE.get().defaultBlockState(), props),
            withBlockId("light_gray_toa_stone_stairs", BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<FenceBlock> LIGHTGRAYSTONE_FENCE = BLOCKS.registerBlock("lightgraystone_fence",
            FenceBlock::new, withBlockId("lightgraystone_fence", BlockBehaviour.Properties.of().strength(1.5F, 6.0F).sound(SoundType.STONE)));

    // ---- Ko-Koro decorative text blocks ----
    public static final DeferredBlock<Block> SNOW_WALL_TEXT_BLOCK = BLOCKS.registerSimpleBlock("snow_wall_text_block",
            withBlockId("snow_wall_text_block", BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.SNOW)));
    public static final DeferredBlock<Block> SNOW_WALL_TEXT_BLOCK_2 = BLOCKS.registerSimpleBlock("snow_wall_text_block_2",
            withBlockId("snow_wall_text_block_2", BlockBehaviour.Properties.of().strength(0.2F).sound(SoundType.SNOW)));

    // ---- Onu-Koro ore ----
    public static final DeferredBlock<BlockOre> LIGHTSTONES_ORE = BLOCKS.registerBlock("lightstones_ore",
            BlockOre::new, withBlockId("lightstones_ore", BlockOre.createProperties().lightLevel(state -> 8)));

    // Koro stones (for structures / decoration) - all behave like vanilla stone
    public static final DeferredBlock<Block> TA_KORO_STONE = BLOCKS.registerSimpleBlock("ta_koro_stone",
            withBlockId("ta_koro_stone", koroStoneProps()));
    public static final DeferredBlock<Block> ONU_KORO_STONE = BLOCKS.registerSimpleBlock("onu_koro_stone",
            withBlockId("onu_koro_stone", koroStoneProps()));
    public static final DeferredBlock<Block> GA_KORO_STONE = BLOCKS.registerSimpleBlock("ga_koro_stone",
            withBlockId("ga_koro_stone", koroStoneProps()));
    public static final DeferredBlock<Block> KO_KORO_STONE = BLOCKS.registerSimpleBlock("ko_koro_stone",
            withBlockId("ko_koro_stone", koroStoneProps()));
    public static final DeferredBlock<Block> LE_KORO_STONE = BLOCKS.registerSimpleBlock("le_koro_stone",
            withBlockId("le_koro_stone", koroStoneProps()));
    public static final DeferredBlock<Block> PO_KORO_STONE = BLOCKS.registerSimpleBlock("po_koro_stone",
            withBlockId("po_koro_stone", koroStoneProps()));

    private static BlockBehaviour.Properties koroStoneProps() {
        return BlockBehaviour.Properties.of()
                .strength(1.5F, 6.0F)
                .requiresCorrectToolForDrops()
                .sound(SoundType.STONE);
    }
}
