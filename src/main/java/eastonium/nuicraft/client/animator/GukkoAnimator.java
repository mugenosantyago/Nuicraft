package eastonium.nuicraft.client.animator;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntityGukko;
import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GukkoAnimator extends AzEntityAnimator<EntityGukko> {

    private static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "animations/entity/gukko.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<EntityGukko> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
        container.add(AzAnimationController.builder(this, "attack_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(EntityGukko entity) {
        return ANIMATIONS;
    }

    public static void sendMovementCommand(EntityGukko entity) {
        boolean moving = entity.getDeltaMovement().lengthSqr() > 1.0E-5;
        AzCommand.create("base_controller", moving ? "walk" : "idle", AzPlayBehaviors.LOOP)
                .sendForEntity(entity);
    }

    public static void sendAttackCommand(EntityGukko entity) {
        AzCommand.create("attack_controller", "attack", AzPlayBehaviors.PLAY_ONCE)
                .sendForEntity(entity);
    }
}
