package eastonium.mnogii.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import eastonium.mnogii.Mnogii;
import eastonium.mnogii.client.model.MnogiiModelLayers;
import eastonium.mnogii.client.model.NuiJagaModel;
import eastonium.mnogii.entity.EntityNuiJaga;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

public class NuiJagaRenderer extends MobRenderer<EntityNuiJaga, LivingEntityRenderState, NuiJagaModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(Mnogii.MODID, "textures/entity/nui_jaga.png");

    public NuiJagaRenderer(EntityRendererProvider.Context context) {
        super(context, new NuiJagaModel(context.bakeLayer(MnogiiModelLayers.NUI_JAGA)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) { return TEXTURE; }

    @Override
    public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
}
