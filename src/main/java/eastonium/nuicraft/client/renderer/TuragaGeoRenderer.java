package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntityTuraga;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

/**
 * AzureLib Geo renderer for Turaga NPCs.
 *
 * Model  : geo/entity/{type}.geo.json   (e.g. matatu_turaga.geo.json)
 * Texture: textures/entity/{type}/default.png
 *
 * Turaga types are assigned in EntityTuraga.finalizeSpawn() based on biome:
 *   Ga biome  → RAU    (Turaga Nokama)
 *   Ko biome  → MATATU (Turaga Nuju)
 *   All other → MATATU (fallback until remaining models arrive)
 */
public class TuragaGeoRenderer extends AzEntityRenderer<EntityTuraga> {

    public TuragaGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityTuraga>builder(
                        TuragaGeoRenderer::geoFor,
                        TuragaGeoRenderer::textureFor
                )
                .setShadowRadius(0.4f)
                .build(),
                context
        );
    }

    private static ResourceLocation geoFor(EntityTuraga turaga) {
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID,
                "geo/entity/" + turaga.getTuragaType().getId() + ".geo.json"
        );
    }

    private static ResourceLocation textureFor(EntityTuraga turaga) {
        return ResourceLocation.fromNamespaceAndPath(
                NuiCraft.MODID,
                "textures/entity/" + turaga.getTuragaType().getId() + "/default.png"
        );
    }
}
