package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import net.minecraft.world.item.*;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NuiCraftItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NuiCraft.MODID);

    // Block items - automatically create BlockItems for all blocks
    public static final DeferredItem<BlockItem> FLUID_PROTODERMIS = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.FLUID_PROTODERMIS);
    public static final DeferredItem<BlockItem> FLUID_PROTODERMIS_PURE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.FLUID_PROTODERMIS_PURE);
    public static final DeferredItem<BlockItem> FLUID_PROTODERMIS_MOLTEN = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.FLUID_PROTODERMIS_MOLTEN);
    public static final DeferredItem<BlockItem> FLUID_PROTODERMIS_PURE_MOLTEN = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.FLUID_PROTODERMIS_PURE_MOLTEN);
    
    public static final DeferredItem<BlockItem> MASK_FORGE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.MASK_FORGE);
    public static final DeferredItem<BlockItem> PURIFIER_ITEM = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.PURIFIER);
    
    public static final DeferredItem<BlockItem> KORO_BLOCK = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.KORO_BLOCK);
    
    public static final DeferredItem<BlockItem> NUVA_CUBE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.NUVA_CUBE);
    public static final DeferredItem<BlockItem> MATANUI_STONE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.MATANUI_STONE);
    public static final DeferredItem<BlockItem> MAKUTA_STONE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.MAKUTA_STONE);
    public static final DeferredItem<BlockItem> LIGHTSTONE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.LIGHTSTONE);
    
    public static final DeferredItem<BlockItem> LIGHTSTONE_ORE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.LIGHTSTONE_ORE);
    public static final DeferredItem<BlockItem> HEATSTONE_ORE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.HEATSTONE_ORE);
    public static final DeferredItem<BlockItem> PROTODERMIS_ORE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.PROTODERMIS_ORE);
    
    public static final DeferredItem<BlockItem> BAMBOO = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.BAMBOO);
    public static final DeferredItem<BlockItem> BLOCK_PROTODERMIS = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.BLOCK_PROTODERMIS);
    public static final DeferredItem<BlockItem> BLOCK_PROTOSTEEL = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.BLOCK_PROTOSTEEL);

    // Generic items - TODO: Port ItemGenericMeta
    public static final DeferredItem<Item> GENERIC_ITEM = ITEMS.registerSimpleItem("generic_item");

    // Kanoka - TODO: Port ItemBambooDisc and ItemKanokaDisc
    public static final DeferredItem<Item> KANOKA_BAMBOO = ITEMS.registerSimpleItem("kanoka_bamboo");
    public static final DeferredItem<Item> KANOKA_DISC = ITEMS.registerSimpleItem("kanoka_disc");

    // Weapons - TODO: Port ItemDiscLauncher and tool classes
    public static final DeferredItem<Item> DISC_LAUNCHER = ITEMS.registerSimpleItem("disc_launcher");
    
    // Protodermis tools
    public static final DeferredItem<SwordItem> PROTODERMIS_SWORD = ITEMS.register("protodermis_sword",
            () -> new SwordItem(Tiers.IRON, new Item.Properties()
                    .attributes(SwordItem.createAttributes(Tiers.IRON, 3, -2.4F))));
    
    public static final DeferredItem<PickaxeItem> PROTODERMIS_PICK = ITEMS.register("protodermis_pick",
            () -> new PickaxeItem(Tiers.IRON, new Item.Properties()
                    .attributes(PickaxeItem.createAttributes(Tiers.IRON, 1.0F, -2.8F))));
    
    public static final DeferredItem<AxeItem> PROTODERMIS_AXE = ITEMS.register("protodermis_axe",
            () -> new AxeItem(Tiers.IRON, new Item.Properties()
                    .attributes(AxeItem.createAttributes(Tiers.IRON, 6.0F, -3.1F))));
    
    public static final DeferredItem<ShovelItem> PROTODERMIS_SHOVEL = ITEMS.register("protodermis_shovel",
            () -> new ShovelItem(Tiers.IRON, new Item.Properties()
                    .attributes(ShovelItem.createAttributes(Tiers.IRON, 1.5F, -3.0F))));
    
    public static final DeferredItem<HoeItem> PROTODERMIS_SCYTHE = ITEMS.register("protodermis_scythe",
            () -> new HoeItem(Tiers.IRON, new Item.Properties()
                    .attributes(HoeItem.createAttributes(Tiers.IRON, -2.0F, -1.0F))));

    // Special items - TODO: Port ItemHeatstoneLighter and ItemSluice
    public static final DeferredItem<Item> HEATSTONE_LIGHTER = ITEMS.registerSimpleItem("heatstone_lighter");
    public static final DeferredItem<Item> SLUICE = ITEMS.registerSimpleItem("sluice");

    // Mata Masks - TODO: Port ItemGoldMataMask and ItemColoredMask
    public static final DeferredItem<Item> MASK_MATA_GOLD = ITEMS.registerSimpleItem("mask_mata_gold");
    public static final DeferredItem<Item> MASK_MATA_KAKAMA = ITEMS.registerSimpleItem("mask_mata_kakama");
    public static final DeferredItem<Item> MASK_MATA_PAKARI = ITEMS.registerSimpleItem("mask_mata_pakari");
    public static final DeferredItem<Item> MASK_MATA_KAUKAU = ITEMS.registerSimpleItem("mask_mata_kaukau");
    public static final DeferredItem<Item> MASK_MATA_MIRU = ITEMS.registerSimpleItem("mask_mata_miru");
    public static final DeferredItem<Item> MASK_MATA_HAU = ITEMS.registerSimpleItem("mask_mata_hau");
    public static final DeferredItem<Item> MASK_MATA_AKAKU = ITEMS.registerSimpleItem("mask_mata_akaku");

    // Nuva Masks - TODO: Port ItemMask
    public static final DeferredItem<Item> MASK_NUVA_KAKAMA = ITEMS.registerSimpleItem("mask_nuva_kakama");
    public static final DeferredItem<Item> MASK_NUVA_PAKARI = ITEMS.registerSimpleItem("mask_nuva_pakari");
    public static final DeferredItem<Item> MASK_NUVA_KAUKAU = ITEMS.registerSimpleItem("mask_nuva_kaukau");
    public static final DeferredItem<Item> MASK_NUVA_MIRU = ITEMS.registerSimpleItem("mask_nuva_miru");
    public static final DeferredItem<Item> MASK_NUVA_HAU = ITEMS.registerSimpleItem("mask_nuva_hau");
    public static final DeferredItem<Item> MASK_NUVA_AKAKU = ITEMS.registerSimpleItem("mask_nuva_akaku");

    // Legendary Masks - TODO: Port ItemMaskMeta
    public static final DeferredItem<Item> MASK_IGNIKA = ITEMS.registerSimpleItem("mask_ignika");
    public static final DeferredItem<Item> MASK_VAHI = ITEMS.registerSimpleItem("mask_vahi");
}
