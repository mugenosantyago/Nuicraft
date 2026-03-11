package eastonium.mnogii.client.animator;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.entity.EntityFikou;
import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FikouAnimator extends AzEntityAnimator<EntityFikou> {

    private static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "animations/entity/fikou.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<EntityFikou> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(EntityFikou animatable) {
        return ANIMATIONS;
    }

    /**
     * Called from EntityFikou.tick() whenever the movement state changes.
     * Plays "walk" when moving, "idle" when still.
     */
    public static void sendMovementCommand(EntityFikou entity) {
        boolean moving = entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
        AzCommand.create("base_controller", moving ? "walk" : "idle", AzPlayBehaviors.LOOP)
                .sendForEntity(entity);
    }
}
