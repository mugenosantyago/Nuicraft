package eastonium.mnogii.client.renderer;

import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.model.HoiModel;
import eastonium.mnogii.client.model.MnogiiModelLayers;
import eastonium.mnogii.entity.EntityHoi;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class HoiRenderer extends MobRenderer<EntityHoi, LivingEntityRenderState, HoiModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/entity/hoi.png");

    public HoiRenderer(EntityRendererProvider.Context context) {
        super(context, new HoiModel(context.bakeLayer(MnogiiModelLayers.HOI)), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) { return TEXTURE; }

    @Override
    public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
}
