package eastonium.nuicraft.jei;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.core.NuiCraftBlocks;
import eastonium.nuicraft.core.NuiCraftItems;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

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
    public void registerRecipes(IRecipeRegistration registration) {

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

    }
}
