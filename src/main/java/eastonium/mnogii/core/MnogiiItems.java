package eastonium.mnogii.core;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.item.MnogiiTiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.Equippable;
import net.minecraft.world.item.enchantment.Enchantable;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MnogiiItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Mnogii.MODID);

    /** Call on Item.Properties before passing to Item constructor (1.21 requires id set). */
    private static Item.Properties withItemId(String name, Item.Properties props) {
        return props.setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, name)));
    }

    // Block items
    public static final DeferredItem<BlockItem> FLUID_PROTODERMIS = ITEMS.registerSimpleBlockItem(MnogiiBlocks.FLUID_PROTODERMIS);
    public static final DeferredItem<BlockItem> FLUID_PROTODERMIS_PURE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.FLUID_PROTODERMIS_PURE);
    public static final DeferredItem<BlockItem> FLUID_PROTODERMIS_MOLTEN = ITEMS.registerSimpleBlockItem(MnogiiBlocks.FLUID_PROTODERMIS_MOLTEN);
    public static final DeferredItem<BlockItem> FLUID_PROTODERMIS_PURE_MOLTEN = ITEMS.registerSimpleBlockItem(MnogiiBlocks.FLUID_PROTODERMIS_PURE_MOLTEN);
    public static final DeferredItem<BlockItem> MASK_FORGE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.MASK_FORGE);
    public static final DeferredItem<BlockItem> PURIFIER_ITEM = ITEMS.registerSimpleBlockItem(MnogiiBlocks.PURIFIER);
    public static final DeferredItem<BlockItem> KORO_BLOCK = ITEMS.registerSimpleBlockItem(MnogiiBlocks.KORO_BLOCK);
    public static final DeferredItem<BlockItem> NUVA_CUBE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.NUVA_CUBE);
    public static final DeferredItem<BlockItem> MATANUI_STONE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.MATANUI_STONE);
    public static final DeferredItem<BlockItem> MAKUTA_STONE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.MAKUTA_STONE);
    public static final DeferredItem<BlockItem> LIGHTSTONE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.LIGHTSTONE);
    public static final DeferredItem<BlockItem> LIGHTSTONE_ORE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.LIGHTSTONE_ORE);
    public static final DeferredItem<BlockItem> HEATSTONE_ORE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.HEATSTONE_ORE);
    public static final DeferredItem<BlockItem> PROTODERMIS_ORE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.PROTODERMIS_ORE);
    public static final DeferredItem<BlockItem> ONU_WAHI_STONE_ORE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.ONU_WAHI_STONE_ORE);
    public static final DeferredItem<BlockItem> BLOCK_PROTODERMIS = ITEMS.registerSimpleBlockItem(MnogiiBlocks.BLOCK_PROTODERMIS);
    public static final DeferredItem<BlockItem> BLOCK_PROTOSTEEL = ITEMS.registerSimpleBlockItem(MnogiiBlocks.BLOCK_PROTOSTEEL);
    public static final DeferredItem<BlockItem> TA_KORO_STONE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.TA_KORO_STONE);
    public static final DeferredItem<BlockItem> ONU_KORO_STONE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.ONU_KORO_STONE);
    public static final DeferredItem<BlockItem> GA_KORO_STONE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.GA_KORO_STONE);
    public static final DeferredItem<BlockItem> KO_KORO_STONE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.KO_KORO_STONE);
    public static final DeferredItem<BlockItem> LE_KORO_STONE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.LE_KORO_STONE);
    public static final DeferredItem<BlockItem> PO_KORO_STONE = ITEMS.registerSimpleBlockItem(MnogiiBlocks.PO_KORO_STONE);
    // Ga-Koro structure blocks (ported from QFN 1.20.1)
    public static final DeferredItem<BlockItem> SEAWEED_WOOD_PLANKS = ITEMS.registerSimpleBlockItem(MnogiiBlocks.SEAWEED_WOOD_PLANKS);
    public static final DeferredItem<BlockItem> SEAWEED_WOOD_WOOD   = ITEMS.registerSimpleBlockItem(MnogiiBlocks.SEAWEED_WOOD_WOOD);
    public static final DeferredItem<BlockItem> SEAWEED_WOOD_SLAB   = ITEMS.registerSimpleBlockItem(MnogiiBlocks.SEAWEED_WOOD_SLAB);
    public static final DeferredItem<BlockItem> SEAWEED_WOOD_STAIRS = ITEMS.registerSimpleBlockItem(MnogiiBlocks.SEAWEED_WOOD_STAIRS);
    public static final DeferredItem<BlockItem> CURED_WOOD_LOG      = ITEMS.registerSimpleBlockItem(MnogiiBlocks.CURED_WOOD_LOG);
    public static final DeferredItem<BlockItem> CURED_WOOD_FENCE    = ITEMS.registerSimpleBlockItem(MnogiiBlocks.CURED_WOOD_FENCE);
    public static final DeferredItem<BlockItem> LIGHTSTONES_BLOCK   = ITEMS.registerSimpleBlockItem(MnogiiBlocks.LIGHTSTONES_BLOCK);
    public static final DeferredItem<BlockItem> LIGHT_GRAY_STONE    = ITEMS.registerSimpleBlockItem(MnogiiBlocks.LIGHT_GRAY_STONE);
    public static final DeferredItem<BlockItem> MATA_NUI_STONE      = ITEMS.registerSimpleBlockItem(MnogiiBlocks.MATA_NUI_STONE);
    public static final DeferredItem<BlockItem> LE_KORO_LADDER      = ITEMS.registerSimpleBlockItem(MnogiiBlocks.LE_KORO_LADDER);
    public static final DeferredItem<BlockItem> PATH                = ITEMS.registerSimpleBlockItem(MnogiiBlocks.PATH);
    public static final DeferredItem<BlockItem> PATH2               = ITEMS.registerSimpleBlockItem(MnogiiBlocks.PATH2);

    public static final DeferredItem<Item> KANOKA_BAMBOO = ITEMS.registerItem("kanoka_bamboo",
            props -> new eastonium.mnogii.item.ItemThrowableDisc(withItemId("kanoka_bamboo", props)));

    // Protodermis fluid buckets — proper BucketItem instances backed by the registered fluids
    public static final DeferredItem<Item> PROTODERMIS_BUCKET =
        ITEMS.registerItem("protodermis_bucket", props ->
            new BucketItem(MnogiiRegistration.SOURCE_PROTODERMIS.get(),
                withItemId("protodermis_bucket", props).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> PURE_PROTODERMIS_BUCKET =
        ITEMS.registerItem("pure_protodermis_bucket", props ->
            new BucketItem(MnogiiRegistration.SOURCE_PROTODERMIS_PURE.get(),
                withItemId("pure_protodermis_bucket", props).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> MOLTEN_PROTODERMIS_BUCKET =
        ITEMS.registerItem("molten_protodermis_bucket", props ->
            new BucketItem(MnogiiRegistration.SOURCE_PROTODERMIS_MOLTEN.get(),
                withItemId("molten_protodermis_bucket", props).stacksTo(1).craftRemainder(Items.BUCKET)));
    public static final DeferredItem<Item> PURE_MOLTEN_PROTODERMIS_BUCKET =
        ITEMS.registerItem("pure_molten_protodermis_bucket", props ->
            new BucketItem(MnogiiRegistration.SOURCE_PROTODERMIS_PURE_MOLTEN.get(),
                withItemId("pure_molten_protodermis_bucket", props).stacksTo(1).craftRemainder(Items.BUCKET)));

    // Materials (from bionicle_qfn)
    public static final DeferredItem<Item> PROTODERMIS_SOLID = ITEMS.registerSimpleItem("protodermis_solid");
    public static final DeferredItem<Item> INGOT_PROTODERMIS = ITEMS.registerSimpleItem("ingot_protodermis");
    public static final DeferredItem<Item> INGOT_PROTOSTEEL = ITEMS.registerSimpleItem("ingot_protosteel");
    public static final DeferredItem<Item> NUGGET_PROTODERMIS = ITEMS.registerSimpleItem("nugget_protodermis");
    public static final DeferredItem<Item> NUGGET_PROTOSTEEL = ITEMS.registerSimpleItem("nugget_protosteel");
    public static final DeferredItem<Item> GEAR = ITEMS.registerSimpleItem("gear");
    public static final DeferredItem<Item> HAMMER = ITEMS.registerSimpleItem("hammer");
    public static final DeferredItem<Item> HEATSTONE = ITEMS.registerSimpleItem("heatstone");
    public static final DeferredItem<Item> ELEMENT_SWIPER = ITEMS.registerItem("element_swiper",
            props -> new eastonium.mnogii.item.ItemElementSwiper(withItemId("element_swiper", props)));

    // Toa stones (from bionicle_qfn)
    public static final DeferredItem<Item> WATER_TOA_STONE = ITEMS.registerSimpleItem("water_toa_stone");
    public static final DeferredItem<Item> EARTH_TOA_STONE = ITEMS.registerSimpleItem("earth_toa_stone");
    public static final DeferredItem<Item> AIR_TOA_STONE = ITEMS.registerSimpleItem("air_toa_stone");
    public static final DeferredItem<Item> FIRE_TOA_STONE = ITEMS.registerSimpleItem("fire_toa_stone");
    public static final DeferredItem<Item> ICE_TOA_STONE = ITEMS.registerSimpleItem("ice_toa_stone");
    public static final DeferredItem<Item> ROCK_TOA_STONE = ITEMS.registerSimpleItem("rock_toa_stone");
    public static final DeferredItem<Item> ONU_WAHI_STONE = ITEMS.registerSimpleItem("onu_wahi_stone");

    // Kanoka disk variants (from bionicle_qfn) - all throwable
    public static final DeferredItem<Item> KANOKA_DISK_GA = ITEMS.registerItem("kanoka_disk_ga",
            props -> new eastonium.mnogii.item.ItemThrowableDisc(withItemId("kanoka_disk_ga", props)));
    public static final DeferredItem<Item> KANOKA_DISK_KO = ITEMS.registerItem("kanoka_disk_ko",
            props -> new eastonium.mnogii.item.ItemThrowableDisc(withItemId("kanoka_disk_ko", props)));
    public static final DeferredItem<Item> KANOKA_DISK_LE = ITEMS.registerItem("kanoka_disk_le",
            props -> new eastonium.mnogii.item.ItemThrowableDisc(withItemId("kanoka_disk_le", props)));
    public static final DeferredItem<Item> KANOKA_DISK_ONU = ITEMS.registerItem("kanoka_disk_onu",
            props -> new eastonium.mnogii.item.ItemThrowableDisc(withItemId("kanoka_disk_onu", props)));
    public static final DeferredItem<Item> KANOKA_DISK_PO = ITEMS.registerItem("kanoka_disk_po",
            props -> new eastonium.mnogii.item.ItemThrowableDisc(withItemId("kanoka_disk_po", props)));
    public static final DeferredItem<Item> KANOKA_DISK_TA = ITEMS.registerItem("kanoka_disk_ta",
            props -> new eastonium.mnogii.item.ItemThrowableDisc(withItemId("kanoka_disk_ta", props)));
    public static final DeferredItem<Item> KANOKA_OF_TIME = ITEMS.registerItem("kanoka_of_time",
            props -> new eastonium.mnogii.item.ItemThrowableDisc(withItemId("kanoka_of_time", props)));

    // =====================================================================
    // Toa Weapons — 3D Blockbench models ported from QFN 1.20.1
    // =====================================================================
    public static final DeferredItem<Item> AIR_AXE     = ITEMS.registerItem("air_axe",     props -> new Item(withItemId("air_axe",     props).stacksTo(1).axe(MnogiiTiers.PROTODERMIS,    6.0F, -3.1F)));
    public static final DeferredItem<Item> FIRE_STAFF   = ITEMS.registerItem("fire_staff",  props -> new Item(withItemId("fire_staff",  props).stacksTo(1).sword(MnogiiTiers.PROTODERMIS,  4, -2.0F)));
    public static final DeferredItem<Item> FIRE_SWORD   = ITEMS.registerItem("fire_sword",  props -> new Item(withItemId("fire_sword",  props).stacksTo(1).sword(MnogiiTiers.PROTODERMIS,  3, -2.4F)));
    public static final DeferredItem<Item> ICE_SWORD    = ITEMS.registerItem("ice_sword",   props -> new Item(withItemId("ice_sword",   props).stacksTo(1).sword(MnogiiTiers.PROTODERMIS,  3, -2.4F)));
    public static final DeferredItem<Item> ICE_PICKAXE  = ITEMS.registerItem("ice_pickaxe", props -> new Item(withItemId("ice_pickaxe", props).stacksTo(1).pickaxe(MnogiiTiers.PROTODERMIS,1, -2.8F)));
    public static final DeferredItem<Item> ICE_SHIELD   = ITEMS.registerItem("ice_shield",  props -> new Item(withItemId("ice_shield",  props).stacksTo(1)));
    public static final DeferredItem<Item> KAUKAU_STAFF = ITEMS.registerItem("kaukau_staff",props -> new Item(withItemId("kaukau_staff",props).stacksTo(1).sword(MnogiiTiers.PROTODERMIS,  2, -2.0F)));
    public static final DeferredItem<Item> ONUA_CLAWS   = ITEMS.registerItem("onua_claws",  props -> new Item(withItemId("onua_claws",  props).stacksTo(1).sword(MnogiiTiers.PROTODERMIS,  4, -2.4F)));
    public static final DeferredItem<Item> ONUA_DRILL   = ITEMS.registerItem("onua_drill",  props -> new Item(withItemId("onua_drill",  props).stacksTo(1).pickaxe(MnogiiTiers.PROTODERMIS,2, -2.8F)));
    public static final DeferredItem<Item> POHATU_HANDS = ITEMS.registerItem("pohatu_hands",props -> new Item(withItemId("pohatu_hands",props).stacksTo(1).sword(MnogiiTiers.PROTODERMIS,  5, -2.4F)));
    public static final DeferredItem<Item> WATER_HOOKS  = ITEMS.registerItem("water_hooks", props -> new Item(withItemId("water_hooks", props).stacksTo(1).sword(MnogiiTiers.PROTODERMIS,  2, -2.0F)));
    public static final DeferredItem<Item> TOA_TRIDENT  = ITEMS.registerItem("toa_trident", props -> new Item(withItemId("toa_trident", props).stacksTo(1).sword(MnogiiTiers.PROTODERMIS,  4, -2.4F)));

    // =====================================================================
    // Toa Armor — 6 elemental sets (boots, chestplate, leggings)
    // Helmets are the Kanohi masks above. Rendering via 1.21 EquipmentAsset.
    // Defense: boots=2, leggings=5, chestplate=6 (iron-equivalent per set).
    // =====================================================================
    private static Item.Properties armorProps(String id, EquipmentSlot slot, String assetName, int defense) {
        ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> asset = ResourceKey.create(
                ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("minecraft", "equipment_asset")),
                ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, assetName));
        return withItemId(id, new Item.Properties()).stacksTo(1).durability(slot == EquipmentSlot.LEGS ? 225 : slot == EquipmentSlot.CHEST ? 240 : 195)
                .component(DataComponents.EQUIPPABLE, Equippable.builder(slot).setAsset(asset).build())
                .component(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder()
                        .add(Attributes.ARMOR, new AttributeModifier(
                                ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, id + "_armor"),
                                defense, AttributeModifier.Operation.ADD_VALUE),
                                EquipmentSlotGroup.bySlot(slot))
                        .build())
                .component(DataComponents.ENCHANTABLE, new Enchantable(9));
    }
    // Fire Toa armor
    public static final DeferredItem<Item> BOOTS_FIRE      = ITEMS.registerItem("boots_fire",       props -> new Item(armorProps("boots_fire",      EquipmentSlot.FEET,  "fire_toa",  2)));
    public static final DeferredItem<Item> CHESTPLATE_FIRE = ITEMS.registerItem("chestplate_fire",  props -> new Item(armorProps("chestplate_fire",  EquipmentSlot.CHEST, "fire_toa",  6)));
    public static final DeferredItem<Item> LEGGINGS_FIRE   = ITEMS.registerItem("leggings_fire",    props -> new Item(armorProps("leggings_fire",    EquipmentSlot.LEGS,  "fire_toa",  5)));
    // Water Toa armor
    public static final DeferredItem<Item> BOOTS_WATER     = ITEMS.registerItem("boots_water",      props -> new Item(armorProps("boots_water",      EquipmentSlot.FEET,  "water_toa", 2)));
    public static final DeferredItem<Item> CHESTPLATE_WATER= ITEMS.registerItem("chestplate_water", props -> new Item(armorProps("chestplate_water", EquipmentSlot.CHEST, "water_toa", 6)));
    public static final DeferredItem<Item> LEGGINGS_WATER  = ITEMS.registerItem("leggings_water",   props -> new Item(armorProps("leggings_water",   EquipmentSlot.LEGS,  "water_toa", 5)));
    // Air Toa armor
    public static final DeferredItem<Item> BOOTS_AIR       = ITEMS.registerItem("boots_air",        props -> new Item(armorProps("boots_air",        EquipmentSlot.FEET,  "air_toa",   2)));
    public static final DeferredItem<Item> CHESTPLATE_AIR  = ITEMS.registerItem("chestplate_air",   props -> new Item(armorProps("chestplate_air",   EquipmentSlot.CHEST, "air_toa",   6)));
    public static final DeferredItem<Item> LEGGINGS_AIR    = ITEMS.registerItem("leggings_air",     props -> new Item(armorProps("leggings_air",     EquipmentSlot.LEGS,  "air_toa",   5)));
    // Earth Toa armor
    public static final DeferredItem<Item> BOOTS_EARTH     = ITEMS.registerItem("boots_earth",      props -> new Item(armorProps("boots_earth",      EquipmentSlot.FEET,  "earth_toa", 2)));
    public static final DeferredItem<Item> CHESTPLATE_EARTH= ITEMS.registerItem("chestplate_earth", props -> new Item(armorProps("chestplate_earth", EquipmentSlot.CHEST, "earth_toa", 6)));
    public static final DeferredItem<Item> LEGGINGS_EARTH  = ITEMS.registerItem("leggings_earth",   props -> new Item(armorProps("leggings_earth",   EquipmentSlot.LEGS,  "earth_toa", 5)));
    // Ice Toa armor
    public static final DeferredItem<Item> BOOTS_ICE       = ITEMS.registerItem("boots_ice",        props -> new Item(armorProps("boots_ice",        EquipmentSlot.FEET,  "ice_toa",   2)));
    public static final DeferredItem<Item> CHESTPLATE_ICE  = ITEMS.registerItem("chestplate_ice",   props -> new Item(armorProps("chestplate_ice",   EquipmentSlot.CHEST, "ice_toa",   6)));
    public static final DeferredItem<Item> LEGGINGS_ICE    = ITEMS.registerItem("leggings_ice",     props -> new Item(armorProps("leggings_ice",     EquipmentSlot.LEGS,  "ice_toa",   5)));
    // Stone Toa armor
    public static final DeferredItem<Item> BOOTS_STONE     = ITEMS.registerItem("boots_stone",      props -> new Item(armorProps("boots_stone",      EquipmentSlot.FEET,  "stone_toa", 2)));
    public static final DeferredItem<Item> CHESTPLATE_STONE= ITEMS.registerItem("chestplate_stone", props -> new Item(armorProps("chestplate_stone", EquipmentSlot.CHEST, "stone_toa", 6)));
    public static final DeferredItem<Item> LEGGINGS_STONE  = ITEMS.registerItem("leggings_stone",   props -> new Item(armorProps("leggings_stone",   EquipmentSlot.LEGS,  "stone_toa", 5)));

    // Protodermis tools
    public static final DeferredItem<Item> PROTODERMIS_SWORD = ITEMS.registerItem("protodermis_sword",
            props -> new Item(withItemId("protodermis_sword", props).sword(MnogiiTiers.PROTODERMIS, 3, -2.4F)));
    public static final DeferredItem<Item> PROTODERMIS_PICK = ITEMS.registerItem("protodermis_pick",
            props -> new Item(withItemId("protodermis_pick", props).pickaxe(MnogiiTiers.PROTODERMIS, 1, -2.8F)));
    public static final DeferredItem<Item> PROTODERMIS_AXE = ITEMS.registerItem("protodermis_axe",
            props -> new Item(withItemId("protodermis_axe", props).axe(MnogiiTiers.PROTODERMIS, 6.0F, -3.1F)));
    public static final DeferredItem<Item> PROTODERMIS_SHOVEL = ITEMS.registerItem("protodermis_shovel",
            props -> new Item(withItemId("protodermis_shovel", props).shovel(MnogiiTiers.PROTODERMIS, 1.5F, -3.0F)));
    public static final DeferredItem<Item> PROTODERMIS_SCYTHE = ITEMS.registerItem("protodermis_scythe",
            props -> new Item(withItemId("protodermis_scythe", props).hoe(MnogiiTiers.PROTODERMIS, -2, -1.0F)));

    // Special items
    public static final DeferredItem<Item> KOHLII_STICK = ITEMS.registerItem("kohlii_stick",
            props -> new Item(withItemId("kohlii_stick", props).sword(MnogiiTiers.PROTODERMIS, 2, -3.0F)));
    public static final DeferredItem<Item> HEATSTONE_LIGHTER = ITEMS.registerItem("heatstone_lighter",
            props -> new eastonium.mnogii.item.ItemHeatstoneLighter(withItemId("heatstone_lighter", props).stacksTo(1).durability(128)));
    public static final DeferredItem<Item> SLUICE = ITEMS.registerItem("sluice",
            props -> new eastonium.mnogii.item.ItemSluice(withItemId("sluice", props).stacksTo(1)));

    // =====================================================================
    // Mata Masks - 12 equippable helmet masks with 3D geo rendering
    // Each mask provides armor and a unique Kanohi power.
    // =====================================================================

    /** +3 armor when worn in head slot. Applied to all masks. */
    private static final ItemAttributeModifiers MASK_ARMOR_MODIFIERS = ItemAttributeModifiers.builder()
            .add(Attributes.ARMOR, new AttributeModifier(ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "mask_armor"), 3.0, AttributeModifier.Operation.ADD_VALUE), EquipmentSlotGroup.HEAD)
            .build();

    /**
     * Build mask Item.Properties - equippable in HEAD slot, enchantable like helmets.
     * Uses empty equipment asset to suppress vanilla 2D armor overlay rendering.
     * Enchantability 15 (between iron 9 and gold 25) - accepts Protection, Unbreaking, Mending, Respiration, Aqua Affinity.
     * Each mask adds +3 armor when worn.
     */
    private static Item.Properties maskProps(Item.Properties props) {
        ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> emptyAsset = ResourceKey.create(
                ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("minecraft", "equipment_asset")),
                ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "empty")
        );
        return props.stacksTo(1)
                .component(DataComponents.EQUIPPABLE,
                        Equippable.builder(EquipmentSlot.HEAD)
                                .setAsset(emptyAsset)
                                .build())
                .component(DataComponents.ATTRIBUTE_MODIFIERS, MASK_ARMOR_MODIFIERS)
                .component(DataComponents.ENCHANTABLE, new Enchantable(15));
    }

    // --- Masks: cosmetic only (equippable in head slot, 3D rendered via AzureLib) ---
    public static final DeferredItem<Item> MASK_MATA_HAU = ITEMS.registerItem("mask_mata_hau",
            props -> new Item(maskProps(withItemId("mask_mata_hau", props))));
    public static final DeferredItem<Item> MASK_MATA_KAUKAU = ITEMS.registerItem("mask_mata_kaukau",
            props -> new Item(maskProps(withItemId("mask_mata_kaukau", props))));
    public static final DeferredItem<Item> MASK_MATA_MIRU = ITEMS.registerItem("mask_mata_miru",
            props -> new Item(maskProps(withItemId("mask_mata_miru", props))));
    public static final DeferredItem<Item> MASK_MATA_KAKAMA = ITEMS.registerItem("mask_mata_kakama",
            props -> new Item(maskProps(withItemId("mask_mata_kakama", props))));
    public static final DeferredItem<Item> MASK_MATA_PAKARI = ITEMS.registerItem("mask_mata_pakari",
            props -> new Item(maskProps(withItemId("mask_mata_pakari", props))));
    public static final DeferredItem<Item> MASK_MATA_AKAKU = ITEMS.registerItem("mask_mata_akaku",
            props -> new Item(maskProps(withItemId("mask_mata_akaku", props))));
    public static final DeferredItem<Item> MASK_MATA_HUNA = ITEMS.registerItem("mask_mata_huna",
            props -> new Item(maskProps(withItemId("mask_mata_huna", props))));
    public static final DeferredItem<Item> MASK_MATA_MAHIKI = ITEMS.registerItem("mask_mata_mahiki",
            props -> new Item(maskProps(withItemId("mask_mata_mahiki", props))));
    public static final DeferredItem<Item> MASK_MATA_MATATU = ITEMS.registerItem("mask_mata_matatu",
            props -> new Item(maskProps(withItemId("mask_mata_matatu", props))));
    public static final DeferredItem<Item> MASK_MATA_KOMAU = ITEMS.registerItem("mask_mata_komau",
            props -> new Item(maskProps(withItemId("mask_mata_komau", props))));
    public static final DeferredItem<Item> MASK_MATA_RARU = ITEMS.registerItem("mask_mata_raru",
            props -> new Item(maskProps(withItemId("mask_mata_raru", props))));
    public static final DeferredItem<Item> MASK_MATA_RURU = ITEMS.registerItem("mask_mata_ruru",
            props -> new Item(maskProps(withItemId("mask_mata_ruru", props))));

    // --- Koro-colored masks (mask + dye → colored variant; models/textures TBD) ---
    // Ta-Koro (red)
    public static final DeferredItem<Item> MASK_MATA_HAU_TA     = ITEMS.registerItem("mask_mata_hau_ta",     props -> new Item(maskProps(withItemId("mask_mata_hau_ta",     props))));
    public static final DeferredItem<Item> MASK_MATA_KAUKAU_TA  = ITEMS.registerItem("mask_mata_kaukau_ta",  props -> new Item(maskProps(withItemId("mask_mata_kaukau_ta",  props))));
    public static final DeferredItem<Item> MASK_MATA_MIRU_TA    = ITEMS.registerItem("mask_mata_miru_ta",    props -> new Item(maskProps(withItemId("mask_mata_miru_ta",    props))));
    public static final DeferredItem<Item> MASK_MATA_KAKAMA_TA  = ITEMS.registerItem("mask_mata_kakama_ta",  props -> new Item(maskProps(withItemId("mask_mata_kakama_ta",  props))));
    public static final DeferredItem<Item> MASK_MATA_PAKARI_TA  = ITEMS.registerItem("mask_mata_pakari_ta",  props -> new Item(maskProps(withItemId("mask_mata_pakari_ta",  props))));
    public static final DeferredItem<Item> MASK_MATA_AKAKU_TA   = ITEMS.registerItem("mask_mata_akaku_ta",   props -> new Item(maskProps(withItemId("mask_mata_akaku_ta",   props))));
    public static final DeferredItem<Item> MASK_MATA_HUNA_TA    = ITEMS.registerItem("mask_mata_huna_ta",    props -> new Item(maskProps(withItemId("mask_mata_huna_ta",    props))));
    public static final DeferredItem<Item> MASK_MATA_MAHIKI_TA  = ITEMS.registerItem("mask_mata_mahiki_ta",  props -> new Item(maskProps(withItemId("mask_mata_mahiki_ta",  props))));
    public static final DeferredItem<Item> MASK_MATA_MATATU_TA  = ITEMS.registerItem("mask_mata_matatu_ta",  props -> new Item(maskProps(withItemId("mask_mata_matatu_ta",  props))));
    public static final DeferredItem<Item> MASK_MATA_KOMAU_TA   = ITEMS.registerItem("mask_mata_komau_ta",   props -> new Item(maskProps(withItemId("mask_mata_komau_ta",   props))));
    public static final DeferredItem<Item> MASK_MATA_RARU_TA    = ITEMS.registerItem("mask_mata_raru_ta",    props -> new Item(maskProps(withItemId("mask_mata_raru_ta",    props))));
    public static final DeferredItem<Item> MASK_MATA_RURU_TA    = ITEMS.registerItem("mask_mata_ruru_ta",    props -> new Item(maskProps(withItemId("mask_mata_ruru_ta",    props))));
    // Ga-Koro (blue)
    public static final DeferredItem<Item> MASK_MATA_HAU_GA     = ITEMS.registerItem("mask_mata_hau_ga",     props -> new Item(maskProps(withItemId("mask_mata_hau_ga",     props))));
    public static final DeferredItem<Item> MASK_MATA_KAUKAU_GA  = ITEMS.registerItem("mask_mata_kaukau_ga",  props -> new Item(maskProps(withItemId("mask_mata_kaukau_ga",  props))));
    public static final DeferredItem<Item> MASK_MATA_MIRU_GA    = ITEMS.registerItem("mask_mata_miru_ga",    props -> new Item(maskProps(withItemId("mask_mata_miru_ga",    props))));
    public static final DeferredItem<Item> MASK_MATA_KAKAMA_GA  = ITEMS.registerItem("mask_mata_kakama_ga",  props -> new Item(maskProps(withItemId("mask_mata_kakama_ga",  props))));
    public static final DeferredItem<Item> MASK_MATA_PAKARI_GA  = ITEMS.registerItem("mask_mata_pakari_ga",  props -> new Item(maskProps(withItemId("mask_mata_pakari_ga",  props))));
    public static final DeferredItem<Item> MASK_MATA_AKAKU_GA   = ITEMS.registerItem("mask_mata_akaku_ga",   props -> new Item(maskProps(withItemId("mask_mata_akaku_ga",   props))));
    public static final DeferredItem<Item> MASK_MATA_HUNA_GA    = ITEMS.registerItem("mask_mata_huna_ga",    props -> new Item(maskProps(withItemId("mask_mata_huna_ga",    props))));
    public static final DeferredItem<Item> MASK_MATA_MAHIKI_GA  = ITEMS.registerItem("mask_mata_mahiki_ga",  props -> new Item(maskProps(withItemId("mask_mata_mahiki_ga",  props))));
    public static final DeferredItem<Item> MASK_MATA_MATATU_GA  = ITEMS.registerItem("mask_mata_matatu_ga",  props -> new Item(maskProps(withItemId("mask_mata_matatu_ga",  props))));
    public static final DeferredItem<Item> MASK_MATA_KOMAU_GA   = ITEMS.registerItem("mask_mata_komau_ga",   props -> new Item(maskProps(withItemId("mask_mata_komau_ga",   props))));
    public static final DeferredItem<Item> MASK_MATA_RARU_GA    = ITEMS.registerItem("mask_mata_raru_ga",    props -> new Item(maskProps(withItemId("mask_mata_raru_ga",    props))));
    public static final DeferredItem<Item> MASK_MATA_RURU_GA    = ITEMS.registerItem("mask_mata_ruru_ga",    props -> new Item(maskProps(withItemId("mask_mata_ruru_ga",    props))));
    // Po-Koro (brown)
    public static final DeferredItem<Item> MASK_MATA_HAU_PO     = ITEMS.registerItem("mask_mata_hau_po",     props -> new Item(maskProps(withItemId("mask_mata_hau_po",     props))));
    public static final DeferredItem<Item> MASK_MATA_KAUKAU_PO  = ITEMS.registerItem("mask_mata_kaukau_po",  props -> new Item(maskProps(withItemId("mask_mata_kaukau_po",  props))));
    public static final DeferredItem<Item> MASK_MATA_MIRU_PO    = ITEMS.registerItem("mask_mata_miru_po",    props -> new Item(maskProps(withItemId("mask_mata_miru_po",    props))));
    public static final DeferredItem<Item> MASK_MATA_KAKAMA_PO  = ITEMS.registerItem("mask_mata_kakama_po",  props -> new Item(maskProps(withItemId("mask_mata_kakama_po",  props))));
    public static final DeferredItem<Item> MASK_MATA_PAKARI_PO  = ITEMS.registerItem("mask_mata_pakari_po",  props -> new Item(maskProps(withItemId("mask_mata_pakari_po",  props))));
    public static final DeferredItem<Item> MASK_MATA_AKAKU_PO   = ITEMS.registerItem("mask_mata_akaku_po",   props -> new Item(maskProps(withItemId("mask_mata_akaku_po",   props))));
    public static final DeferredItem<Item> MASK_MATA_HUNA_PO    = ITEMS.registerItem("mask_mata_huna_po",    props -> new Item(maskProps(withItemId("mask_mata_huna_po",    props))));
    public static final DeferredItem<Item> MASK_MATA_MAHIKI_PO  = ITEMS.registerItem("mask_mata_mahiki_po",  props -> new Item(maskProps(withItemId("mask_mata_mahiki_po",  props))));
    public static final DeferredItem<Item> MASK_MATA_MATATU_PO  = ITEMS.registerItem("mask_mata_matatu_po",  props -> new Item(maskProps(withItemId("mask_mata_matatu_po",  props))));
    public static final DeferredItem<Item> MASK_MATA_KOMAU_PO   = ITEMS.registerItem("mask_mata_komau_po",   props -> new Item(maskProps(withItemId("mask_mata_komau_po",   props))));
    public static final DeferredItem<Item> MASK_MATA_RARU_PO    = ITEMS.registerItem("mask_mata_raru_po",    props -> new Item(maskProps(withItemId("mask_mata_raru_po",    props))));
    public static final DeferredItem<Item> MASK_MATA_RURU_PO    = ITEMS.registerItem("mask_mata_ruru_po",    props -> new Item(maskProps(withItemId("mask_mata_ruru_po",    props))));
    // Ko-Koro (light blue)
    public static final DeferredItem<Item> MASK_MATA_HAU_KO     = ITEMS.registerItem("mask_mata_hau_ko",     props -> new Item(maskProps(withItemId("mask_mata_hau_ko",     props))));
    public static final DeferredItem<Item> MASK_MATA_KAUKAU_KO  = ITEMS.registerItem("mask_mata_kaukau_ko",  props -> new Item(maskProps(withItemId("mask_mata_kaukau_ko",  props))));
    public static final DeferredItem<Item> MASK_MATA_MIRU_KO    = ITEMS.registerItem("mask_mata_miru_ko",    props -> new Item(maskProps(withItemId("mask_mata_miru_ko",    props))));
    public static final DeferredItem<Item> MASK_MATA_KAKAMA_KO  = ITEMS.registerItem("mask_mata_kakama_ko",  props -> new Item(maskProps(withItemId("mask_mata_kakama_ko",  props))));
    public static final DeferredItem<Item> MASK_MATA_PAKARI_KO  = ITEMS.registerItem("mask_mata_pakari_ko",  props -> new Item(maskProps(withItemId("mask_mata_pakari_ko",  props))));
    public static final DeferredItem<Item> MASK_MATA_AKAKU_KO   = ITEMS.registerItem("mask_mata_akaku_ko",   props -> new Item(maskProps(withItemId("mask_mata_akaku_ko",   props))));
    public static final DeferredItem<Item> MASK_MATA_HUNA_KO    = ITEMS.registerItem("mask_mata_huna_ko",    props -> new Item(maskProps(withItemId("mask_mata_huna_ko",    props))));
    public static final DeferredItem<Item> MASK_MATA_MAHIKI_KO  = ITEMS.registerItem("mask_mata_mahiki_ko",  props -> new Item(maskProps(withItemId("mask_mata_mahiki_ko",  props))));
    public static final DeferredItem<Item> MASK_MATA_MATATU_KO  = ITEMS.registerItem("mask_mata_matatu_ko",  props -> new Item(maskProps(withItemId("mask_mata_matatu_ko",  props))));
    public static final DeferredItem<Item> MASK_MATA_KOMAU_KO   = ITEMS.registerItem("mask_mata_komau_ko",   props -> new Item(maskProps(withItemId("mask_mata_komau_ko",   props))));
    public static final DeferredItem<Item> MASK_MATA_RARU_KO    = ITEMS.registerItem("mask_mata_raru_ko",    props -> new Item(maskProps(withItemId("mask_mata_raru_ko",    props))));
    public static final DeferredItem<Item> MASK_MATA_RURU_KO    = ITEMS.registerItem("mask_mata_ruru_ko",    props -> new Item(maskProps(withItemId("mask_mata_ruru_ko",    props))));


    // =====================================================================
    // Spawn Eggs
    // =====================================================================

    public static final DeferredItem<Item> MAHI_SPAWN_EGG = ITEMS.registerItem("mahi_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.MAHI.get(), withItemId("mahi_spawn_egg", props)));
    public static final DeferredItem<Item> FIKOU_SPAWN_EGG = ITEMS.registerItem("fikou_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.FIKOU.get(), withItemId("fikou_spawn_egg", props)));
    public static final DeferredItem<Item> SPIDER_FIKOU_SPAWN_EGG = ITEMS.registerItem("spider_fikou_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.SPIDER_FIKOU.get(), withItemId("spider_fikou_spawn_egg", props)));
    public static final DeferredItem<Item> HOI_SPAWN_EGG = ITEMS.registerItem("hoi_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.HOI.get(), withItemId("hoi_spawn_egg", props)));
    public static final DeferredItem<Item> KOFO_JAGA_SPAWN_EGG = ITEMS.registerItem("kofo_jaga_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.KOFO_JAGA.get(), withItemId("kofo_jaga_spawn_egg", props)));
    public static final DeferredItem<Item> NUI_JAGA_SPAWN_EGG = ITEMS.registerItem("nui_jaga_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.NUI_JAGA.get(), withItemId("nui_jaga_spawn_egg", props)));
    // Koro-specific Matoran spawn eggs (one per koro, canonical mask)
    public static final DeferredItem<Item> MATORAN_TA_SPAWN_EGG = ITEMS.registerItem("matoran_ta_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.MATORAN_TA.get(), withItemId("matoran_ta_spawn_egg", props)));
    public static final DeferredItem<Item> MATORAN_GA_SPAWN_EGG = ITEMS.registerItem("matoran_ga_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.MATORAN_GA.get(), withItemId("matoran_ga_spawn_egg", props)));
    public static final DeferredItem<Item> MATORAN_LE_SPAWN_EGG = ITEMS.registerItem("matoran_le_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.MATORAN_LE.get(), withItemId("matoran_le_spawn_egg", props)));
    public static final DeferredItem<Item> MATORAN_ONU_SPAWN_EGG = ITEMS.registerItem("matoran_onu_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.MATORAN_ONU.get(), withItemId("matoran_onu_spawn_egg", props)));
    public static final DeferredItem<Item> MATORAN_KO_SPAWN_EGG = ITEMS.registerItem("matoran_ko_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.MATORAN_KO.get(), withItemId("matoran_ko_spawn_egg", props)));
    public static final DeferredItem<Item> MATORAN_PO_SPAWN_EGG = ITEMS.registerItem("matoran_po_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.MATORAN_PO.get(), withItemId("matoran_po_spawn_egg", props)));

    // ---- Koro × Mask Matoran spawn eggs (36 variants: 6 koros × 6 implemented masks) ----
    // TA koro
    public static final DeferredItem<Item> MATORAN_TA_HAU_EGG   = ITEMS.registerItem("matoran_ta_hau_spawn_egg",   p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.TA,  eastonium.mnogii.entity.EntityMatoran.Mask.HAU,    withItemId("matoran_ta_hau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_TA_HUNA_EGG  = ITEMS.registerItem("matoran_ta_huna_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.TA,  eastonium.mnogii.entity.EntityMatoran.Mask.HUNA,   withItemId("matoran_ta_huna_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_TA_KAKAMA_EGG= ITEMS.registerItem("matoran_ta_kakama_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.TA,  eastonium.mnogii.entity.EntityMatoran.Mask.KAKAMA, withItemId("matoran_ta_kakama_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_TA_KAUKAU_EGG= ITEMS.registerItem("matoran_ta_kaukau_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.TA,  eastonium.mnogii.entity.EntityMatoran.Mask.KAUKAU, withItemId("matoran_ta_kaukau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_TA_MIRU_EGG  = ITEMS.registerItem("matoran_ta_miru_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.TA,  eastonium.mnogii.entity.EntityMatoran.Mask.MIRU,   withItemId("matoran_ta_miru_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_TA_PAKARI_EGG= ITEMS.registerItem("matoran_ta_pakari_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.TA,  eastonium.mnogii.entity.EntityMatoran.Mask.PAKARI, withItemId("matoran_ta_pakari_spawn_egg", p)));
    // GA koro
    public static final DeferredItem<Item> MATORAN_GA_HAU_EGG   = ITEMS.registerItem("matoran_ga_hau_spawn_egg",   p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.GA,  eastonium.mnogii.entity.EntityMatoran.Mask.HAU,    withItemId("matoran_ga_hau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_GA_HUNA_EGG  = ITEMS.registerItem("matoran_ga_huna_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.GA,  eastonium.mnogii.entity.EntityMatoran.Mask.HUNA,   withItemId("matoran_ga_huna_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_GA_KAKAMA_EGG= ITEMS.registerItem("matoran_ga_kakama_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.GA,  eastonium.mnogii.entity.EntityMatoran.Mask.KAKAMA, withItemId("matoran_ga_kakama_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_GA_KAUKAU_EGG= ITEMS.registerItem("matoran_ga_kaukau_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.GA,  eastonium.mnogii.entity.EntityMatoran.Mask.KAUKAU, withItemId("matoran_ga_kaukau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_GA_MIRU_EGG  = ITEMS.registerItem("matoran_ga_miru_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.GA,  eastonium.mnogii.entity.EntityMatoran.Mask.MIRU,   withItemId("matoran_ga_miru_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_GA_PAKARI_EGG= ITEMS.registerItem("matoran_ga_pakari_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.GA,  eastonium.mnogii.entity.EntityMatoran.Mask.PAKARI, withItemId("matoran_ga_pakari_spawn_egg", p)));
    // PO koro
    public static final DeferredItem<Item> MATORAN_PO_HAU_EGG   = ITEMS.registerItem("matoran_po_hau_spawn_egg",   p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.PO,  eastonium.mnogii.entity.EntityMatoran.Mask.HAU,    withItemId("matoran_po_hau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_PO_HUNA_EGG  = ITEMS.registerItem("matoran_po_huna_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.PO,  eastonium.mnogii.entity.EntityMatoran.Mask.HUNA,   withItemId("matoran_po_huna_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_PO_KAKAMA_EGG= ITEMS.registerItem("matoran_po_kakama_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.PO,  eastonium.mnogii.entity.EntityMatoran.Mask.KAKAMA, withItemId("matoran_po_kakama_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_PO_KAUKAU_EGG= ITEMS.registerItem("matoran_po_kaukau_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.PO,  eastonium.mnogii.entity.EntityMatoran.Mask.KAUKAU, withItemId("matoran_po_kaukau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_PO_MIRU_EGG  = ITEMS.registerItem("matoran_po_miru_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.PO,  eastonium.mnogii.entity.EntityMatoran.Mask.MIRU,   withItemId("matoran_po_miru_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_PO_PAKARI_EGG= ITEMS.registerItem("matoran_po_pakari_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.PO,  eastonium.mnogii.entity.EntityMatoran.Mask.PAKARI, withItemId("matoran_po_pakari_spawn_egg", p)));
    // ONU koro
    public static final DeferredItem<Item> MATORAN_ONU_HAU_EGG   = ITEMS.registerItem("matoran_onu_hau_spawn_egg",   p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.ONU, eastonium.mnogii.entity.EntityMatoran.Mask.HAU,    withItemId("matoran_onu_hau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_ONU_HUNA_EGG  = ITEMS.registerItem("matoran_onu_huna_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.ONU, eastonium.mnogii.entity.EntityMatoran.Mask.HUNA,   withItemId("matoran_onu_huna_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_ONU_KAKAMA_EGG= ITEMS.registerItem("matoran_onu_kakama_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.ONU, eastonium.mnogii.entity.EntityMatoran.Mask.KAKAMA, withItemId("matoran_onu_kakama_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_ONU_KAUKAU_EGG= ITEMS.registerItem("matoran_onu_kaukau_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.ONU, eastonium.mnogii.entity.EntityMatoran.Mask.KAUKAU, withItemId("matoran_onu_kaukau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_ONU_MIRU_EGG  = ITEMS.registerItem("matoran_onu_miru_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.ONU, eastonium.mnogii.entity.EntityMatoran.Mask.MIRU,   withItemId("matoran_onu_miru_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_ONU_PAKARI_EGG= ITEMS.registerItem("matoran_onu_pakari_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.ONU, eastonium.mnogii.entity.EntityMatoran.Mask.PAKARI, withItemId("matoran_onu_pakari_spawn_egg", p)));
    // LE koro
    public static final DeferredItem<Item> MATORAN_LE_HAU_EGG   = ITEMS.registerItem("matoran_le_hau_spawn_egg",   p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.LE,  eastonium.mnogii.entity.EntityMatoran.Mask.HAU,    withItemId("matoran_le_hau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_LE_HUNA_EGG  = ITEMS.registerItem("matoran_le_huna_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.LE,  eastonium.mnogii.entity.EntityMatoran.Mask.HUNA,   withItemId("matoran_le_huna_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_LE_KAKAMA_EGG= ITEMS.registerItem("matoran_le_kakama_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.LE,  eastonium.mnogii.entity.EntityMatoran.Mask.KAKAMA, withItemId("matoran_le_kakama_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_LE_KAUKAU_EGG= ITEMS.registerItem("matoran_le_kaukau_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.LE,  eastonium.mnogii.entity.EntityMatoran.Mask.KAUKAU, withItemId("matoran_le_kaukau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_LE_MIRU_EGG  = ITEMS.registerItem("matoran_le_miru_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.LE,  eastonium.mnogii.entity.EntityMatoran.Mask.MIRU,   withItemId("matoran_le_miru_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_LE_PAKARI_EGG= ITEMS.registerItem("matoran_le_pakari_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.LE,  eastonium.mnogii.entity.EntityMatoran.Mask.PAKARI, withItemId("matoran_le_pakari_spawn_egg", p)));
    // KO koro
    public static final DeferredItem<Item> MATORAN_KO_HAU_EGG   = ITEMS.registerItem("matoran_ko_hau_spawn_egg",   p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.KO,  eastonium.mnogii.entity.EntityMatoran.Mask.HAU,    withItemId("matoran_ko_hau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_KO_HUNA_EGG  = ITEMS.registerItem("matoran_ko_huna_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.KO,  eastonium.mnogii.entity.EntityMatoran.Mask.HUNA,   withItemId("matoran_ko_huna_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_KO_KAKAMA_EGG= ITEMS.registerItem("matoran_ko_kakama_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.KO,  eastonium.mnogii.entity.EntityMatoran.Mask.KAKAMA, withItemId("matoran_ko_kakama_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_KO_KAUKAU_EGG= ITEMS.registerItem("matoran_ko_kaukau_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.KO,  eastonium.mnogii.entity.EntityMatoran.Mask.KAUKAU, withItemId("matoran_ko_kaukau_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_KO_MIRU_EGG  = ITEMS.registerItem("matoran_ko_miru_spawn_egg",  p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.KO,  eastonium.mnogii.entity.EntityMatoran.Mask.MIRU,   withItemId("matoran_ko_miru_spawn_egg", p)));
    public static final DeferredItem<Item> MATORAN_KO_PAKARI_EGG= ITEMS.registerItem("matoran_ko_pakari_spawn_egg",p -> new eastonium.mnogii.item.MatoranSpawnEggItem(eastonium.mnogii.entity.EntityMatoran.Koro.KO,  eastonium.mnogii.entity.EntityMatoran.Mask.PAKARI, withItemId("matoran_ko_pakari_spawn_egg", p)));

    // Per-character Turaga spawn eggs (Mata series)
    public static final DeferredItem<Item> TURAGA_VAKAMA_SPAWN_EGG = ITEMS.registerItem("turaga_vakama_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TURAGA_VAKAMA.get(), withItemId("turaga_vakama_spawn_egg", props)));
    public static final DeferredItem<Item> TURAGA_NOKAMA_SPAWN_EGG = ITEMS.registerItem("turaga_nokama_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TURAGA_NOKAMA.get(), withItemId("turaga_nokama_spawn_egg", props)));
    public static final DeferredItem<Item> TURAGA_MATAU_SPAWN_EGG = ITEMS.registerItem("turaga_matau_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TURAGA_MATAU.get(), withItemId("turaga_matau_spawn_egg", props)));
    public static final DeferredItem<Item> TURAGA_ONEWA_SPAWN_EGG = ITEMS.registerItem("turaga_onewa_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TURAGA_ONEWA.get(), withItemId("turaga_onewa_spawn_egg", props)));
    public static final DeferredItem<Item> TURAGA_WHENUA_SPAWN_EGG = ITEMS.registerItem("turaga_whenua_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TURAGA_WHENUA.get(), withItemId("turaga_whenua_spawn_egg", props)));
    public static final DeferredItem<Item> TURAGA_NUJU_SPAWN_EGG = ITEMS.registerItem("turaga_nuju_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TURAGA_NUJU.get(), withItemId("turaga_nuju_spawn_egg", props)));
    public static final DeferredItem<Item> MUAKA_SPAWN_EGG = ITEMS.registerItem("muaka_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.MUAKA.get(), withItemId("muaka_spawn_egg", props)));
    public static final DeferredItem<Item> TARAKAVA_SPAWN_EGG = ITEMS.registerItem("tarakava_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TARAKAVA.get(), withItemId("tarakava_spawn_egg", props)));
    public static final DeferredItem<Item> GUKKO_SPAWN_EGG = ITEMS.registerItem("gukko_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.GUKKO.get(), withItemId("gukko_spawn_egg", props)));
    public static final DeferredItem<Item> NUI_RAMA_SPAWN_EGG = ITEMS.registerItem("nui_rama_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.NUI_RAMA.get(), withItemId("nui_rama_spawn_egg", props)));

    // Toa spawn eggs
    public static final DeferredItem<Item> TOA_TAHU_SPAWN_EGG = ITEMS.registerItem("toa_tahu_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TOA_TAHU.get(), withItemId("toa_tahu_spawn_egg", props)));
    public static final DeferredItem<Item> TOA_GALI_SPAWN_EGG = ITEMS.registerItem("toa_gali_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TOA_GALI.get(), withItemId("toa_gali_spawn_egg", props)));
    public static final DeferredItem<Item> TOA_LEWA_SPAWN_EGG = ITEMS.registerItem("toa_lewa_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TOA_LEWA.get(), withItemId("toa_lewa_spawn_egg", props)));
    public static final DeferredItem<Item> TOA_ONUA_SPAWN_EGG = ITEMS.registerItem("toa_onua_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TOA_ONUA.get(), withItemId("toa_onua_spawn_egg", props)));
    public static final DeferredItem<Item> TOA_POHATU_SPAWN_EGG = ITEMS.registerItem("toa_pohatu_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TOA_POHATU.get(), withItemId("toa_pohatu_spawn_egg", props)));
    public static final DeferredItem<Item> TOA_KOPAKA_SPAWN_EGG = ITEMS.registerItem("toa_kopaka_spawn_egg",
            props -> new SpawnEggItem(MnogiiEntityTypes.TOA_KOPAKA.get(), withItemId("toa_kopaka_spawn_egg", props)));
}
