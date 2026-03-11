package eastonium.mnogii.client.renderer;

import eastonium.mnogii.client.MorphRenderHelper;
import eastonium.mnogii.client.animator.PlayerMorphAnimator;
import eastonium.mnogii.morph.MorphState;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import mod.azure.azurelib.common.render.entity.state.AzEntityRenderState;
import net.minecraft.client.player.AbstractClientPlayer;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Renders the player body as a Matoran (or Toa) using AzureLib Geo.
 *
 * A single shared instance is created during EntityRenderersEvent.AddLayers
 * and reused for all morphed players.
 */
public class PlayerMorphGeoRenderer extends AzEntityRenderer<AbstractClientPlayer> {

    /** Client-side morph state cache: player UUID → current MorphState. */
    public static final Map<UUID, MorphState> MORPH_STATES = new HashMap<>();

    private static PlayerMorphGeoRenderer INSTANCE;

    public static void init(EntityRendererProvider.Context context) {
        INSTANCE = new PlayerMorphGeoRenderer(context);
    }

    public static PlayerMorphGeoRenderer get() { return INSTANCE; }

    private PlayerMorphGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<AbstractClientPlayer>builder(
                        PlayerMorphGeoRenderer::geoFor,
                        PlayerMorphGeoRenderer::textureFor
                )
                .setAnimatorProvider(PlayerMorphAnimator::new)
                .setShadowRadius(0.4f)
                .build(),
                context
        );
    }

    // ── Resource selectors ────────────────────────────────────────────────────

    private static String maskIdFor(AbstractClientPlayer player) {
        String id = MorphRenderHelper.getMaskId(player.getItemBySlot(EquipmentSlot.HEAD));
        return id != null ? id : "hau";
    }

    private static MorphState stateFor(AbstractClientPlayer player) {
        return MORPH_STATES.getOrDefault(player.getUUID(), MorphState.NONE);
    }

    private static ResourceLocation geoFor(AbstractClientPlayer player) {
        return MorphRenderHelper.geoLocation(stateFor(player), maskIdFor(player));
    }

    private static ResourceLocation textureFor(AbstractClientPlayer player) {
        return MorphRenderHelper.textureLocation(stateFor(player), maskIdFor(player));
    }

    // ── Render entry point called from PlayerMorphEventHandler ───────────────

    public void renderMorphed(AbstractClientPlayer player, float partialTick,
                              PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        AzEntityRenderState state = createRenderState();
        extractRenderState(player, state, partialTick);
        render(state, poseStack, bufferSource, packedLight);
    }
}
