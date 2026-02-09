package eastonium.nuicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.model.MaskModel;
import eastonium.nuicraft.client.model.NuiCraftModelLayers;
import eastonium.nuicraft.core.NuiCraftItems;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.HumanoidRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;

/**
 * Renders a 2D Bionicle mask flat in front of the player's face
 */
public class MaskRenderLayer<S extends HumanoidRenderState, M extends HumanoidModel<S>> extends RenderLayer<S, M> {
    private static final ResourceLocation MASK_TEXTURE = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "textures/entity/equipment/humanoid/nuicraft_mask.png");
    
    private final MaskModel maskModel;

    public MaskRenderLayer(RenderLayerParent<S, M> renderer, MaskModel maskModel) {
        super(renderer);
        this.maskModel = maskModel;
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, S state, float yRot, float xRot) {
        ItemStack headItem = state.headEquipment;
        if (headItem.isEmpty()) return;

        // Check if wearing any NuiCraft mask
        if (!isMask(headItem)) return;

        // Move to head position and copy its rotation (mask follows head look direction)
        this.getParentModel().head.translateAndRotate(poseStack);
        // In HumanoidModel head-local space: face is -Z (player looks toward -Z when facing camera).
        // Push mask out in front of face.
        poseStack.translate(0, 0, -1.5);
        
        // Render the 2D mask flat in front of face
        VertexConsumer vertexConsumer = buffer.getBuffer(RenderType.entityCutoutNoCull(MASK_TEXTURE));
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
