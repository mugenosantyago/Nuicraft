package eastonium.mnogii.client.animator;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.entity.EntityToa;
import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class ToaAnimator extends AzEntityAnimator<EntityToa> {

    @Override
    public void registerControllers(AzAnimationControllerContainer<EntityToa> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
        container.add(AzAnimationController.builder(this, "attack_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(EntityToa entity) {
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID,
                "animations/entity/" + entity.getVariant().getTextureName() + ".animation.json"
        );
    }

    public static void sendMovementCommand(EntityToa entity) {
        boolean moving = entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
        String anim = moving ? "walk" : "idle";
        AzCommand.create("base_controller", anim, AzPlayBehaviors.LOOP)
                .sendForEntity(entity);
    }

    public static void sendAttackCommand(EntityToa entity) {
        AzCommand.create("attack_controller", "attack", AzPlayBehaviors.PLAY_ONCE)
                .sendForEntity(entity);
    }
}
