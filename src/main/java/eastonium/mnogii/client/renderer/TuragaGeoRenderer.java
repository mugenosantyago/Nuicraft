package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.animator.TuragaAnimator;
import eastonium.mnogii.entity.EntityTuraga;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * AzureLib Geo renderer for all six Turaga of the Mata series.
 *
 * Model  : geo/entity/{geoId}.geo.json  (matatu_turaga or rau_turaga)
 * Texture: textures/entity/{texturePath}.png  (e.g. matatu_turaga/ta.png)
 */
public class TuragaGeoRenderer extends AzEntityRenderer<EntityTuraga> {

    public TuragaGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityTuraga>builder(
                        TuragaGeoRenderer::geoFor,
                        TuragaGeoRenderer::textureFor
                )
                .setAnimatorProvider(TuragaAnimator::new)
                .setShadowRadius(0.35f)
                .setScale(0.8f)
                .build(),
                context
        );
    }

    private static ResourceLocation geoFor(EntityTuraga turaga) {
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID,
                "geo/entity/" + turaga.getTuragaType().getGeoId() + ".geo.json"
        );
    }

    private static ResourceLocation textureFor(EntityTuraga turaga) {
        return ResourceLocation.fromNamespaceAndPath(
                Mnogii.MODID,
                "textures/entity/" + turaga.getTuragaType().getTexturePath() + ".png"
        );
    }
}
