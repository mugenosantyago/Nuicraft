package eastonium.nuicraft.client.animator;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntitySpiderFikou;
import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class SpiderFikouAnimator extends AzEntityAnimator<EntitySpiderFikou> {

    /** Reuses the same animation file as the regular Fikou — both share the same rig. */
    private static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "animations/entity/fikou.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<EntitySpiderFikou> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(EntitySpiderFikou animatable) {
        return ANIMATIONS;
    }

    public static void sendMovementCommand(EntitySpiderFikou entity, boolean moving) {
        AzCommand.create("base_controller", moving ? "walk" : "idle", AzPlayBehaviors.LOOP)
                .sendForEntity(entity);
    }
}
