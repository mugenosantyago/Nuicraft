package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntityHoi;
import net.minecraft.client.model.ChickenModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class HoiRenderer extends MobRenderer<EntityHoi, ChickenModel<EntityHoi>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/hoi.png");

    public HoiRenderer(EntityRendererProvider.Context context) {
        super(context, new ChickenModel<>(context.bakeLayer(ModelLayers.CHICKEN)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityHoi entity) {
        return TEXTURE;
    }
}
