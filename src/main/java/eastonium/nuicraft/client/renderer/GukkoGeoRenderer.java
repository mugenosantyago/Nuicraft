package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.animator.GukkoAnimator;
import eastonium.nuicraft.entity.EntityGukko;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class GukkoGeoRenderer extends AzEntityRenderer<EntityGukko> {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "geo/entity/gukko.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            NuiCraft.MODID, "textures/entity/gukko.png");

    public GukkoGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityGukko>builder(GEO, TEX)
                        .setAnimatorProvider(GukkoAnimator::new)
                        .setShadowRadius(0.8f)
                        .build(),
                context
        );
    }
}
