package eastonium.nuicraft.client.renderer;

import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.entity.EntityMahi;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.model.CowModel;
import net.minecraft.resources.ResourceLocation;

public class MahiRenderer extends MobRenderer<EntityMahi, CowModel<EntityMahi>> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/mahi.png");

    public MahiRenderer(EntityRendererProvider.Context context) {
        super(context, new CowModel<>(context.bakeLayer(ModelLayers.COW)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityMahi entity) {
        return TEXTURE;
    }
}
