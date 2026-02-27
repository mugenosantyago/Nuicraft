package eastonium.nuicraft.client.animator;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntityMatoran;
import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import java.util.Set;

/**
 * Plays the idle animation embedded in each mask's .animation.json.
 *
 * The animation file is selected dynamically based on the matoran's current mask,
 * matching the same fallback logic used in MatoranGeoRenderer.
 *
 * Dispatch: EntityMatoran.tick() sends AzCommand.create("base_controller", "idle", LOOP)
 * every 20 ticks to keep the looping idle alive.
 */
public class MatoranAnimator extends AzEntityAnimator<EntityMatoran> {

    private static final Set<String> IMPLEMENTED_MASKS = Set.of(
            "hau", "huna", "kakama", "kaukau", "miru", "pakari"
    );

    @Override
    public void registerControllers(AzAnimationControllerContainer<EntityMatoran> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(EntityMatoran entity) {
        String maskId = entity.getMask().getId();
        if (!IMPLEMENTED_MASKS.contains(maskId)) maskId = "hau";
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID,
                "animations/entity/" + maskId + "_matoran.animation.json"
        );
    }

    /**
     * Called from EntityMatoran.tick() every 5 ticks.
     * Sends "walk" when the entity is moving horizontally, "idle" when still.
     */
    public static void sendMovementCommand(EntityMatoran entity) {
        boolean moving = entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
        String anim = moving ? "walk" : "idle";
        AzCommand.create("base_controller", anim, AzPlayBehaviors.LOOP)
                .sendForEntity(entity);
    }
}
