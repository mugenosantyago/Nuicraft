package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.animator.ToaAnimator;
import eastonium.nuicraft.entity.EntityToa;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * AzureLib Geo renderer for all six Toa variants (Tahu, Gali, Lewa, Onua, Pohatu, Kopaka).
 *
 * Model  : geo/entity/toa_{name}.geo.json
 * Texture: textures/entity/toa_{name}.png
 * Anim   : animations/entity/toa_{name}.animation.json
 */
public class ToaGeoRenderer extends AzEntityRenderer<EntityToa> {

    public ToaGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityToa>builder(
                        ToaGeoRenderer::geoFor,
                        ToaGeoRenderer::textureFor
                )
                .setAnimatorProvider(ToaAnimator::new)
                .setShadowRadius(0.5f)
                .build(),
                context
        );
    }

    private static ResourceLocation geoFor(EntityToa toa) {
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID,
                "geo/entity/" + toa.getVariant().getTextureName() + ".geo.json"
        );
    }

    private static ResourceLocation textureFor(EntityToa toa) {
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID,
                "textures/entity/" + toa.getVariant().getTextureName() + ".png"
        );
    }
}
