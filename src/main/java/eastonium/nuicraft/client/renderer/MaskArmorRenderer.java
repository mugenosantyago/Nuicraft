package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.core.NuiCraftItems;
import mod.azure.azurelib.common.model.AzBone;
import mod.azure.azurelib.common.render.AzRendererPipelineContext;
import mod.azure.azurelib.common.render.armor.AzArmorRenderer;
import mod.azure.azurelib.common.render.armor.AzArmorRendererConfig;
import mod.azure.azurelib.common.render.armor.AzArmorRendererPipelineContext;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * AzureLib armor renderer for NuiCraft masks. Renders the mask geo on the head slot
 * and offsets the head bone so the mask sits in front of the face instead of on top.
 * Uses the same texture as each mask's item icon.
 */
public class MaskArmorRenderer extends AzArmorRenderer {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "geo/armor/mask.geo.json");

    /** Per-mask texture paths (same as item icon); fallback if unknown mask. */
    private static final Map<Item, ResourceLocation> MASK_TEXTURES = new HashMap<>();

    static {
        put(NuiCraftItems.MASK_MATA_GOLD.get(), "item/masks/gold/mata_hau_gold");
        put(NuiCraftItems.MASK_MATA_KAKAMA.get(), "item/masks/normal/mata_kakama");
        put(NuiCraftItems.MASK_MATA_PAKARI.get(), "item/masks/normal/mata_pakari");
        put(NuiCraftItems.MASK_MATA_KAUKAU.get(), "item/masks/normal/mata_kaukau");
        put(NuiCraftItems.MASK_MATA_MIRU.get(), "item/masks/normal/mata_miru");
        put(NuiCraftItems.MASK_MATA_HAU.get(), "item/masks/normal/mata_hau");
        put(NuiCraftItems.MASK_MATA_AKAKU.get(), "item/masks/normal/mata_akaku");
        put(NuiCraftItems.MASK_NUVA_KAKAMA.get(), "item/masks/normal/nuva_kakama");
        put(NuiCraftItems.MASK_NUVA_PAKARI.get(), "item/masks/normal/nuva_pakari");
        put(NuiCraftItems.MASK_NUVA_KAUKAU.get(), "item/masks/normal/nuva_kaukau");
        put(NuiCraftItems.MASK_NUVA_MIRU.get(), "item/masks/normal/nuva_miru");
        put(NuiCraftItems.MASK_NUVA_HAU.get(), "item/masks/normal/nuva_hau");
        put(NuiCraftItems.MASK_NUVA_AKAKU.get(), "item/masks/normal/nuva_akaku");
        put(NuiCraftItems.MASK_IGNIKA.get(), "item/masks/normal/ignika_0");
        put(NuiCraftItems.MASK_VAHI.get(), "item/masks/normal/vahi_0");
    }

    private static void put(Item item, String path) {
        MASK_TEXTURES.put(item, ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/" + path));
    }

    /** Get the texture for the given mask stack (same as item icon). */
    static ResourceLocation getTextureForStack(@Nullable Entity entity, ItemStack stack) {
        return MASK_TEXTURES.getOrDefault(stack.getItem(),
                ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/item/masks/normal/mata_kaukau"));
    }

    /** Nudge mask forward in front of face (negative Z in model space). Units are 1/16 block. */
    private static final float MASK_FORWARD_OFFSET = -3f;
    /** No vertical offset - geo cube is already at face level (Y 21-28). */
    private static final float MASK_DOWN_OFFSET = 0f;

    public MaskArmorRenderer() {
        super(
                AzArmorRendererConfig.builder((e, s) -> GEO, MaskArmorRenderer::getTextureForStack)
                        .setPrerenderEntry(MaskArmorRenderer::pushMaskInFrontOfFace)
                        .setBoneTextureOverrideProvider(MaskArmorRenderer::maskTextureForBone)
                        .build()
        );
    }

    /** Use main texture from textureLocationProvider for head bone. */
    @Nullable
    private static ResourceLocation maskTextureForBone(AzBone bone) {
        return null;
    }

    private static AzRendererPipelineContext<UUID, ItemStack> pushMaskInFrontOfFace(
            AzRendererPipelineContext<UUID, ItemStack> context) {
        if (!(context instanceof AzArmorRendererPipelineContext armorContext)
                || armorContext.currentSlot() != EquipmentSlot.HEAD) {
            return context;
        }
        ItemStack stack = context.animatable();
        context.setTextureOverride(getTextureForStack(armorContext.currentEntity(), stack));
        AzBone head = armorContext.boneContext().head;
        if (head == null) {
            return context;
        }
        // Only adjust position so the quad stays at head/face. Do not change pivot (keeps cube in correct place).
        // Large down offset would move the quad to waist/legs and appear as "flat thing in front of pants".
        head.updatePosition(
                head.getPosX(),
                head.getPosY() - MASK_DOWN_OFFSET,
                head.getPosZ() + MASK_FORWARD_OFFSET
        );
        return context;
    }
}
