package eastonium.mnogii.client.animator;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.entity.EntityMuaka;
import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class MuakaAnimator extends AzEntityAnimator<EntityMuaka> {

    private static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "animations/entity/muaka.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<EntityMuaka> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
        container.add(AzAnimationController.builder(this, "attack_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(EntityMuaka entity) {
        return ANIMATIONS;
    }

    public static void sendMovementCommand(EntityMuaka entity) {
        boolean moving = entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
        AzCommand.create("base_controller", moving ? "walk" : "idle", AzPlayBehaviors.LOOP)
                .sendForEntity(entity);
    }

    public static void sendAttackCommand(EntityMuaka entity) {
        AzCommand.create("attack_controller", "attack", AzPlayBehaviors.PLAY_ONCE)
                .sendForEntity(entity);
    }
}
