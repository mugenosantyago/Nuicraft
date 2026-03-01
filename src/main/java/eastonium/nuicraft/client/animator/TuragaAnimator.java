package eastonium.nuicraft.client.animator;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntityTuraga;
import mod.azure.azurelib.common.animation.controller.AzAnimationController;
import mod.azure.azurelib.common.animation.controller.AzAnimationControllerContainer;
import mod.azure.azurelib.common.animation.dispatch.command.AzCommand;
import mod.azure.azurelib.common.animation.impl.AzEntityAnimator;
import mod.azure.azurelib.common.animation.play_behavior.AzPlayBehaviors;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

/**
 * Drives Turaga animations on two independent controllers:
 *
 *  base_controller    — looping idle / walk, driven by movement state changes.
 *  ambient_controller — one-shot wave animation fired occasionally when idle.
 *
 * The animation file is selected dynamically from the turaga's geoId.
 */
public class TuragaAnimator extends AzEntityAnimator<EntityTuraga> {

    @Override
    public void registerControllers(AzAnimationControllerContainer<EntityTuraga> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
        // pose_controller sits between base and ambient so it overrides idle/walk arm positions
        // but the wave (ambient_controller, registered last) can still override it.
        container.add(AzAnimationController.builder(this, "pose_controller").build());
        container.add(AzAnimationController.builder(this, "ambient_controller").build());
    }

    @Override
    public @NotNull ResourceLocation getAnimationLocation(EntityTuraga entity) {
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID,
                "animations/entity/" + entity.getTuragaType().getGeoId() + ".animation.json"
        );
    }

    /**
     * Called from EntityTuraga.tick() on movement state change.
     * Sends "walk" when moving horizontally, "idle" when still.
     */
    public static void sendMovementCommand(EntityTuraga entity) {
        boolean moving = entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
        String anim = moving ? "walk" : "idle";
        AzCommand.create("base_controller", anim, AzPlayBehaviors.LOOP)
                .sendForEntity(entity);
    }

    /**
     * Called once from EntityTuraga.tick() to start the holding-staff pose loop.
     * The pose_controller runs between base and ambient so it overrides idle/walk
     * arm positions while still being overridden by the wave animation.
     * Only models that have a "pose" animation (matatu_turaga) are affected;
     * models without one (rau_turaga) will silently ignore the missing animation.
     */
    public static void sendPoseCommand(EntityTuraga entity) {
        AzCommand.create("pose_controller", "pose", AzPlayBehaviors.LOOP)
                .sendForEntity(entity);
    }

    /**
     * Called from EntityTuraga.tick() occasionally while idle.
     * Plays the wave animation once on the ambient controller.
     */
    public static void sendWaveCommand(EntityTuraga entity) {
        AzCommand.create("ambient_controller", "wave", AzPlayBehaviors.PLAY_ONCE)
                .sendForEntity(entity);
    }
}
