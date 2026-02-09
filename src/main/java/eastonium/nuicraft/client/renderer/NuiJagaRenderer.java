package eastonium.nuicraft.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import eastonium.nuicraft.NuiCraft;
import eastonium.nuicraft.client.model.NuiCraftModelLayers;
import eastonium.nuicraft.client.model.NuiJagaModel;
import eastonium.nuicraft.entity.EntityNuiJaga;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NuiJagaRenderer extends MobRenderer<EntityNuiJaga, LivingEntityRenderState, NuiJagaModel> {
    private static final ResourceLocation TEXTURE = ResourceLocation.fromNamespaceAndPath(NuiCraft.MODID, "textures/entity/nui_jaga.png");

    public NuiJagaRenderer(EntityRendererProvider.Context context) {
        super(context, new NuiJagaModel(context.bakeLayer(NuiCraftModelLayers.NUI_JAGA)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) { return TEXTURE; }

    @Override
    public LivingEntityRenderState createRenderState() { return new LivingEntityRenderState(); }
}
