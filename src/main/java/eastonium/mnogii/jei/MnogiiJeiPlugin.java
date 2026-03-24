package eastonium.mnogii.jei;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.core.MnogiiBlocks;
import eastonium.mnogii.core.MnogiiItems;
import eastonium.mnogii.recipe.PurifyingRecipe;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.List;
import java.util.stream.Stream;

/**
 * JEI plugin for Mnogii. Registers mod items with JEI (item list + info) and ensures
 * data-pack recipes are available for recipe lookup.
 */
@JeiPlugin
public class MnogiiJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new PurifierRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(
                new ItemStack(MnogiiBlocks.PURIFIER.get()),
                PurifierRecipeCategory.TYPE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Purifier recipes — built inline to match the data-pack JSON recipes
        registration.addRecipes(PurifierRecipeCategory.TYPE, List.of(
                new PurifyingRecipe(
                        "",
                        CookingBookCategory.MISC,
                        Ingredient.of(MnogiiItems.MOLTEN_PROTODERMIS_BUCKET.get()),
                        new ItemStack(MnogiiItems.PURE_MOLTEN_PROTODERMIS_BUCKET.get()),
                        1.0f,
                        200)
        ));

        // ---- Mata masks — base 10 ----
        List<ItemStack> masks = Stream.of(
                MnogiiItems.MASK_MATA_HAU,    MnogiiItems.MASK_MATA_KAUKAU,
                MnogiiItems.MASK_MATA_MIRU,   MnogiiItems.MASK_MATA_KAKAMA,
                MnogiiItems.MASK_MATA_PAKARI, MnogiiItems.MASK_MATA_HUNA,
                MnogiiItems.MASK_MATA_MAHIKI, MnogiiItems.MASK_MATA_MATATU,
                MnogiiItems.MASK_MATA_RARU,   MnogiiItems.MASK_MATA_RURU
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(masks, Component.translatable("jei.mnogii.mask.info"));

        // ---- Koro-colored masks (ta/ga/po/ko via dye, le/onu via koro stone) ----
        List<ItemStack> koroMasks = Stream.of(
                // Ta (red)
                MnogiiItems.MASK_MATA_HAU_TA,     MnogiiItems.MASK_MATA_KAUKAU_TA,
                MnogiiItems.MASK_MATA_MIRU_TA,    MnogiiItems.MASK_MATA_KAKAMA_TA,
                MnogiiItems.MASK_MATA_PAKARI_TA,  MnogiiItems.MASK_MATA_HUNA_TA,
                MnogiiItems.MASK_MATA_MAHIKI_TA,  MnogiiItems.MASK_MATA_MATATU_TA,
                MnogiiItems.MASK_MATA_RARU_TA,    MnogiiItems.MASK_MATA_RURU_TA,
                // Ga (blue)
                MnogiiItems.MASK_MATA_HAU_GA,     MnogiiItems.MASK_MATA_KAUKAU_GA,
                MnogiiItems.MASK_MATA_MIRU_GA,    MnogiiItems.MASK_MATA_KAKAMA_GA,
                MnogiiItems.MASK_MATA_PAKARI_GA,  MnogiiItems.MASK_MATA_HUNA_GA,
                MnogiiItems.MASK_MATA_MAHIKI_GA,  MnogiiItems.MASK_MATA_MATATU_GA,
                MnogiiItems.MASK_MATA_RARU_GA,    MnogiiItems.MASK_MATA_RURU_GA,
                // Po (brown)
                MnogiiItems.MASK_MATA_HAU_PO,     MnogiiItems.MASK_MATA_KAUKAU_PO,
                MnogiiItems.MASK_MATA_MIRU_PO,    MnogiiItems.MASK_MATA_KAKAMA_PO,
                MnogiiItems.MASK_MATA_PAKARI_PO,  MnogiiItems.MASK_MATA_HUNA_PO,
                MnogiiItems.MASK_MATA_MAHIKI_PO,  MnogiiItems.MASK_MATA_MATATU_PO,
                MnogiiItems.MASK_MATA_RARU_PO,    MnogiiItems.MASK_MATA_RURU_PO,
                // Ko (white)
                MnogiiItems.MASK_MATA_HAU_KO,     MnogiiItems.MASK_MATA_KAUKAU_KO,
                MnogiiItems.MASK_MATA_MIRU_KO,    MnogiiItems.MASK_MATA_KAKAMA_KO,
                MnogiiItems.MASK_MATA_PAKARI_KO,  MnogiiItems.MASK_MATA_HUNA_KO,
                MnogiiItems.MASK_MATA_MAHIKI_KO,  MnogiiItems.MASK_MATA_MATATU_KO,
                MnogiiItems.MASK_MATA_RARU_KO,    MnogiiItems.MASK_MATA_RURU_KO,
                // Le (green) — use le_koro_stone
                MnogiiItems.MASK_MATA_HAU_LE,     MnogiiItems.MASK_MATA_KAUKAU_LE,
                MnogiiItems.MASK_MATA_MIRU_LE,    MnogiiItems.MASK_MATA_KAKAMA_LE,
                MnogiiItems.MASK_MATA_PAKARI_LE,  MnogiiItems.MASK_MATA_HUNA_LE,
                MnogiiItems.MASK_MATA_MAHIKI_LE,  MnogiiItems.MASK_MATA_MATATU_LE,
                MnogiiItems.MASK_MATA_RARU_LE,    MnogiiItems.MASK_MATA_RURU_LE,
                // Onu (black) — use onu_koro_stone
                MnogiiItems.MASK_MATA_HAU_ONU,    MnogiiItems.MASK_MATA_KAUKAU_ONU,
                MnogiiItems.MASK_MATA_MIRU_ONU,   MnogiiItems.MASK_MATA_KAKAMA_ONU,
                MnogiiItems.MASK_MATA_PAKARI_ONU, MnogiiItems.MASK_MATA_HUNA_ONU,
                MnogiiItems.MASK_MATA_MAHIKI_ONU, MnogiiItems.MASK_MATA_MATATU_ONU,
                MnogiiItems.MASK_MATA_RARU_ONU,   MnogiiItems.MASK_MATA_RURU_ONU
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(koroMasks, Component.translatable("jei.mnogii.mask.koro_colored.info"));

        // ---- Accent-colored masks (purple / yellow) ----
        List<ItemStack> accentMasks = Stream.of(
                MnogiiItems.MASK_MATA_HAU_PURPLE,     MnogiiItems.MASK_MATA_KAUKAU_PURPLE,
                MnogiiItems.MASK_MATA_MIRU_PURPLE,    MnogiiItems.MASK_MATA_KAKAMA_PURPLE,
                MnogiiItems.MASK_MATA_PAKARI_PURPLE,  MnogiiItems.MASK_MATA_HUNA_PURPLE,
                MnogiiItems.MASK_MATA_MAHIKI_PURPLE,  MnogiiItems.MASK_MATA_MATATU_PURPLE,
                MnogiiItems.MASK_MATA_RARU_PURPLE,    MnogiiItems.MASK_MATA_RURU_PURPLE,
                MnogiiItems.MASK_MATA_HAU_YELLOW,     MnogiiItems.MASK_MATA_KAUKAU_YELLOW,
                MnogiiItems.MASK_MATA_MIRU_YELLOW,    MnogiiItems.MASK_MATA_KAKAMA_YELLOW,
                MnogiiItems.MASK_MATA_PAKARI_YELLOW,  MnogiiItems.MASK_MATA_HUNA_YELLOW,
                MnogiiItems.MASK_MATA_MAHIKI_YELLOW,  MnogiiItems.MASK_MATA_MATATU_YELLOW,
                MnogiiItems.MASK_MATA_RARU_YELLOW,    MnogiiItems.MASK_MATA_RURU_YELLOW
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(accentMasks, Component.translatable("jei.mnogii.mask.accent_colored.info"));

        // Mask Forge
        registration.addItemStackInfo(
                List.of(new ItemStack(MnogiiBlocks.MASK_FORGE.get())),
                Component.translatable("jei.mnogii.mask_forge.info"));

        // ---- Kanoka discs ----
        // General throwable discs
        registration.addItemStackInfo(
                List.of(
                        new ItemStack(MnogiiItems.KANOKA_BAMBOO.get()),
                        new ItemStack(MnogiiItems.KANOKA_DISK_TA.get()),
                        new ItemStack(MnogiiItems.KANOKA_DISK_GA.get()),
                        new ItemStack(MnogiiItems.KANOKA_DISK_LE.get()),
                        new ItemStack(MnogiiItems.KANOKA_DISK_PO.get()),
                        new ItemStack(MnogiiItems.KANOKA_DISK_ONU.get()),
                        new ItemStack(MnogiiItems.KANOKA_DISK_KO.get())
                ),
                Component.translatable("jei.mnogii.disc.info"));

        // Heatstone Lighter
        registration.addItemStackInfo(
                List.of(new ItemStack(MnogiiItems.HEATSTONE_LIGHTER.get())),
                Component.translatable("jei.mnogii.heatstone_lighter.info"));

        // Koro-specific Kanoka disks
        List<ItemStack> koroDiscs = Stream.of(
                MnogiiItems.KANOKA_DISK_TA,
                MnogiiItems.KANOKA_DISK_GA,
                MnogiiItems.KANOKA_DISK_LE,
                MnogiiItems.KANOKA_DISK_ONU,
                MnogiiItems.KANOKA_DISK_PO,
                MnogiiItems.KANOKA_DISK_KO,
                MnogiiItems.KANOKA_OF_TIME
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(koroDiscs, Component.translatable("jei.mnogii.disc.koro.info"));

        // ---- Toa Mata weapons ----
        List<ItemStack> toaWeapons = Stream.of(
                MnogiiItems.FIRE_STAFF,
                MnogiiItems.FIRE_SWORD,
                MnogiiItems.KAUKAU_STAFF,
                MnogiiItems.WATER_HOOKS,
                MnogiiItems.AIR_AXE,
                MnogiiItems.ONUA_CLAWS,
                MnogiiItems.ONUA_DRILL,
                MnogiiItems.POHATU_HANDS,
                MnogiiItems.ICE_SWORD,
                MnogiiItems.ICE_PICKAXE,
                MnogiiItems.ICE_SHIELD,
                MnogiiItems.TOA_TRIDENT
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(toaWeapons, Component.translatable("jei.mnogii.toa_weapons.info"));

        // ---- Protodermis tools ----
        registration.addItemStackInfo(
                List.of(
                        new ItemStack(MnogiiItems.PROTODERMIS_SWORD.get()),
                        new ItemStack(MnogiiItems.PROTODERMIS_PICK.get()),
                        new ItemStack(MnogiiItems.PROTODERMIS_AXE.get())
                ),
                Component.translatable("jei.mnogii.protodermis_tools.info"));

        // ---- Building blocks ----
        List<ItemStack> koroStones = Stream.of(
                MnogiiItems.TA_KORO_STONE,  MnogiiItems.GA_KORO_STONE,
                MnogiiItems.LE_KORO_STONE,  MnogiiItems.KO_KORO_STONE,
                MnogiiItems.ONU_KORO_STONE, MnogiiItems.PO_KORO_STONE
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(koroStones, Component.translatable("jei.mnogii.koro_stone.info"));

        List<ItemStack> seaweedWood = Stream.of(
                MnogiiItems.SEAWEED_WOOD_PLANKS, MnogiiItems.SEAWEED_WOOD_WOOD,
                MnogiiItems.SEAWEED_WOOD_SLAB,   MnogiiItems.SEAWEED_WOOD_STAIRS
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(seaweedWood, Component.translatable("jei.mnogii.seaweed_wood.info"));

        List<ItemStack> curedWood = Stream.of(
                MnogiiItems.CURED_WOOD_LOG,  MnogiiItems.CURED_WOOD_FENCE,
                MnogiiItems.CURED_WOOD_PLANKS, MnogiiItems.CURED_WOOD_WOOD,
                MnogiiItems.CURED_WOOD_PRESSURE_PLATE
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(curedWood, Component.translatable("jei.mnogii.cured_wood.info"));

        List<ItemStack> iceWood = Stream.of(
                MnogiiItems.ICE_PLANKS, MnogiiItems.ICE_WOOD,
                MnogiiItems.ICE_SLAB,   MnogiiItems.ICE_STAIRS,
                MnogiiItems.ICE_FENCE,  MnogiiItems.ICE_PRESSURE_PLATE
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(iceWood, Component.translatable("jei.mnogii.ice_wood.info"));

        List<ItemStack> blackStone = Stream.of(
                MnogiiItems.BLACK_STONE_BRICK, MnogiiItems.BLACK_STONE_SLAB,
                MnogiiItems.BLACK_STONE_STAIRS, MnogiiItems.BLACK_STONE_FENCE,
                MnogiiItems.BLACK_STONE_LAMP, MnogiiItems.BLACK_STONE_PRESSURE_PLATE
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(blackStone, Component.translatable("jei.mnogii.black_stone.info"));

        List<ItemStack> lightGrayStone = Stream.of(
                MnogiiItems.LIGHT_GRAY_STONE,
                MnogiiItems.LIGHT_GRAY_STONE_BRICK,
                MnogiiItems.LIGHT_GRAY_TOA_STONE_SLAB,
                MnogiiItems.LIGHT_GRAY_TOA_STONE_STAIRS,
                MnogiiItems.LIGHTGRAYSTONE_FENCE
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(lightGrayStone, Component.translatable("jei.mnogii.light_gray_stone.info"));

        // ---- Toa Mata armour sets ----
        registration.addItemStackInfo(
                List.of(
                        new ItemStack(MnogiiItems.BOOTS_FIRE.get()),
                        new ItemStack(MnogiiItems.CHESTPLATE_FIRE.get()),
                        new ItemStack(MnogiiItems.LEGGINGS_FIRE.get())
                ),
                Component.translatable("jei.mnogii.armor.fire.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(MnogiiItems.BOOTS_WATER.get()),
                        new ItemStack(MnogiiItems.CHESTPLATE_WATER.get()),
                        new ItemStack(MnogiiItems.LEGGINGS_WATER.get())
                ),
                Component.translatable("jei.mnogii.armor.water.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(MnogiiItems.BOOTS_AIR.get()),
                        new ItemStack(MnogiiItems.CHESTPLATE_AIR.get()),
                        new ItemStack(MnogiiItems.LEGGINGS_AIR.get())
                ),
                Component.translatable("jei.mnogii.armor.air.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(MnogiiItems.BOOTS_EARTH.get()),
                        new ItemStack(MnogiiItems.CHESTPLATE_EARTH.get()),
                        new ItemStack(MnogiiItems.LEGGINGS_EARTH.get())
                ),
                Component.translatable("jei.mnogii.armor.earth.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(MnogiiItems.BOOTS_ICE.get()),
                        new ItemStack(MnogiiItems.CHESTPLATE_ICE.get()),
                        new ItemStack(MnogiiItems.LEGGINGS_ICE.get())
                ),
                Component.translatable("jei.mnogii.armor.ice.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(MnogiiItems.BOOTS_STONE.get()),
                        new ItemStack(MnogiiItems.CHESTPLATE_STONE.get()),
                        new ItemStack(MnogiiItems.LEGGINGS_STONE.get())
                ),
                Component.translatable("jei.mnogii.armor.stone.info"));
    }
}
