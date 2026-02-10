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

    public static final DeferredItem<Item> MASK_MATA_GOLD = ITEMS.registerItem("mask_mata_gold", props -> new Item(maskProps(withItemId("mask_mata_gold", props), maskAsset("mata_gold"), 2, 0.5)));
    public static final DeferredItem<Item> MASK_MATA_KAKAMA = ITEMS.registerItem("mask_mata_kakama", props -> new Item(maskProps(withItemId("mask_mata_kakama", props), maskAsset("mata_kakama"), 1, 0)));
    public static final DeferredItem<Item> MASK_MATA_PAKARI = ITEMS.registerItem("mask_mata_pakari", props -> new Item(maskProps(withItemId("mask_mata_pakari", props), maskAsset("mata_pakari"), 1, 0)));
    public static final DeferredItem<Item> MASK_MATA_KAUKAU = ITEMS.registerItem("mask_mata_kaukau", props -> new Item(maskProps(withItemId("mask_mata_kaukau", props), maskAsset("mata_kaukau"), 1, 0)));
    public static final DeferredItem<Item> MASK_MATA_MIRU = ITEMS.registerItem("mask_mata_miru", props -> new Item(maskProps(withItemId("mask_mata_miru", props), maskAsset("mata_miru"), 1, 0)));
    public static final DeferredItem<Item> MASK_MATA_HAU = ITEMS.registerItem("mask_mata_hau", props -> new Item(maskProps(withItemId("mask_mata_hau", props), maskAsset("mata_hau"), 1, 0)));
    public static final DeferredItem<Item> MASK_MATA_AKAKU = ITEMS.registerItem("mask_mata_akaku", props -> new Item(maskProps(withItemId("mask_mata_akaku", props), maskAsset("mata_akaku"), 1, 0)));
    public static final DeferredItem<Item> MASK_NUVA_KAKAMA = ITEMS.registerItem("mask_nuva_kakama", props -> new Item(maskProps(withItemId("mask_nuva_kakama", props), maskAsset("nuva_kakama"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_PAKARI = ITEMS.registerItem("mask_nuva_pakari", props -> new Item(maskProps(withItemId("mask_nuva_pakari", props), maskAsset("nuva_pakari"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_KAUKAU = ITEMS.registerItem("mask_nuva_kaukau", props -> new Item(maskProps(withItemId("mask_nuva_kaukau", props), maskAsset("nuva_kaukau"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_MIRU = ITEMS.registerItem("mask_nuva_miru", props -> new Item(maskProps(withItemId("mask_nuva_miru", props), maskAsset("nuva_miru"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_HAU = ITEMS.registerItem("mask_nuva_hau", props -> new Item(maskProps(withItemId("mask_nuva_hau", props), maskAsset("nuva_hau"), 2, 1)));
    public static final DeferredItem<Item> MASK_NUVA_AKAKU = ITEMS.registerItem("mask_nuva_akaku", props -> new Item(maskProps(withItemId("mask_nuva_akaku", props), maskAsset("nuva_akaku"), 2, 1)));
    public static final DeferredItem<Item> MASK_IGNIKA = ITEMS.registerItem("mask_ignika", props -> new Item(maskProps(withItemId("mask_ignika", props), maskAsset("ignika"), 3, 2)));
    public static final DeferredItem<Item> MASK_VAHI = ITEMS.registerItem("mask_vahi", props -> new Item(maskProps(withItemId("mask_vahi", props), maskAsset("vahi"), 3, 2)));

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
}
