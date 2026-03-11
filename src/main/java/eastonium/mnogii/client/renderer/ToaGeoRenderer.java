package eastonium.mnogii.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.animator.ToaAnimator;
import eastonium.mnogii.entity.EntityToa;
import mod.azure.azurelib.common.model.AzBone;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import mod.azure.azurelib.common.render.entity.state.AzEntityRenderState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * AzureLib Geo renderer for all six Toa variants (Tahu, Gali, Lewa, Onua, Pohatu, Kopaka).
 *
 * Model  : geo/entity/toa_{name}.geo.json
 * Texture: textures/entity/toa_{name}.png
 * Anim   : animations/entity/toa_{name}.animation.json
 *
 * Each Toa model contains all six mask bones (Hau, Miru, Kakama, Pakari, Akaku, Kaukau).
 * Before rendering, the five non-matching masks are hidden so only the correct one shows.
 */
public class ToaGeoRenderer extends AzEntityRenderer<EntityToa> {

    private static final List<String> ALL_MASK_BONES =
            List.of("Hau", "Miru", "Kakama", "Pakari", "Akaku", "Kaukau");

    public ToaGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityToa>builder(
                        ToaGeoRenderer::geoFor,
                        ToaGeoRenderer::textureFor
                )
                .setAnimatorProvider(ToaAnimator::new)
                .setShadowRadius(0.7f)
                .setScale(1.5f)
                .build(),
                context
        );
    }

    @Override
    public void render(@NotNull AzEntityRenderState renderState,
                       @NotNull PoseStack poseStack,
                       @NotNull MultiBufferSource bufferSource,
                       int packedLight) {
        @SuppressWarnings("unchecked")
        EntityToa toa = (EntityToa) renderState.entity;
        if (toa != null) {
            String activeMask = toa.getVariant().getMaskBone();
            var model = provider.provideBakedModel(toa, toa);
            if (model != null) {
                for (String boneName : ALL_MASK_BONES) {
                    AzBone bone = model.getBoneOrNull(boneName);
                    if (bone != null) {
                        boolean shouldHide = !boneName.equals(activeMask);
                        // Only write the field when it needs to change — avoids redundant
                        // work (including child-bone recursion inside setHidden) every frame.
                        if (bone.isHidden() != shouldHide) {
                            bone.setHidden(shouldHide);
                        }
                    }
                }
            }
        }
        super.render(renderState, poseStack, bufferSource, packedLight);
    }

    private static ResourceLocation geoFor(EntityToa toa) {
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID,
                "geo/entity/" + toa.getVariant().getTextureName() + ".geo.json"
        );
    }

    private static ResourceLocation textureFor(EntityToa toa) {
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID,
                "textures/entity/" + toa.getVariant().getTextureName() + ".png"
        );
    }
}
