package eastonium.mnogii.mixin;

import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;
import java.util.Map;

/**
 * Prevents crashes caused by mods that reference a model layer location in
 * their EntityRenderer/PlayerLayer constructors without having registered the
 * corresponding LayerDefinition.
 *
 * Instead of throwing IllegalArgumentException ("No model for layer ..."),
 * this mixin logs a warning and returns an empty ModelPart so the renderer
 * can still be created (the missing layer simply renders nothing).
 */
@Mixin(EntityModelSet.class)
public class MixinEntityModelSet {

    @Shadow private Map<ModelLayerLocation, LayerDefinition> roots;

    @Inject(method = "bakeLayer", at = @At("HEAD"), cancellable = true)
    private void mnogii_handleMissingModelLayer(ModelLayerLocation location, CallbackInfoReturnable<ModelPart> cir) {
        if (!this.roots.containsKey(location)) {
            LoggerFactory.getLogger("Mnogii").warn(
                "Model layer '{}' is not registered — returning empty ModelPart. " +
                "This is a bug in the mod that owns this layer.", location);
            cir.setReturnValue(new ModelPart(List.of(), Map.of()));
        }
    }
}
