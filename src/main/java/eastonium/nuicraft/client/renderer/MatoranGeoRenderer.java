package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.animator.MatoranAnimator;
import eastonium.nuicraft.entity.EntityMatoran;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

/**
 * AzureLib Geo renderer for Matoran NPCs.
 *
 * Model  : geo/entity/{mask}_matoran.geo.json
 * Texture: textures/entity/{mask}_matoran/{koro}.png
 *
 * Masks that already have converted geo files are listed in IMPLEMENTED_MASKS.
 * Any unimplemented mask falls back to the HAU model.
 */
public class MatoranGeoRenderer extends AzEntityRenderer<EntityMatoran> {

    /** Mask IDs that have a geo.json + full texture set ready in resources. */
    private static final Set<String> IMPLEMENTED_MASKS = Set.of(
            "hau", "huna", "kakama", "kaukau", "miru", "pakari"
    );

    public MatoranGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityMatoran>builder(
                        MatoranGeoRenderer::geoFor,
                        MatoranGeoRenderer::textureFor
                )
                .setAnimatorProvider(MatoranAnimator::new)
                .setShadowRadius(0.4f)
                .build(),
                context
        );
    }

    private static String maskId(EntityMatoran matoran) {
        String id = matoran.getMask().getId();
        return IMPLEMENTED_MASKS.contains(id) ? id : "hau";
    }

    private static ResourceLocation geoFor(EntityMatoran matoran) {
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID,
                "geo/entity/" + maskId(matoran) + "_matoran.geo.json"
        );
    }

    private static ResourceLocation textureFor(EntityMatoran matoran) {
        String koro = matoran.getKoro().name().toLowerCase();
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID,
                "textures/entity/" + maskId(matoran) + "_matoran/" + koro + ".png"
        );
    }
}
