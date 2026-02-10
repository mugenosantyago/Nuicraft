package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.block.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NuiCraftBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(NuiCraft.MODID);

    /** Call on BlockBehaviour.Properties before registering (1.21 requires id set). */
    private static BlockBehaviour.Properties withBlockId(String name, BlockBehaviour.Properties props) {
        return props.setId(ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, name)));
    }

    // Fluid blocks - placeholder
    public static final DeferredBlock<Block> FLUID_PROTODERMIS = BLOCKS.registerSimpleBlock("fluid_protodermis",
            withBlockId("fluid_protodermis", BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable()));
    
    public static final DeferredBlock<Block> FLUID_PROTODERMIS_PURE = BLOCKS.registerSimpleBlock("fluid_protodermis_pure",
            withBlockId("fluid_protodermis_pure", BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable()));
    
    public static final DeferredBlock<Block> FLUID_PROTODERMIS_MOLTEN = BLOCKS.registerSimpleBlock("fluid_protodermis_molten",
            withBlockId("fluid_protodermis_molten", BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable()));
    
    public static final DeferredBlock<Block> FLUID_PROTODERMIS_PURE_MOLTEN = BLOCKS.registerSimpleBlock("fluid_protodermis_pure_molten",
            withBlockId("fluid_protodermis_pure_molten", BlockBehaviour.Properties.of().noCollission().strength(100.0F).noLootTable()));

    // Machines
    public static final DeferredBlock<Block> MASK_FORGE = BLOCKS.registerSimpleBlock("mask_forge",
            withBlockId("mask_forge", BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.METAL)));
    
    public static final DeferredBlock<Block> PURIFIER = BLOCKS.registerSimpleBlock("purifier",
            withBlockId("purifier", BlockBehaviour.Properties.of().strength(3.5F).sound(SoundType.METAL)));

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
    public static final DeferredBlock<BlockBamboo> BAMBOO = BLOCKS.registerBlock("bamboo",
            BlockBamboo::new, withBlockId("bamboo", BlockBamboo.createProperties()));

    // Metal blocks
    public static final DeferredBlock<BlockMetal> BLOCK_PROTODERMIS = BLOCKS.registerBlock("block_protodermis",
            BlockMetal::new, withBlockId("block_protodermis", BlockMetal.createProperties()));
    
    public static final DeferredBlock<BlockMetal> BLOCK_PROTOSTEEL = BLOCKS.registerBlock("block_protosteel",
            BlockMetal::new, withBlockId("block_protosteel", BlockMetal.createProperties()));

    // Koro stones (for structures / decoration)
    public static final DeferredBlock<Block> TA_KORO_STONE = BLOCKS.registerSimpleBlock("ta_koro_stone",
            withBlockId("ta_koro_stone", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ONU_KORO_STONE = BLOCKS.registerSimpleBlock("onu_koro_stone",
            withBlockId("onu_koro_stone", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> GA_KORO_STONE = BLOCKS.registerSimpleBlock("ga_koro_stone",
            withBlockId("ga_koro_stone", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> KO_KORO_STONE = BLOCKS.registerSimpleBlock("ko_koro_stone",
            withBlockId("ko_koro_stone", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> LE_KORO_STONE = BLOCKS.registerSimpleBlock("le_koro_stone",
            withBlockId("le_koro_stone", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
    public static final DeferredBlock<Block> PO_KORO_STONE = BLOCKS.registerSimpleBlock("po_koro_stone",
            withBlockId("po_koro_stone", BlockBehaviour.Properties.of().strength(2.0F).sound(SoundType.STONE)));
}
