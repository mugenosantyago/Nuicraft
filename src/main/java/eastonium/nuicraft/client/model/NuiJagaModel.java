package eastonium.nuicraft.client.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class NuiJagaModel extends KofoJagaModel {
    public NuiJagaModel(ModelPart root) {
        super(root);
    }

    public static LayerDefinition createBodyLayer() {
        // Nui Jaga uses same skeleton as Kofo Jaga but bigger
        return KofoJagaModel.createBodyLayer();
    }
}
