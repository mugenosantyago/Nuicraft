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

    // =====================================================================
    // Mata Masks - 12 equippable helmet masks with 3D geo rendering
    // Each mask provides armor and a unique Kanohi power.
    // =====================================================================

    private static final int MASK_DURABILITY = 300;

    private static ResourceLocation maskLoc(String path) {
        return ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, path);
    }

    private static final ResourceKey<EquipmentAsset> MASK_EMPTY_ASSET = ResourceKey.create(
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("minecraft", "equipment_asset")),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "empty")
    );

    /**
     * Build mask Item.Properties with the given attribute modifiers, durability, and equippable HEAD slot.
     * All masks are equippable as helmets with 3D AzureLib rendering.
     */
    private static Item.Properties maskProps(Item.Properties props, ItemAttributeModifiers attributes) {
        return props.stacksTo(1)
                .durability(MASK_DURABILITY)
                .attributes(attributes)
                .component(net.minecraft.core.component.DataComponents.EQUIPPABLE,
                        Equippable.builder(EquipmentSlot.HEAD)
                                .setAsset(MASK_EMPTY_ASSET)
                                .build());
    }

    /** Build standard mask attributes with armor and optional toughness. */
    private static ItemAttributeModifiers buildMaskAttributes(double armor, double toughness) {
        ItemAttributeModifiers.Builder b = ItemAttributeModifiers.builder();
        b.add(Attributes.ARMOR,
                new AttributeModifier(maskLoc("mask_armor"), armor, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
        if (toughness > 0) {
            b.add(Attributes.ARMOR_TOUGHNESS,
                    new AttributeModifier(maskLoc("mask_toughness"), toughness, AttributeModifier.Operation.ADD_VALUE),
                    EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
        }
        return b.build();
    }

    // --- Hau: Mask of Shielding - heavy armor + Resistance I effect ---
    public static final DeferredItem<Item> MASK_MATA_HAU = ITEMS.registerItem("mask_mata_hau",
            props -> new Item(maskProps(withItemId("mask_mata_hau", props), buildMaskAttributes(4, 2))));

    // --- Kaukau: Mask of Water Breathing - Water Breathing effect ---
    public static final DeferredItem<Item> MASK_MATA_KAUKAU = ITEMS.registerItem("mask_mata_kaukau",
            props -> new Item(maskProps(withItemId("mask_mata_kaukau", props), buildMaskAttributes(2, 0))));

    // --- Miru: Mask of Levitation - Slow Falling + Jump Boost I effect ---
    public static final DeferredItem<Item> MASK_MATA_MIRU = ITEMS.registerItem("mask_mata_miru",
            props -> new Item(maskProps(withItemId("mask_mata_miru", props), buildMaskAttributes(2, 0))));

    // --- Kakama: Mask of Speed - +40% movement speed ---
    public static final DeferredItem<Item> MASK_MATA_KAKAMA = ITEMS.registerItem("mask_mata_kakama",
            props -> {
                ItemAttributeModifiers.Builder b = ItemAttributeModifiers.builder();
                b.add(Attributes.ARMOR,
                        new AttributeModifier(maskLoc("mask_armor"), 2, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                b.add(Attributes.MOVEMENT_SPEED,
                        new AttributeModifier(maskLoc("mask_speed"), 0.4, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                return new Item(maskProps(withItemId("mask_mata_kakama", props), b.build()));
            });

    // --- Pakari: Mask of Strength - +3 attack damage ---
    public static final DeferredItem<Item> MASK_MATA_PAKARI = ITEMS.registerItem("mask_mata_pakari",
            props -> {
                ItemAttributeModifiers.Builder b = ItemAttributeModifiers.builder();
                b.add(Attributes.ARMOR,
                        new AttributeModifier(maskLoc("mask_armor"), 3, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                b.add(Attributes.ARMOR_TOUGHNESS,
                        new AttributeModifier(maskLoc("mask_toughness"), 1, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                b.add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(maskLoc("mask_strength"), 3.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                return new Item(maskProps(withItemId("mask_mata_pakari", props), b.build()));
            });

    // --- Akaku: Mask of X-Ray Vision - Night Vision effect ---
    public static final DeferredItem<Item> MASK_MATA_AKAKU = ITEMS.registerItem("mask_mata_akaku",
            props -> new Item(maskProps(withItemId("mask_mata_akaku", props), buildMaskAttributes(2, 0))));

    // --- Huna: Mask of Concealment - Invisibility effect ---
    public static final DeferredItem<Item> MASK_MATA_HUNA = ITEMS.registerItem("mask_mata_huna",
            props -> new Item(maskProps(withItemId("mask_mata_huna", props), buildMaskAttributes(1, 0))));

    // --- Mahiki: Mask of Illusion - Invisibility effect ---
    public static final DeferredItem<Item> MASK_MATA_MAHIKI = ITEMS.registerItem("mask_mata_mahiki",
            props -> new Item(maskProps(withItemId("mask_mata_mahiki", props), buildMaskAttributes(1, 0))));

    // --- Matatu: Mask of Telekinesis - knockback resistance + extended reach ---
    public static final DeferredItem<Item> MASK_MATA_MATATU = ITEMS.registerItem("mask_mata_matatu",
            props -> {
                ItemAttributeModifiers.Builder b = ItemAttributeModifiers.builder();
                b.add(Attributes.ARMOR,
                        new AttributeModifier(maskLoc("mask_armor"), 2, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                b.add(Attributes.KNOCKBACK_RESISTANCE,
                        new AttributeModifier(maskLoc("mask_telekinesis_kb"), 0.5, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                b.add(Attributes.ENTITY_INTERACTION_RANGE,
                        new AttributeModifier(maskLoc("mask_telekinesis_reach"), 3.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                return new Item(maskProps(withItemId("mask_mata_matatu", props), b.build()));
            });

    // --- Komau: Mask of Mind Control - weakens nearby enemies (via tick handler) ---
    public static final DeferredItem<Item> MASK_MATA_KOMAU = ITEMS.registerItem("mask_mata_komau",
            props -> new Item(maskProps(withItemId("mask_mata_komau", props), buildMaskAttributes(2, 0))));

    // --- Raru: Mask of Translation - Luck I effect ---
    public static final DeferredItem<Item> MASK_MATA_RARU = ITEMS.registerItem("mask_mata_raru",
            props -> new Item(maskProps(withItemId("mask_mata_raru", props), buildMaskAttributes(2, 0))));

    // --- Ruru: Mask of Night Vision - Night Vision effect ---
    public static final DeferredItem<Item> MASK_MATA_RURU = ITEMS.registerItem("mask_mata_ruru",
            props -> new Item(maskProps(withItemId("mask_mata_ruru", props), buildMaskAttributes(2, 0))));


    // =====================================================================
    // Spawn Eggs - one for every registered entity type
    // =====================================================================

    // Core Rahi
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
    public static final DeferredItem<Item> KANE_RA_SPAWN_EGG = ITEMS.registerItem("kane_ra_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KANERA.get(), withItemId("kane_ra_spawn_egg", props)));
    public static final DeferredItem<Item> MUAKA_SPAWN_EGG = ITEMS.registerItem("muaka_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.MUAKA.get(), withItemId("muaka_spawn_egg", props)));
    public static final DeferredItem<Item> NUI_RAMA_GREEN_SPAWN_EGG = ITEMS.registerItem("nui_rama_green_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.NUIRAMAGREEN.get(), withItemId("nui_rama_green_spawn_egg", props)));
    public static final DeferredItem<Item> NUI_RAMA_ORANGE_SPAWN_EGG = ITEMS.registerItem("nui_rama_orange_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.NUIRAMAORANGE.get(), withItemId("nui_rama_orange_spawn_egg", props)));
    public static final DeferredItem<Item> SPIDER_FIKOU_SPAWN_EGG = ITEMS.registerItem("spider_fikou_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.SPIDERFIKOU.get(), withItemId("spider_fikou_spawn_egg", props)));
    public static final DeferredItem<Item> TARAKAVA_BLUE_SPAWN_EGG = ITEMS.registerItem("tarakava_blue_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.TARAKAVABLUE.get(), withItemId("tarakava_blue_spawn_egg", props)));
    public static final DeferredItem<Item> TARAKAVA_GREEN_SPAWN_EGG = ITEMS.registerItem("tarakava_green_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.TARAKAVAGREEN.get(), withItemId("tarakava_green_spawn_egg", props)));
    public static final DeferredItem<Item> TARAKAVA_YELLOW_SPAWN_EGG = ITEMS.registerItem("tarakava_yellow_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.TARAKAVAYELLOW.get(), withItemId("tarakava_yellow_spawn_egg", props)));

    // Bohrok
    public static final DeferredItem<Item> BOHROK_TAHNOK_SPAWN_EGG = ITEMS.registerItem("bohrok_tahnok_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.BOHROKTAHNOK.get(), withItemId("bohrok_tahnok_spawn_egg", props)));
    public static final DeferredItem<Item> GAHLOK_SPAWN_EGG = ITEMS.registerItem("gahlok_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.GAHLOK.get(), withItemId("gahlok_spawn_egg", props)));
    public static final DeferredItem<Item> KOHRAK_SPAWN_EGG = ITEMS.registerItem("kohrak_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KOHRAK.get(), withItemId("kohrak_spawn_egg", props)));
    public static final DeferredItem<Item> LEHVAK_SPAWN_EGG = ITEMS.registerItem("lehvak_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.LEHVAK.get(), withItemId("lehvak_spawn_egg", props)));
    public static final DeferredItem<Item> NUHVOK_SPAWN_EGG = ITEMS.registerItem("nuhvok_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.NUHVOK.get(), withItemId("nuhvok_spawn_egg", props)));
    public static final DeferredItem<Item> PAHRAK_SPAWN_EGG = ITEMS.registerItem("pahrak_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.PAHRAK.get(), withItemId("pahrak_spawn_egg", props)));

    // Rahkshi
    public static final DeferredItem<Item> GUURAHK_SPAWN_EGG = ITEMS.registerItem("guurahk_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.GUURAHK.get(), withItemId("guurahk_spawn_egg", props)));
    public static final DeferredItem<Item> KURAHK_SPAWN_EGG = ITEMS.registerItem("kurahk_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KURAHK.get(), withItemId("kurahk_spawn_egg", props)));
    public static final DeferredItem<Item> LERAHK_SPAWN_EGG = ITEMS.registerItem("lerahk_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.LERAHK.get(), withItemId("lerahk_spawn_egg", props)));
    public static final DeferredItem<Item> PANRAHK_SPAWN_EGG = ITEMS.registerItem("panrahk_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.PANRAHK.get(), withItemId("panrahk_spawn_egg", props)));
    public static final DeferredItem<Item> RAHKSHI_YELLOW_SPAWN_EGG = ITEMS.registerItem("rahkshi_yellow_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.RAHKSHIYELLOW.get(), withItemId("rahkshi_yellow_spawn_egg", props)));
    public static final DeferredItem<Item> TURAHK_SPAWN_EGG = ITEMS.registerItem("turahk_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.TURAHK.get(), withItemId("turahk_spawn_egg", props)));
    public static final DeferredItem<Item> VOHRAK_SPAWN_EGG = ITEMS.registerItem("vohrak_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.VOHRAK.get(), withItemId("vohrak_spawn_egg", props)));

    // Matoran
    public static final DeferredItem<Item> AGNI_MATORAN_SPAWN_EGG = ITEMS.registerItem("agni_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.AGNIMATORAN.get(), withItemId("agni_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> AHKMOU_MATORAN_SPAWN_EGG = ITEMS.registerItem("ahkmou_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.AKHMOUMATORAN.get(), withItemId("ahkmou_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> BOREAS_MATORAN_SPAWN_EGG = ITEMS.registerItem("boreas_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.BOREASMATORAN.get(), withItemId("boreas_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> HAFU_MATORAN_SPAWN_EGG = ITEMS.registerItem("hafu_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.HAFUMATORAN.get(), withItemId("hafu_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> HEWKII_MATORAN_SPAWN_EGG = ITEMS.registerItem("hewkii_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.HEWKIIMATORAN.get(), withItemId("hewkii_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> JALLER_MATORAN_SPAWN_EGG = ITEMS.registerItem("jaller_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.JALLERMATORAN.get(), withItemId("jaller_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> KOKKAN_MATORAN_SPAWN_EGG = ITEMS.registerItem("kokkan_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KOKKANMATORAN.get(), withItemId("kokkan_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> KONGU_MATORAN_SPAWN_EGG = ITEMS.registerItem("kongu_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KONGUMATORAN.get(), withItemId("kongu_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> KOTU_MATORAN_SPAWN_EGG = ITEMS.registerItem("kotu_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.KOTUMATORAN.get(), withItemId("kotu_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> MACKU_MATORAN_SPAWN_EGG = ITEMS.registerItem("macku_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.MACKUMATORAN.get(), withItemId("macku_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> MATORO_MATORAN_SPAWN_EGG = ITEMS.registerItem("matoro_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.MATOROMATORAN.get(), withItemId("matoro_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> NUPARU_MATORAN_SPAWN_EGG = ITEMS.registerItem("nuparu_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.NUPARUMATORAN.get(), withItemId("nuparu_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> OKOTH_MATORAN_SPAWN_EGG = ITEMS.registerItem("okoth_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.OKOTHMATORAN.get(), withItemId("okoth_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> ONEPU_MATORAN_SPAWN_EGG = ITEMS.registerItem("onepu_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.ONEPUMATORAN.get(), withItemId("onepu_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> PAKASTAA_MATORAN_SPAWN_EGG = ITEMS.registerItem("pakastaa_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.PAKASTAAMATORAN.get(), withItemId("pakastaa_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> TUULI_MATORAN_SPAWN_EGG = ITEMS.registerItem("tuuli_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.TUULIMATORAN.get(), withItemId("tuuli_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> VOHON_MATORAN_SPAWN_EGG = ITEMS.registerItem("vohon_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.VOHONMATORAN.get(), withItemId("vohon_matoran_spawn_egg", props)));
    public static final DeferredItem<Item> ZEMYA_MATORAN_SPAWN_EGG = ITEMS.registerItem("zemya_matoran_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.ZEMYAMATORAN.get(), withItemId("zemya_matoran_spawn_egg", props)));

    // Turaga
    public static final DeferredItem<Item> MATAU_TURAGA_SPAWN_EGG = ITEMS.registerItem("matau_turaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.MATAUTURAGA.get(), withItemId("matau_turaga_spawn_egg", props)));
    public static final DeferredItem<Item> NOKAMA_TURAGA_SPAWN_EGG = ITEMS.registerItem("nokama_turaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.NOKAMATURAGA.get(), withItemId("nokama_turaga_spawn_egg", props)));
    public static final DeferredItem<Item> NUJU_TURAGA_SPAWN_EGG = ITEMS.registerItem("nuju_turaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.NUJUTURAGA.get(), withItemId("nuju_turaga_spawn_egg", props)));
    public static final DeferredItem<Item> ONEWA_TURAGA_SPAWN_EGG = ITEMS.registerItem("onewa_turaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.ONEWATURAGA.get(), withItemId("onewa_turaga_spawn_egg", props)));
    public static final DeferredItem<Item> VAKAMA_TURAGA_SPAWN_EGG = ITEMS.registerItem("vakama_turaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.VAKAMATURAGA.get(), withItemId("vakama_turaga_spawn_egg", props)));
    public static final DeferredItem<Item> WHENUA_TURAGA_SPAWN_EGG = ITEMS.registerItem("whenua_turaga_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.WHENAUTURAGA.get(), withItemId("whenua_turaga_spawn_egg", props)));

    // Boss
    public static final DeferredItem<Item> MAKUTA_SPAWN_EGG = ITEMS.registerItem("makuta_spawn_egg",
            props -> new SpawnEggItem(NuiCraftEntityTypes.MAKUTA.get(), withItemId("makuta_spawn_egg", props)));
}
