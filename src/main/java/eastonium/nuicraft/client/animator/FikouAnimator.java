package eastonium.nuicraft.client.animator;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntityFikou;
import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class FikouAnimator extends AzEntityAnimator<EntityFikou> {

    private static final ResourceLocation ANIMATIONS = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "animations/entity/fikou.animation.json");

    @Override
    public void registerControllers(AzAnimationControllerContainer<EntityFikou> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(EntityFikou animatable) {
        return ANIMATIONS;
    }
}
