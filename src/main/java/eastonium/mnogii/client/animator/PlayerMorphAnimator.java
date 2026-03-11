package eastonium.mnogii.client.animator;

import eastonium.mnogii.client.MorphRenderHelper;
import eastonium.mnogii.client.renderer.PlayerMorphGeoRenderer;
import eastonium.mnogii.morph.MorphState;
import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Drives the idle animation for a morphed player.
 * Selects the animation file based on which mask the player is wearing.
 */
public class PlayerMorphAnimator extends AzEntityAnimator<AbstractClientPlayer> {

    @Override
    public void registerControllers(AzAnimationControllerContainer<AbstractClientPlayer> container) {
        container.add(AzAnimationController.builder(this, "morph_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(AbstractClientPlayer player) {
        String maskId = MorphRenderHelper.getMaskId(player.getItemBySlot(EquipmentSlot.HEAD));
        if (maskId == null) maskId = "hau";
        MorphState state = PlayerMorphGeoRenderer.MORPH_STATES.getOrDefault(
                player.getUUID(), MorphState.NONE);
        return MorphRenderHelper.animationLocation(state, maskId);
    }

    /** Dispatches the looping idle command for a morphed player. */
    public static void sendIdleCommand(AbstractClientPlayer player) {
        mod.azure.azurelib.common.animation.dispatch.command.AzCommand
                .create("morph_controller", "idle",
                        mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors.LOOP)
                .sendForEntity(player);
    }
}
