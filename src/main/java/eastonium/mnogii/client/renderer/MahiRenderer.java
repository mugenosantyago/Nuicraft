package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.model.MahiModel;
import eastonium.mnogii.client.model.MnogiiModelLayers;
import eastonium.mnogii.entity.EntityMahi;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class MahiRenderer extends MobRenderer<EntityMahi, LivingEntityRenderState, MahiModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(
            Mnogii.MODID, "textures/entity/mahi.png");

    public MahiRenderer(EntityRendererProvider.Context context) {
        super(context, new MahiModel(context.bakeLayer(MnogiiModelLayers.MAHI)), 0.6F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return TEXTURE;
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }
}
