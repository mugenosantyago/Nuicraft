package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.animator.MuakaAnimator;
import eastonium.mnogii.entity.EntityMuaka;
import mod.azure.azurelib.common.render.entity.AzEntityRenderer;
import mod.azure.azurelib.common.render.entity.AzEntityRendererConfig;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class MuakaGeoRenderer extends AzEntityRenderer<EntityMuaka> {

    private static final ResourceLocation GEO = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "geo/entity/muaka.geo.json");
    private static final ResourceLocation TEX = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "textures/entity/muaka.png");

    public MuakaGeoRenderer(EntityRendererProvider.Context context) {
        super(
                AzEntityRendererConfig.<EntityMuaka>builder(GEO, TEX)
                        .setAnimatorProvider(MuakaAnimator::new)
                        .setShadowRadius(1.0f)
                        .build(),
                context
        );
    }
}
