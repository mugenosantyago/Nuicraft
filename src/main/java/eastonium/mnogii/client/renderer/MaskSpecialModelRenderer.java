package eastonium.mnogii.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import eastonium.mnogii.Mnogii;
import mod.azure.azurelib.common.cache.object.GeoCube;
import mod.azure.azurelib.common.cache.object.GeoQuad;
import mod.azure.azurelib.common.cache.object.GeoVertex;
import mod.azure.azurelib.common.model.AzBakedModel;
import mod.azure.azurelib.common.model.AzBone;
import mod.azure.azurelib.common.model.cache.AzBakedModelCache;
import mod.azure.azurelib.common.util.client.RenderUtils;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import org.joml.Matrix4f;
import org.joml.Vector3f;
import org.joml.Vector4f;

import java.util.Set;

/**
 * Renders an AzureLib geo mask model as a Minecraft 1.21.8 SpecialModelRenderer.
 * Used by the minecraft:special item model type so masks render as 3D models
 * both when held in hand and displayed in inventory/JEI.
 */
public class MaskSpecialModelRenderer implements SpecialModelRenderer<Void> {

    /**
     * Base scale applied to all geo item models in GUI slots.
     * After fixing the armor geo pivot to [0,0,0], mask cubes span
     * Bedrock y=0 (chin) to ~y=9.5 (crown) = 0–0.594 block units.
     * Scale 1.5 expands that to ~0.89 units, nearly filling a 1-unit slot.
     */
    private static final float DEFAULT_SCALE = 1.5f;

    private final ResourceLocation geoPath;
    private final ResourceLocation texturePath;
    /** Per-model scale multiplier, applied on top of DEFAULT_SCALE. */
    private final float scale;
    /**
     * Y translate (in pre-scale block units) that centres the model in the GUI slot.
     * For masks: Mask Y centre ≈ 0.297 blocks → -0.3 shifts the mask centred to y=0.
     * For items already centred at the origin (e.g. kohlii_stick): pass 0.0.
     */
    private final float centerY;

    private final Matrix4f poseStateCache = new Matrix4f();
    private final Vector3f normalScratch  = new Vector3f();
    private final Vector4f quadPosition   = new Vector4f();

    public MaskSpecialModelRenderer(ResourceLocation geoPath, ResourceLocation texturePath, float scale, float centerY) {
        this.geoPath     = geoPath;
        this.texturePath = texturePath;
        this.scale       = scale;
        this.centerY     = centerY;
    }

    @Override
    public void render(
        @Nullable Void data,
        ItemDisplayContext context,
        PoseStack poseStack,
        MultiBufferSource source,
        int light,
        int overlay,
        boolean hasFoil
    ) {
        AzBakedModel model = AzBakedModelCache.getInstance().getNullable(geoPath);
        if (model == null) return;

        RenderType renderType = RenderType.entityCutoutNoCull(texturePath);
        VertexConsumer buffer = source.getBuffer(renderType);

        poseStack.pushPose();
        // Apply base scale + centering translate, then the per-model scale multiplier.
        // The extra scale is applied last (innermost) so it enlarges around the
        // already-centred origin rather than shifting the mask off-screen.
        float s = DEFAULT_SCALE * scale;
        poseStack.scale(s, s, s);
        poseStack.translate(0, CENTER_TRANSLATE_Y / scale, 0);

        for (AzBone bone : model.getTopLevelBones()) {
            renderBoneRecursively(poseStack, buffer, bone, light, overlay);
        }

        poseStack.popPose();
    }

    private void renderBoneRecursively(PoseStack poseStack, VertexConsumer buffer, AzBone bone, int light, int overlay) {
        if (bone.isHidden()) return;

        poseStack.pushPose();
        RenderUtils.prepMatrixForBone(poseStack, bone);

        for (GeoCube cube : bone.getCubes()) {
            poseStack.pushPose();
            renderCube(poseStack, buffer, cube, light, overlay);
            poseStack.popPose();
        }

        if (!bone.isHidingChildren()) {
            for (AzBone child : bone.getChildBones()) {
                renderBoneRecursively(poseStack, buffer, child, light, overlay);
            }
        }

        poseStack.popPose();
    }

    private void renderCube(PoseStack poseStack, VertexConsumer buffer, GeoCube cube, int light, int overlay) {
        RenderUtils.translateToPivotPoint(poseStack, cube);
        RenderUtils.rotateMatrixAroundCube(poseStack, cube);
        RenderUtils.translateAwayFromPivotPoint(poseStack, cube);

        var normalisedPoseState = poseStack.last().normal();
        var poseState = poseStateCache.set(poseStack.last().pose());

        for (GeoQuad quad : cube.quads()) {
            if (quad == null) continue;

            normalScratch.set(quad.normal());
            normalisedPoseState.transform(normalScratch);
            RenderUtils.fixInvertedFlatCube(cube, normalScratch);

            for (GeoVertex vertex : quad.vertices()) {
                var pos = vertex.position();
                var vec = poseState.transform(quadPosition.set(pos.x(), pos.y(), pos.z(), 1.0f));
                buffer.addVertex(
                    vec.x(), vec.y(), vec.z(),
                    -1,                          // 0xFFFFFFFF — white, no tint
                    vertex.texU(), vertex.texV(),
                    overlay, light,
                    normalScratch.x(), normalScratch.y(), normalScratch.z()
                );
            }
        }
    }

    @Override
    public void getExtents(Set<Vector3f> extents) {
        extents.add(new Vector3f(-0.5f, -0.5f, -0.5f));
        extents.add(new Vector3f( 0.5f,  0.5f,  0.5f));
    }

    @Override
    public @Nullable Void extractArgument(ItemStack stack) {
        return null;
    }

    // -------------------------------------------------------------------------
    // Unbaked — codec-driven registration for minecraft:special item models
    // -------------------------------------------------------------------------

    public record Unbaked(ResourceLocation geo, ResourceLocation texture, float scale)
            implements SpecialModelRenderer.Unbaked {

        public static final MapCodec<Unbaked> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                ResourceLocation.CODEC.fieldOf("geo").forGetter(Unbaked::geo),
                ResourceLocation.CODEC.fieldOf("texture").forGetter(Unbaked::texture),
                Codec.FLOAT.optionalFieldOf("scale", 1.0f).forGetter(Unbaked::scale)
            ).apply(instance, Unbaked::new)
        );

        /** Type ID used in item model JSON: {@code "type": "mnogii:mask_geo"} */
        public static final ResourceLocation TYPE_ID =
            ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "mask_geo");

        @Override
        public @Nullable SpecialModelRenderer<?> bake(EntityModelSet modelSet) {
            return new MaskSpecialModelRenderer(geo, texture, scale);
        }

        @Override
        public MapCodec<? extends SpecialModelRenderer.Unbaked> type() {
            return CODEC;
        }
    }
}
