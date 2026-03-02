package eastonium.nuicraft.jei;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.core.NuiCraftBlocks;
import eastonium.nuicraft.core.NuiCraftItems;
import eastonium.nuicraft.recipe.PurifyingRecipe;
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
 * JEI plugin for NuiCraft. Registers mod items with JEI (item list + info) and ensures
 * data-pack recipes are available for recipe lookup.
 */
@JeiPlugin
public class NuiCraftJeiPlugin implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(new PurifierRecipeCategory(guiHelper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(
                new ItemStack(NuiCraftBlocks.PURIFIER.get()),
                PurifierRecipeCategory.TYPE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        // Purifier recipes — built inline to match the data-pack JSON recipes
        registration.addRecipes(PurifierRecipeCategory.TYPE, List.of(
                new PurifyingRecipe(
                        "",
                        CookingBookCategory.MISC,
                        Ingredient.of(NuiCraftItems.MOLTEN_PROTODERMIS_BUCKET.get()),
                        new ItemStack(NuiCraftItems.PURE_MOLTEN_PROTODERMIS_BUCKET.get()),
                        1.0f,
                        200)
        ));

        // ---- Mata masks ----
        List<ItemStack> masks = Stream.of(
                NuiCraftItems.MASK_MATA_HAU,
                NuiCraftItems.MASK_MATA_KAUKAU,
                NuiCraftItems.MASK_MATA_MIRU,
                NuiCraftItems.MASK_MATA_KAKAMA,
                NuiCraftItems.MASK_MATA_PAKARI,
                NuiCraftItems.MASK_MATA_AKAKU,
                NuiCraftItems.MASK_MATA_HUNA,
                NuiCraftItems.MASK_MATA_MAHIKI,
                NuiCraftItems.MASK_MATA_MATATU,
                NuiCraftItems.MASK_MATA_KOMAU,
                NuiCraftItems.MASK_MATA_RARU,
                NuiCraftItems.MASK_MATA_RURU
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(masks, Component.translatable("jei.nuicraft.mask.info"));

        // Mask Forge
        registration.addItemStackInfo(
                List.of(new ItemStack(NuiCraftBlocks.MASK_FORGE.get())),
                Component.translatable("jei.nuicraft.mask_forge.info"));

        // ---- Kanoka discs ----
        // General throwable discs
        registration.addItemStackInfo(
                List.of(
                        new ItemStack(NuiCraftItems.KANOKA_BAMBOO.get()),
                        new ItemStack(NuiCraftItems.KANOKA_DISK_TA.get()),
                        new ItemStack(NuiCraftItems.KANOKA_DISK_GA.get()),
                        new ItemStack(NuiCraftItems.KANOKA_DISK_LE.get()),
                        new ItemStack(NuiCraftItems.KANOKA_DISK_PO.get()),
                        new ItemStack(NuiCraftItems.KANOKA_DISK_ONU.get()),
                        new ItemStack(NuiCraftItems.KANOKA_DISK_KO.get())
                ),
                Component.translatable("jei.nuicraft.disc.info"));

        // Heatstone Lighter
        registration.addItemStackInfo(
                List.of(new ItemStack(NuiCraftItems.HEATSTONE_LIGHTER.get())),
                Component.translatable("jei.nuicraft.heatstone_lighter.info"));

        // Koro-specific Kanoka disks
        List<ItemStack> koroDiscs = Stream.of(
                NuiCraftItems.KANOKA_DISK_TA,
                NuiCraftItems.KANOKA_DISK_GA,
                NuiCraftItems.KANOKA_DISK_LE,
                NuiCraftItems.KANOKA_DISK_ONU,
                NuiCraftItems.KANOKA_DISK_PO,
                NuiCraftItems.KANOKA_DISK_KO,
                NuiCraftItems.KANOKA_OF_TIME
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(koroDiscs, Component.translatable("jei.nuicraft.disc.koro.info"));

        // ---- Toa Mata weapons ----
        List<ItemStack> toaWeapons = Stream.of(
                NuiCraftItems.FIRE_STAFF,
                NuiCraftItems.FIRE_SWORD,
                NuiCraftItems.KAUKAU_STAFF,
                NuiCraftItems.WATER_HOOKS,
                NuiCraftItems.AIR_AXE,
                NuiCraftItems.ONUA_CLAWS,
                NuiCraftItems.ONUA_DRILL,
                NuiCraftItems.POHATU_HANDS,
                NuiCraftItems.ICE_SWORD,
                NuiCraftItems.ICE_PICKAXE,
                NuiCraftItems.ICE_SHIELD,
                NuiCraftItems.TOA_TRIDENT
        ).map(d -> new ItemStack(d.get())).toList();
        registration.addItemStackInfo(toaWeapons, Component.translatable("jei.nuicraft.toa_weapons.info"));

        // ---- Protodermis tools ----
        registration.addItemStackInfo(
                List.of(
                        new ItemStack(NuiCraftItems.PROTODERMIS_SWORD.get()),
                        new ItemStack(NuiCraftItems.PROTODERMIS_PICK.get()),
                        new ItemStack(NuiCraftItems.PROTODERMIS_AXE.get())
                ),
                Component.translatable("jei.nuicraft.protodermis_tools.info"));

        // ---- Toa Mata armour sets ----
        registration.addItemStackInfo(
                List.of(
                        new ItemStack(NuiCraftItems.BOOTS_FIRE.get()),
                        new ItemStack(NuiCraftItems.CHESTPLATE_FIRE.get()),
                        new ItemStack(NuiCraftItems.LEGGINGS_FIRE.get())
                ),
                Component.translatable("jei.nuicraft.armor.fire.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(NuiCraftItems.BOOTS_WATER.get()),
                        new ItemStack(NuiCraftItems.CHESTPLATE_WATER.get()),
                        new ItemStack(NuiCraftItems.LEGGINGS_WATER.get())
                ),
                Component.translatable("jei.nuicraft.armor.water.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(NuiCraftItems.BOOTS_AIR.get()),
                        new ItemStack(NuiCraftItems.CHESTPLATE_AIR.get()),
                        new ItemStack(NuiCraftItems.LEGGINGS_AIR.get())
                ),
                Component.translatable("jei.nuicraft.armor.air.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(NuiCraftItems.BOOTS_EARTH.get()),
                        new ItemStack(NuiCraftItems.CHESTPLATE_EARTH.get()),
                        new ItemStack(NuiCraftItems.LEGGINGS_EARTH.get())
                ),
                Component.translatable("jei.nuicraft.armor.earth.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(NuiCraftItems.BOOTS_ICE.get()),
                        new ItemStack(NuiCraftItems.CHESTPLATE_ICE.get()),
                        new ItemStack(NuiCraftItems.LEGGINGS_ICE.get())
                ),
                Component.translatable("jei.nuicraft.armor.ice.info"));

        registration.addItemStackInfo(
                List.of(
                        new ItemStack(NuiCraftItems.BOOTS_STONE.get()),
                        new ItemStack(NuiCraftItems.CHESTPLATE_STONE.get()),
                        new ItemStack(NuiCraftItems.LEGGINGS_STONE.get())
                ),
                Component.translatable("jei.nuicraft.armor.stone.info"));
    }
}
