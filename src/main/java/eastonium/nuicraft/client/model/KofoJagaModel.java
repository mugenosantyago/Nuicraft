package eastonium.nuicraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

public class KofoJagaModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart tail1, tail2;
    private final ModelPart legR1, legR2, legR3;
    private final ModelPart legL1, legL2, legL3;
    private final ModelPart clawR, clawL;

    public KofoJagaModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.tail1 = root.getChild("tail1");
        this.tail2 = root.getChild("tail2");
        this.legR1 = root.getChild("leg_r1");
        this.legR2 = root.getChild("leg_r2");
        this.legR3 = root.getChild("leg_r3");
        this.legL1 = root.getChild("leg_l1");
        this.legL2 = root.getChild("leg_l2");
        this.legL3 = root.getChild("leg_l3");
        this.clawR = root.getChild("claw_r");
        this.clawL = root.getChild("claw_l");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 4.0F, 10.0F),
                PartPose.offset(0.0F, 18.0F, 0.0F));

        root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(0, 14).addBox(-3.0F, -2.0F, -4.0F, 6.0F, 4.0F, 4.0F),
                PartPose.offset(0.0F, 17.0F, -5.0F));

        root.addOrReplaceChild("tail1", CubeListBuilder.create()
                .texOffs(36, 0).addBox(-1.5F, -2.0F, 0.0F, 3.0F, 3.0F, 5.0F),
                PartPose.offsetAndRotation(0.0F, 16.0F, 5.0F, -0.5F, 0.0F, 0.0F));

        root.addOrReplaceChild("tail2", CubeListBuilder.create()
                .texOffs(36, 8).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 4.0F),
                PartPose.offsetAndRotation(0.0F, 13.0F, 9.0F, -0.8F, 0.0F, 0.0F));

        CubeListBuilder leg = CubeListBuilder.create().texOffs(20, 14).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 5.0F, 2.0F);

        root.addOrReplaceChild("leg_r1", leg, PartPose.offset(-4.0F, 19.0F, -3.0F));
        root.addOrReplaceChild("leg_r2", leg, PartPose.offset(-4.0F, 19.0F, 0.0F));
        root.addOrReplaceChild("leg_r3", leg, PartPose.offset(-4.0F, 19.0F, 3.0F));
        root.addOrReplaceChild("leg_l1", leg, PartPose.offset(4.0F, 19.0F, -3.0F));
        root.addOrReplaceChild("leg_l2", leg, PartPose.offset(4.0F, 19.0F, 0.0F));
        root.addOrReplaceChild("leg_l3", leg, PartPose.offset(4.0F, 19.0F, 3.0F));

        root.addOrReplaceChild("claw_r", CubeListBuilder.create()
                .texOffs(28, 14).addBox(-3.0F, -1.0F, -5.0F, 3.0F, 2.0F, 5.0F),
                PartPose.offset(-4.0F, 17.0F, -5.0F));

        root.addOrReplaceChild("claw_l", CubeListBuilder.create()
                .texOffs(28, 14).mirror().addBox(0.0F, -1.0F, -5.0F, 3.0F, 2.0F, 5.0F),
                PartPose.offset(4.0F, 17.0F, -5.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);
        float f = state.walkAnimationPos;
        float f1 = state.walkAnimationSpeed;
        legR1.zRot = -Mth.cos(f * 0.6662F) * 0.6F * f1;
        legL1.zRot = Mth.cos(f * 0.6662F) * 0.6F * f1;
        legR2.zRot = -Mth.cos(f * 0.6662F + (float) Math.PI * 0.5F) * 0.6F * f1;
        legL2.zRot = Mth.cos(f * 0.6662F + (float) Math.PI * 0.5F) * 0.6F * f1;
        legR3.zRot = -Mth.cos(f * 0.6662F + (float) Math.PI) * 0.6F * f1;
        legL3.zRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 0.6F * f1;
    }
}
