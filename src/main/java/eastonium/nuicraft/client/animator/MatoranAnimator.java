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
 * Drives Matoran animations on two independent controllers:
 *
 *  base_controller    — looping idle / walk, driven by movement state changes.
 *  ambient_controller — one-shot wave / work animations fired occasionally when idle.
 *
 * The animation file is selected dynamically from the matoran's mask, with a HAU fallback
 * for masks that don't yet have a converted geo+animation set.
 */
public class MatoranAnimator extends AzEntityAnimator<EntityMatoran> {

    private static final Set<String> IMPLEMENTED_MASKS = Set.of(
            "hau", "huna", "kakama", "kaukau", "miru", "pakari"
    );

    private static final String[] AMBIENT_ANIMS = {"wave", "work"};

    @Override
    public void registerControllers(AzAnimationControllerContainer<EntityMatoran> container) {
        container.add(AzAnimationController.builder(this, "base_controller").build());
        container.add(AzAnimationController.builder(this, "ambient_controller").build());
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
     * Called from EntityMatoran.tick() on movement state change.
     * Sends "walk" when moving horizontally, "idle" when still.
     */
    public static void sendMovementCommand(EntityMatoran entity) {
        boolean moving = entity.getDeltaMovement().horizontalDistanceSqr() > 1.0E-5;
        String anim = moving ? "walk" : "idle";
        AzCommand.create("base_controller", anim, AzPlayBehaviors.LOOP)
                .sendForEntity(entity);
    }

    /**
     * Called from EntityMatoran.tick() occasionally while idle.
     * Randomly picks "wave" or "work" and plays it once on the ambient controller.
     */
    public static void sendAmbientCommand(EntityMatoran entity) {
        String anim = AMBIENT_ANIMS[entity.getRandom().nextInt(AMBIENT_ANIMS.length)];
        AzCommand.create("ambient_controller", anim, AzPlayBehaviors.PLAY_ONCE)
                .sendForEntity(entity);
    }
}
