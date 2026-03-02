package eastonium.nuicraft.mixin;

import net.minecraft.client.model.geom.ModelPart;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

/**
 * Prevents crashes from mods that request a named child part that doesn't
 * exist on a ModelPart (NoSuchElementException: Can't find part ...).
 *
 * This commonly occurs alongside the missing-layer crash fixed in
 * MixinEntityModelSet: after bakeLayer returns an empty ModelPart, the mod's
 * model constructor tries to navigate child parts that don't exist.
 *
 * Instead of throwing, this mixin logs a warning and returns an empty
 * ModelPart so the renderer can still initialise.
 */
@Mixin(ModelPart.class)
public class MixinModelPart {

    @Shadow private Map<String, ModelPart> children;

    @Inject(method = "getChild", at = @At("HEAD"), cancellable = true)
    private void nuicraft_handleMissingChild(String name, CallbackInfoReturnable<ModelPart> cir) {
        if (!this.children.containsKey(name)) {
            LoggerFactory.getLogger("NuiCraft").warn(
                "ModelPart.getChild('{}') called but that part does not exist — returning empty ModelPart. " +
                "This is a bug in the mod that owns this model.", name);
            cir.setReturnValue(new ModelPart(List.of(), Map.of()));
        }
    }
}
