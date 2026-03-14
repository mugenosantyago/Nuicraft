package eastonium.mnogii.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.MaskArmorRendererRegistry;
import mod.azure.azurelib.common.render.armor.AzArmorModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.state.PlayerRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ARGB;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DyedItemColor;

/**
 * Renders 3D Kanohi mask geo models on the player's head.
 *
 * This runs as a standard {@link RenderLayer}, so it fires unconditionally every frame
 * regardless of whether AzureLib's {@code MixinHumanoidArmorLayer} injection succeeds.
 * That mixin can fail silently in standalone mode (no other mods present); this layer is
 * the reliable fallback that makes rendering work everywhere.
 *
 * Masks must NOT also be registered in AzureLib's {@code AzArmorRendererRegistry} —
 * Nuicraft maintains its own renderer map in {@link MaskArmorRendererRegistry} to avoid
 * double-rendering in modpack environments where the mixin would also fire.
 */
public class MaskRenderLayer extends RenderLayer<PlayerRenderState, PlayerModel> {

    public MaskRenderLayer(RenderLayerParent<PlayerRenderState, PlayerModel> parent) {
        super(parent);
    }

    @Override
    public void render(
            PoseStack poseStack,
            MultiBufferSource bufferSource,
            int packedLight,
            PlayerRenderState state,
            float yRot,
            float xRot
    ) {
        ItemStack headItem = state.headEquipment;
        if (headItem.isEmpty()) return;

        var renderer = MaskArmorRendererRegistry.getOrCreate(headItem.getItem());
        if (renderer == null) return;

        try {
            PlayerModel playerModel = this.getParentModel();

            @SuppressWarnings("unchecked")
            HumanoidModel<PlayerRenderState> typedArmor =
                    (HumanoidModel<PlayerRenderState>) renderer.rendererPipeline().armorModel();

            renderer.prepForRenderWithoutEntity(headItem, EquipmentSlot.HEAD, playerModel);
            playerModel.copyPropertiesTo(typedArmor);

            int dyeColor = headItem.is(ItemTags.DYEABLE)
                    ? ARGB.opaque(DyedItemColor.getOrDefault(headItem, -6265536))
                    : -1;

            AzArmorModel armorModel = renderer.rendererPipeline().armorModel();
            armorModel.azRenderToBuffer(poseStack, null, packedLight, OverlayTexture.NO_OVERLAY, dyeColor);

        } catch (Exception e) {
            Mnogii.LOGGER.error("Nuicraft: error rendering mask layer for {}", headItem.getItem(), e);
        }
    }
}
