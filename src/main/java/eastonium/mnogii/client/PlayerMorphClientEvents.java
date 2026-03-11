package eastonium.mnogii.client;

import eastonium.mnogii.client.animator.PlayerMorphAnimator;
import eastonium.mnogii.client.renderer.PlayerMorphGeoRenderer;
import eastonium.mnogii.morph.MorphState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ClientPlayerNetworkEvent;
import net.neoforged.neoforge.client.event.RenderPlayerEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;

/**
 * Client-only event handlers for the morph system — extracted from PlayerMorphEventHandler so
 * that the server never loads this class (no client-only imports on the server side).
 */
public class PlayerMorphClientEvents {

    @SubscribeEvent
    public static void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null) return;
        int entityId = event.getRenderState().id;
        if (!(mc.level.getEntity(entityId) instanceof AbstractClientPlayer player)) return;

        MorphState state = PlayerMorphGeoRenderer.MORPH_STATES.getOrDefault(
                player.getUUID(), MorphState.NONE);

        if (state == MorphState.NONE) return;
        if (MorphRenderHelper.getMaskId(player.getItemBySlot(EquipmentSlot.HEAD)) == null) return;

        event.setCanceled(true);

        PlayerMorphGeoRenderer renderer = PlayerMorphGeoRenderer.get();
        if (renderer == null) return;

        renderer.renderMorphed(
                player,
                event.getPartialTick(),
                event.getPoseStack(),
                event.getMultiBufferSource(),
                event.getPackedLight()
        );
    }

    @SubscribeEvent
    public static void onClientPlayerTick(PlayerTickEvent.Post event) {
        if (!(event.getEntity() instanceof AbstractClientPlayer player)) return;
        if (!player.level().isClientSide()) return;
        MorphState state = PlayerMorphGeoRenderer.MORPH_STATES.getOrDefault(
                player.getUUID(), MorphState.NONE);
        if (state == MorphState.NONE) return;
        if (player.tickCount % 20 == 0) {
            PlayerMorphAnimator.sendIdleCommand(player);
        }
    }

    @SubscribeEvent
    public static void onClientLogout(ClientPlayerNetworkEvent.LoggingOut event) {
        PlayerMorphGeoRenderer.MORPH_STATES.clear();
    }
}
