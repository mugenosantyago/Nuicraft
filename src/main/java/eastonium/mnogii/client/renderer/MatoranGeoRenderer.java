package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.animator.MatoranAnimator;
import eastonium.mnogii.entity.EntityMatoran;
import com.mojang.blaze3d.vertex.PoseStack;
import mod.azure.azurelib.common.model.AzBone;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import mod.azure.azurelib.common.render.entity.state.AzEntityRenderState;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

/**
 * AzureLib Geo renderer for Matoran NPCs.
 *
 * Base texture  : textures/entity/{mask}_matoran/{bodyColor}.png
 *                 where bodyColor = canonical koro color (red/blue/green/black/brown/white)
 * Mask override : textures/entity/{mask}_matoran/{maskColor}.png  — applied to the "head" bone
 * Feet override : textures/entity/{mask}_matoran/{feetColor}.png  — applied to foot bones
 *
 * Per-bone texture overrides use AzureLib's setBoneTextureOverrideProvider().
 * The current Matoran being rendered is stored in a static field (single render thread)
 * so the lambda can access entity data without being passed the entity directly.
 */
public class MatoranGeoRenderer extends AzEntityRenderer<EntityMatoran> {

    /** Mask IDs that have a geo.json + full texture set in resources. */
    private static final Set<String> IMPLEMENTED_MASKS = Set.of(
            "hau", "huna", "kakama", "kaukau", "miru", "pakari"
    );

    /** Set immediately before rendering each entity; read by boneTextureFor(). */
    private static EntityMatoran currentMatoran = null;

    /**
     * Renderer scale for adult Matoran. The Matoran model has a 0.5 bone scale baked into
     * every animation, so the effective visual scale = ADULT_SCALE × 0.5.
     * At 1.7 → effective 0.85, slightly larger than Turaga (renderer scale 0.8, no bone scale).
     */
    private static final float ADULT_SCALE = 1.7f;
    /** Baby Matoran are half the size of adults. */
    private static final float BABY_SCALE  = 0.5f;

    public MatoranGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityMatoran>builder(
                        MatoranGeoRenderer::geoFor,
                        MatoranGeoRenderer::bodyTextureFor
                )
                .setAnimatorProvider(MatoranAnimator::new)
                .setShadowRadius(0.5f)
                .setScale(ADULT_SCALE)
                .setBoneTextureOverrideProvider(MatoranGeoRenderer::boneTextureFor)
                .build(),
                context
        );
    }

    @Override
    public void render(AzEntityRenderState renderState,
                       PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        EntityMatoran entity = (EntityMatoran) renderState.entity;
        currentMatoran = entity;
        try {
            if (entity != null && entity.isBaby()) {
                // Babies render at BABY_SCALE on top of the config's ADULT_SCALE
                poseStack.pushPose();
                poseStack.scale(BABY_SCALE, BABY_SCALE, BABY_SCALE);
                super.render(renderState, poseStack, bufferSource, packedLight);
                poseStack.popPose();
            } else {
                super.render(renderState, poseStack, bufferSource, packedLight);
            }
        } finally {
            currentMatoran = null;
        }
    }

    // ---- Texture / geo selection ----

    private static String maskId(EntityMatoran matoran) {
        String id = matoran.getMask().getId();
        return IMPLEMENTED_MASKS.contains(id) ? id : "hau";
    }

    private static ResourceLocation geoFor(EntityMatoran matoran) {
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID,
                "geo/entity/" + maskId(matoran) + "_matoran.geo.json"
        );
    }

    /** Base body texture — always the canonical koro color. */
    private static ResourceLocation bodyTextureFor(EntityMatoran matoran) {
        return colorTexture(maskId(matoran),
                EntityMatoran.MatoranColor.forKoro(matoran.getKoro()));
    }

    /**
     * Per-bone texture override — must ALWAYS return a non-null texture.
     *
     * AzureLib's rendering pipeline does not reset the vertex consumer between sibling
     * bones: if a bone returns a texture override, subsequent bones that return null
     * inherit that override. Returning an explicit body texture for all "default" bones
     * prevents accent textures from bleeding into the torso, arms, and legs.
     *
     *  "head"                    → mask accent color
     *  "left_foot" / "right_foot"→ feet accent color
     *  everything else           → canonical koro body color (explicit, not null)
     */
    private static ResourceLocation boneTextureFor(AzBone bone) {
        EntityMatoran mat = currentMatoran;
        if (mat == null) return null;
        return switch (bone.getName()) {
            case "head"       -> colorTexture(maskId(mat), mat.getMaskColor());
            case "left_foot",
                 "right_foot" -> colorTexture(maskId(mat), mat.getFeetColor());
            default           -> bodyTextureFor(mat);
        };
    }

    private static ResourceLocation colorTexture(String maskId, EntityMatoran.MatoranColor color) {
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID,
                "textures/entity/" + maskId + "_matoran/" + color.getId() + ".png"
        );
    }
}
