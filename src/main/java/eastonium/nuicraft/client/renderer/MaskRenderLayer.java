package eastonium.nuicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.model.MaskModel;
import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;

/**
 * Renders a 2D Bionicle mask flat in front of the player's face (vanilla-style, like bionicle_qfn).
 * Uses the same texture as each mask's item icon.
 */
public class MaskRenderLayer<S extends HumanoidRenderState, M extends HumanoidModel<S>> extends RenderLayer<S, M> {

    /** Per-mask texture (same path as item icon). */
    private static final Map<Item, ResourceLocation> MASK_TEXTURES = new HashMap<>();

    static {
        put(NuiCraftItems.MASK_MATA_GOLD.get(), "textures/item/masks/gold/mata_hau_gold.png");
        put(NuiCraftItems.MASK_MATA_KAKAMA.get(), "textures/item/masks/normal/mata_kakama.png");
        put(NuiCraftItems.MASK_MATA_PAKARI.get(), "textures/item/masks/normal/mata_pakari.png");
        put(NuiCraftItems.MASK_MATA_KAUKAU.get(), "textures/item/masks/normal/mata_kaukau.png");
        put(NuiCraftItems.MASK_MATA_MIRU.get(), "textures/item/masks/normal/mata_miru.png");
        put(NuiCraftItems.MASK_MATA_HAU.get(), "textures/item/masks/normal/mata_hau.png");
        put(NuiCraftItems.MASK_MATA_AKAKU.get(), "textures/item/masks/normal/mata_akaku.png");
        put(NuiCraftItems.MASK_NUVA_KAKAMA.get(), "textures/item/masks/normal/nuva_kakama.png");
        put(NuiCraftItems.MASK_NUVA_PAKARI.get(), "textures/item/masks/normal/nuva_pakari.png");
        put(NuiCraftItems.MASK_NUVA_KAUKAU.get(), "textures/item/masks/normal/nuva_kaukau.png");
        put(NuiCraftItems.MASK_NUVA_MIRU.get(), "textures/item/masks/normal/nuva_miru.png");
        put(NuiCraftItems.MASK_NUVA_HAU.get(), "textures/item/masks/normal/nuva_hau.png");
        put(NuiCraftItems.MASK_NUVA_AKAKU.get(), "textures/item/masks/normal/nuva_akaku.png");
        put(NuiCraftItems.MASK_IGNIKA.get(), "textures/item/masks/normal/ignika_0.png");
        put(NuiCraftItems.MASK_VAHI.get(), "textures/item/masks/normal/vahi_0.png");
    }

    private static void put(Item item, String path) {
        MASK_TEXTURES.put(item, ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, path));
    }

    private static ResourceLocation getTexture(ItemStack stack) {
        return MASK_TEXTURES.getOrDefault(stack.getItem(),
                ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/item/masks/normal/mata_hau.png"));
    }

    private final MaskModel maskModel;

    public MaskRenderLayer(RenderLayerParent<S, M> renderer, MaskModel maskModel) {
        super(renderer);
        this.maskModel = maskModel;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, S state, float yRot, float xRot) {
        ItemStack headItem = state.headEquipment;
        if (headItem.isEmpty()) return;

        if (!isMask(headItem)) return;

        this.getParentModel().head.translateAndRotate(poseStack);
        poseStack.translate(0, 0, -1.5);

        ResourceLocation texture = getTexture(headItem);
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(texture));
        this.maskModel.root().render(poseStack, vertexConsumer, packedLight, OverlayTexture.NO_OVERLAY);
    }

    private boolean isMask(ItemStack stack) {
        return stack.is(NuiCraftItems.MASK_MATA_GOLD.get()) ||
               stack.is(NuiCraftItems.MASK_MATA_KAKAMA.get()) ||
               stack.is(NuiCraftItems.MASK_MATA_PAKARI.get()) ||
               stack.is(NuiCraftItems.MASK_MATA_KAUKAU.get()) ||
               stack.is(NuiCraftItems.MASK_MATA_MIRU.get()) ||
               stack.is(NuiCraftItems.MASK_MATA_HAU.get()) ||
               stack.is(NuiCraftItems.MASK_MATA_AKAKU.get()) ||
               stack.is(NuiCraftItems.MASK_NUVA_KAKAMA.get()) ||
               stack.is(NuiCraftItems.MASK_NUVA_PAKARI.get()) ||
               stack.is(NuiCraftItems.MASK_NUVA_KAUKAU.get()) ||
               stack.is(NuiCraftItems.MASK_NUVA_MIRU.get()) ||
               stack.is(NuiCraftItems.MASK_NUVA_HAU.get()) ||
               stack.is(NuiCraftItems.MASK_NUVA_AKAKU.get()) ||
               stack.is(NuiCraftItems.MASK_IGNIKA.get()) ||
               stack.is(NuiCraftItems.MASK_VAHI.get());
    }
}
