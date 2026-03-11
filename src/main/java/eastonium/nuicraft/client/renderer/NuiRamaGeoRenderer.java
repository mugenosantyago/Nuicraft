package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.animator.NuiRamaAnimator;
import eastonium.nuicraft.entity.EntityNuiRama;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class NuiRamaGeoRenderer extends AzEntityRenderer<EntityNuiRama> {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "geo/entity/nui-rama.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "textures/entity/nui_rama.png");

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
