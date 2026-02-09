package eastonium.nuicraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HoiModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart legR, legL;
    private final ModelPart tail;

    public HoiModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.legR = root.getChild("leg_r");
        this.legL = root.getChild("leg_l");
        this.tail = root.getChild("tail");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-2.0F, -2.0F, -3.0F, 4.0F, 4.0F, 6.0F),
                PartPose.offset(0.0F, 20.0F, 0.0F));

        root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(20, 0).addBox(-1.5F, -2.0F, -3.0F, 3.0F, 3.0F, 3.0F),
                PartPose.offset(0.0F, 19.0F, -3.0F));

        root.addOrReplaceChild("leg_r", CubeListBuilder.create()
                .texOffs(0, 10).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offset(-2.0F, 22.0F, 0.0F));

        root.addOrReplaceChild("leg_l", CubeListBuilder.create()
                .texOffs(0, 10).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 2.0F, 2.0F),
                PartPose.offset(2.0F, 22.0F, 0.0F));

        root.addOrReplaceChild("tail", CubeListBuilder.create()
                .texOffs(0, 14).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 3.0F),
                PartPose.offset(0.0F, 20.0F, 3.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);
        float f = state.walkAnimationPos;
        float f1 = state.walkAnimationSpeed;
        this.legR.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
        this.legL.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
    }
}
