package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.item.NuiCraftTiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.equipment.EquipmentAssets;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class NuiCraftItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(NuiCraft.MODID);

    /** Call on Item.Properties before passing to Item constructor (1.21 requires id set). */
    private static Item.Properties withItemId(String name, Item.Properties props) {
        return props.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, name)));
    }

    // Block items
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
    public static final DeferredItem<BlockItem> ONU_WAHI_STONE_ORE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.ONU_WAHI_STONE_ORE);
    public static final DeferredItem<BlockItem> BAMBOO = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.BAMBOO);
    public static final DeferredItem<BlockItem> BLOCK_PROTODERMIS = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.BLOCK_PROTODERMIS);
    public static final DeferredItem<BlockItem> BLOCK_PROTOSTEEL = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.BLOCK_PROTOSTEEL);
    public static final DeferredItem<BlockItem> TA_KORO_STONE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.TA_KORO_STONE);
    public static final DeferredItem<BlockItem> ONU_KORO_STONE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.ONU_KORO_STONE);
    public static final DeferredItem<BlockItem> GA_KORO_STONE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.GA_KORO_STONE);
    public static final DeferredItem<BlockItem> KO_KORO_STONE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.KO_KORO_STONE);
    public static final DeferredItem<BlockItem> LE_KORO_STONE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.LE_KORO_STONE);
    public static final DeferredItem<BlockItem> PO_KORO_STONE = ITEMS.registerSimpleBlockItem(NuiCraftBlocks.PO_KORO_STONE);

    // Generic items
    public static final DeferredItem<Item> GENERIC_ITEM = ITEMS.registerSimpleItem("generic_item");
    public static final DeferredItem<Item> KANOKA_BAMBOO = ITEMS.registerSimpleItem("kanoka_bamboo");
    public static final DeferredItem<Item> KANOKA_DISC = ITEMS.registerSimpleItem("kanoka_disc");
    public static final DeferredItem<Item> DISC_LAUNCHER = ITEMS.registerSimpleItem("disc_launcher");

    // Materials (from bionicle_qfn)
    public static final DeferredItem<Item> PROTODERMIS_SOLID = ITEMS.registerSimpleItem("protodermis_solid");
    public static final DeferredItem<Item> INGOT_PROTODERMIS = ITEMS.registerSimpleItem("ingot_protodermis");
    public static final DeferredItem<Item> INGOT_PROTOSTEEL = ITEMS.registerSimpleItem("ingot_protosteel");
    public static final DeferredItem<Item> NUGGET_PROTODERMIS = ITEMS.registerSimpleItem("nugget_protodermis");
    public static final DeferredItem<Item> NUGGET_PROTOSTEEL = ITEMS.registerSimpleItem("nugget_protosteel");
    public static final DeferredItem<Item> GEAR = ITEMS.registerSimpleItem("gear");
    public static final DeferredItem<Item> HAMMER = ITEMS.registerSimpleItem("hammer");
    public static final DeferredItem<Item> STONE_HAMMER = ITEMS.registerSimpleItem("stone_hammer");
    public static final DeferredItem<Item> ELEMENT_SWIPER = ITEMS.registerSimpleItem("element_swiper");

    // Toa stones (from bionicle_qfn)
    public static final DeferredItem<Item> WATER_TOA_STONE = ITEMS.registerSimpleItem("water_toa_stone");
    public static final DeferredItem<Item> EARTH_TOA_STONE = ITEMS.registerSimpleItem("earth_toa_stone");
    public static final DeferredItem<Item> AIR_TOA_STONE = ITEMS.registerSimpleItem("air_toa_stone");
    public static final DeferredItem<Item> FIRE_TOA_STONE = ITEMS.registerSimpleItem("fire_toa_stone");
    public static final DeferredItem<Item> ICE_TOA_STONE = ITEMS.registerSimpleItem("ice_toa_stone");
    public static final DeferredItem<Item> ROCK_TOA_STONE = ITEMS.registerSimpleItem("rock_toa_stone");
    public static final DeferredItem<Item> ONU_WAHI_STONE = ITEMS.registerSimpleItem("onu_wahi_stone");

    // Kanoka disk variants (from bionicle_qfn)
    public static final DeferredItem<Item> KANOKA_DISK_GA = ITEMS.registerSimpleItem("kanoka_disk_ga");
    public static final DeferredItem<Item> KANOKA_DISK_KO = ITEMS.registerSimpleItem("kanoka_disk_ko");
    public static final DeferredItem<Item> KANOKA_DISK_LE = ITEMS.registerSimpleItem("kanoka_disk_le");
    public static final DeferredItem<Item> KANOKA_DISK_ONU = ITEMS.registerSimpleItem("kanoka_disk_onu");
    public static final DeferredItem<Item> KANOKA_DISK_PO = ITEMS.registerSimpleItem("kanoka_disk_po");
    public static final DeferredItem<Item> KANOKA_DISK_TA = ITEMS.registerSimpleItem("kanoka_disk_ta");
    public static final DeferredItem<Item> KANOKA_OF_TIME = ITEMS.registerSimpleItem("kanoka_of_time");

    // Protodermis tools
    public static final DeferredItem<Item> PROTODERMIS_SWORD = ITEMS.registerItem("protodermis_sword",
            props -> new Item(withItemId("protodermis_sword", props).sword(NuiCraftTiers.PROTODERMIS, 3, -2.4F)));
    public static final DeferredItem<Item> PROTODERMIS_PICK = ITEMS.registerItem("protodermis_pick",
            props -> new Item(withItemId("protodermis_pick", props).pickaxe(NuiCraftTiers.PROTODERMIS, 1, -2.8F)));
    public static final DeferredItem<Item> PROTODERMIS_AXE = ITEMS.registerItem("protodermis_axe",
            props -> new Item(withItemId("protodermis_axe", props).axe(NuiCraftTiers.PROTODERMIS, 6.0F, -3.1F)));
    public static final DeferredItem<Item> PROTODERMIS_SHOVEL = ITEMS.registerItem("protodermis_shovel",
            props -> new Item(withItemId("protodermis_shovel", props).shovel(NuiCraftTiers.PROTODERMIS, 1.5F, -3.0F)));
    public static final DeferredItem<Item> PROTODERMIS_SCYTHE = ITEMS.registerItem("protodermis_scythe",
            props -> new Item(withItemId("protodermis_scythe", props).hoe(NuiCraftTiers.PROTODERMIS, -2, -1.0F)));

    // Special items
    public static final DeferredItem<Item> HEATSTONE_LIGHTER = ITEMS.registerItem("heatstone_lighter",
            props -> new eastonium.nuicraft.item.ItemHeatstoneLighter(withItemId("heatstone_lighter", props).stacksTo(1).durability(128)));
    public static final DeferredItem<Item> SLUICE = ITEMS.registerItem("sluice",
            props -> new eastonium.nuicraft.item.ItemSluice(withItemId("sluice", props).stacksTo(1)));

    // Masks - equippable as helmets with stat boosts (armor/toughness).
    // Rendered via vanilla equipment layer (flat texture on humanoid head). For 3D separate mask
    // rendering (e.g. like 1.20.1 with a private library), a custom render layer would need to
    // draw the model from assets/nuicraft/geo/armor/mask.geo.json on the player head.
    private static ResourceKey<EquipmentAsset> maskAsset(String name) {
        return ResourceKey.create(EquipmentAssets.ROOT_ID,
                ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, name));
    }

    private static Item.Properties maskProps(Item.Properties props, ResourceKey<EquipmentAsset> asset, double armor, double toughness) {
        ItemAttributeModifiers.Builder attrs = ItemAttributeModifiers.builder();
        attrs.add(Attributes.ARMOR, new AttributeModifier(
                ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_armor"),
                armor,
                AttributeModifier.Operation.ADD_VALUE
        ), EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
        if (toughness > 0) {
            attrs.add(Attributes.ARMOR_TOUGHNESS, new AttributeModifier(
                    ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "mask_toughness"),
                    toughness,
                    AttributeModifier.Operation.ADD_VALUE
            ), EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
        }
        return props.stacksTo(1)
                .attributes(attrs.build())
                .component(net.minecraft.core.component.DataComponents.EQUIPPABLE,
                        Equippable.builder(EquipmentSlot.HEAD).setAsset(asset).build());
    }

    public static final DeferredItem<Item> MASK_MATA_GOLD = ITEMS.registerItem("mask_mata_gold", props -> new Item(maskProps(withItemId("mask_mata_gold", props), maskAsset("empty"), 2, 0.5)));
    public static final DeferredItem<Item> MASK_MATA_KAKAMA = ITEMS.registerItem("mask_mata_kakama", props -> new Item(maskProps(withItemId("mask_mata_kakama", props), maskAsset("empty"), 3, 0.8)));
    public static final DeferredItem<Item> MASK_MATA_PAKARI = ITEMS.registerItem("mask_mata_pakari", props -> new Item(maskProps(withItemId("mask_mata_pakari", props), maskAsset("empty"), 3, 1.2)));
    public static final DeferredItem<Item> MASK_MATA_KAUKAU = ITEMS.registerItem("mask_mata_kaukau", props -> new Item(maskProps(withItemId("mask_mata_kaukau", props), maskAsset("empty"), 3, 0.9)));
    public static final DeferredItem<Item> MASK_MATA_MIRU = ITEMS.registerItem("mask_mata_miru", props -> new Item(maskProps(withItemId("mask_mata_miru", props), maskAsset("empty"), 3, 0.7)));
    public static final DeferredItem<Item> MASK_MATA_HAU = ITEMS.registerItem("mask_mata_hau", props -> new Item(maskProps(withItemId("mask_mata_hau", props), maskAsset("empty"), 3, 1.0)));
    public static final DeferredItem<Item> MASK_MATA_AKAKU = ITEMS.registerItem("mask_mata_akaku", props -> new Item(maskProps(withItemId("mask_mata_akaku", props), maskAsset("empty"), 3, 0.8)));
    public static final DeferredItem<Item> MASK_MATA_KOMAU = ITEMS.registerItem("mask_mata_komau", props -> new Item(maskProps(withItemId("mask_mata_komau", props), maskAsset("empty"), 3, 0.8)));
    public static final DeferredItem<Item> MASK_MATA_MAHIKI = ITEMS.registerItem("mask_mata_mahiki", props -> new Item(maskProps(withItemId("mask_mata_mahiki", props), maskAsset("empty"), 3, 0.8)));
    public static final DeferredItem<Item> MASK_MATA_MATATU = ITEMS.registerItem("mask_mata_matatu", props -> new Item(maskProps(withItemId("mask_mata_matatu", props), maskAsset("empty"), 3, 0.8)));
    public static final DeferredItem<Item> MASK_MATA_RARU = ITEMS.registerItem("mask_mata_raru", props -> new Item(maskProps(withItemId("mask_mata_raru", props), maskAsset("empty"), 3, 0.8)));
    public static final DeferredItem<Item> MASK_MATA_RURU = ITEMS.registerItem("mask_mata_ruru", props -> new Item(maskProps(withItemId("mask_mata_ruru", props), maskAsset("empty"), 3, 0.8)));
    public static final DeferredItem<Item> MASK_NUVA_KAKAMA = ITEMS.registerItem("mask_nuva_kakama", props -> new Item(maskProps(withItemId("mask_nuva_kakama", props), maskAsset("empty"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_PAKARI = ITEMS.registerItem("mask_nuva_pakari", props -> new Item(maskProps(withItemId("mask_nuva_pakari", props), maskAsset("empty"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_KAUKAU = ITEMS.registerItem("mask_nuva_kaukau", props -> new Item(maskProps(withItemId("mask_nuva_kaukau", props), maskAsset("empty"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_MIRU = ITEMS.registerItem("mask_nuva_miru", props -> new Item(maskProps(withItemId("mask_nuva_miru", props), maskAsset("empty"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_HAU = ITEMS.registerItem("mask_nuva_hau", props -> new Item(maskProps(withItemId("mask_nuva_hau", props), maskAsset("empty"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_AKAKU = ITEMS.registerItem("mask_nuva_akaku", props -> new Item(maskProps(withItemId("mask_nuva_akaku", props), maskAsset("empty"), 2, 1)));
    public static final DeferredItem<Item> MASK_IGNIKA = ITEMS.registerItem("mask_ignika", props -> new Item(maskProps(withItemId("mask_ignika", props), maskAsset("empty"), 3, 2)));
    public static final DeferredItem<Item> MASK_VAHI = ITEMS.registerItem("mask_vahi", props -> new Item(maskProps(withItemId("mask_vahi", props), maskAsset("empty"), 3, 2)));

    // Spawn Eggs
    public static final DeferredItem<Item> MAHI_SPAWN_EGG = ITEMS.registerItem("mahi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.MAHI.get(), withItemId("mahi_spawn_egg", props)));
    public static final DeferredItem<Item> FIKOU_SPAWN_EGG = ITEMS.registerItem("fikou_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.FIKOU.get(), withItemId("fikou_spawn_egg", props)));
    public static final DeferredItem<Item> HOI_SPAWN_EGG = ITEMS.registerItem("hoi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.HOI.get(), withItemId("hoi_spawn_egg", props)));
    public static final DeferredItem<Item> KOFO_JAGA_SPAWN_EGG = ITEMS.registerItem("kofo_jaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KOFO_JAGA.get(), withItemId("kofo_jaga_spawn_egg", props)));
    public static final DeferredItem<Item> NUI_JAGA_SPAWN_EGG = ITEMS.registerItem("nui_jaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.NUI_JAGA.get(), withItemId("nui_jaga_spawn_egg", props)));
    
    // Batch 1
    public static final DeferredItem<Item> USSAL_CRAB_SPAWN_EGG = ITEMS.registerItem("ussal_crab_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.USSAL_CRAB.get(), withItemId("ussal_crab_spawn_egg", props)));
    public static final DeferredItem<Item> SHEEP_RAHI_SPAWN_EGG = ITEMS.registerItem("sheep_rahi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.SHEEP_RAHI.get(), withItemId("sheep_rahi_spawn_egg", props)));
    public static final DeferredItem<Item> RABBIT_RAHI_SPAWN_EGG = ITEMS.registerItem("rabbit_rahi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.RABBIT_RAHI.get(), withItemId("rabbit_rahi_spawn_egg", props)));
    
    // Batch 2
    public static final DeferredItem<Item> MUAKA_SPAWN_EGG = ITEMS.registerItem("muaka_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.MUAKA.get(), withItemId("muaka_spawn_egg", props)));
    public static final DeferredItem<Item> KANE_RA_SPAWN_EGG = ITEMS.registerItem("kane_ra_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KANE_RA.get(), withItemId("kane_ra_spawn_egg", props)));
    public static final DeferredItem<Item> POKAWI_SPAWN_EGG = ITEMS.registerItem("pokawi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.POKAWI.get(), withItemId("pokawi_spawn_egg", props)));
    
    // Batch 3
    public static final DeferredItem<Item> TURTLE_RAHI_SPAWN_EGG = ITEMS.registerItem("turtle_rahi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.TURTLE_RAHI.get(), withItemId("turtle_rahi_spawn_egg", props)));
    public static final DeferredItem<Item> HIKAKI_SPAWN_EGG = ITEMS.registerItem("hikaki_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.HIKAKI.get(), withItemId("hikaki_spawn_egg", props)));
    public static final DeferredItem<Item> SHORE_TURTLE_SPAWN_EGG = ITEMS.registerItem("shore_turtle_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.SHORE_TURTLE.get(), withItemId("shore_turtle_spawn_egg", props)));
    
    // Batch 4
    public static final DeferredItem<Item> BIRD_RAHI_SPAWN_EGG = ITEMS.registerItem("bird_rahi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.BIRD_RAHI.get(), withItemId("bird_rahi_spawn_egg", props)));
    public static final DeferredItem<Item> BAT_RAHI_SPAWN_EGG = ITEMS.registerItem("bat_rahi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.BAT_RAHI.get(), withItemId("bat_rahi_spawn_egg", props)));
    public static final DeferredItem<Item> HOTO_BUG_SPAWN_EGG = ITEMS.registerItem("hoto_bug_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.HOTO_BUG.get(), withItemId("hoto_bug_spawn_egg", props)));
    
    // Batch 5
    public static final DeferredItem<Item> MATORAN_SPAWN_EGG = ITEMS.registerItem("matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.MATORAN.get(), withItemId("matoran_spawn_egg", props)));
    public static final DeferredItem<Item> BOHROK_SPAWN_EGG = ITEMS.registerItem("bohrok_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.BOHROK.get(), withItemId("bohrok_spawn_egg", props)));
    public static final DeferredItem<Item> TURAGA_SPAWN_EGG = ITEMS.registerItem("turaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.TURAGA.get(), withItemId("turaga_spawn_egg", props)));
    public static final DeferredItem<Item> MUKAU_SPAWN_EGG = ITEMS.registerItem("mukau_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.MUKAU.get(), withItemId("mukau_spawn_egg", props)));
    public static final DeferredItem<Item> HAPAKA_SPAWN_EGG = ITEMS.registerItem("hapaka_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.HAPAKA.get(), withItemId("hapaka_spawn_egg", props)));
    public static final DeferredItem<Item> BOHROK_VA_SPAWN_EGG = ITEMS.registerItem("bohrok_va_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.BOHROK_VA.get(), withItemId("bohrok_va_spawn_egg", props)));
    
    // Batch 5b
    public static final DeferredItem<Item> NUI_RAMA_SPAWN_EGG = ITEMS.registerItem("nui_rama_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.NUI_RAMA.get(), withItemId("nui_rama_spawn_egg", props)));
    public static final DeferredItem<Item> ASH_BEAR_SPAWN_EGG = ITEMS.registerItem("ash_bear_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.ASH_BEAR.get(), withItemId("ash_bear_spawn_egg", props)));
    public static final DeferredItem<Item> RAHAGA_SPAWN_EGG = ITEMS.registerItem("rahaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.RAHAGA.get(), withItemId("rahaga_spawn_egg", props)));
    public static final DeferredItem<Item> VAKO_SPAWN_EGG = ITEMS.registerItem("vako_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.VAKO.get(), withItemId("vako_spawn_egg", props)));
    public static final DeferredItem<Item> KIKANALO_SPAWN_EGG = ITEMS.registerItem("kikanalo_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KIKANALO.get(), withItemId("kikanalo_spawn_egg", props)));
    public static final DeferredItem<Item> RAHKSHI_SPAWN_EGG = ITEMS.registerItem("rahkshi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.RAHKSHI.get(), withItemId("rahkshi_spawn_egg", props)));
    
    // Batch 5c
    public static final DeferredItem<Item> KRAATA_SPAWN_EGG = ITEMS.registerItem("kraata_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KRAATA.get(), withItemId("kraata_spawn_egg", props)));
    public static final DeferredItem<Item> PIRAKA_SPAWN_EGG = ITEMS.registerItem("piraka_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.PIRAKA.get(), withItemId("piraka_spawn_egg", props)));
    public static final DeferredItem<Item> DARK_HUNTER_SPAWN_EGG = ITEMS.registerItem("dark_hunter_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.DARK_HUNTER.get(), withItemId("dark_hunter_spawn_egg", props)));
    public static final DeferredItem<Item> FUSA_SPAWN_EGG = ITEMS.registerItem("fusa_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.FUSA.get(), withItemId("fusa_spawn_egg", props)));
    public static final DeferredItem<Item> SKRALL_SPAWN_EGG = ITEMS.registerItem("skrall_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.SKRALL.get(), withItemId("skrall_spawn_egg", props)));
    public static final DeferredItem<Item> ZESK_SPAWN_EGG = ITEMS.registerItem("zesk_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.ZESK.get(), withItemId("zesk_spawn_egg", props)));
    public static final DeferredItem<Item> AGORI_SPAWN_EGG = ITEMS.registerItem("agori_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.AGORI.get(), withItemId("agori_spawn_egg", props)));
    public static final DeferredItem<Item> FOX_RAHI_SPAWN_EGG = ITEMS.registerItem("fox_rahi_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.FOX_RAHI.get(), withItemId("fox_rahi_spawn_egg", props)));
    public static final DeferredItem<Item> KAVINIKA_SPAWN_EGG = ITEMS.registerItem("kavinika_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KAVINIKA.get(), withItemId("kavinika_spawn_egg", props)));
    public static final DeferredItem<Item> ARCHIVES_MOLE_SPAWN_EGG = ITEMS.registerItem("archives_mole_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.ARCHIVES_MOLE.get(), withItemId("archives_mole_spawn_egg", props)));
    public static final DeferredItem<Item> TAKEA_SHARK_SPAWN_EGG = ITEMS.registerItem("takea_shark_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.TAKEA_SHARK.get(), withItemId("takea_shark_spawn_egg", props)));
}
