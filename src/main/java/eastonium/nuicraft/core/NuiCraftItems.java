package eastonium.nuicraft.core;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.item.NuiCraftTiers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
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

    private static final ResourceKey<net.minecraft.world.item.equipment.EquipmentAsset> MASK_EMPTY_ASSET = ResourceKey.create(
            ResourceKey.createRegistryKey(ResourceLocation.fromNamespaceAndPath("minecraft", "equipment_asset")),
            ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "empty")
    );

    /**
     * Build mask Item.Properties with the given attribute modifiers and equippable HEAD slot.
     * Uses an empty equipment asset (no layers) to prevent vanilla 2D armor overlay.
     * The 3D mask model is rendered separately by AzureLib.
     * Durability is omitted to avoid triggering vanilla armor rendering behaviour.
     */
    private static Item.Properties maskProps(Item.Properties props, ItemAttributeModifiers attributes) {
        return props.stacksTo(1)
                .attributes(attributes)
                .component(net.minecraft.core.component.DataComponents.EQUIPPABLE,
                        Equippable.builder(EquipmentSlot.HEAD)
                                .setAsset(MASK_EMPTY_ASSET)
                                .setDamageOnHurt(false)
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
    //     Armor: 4, Toughness: 2
    public static final DeferredItem<Item> MASK_MATA_HAU = ITEMS.registerItem("mask_mata_hau",
            props -> new Item(maskProps(withItemId("mask_mata_hau", props), buildMaskAttributes(4.0, 2.0))));

    // --- Kaukau: Mask of Water Breathing - Water Breathing effect ---
    //     Armor: 2
    public static final DeferredItem<Item> MASK_MATA_KAUKAU = ITEMS.registerItem("mask_mata_kaukau",
            props -> new Item(maskProps(withItemId("mask_mata_kaukau", props), buildMaskAttributes(2.0, 0.0))));

    // --- Miru: Mask of Levitation - Slow Falling + Jump Boost I effect ---
    //     Armor: 2
    public static final DeferredItem<Item> MASK_MATA_MIRU = ITEMS.registerItem("mask_mata_miru",
            props -> new Item(maskProps(withItemId("mask_mata_miru", props), buildMaskAttributes(2.0, 0.0))));

    // --- Kakama: Mask of Speed - +40% movement speed ---
    //     Armor: 2, Speed: +40%
    public static final DeferredItem<Item> MASK_MATA_KAKAMA = ITEMS.registerItem("mask_mata_kakama",
            props -> {
                ItemAttributeModifiers.Builder b = ItemAttributeModifiers.builder();
                b.add(Attributes.ARMOR,
                        new AttributeModifier(maskLoc("mask_armor"), 2.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                b.add(Attributes.MOVEMENT_SPEED,
                        new AttributeModifier(maskLoc("mask_speed"), 0.4, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                return new Item(maskProps(withItemId("mask_mata_kakama", props), b.build()));
            });

    // --- Pakari: Mask of Strength - +3 attack damage ---
    //     Armor: 3, Toughness: 1, Attack Damage: +3
    public static final DeferredItem<Item> MASK_MATA_PAKARI = ITEMS.registerItem("mask_mata_pakari",
            props -> {
                ItemAttributeModifiers.Builder b = ItemAttributeModifiers.builder();
                b.add(Attributes.ARMOR,
                        new AttributeModifier(maskLoc("mask_armor"), 3.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                b.add(Attributes.ARMOR_TOUGHNESS,
                        new AttributeModifier(maskLoc("mask_toughness"), 1.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                b.add(Attributes.ATTACK_DAMAGE,
                        new AttributeModifier(maskLoc("mask_strength"), 3.0, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.bySlot(EquipmentSlot.HEAD));
                return new Item(maskProps(withItemId("mask_mata_pakari", props), b.build()));
            });

    // --- Akaku: Mask of X-Ray Vision - Night Vision effect ---
    //     Armor: 2
    public static final DeferredItem<Item> MASK_MATA_AKAKU = ITEMS.registerItem("mask_mata_akaku",
            props -> new Item(maskProps(withItemId("mask_mata_akaku", props), buildMaskAttributes(2.0, 0.0))));

    // --- Huna: Mask of Concealment - Invisibility effect ---
    //     Armor: 1
    public static final DeferredItem<Item> MASK_MATA_HUNA = ITEMS.registerItem("mask_mata_huna",
            props -> new Item(maskProps(withItemId("mask_mata_huna", props), buildMaskAttributes(1.0, 0.0))));

    // --- Mahiki: Mask of Illusion - Invisibility effect ---
    //     Armor: 1
    public static final DeferredItem<Item> MASK_MATA_MAHIKI = ITEMS.registerItem("mask_mata_mahiki",
            props -> new Item(maskProps(withItemId("mask_mata_mahiki", props), buildMaskAttributes(1.0, 0.0))));

    // --- Matatu: Mask of Telekinesis - knockback resistance + extended reach ---
    //     Armor: 2, KB Resist: 50%, Reach: +3
    public static final DeferredItem<Item> MASK_MATA_MATATU = ITEMS.registerItem("mask_mata_matatu",
            props -> {
                ItemAttributeModifiers.Builder b = ItemAttributeModifiers.builder();
                b.add(Attributes.ARMOR,
                        new AttributeModifier(maskLoc("mask_armor"), 2.0, AttributeModifier.Operation.ADD_VALUE),
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
    //     Armor: 2
    public static final DeferredItem<Item> MASK_MATA_KOMAU = ITEMS.registerItem("mask_mata_komau",
            props -> new Item(maskProps(withItemId("mask_mata_komau", props), buildMaskAttributes(2.0, 0.0))));

    // --- Raru: Mask of Translation - Luck I effect ---
    //     Armor: 2
    public static final DeferredItem<Item> MASK_MATA_RARU = ITEMS.registerItem("mask_mata_raru",
            props -> new Item(maskProps(withItemId("mask_mata_raru", props), buildMaskAttributes(2.0, 0.0))));

    // --- Ruru: Mask of Night Vision - Night Vision effect ---
    //     Armor: 2
    public static final DeferredItem<Item> MASK_MATA_RURU = ITEMS.registerItem("mask_mata_ruru",
            props -> new Item(maskProps(withItemId("mask_mata_ruru", props), buildMaskAttributes(2.0, 0.0))));


    // =====================================================================
    // Spawn Eggs
    // =====================================================================

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
