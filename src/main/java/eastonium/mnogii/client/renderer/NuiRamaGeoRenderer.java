package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.animator.NuiRamaAnimator;
import eastonium.mnogii.entity.EntityNuiRama;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class NuiRamaGeoRenderer extends AzEntityRenderer<EntityNuiRama> {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "geo/entity/nui-rama.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "textures/entity/nui_rama.png");

    public NuiRamaGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityNuiRama>builder(GEO, TEX)
                        .setAnimatorProvider(NuiRamaAnimator::new)
                        .setShadowRadius(0.7f)
                        .build(),
                context
        );
    }
}
