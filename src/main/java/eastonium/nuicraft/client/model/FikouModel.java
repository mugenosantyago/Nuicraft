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
public class FikouModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart legR1, legR2, legR3, legR4;
    private final ModelPart legL1, legL2, legL3, legL4;

    public FikouModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.legR1 = root.getChild("leg_r1");
        this.legR2 = root.getChild("leg_r2");
        this.legR3 = root.getChild("leg_r3");
        this.legR4 = root.getChild("leg_r4");
        this.legL1 = root.getChild("leg_l1");
        this.legL2 = root.getChild("leg_l2");
        this.legL3 = root.getChild("leg_l3");
        this.legL4 = root.getChild("leg_l4");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        root.addOrReplaceChild("body", CubeListBuilder.create()
                .texOffs(0, 0).addBox(-3.0F, -3.0F, -3.0F, 6.0F, 6.0F, 6.0F),
                PartPose.offset(0.0F, 18.0F, 0.0F));

        root.addOrReplaceChild("head", CubeListBuilder.create()
                .texOffs(24, 0).addBox(-2.0F, -2.0F, -4.0F, 4.0F, 4.0F, 4.0F),
                PartPose.offset(0.0F, 16.0F, -3.0F));

        CubeListBuilder legR = CubeListBuilder.create().texOffs(0, 12).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F);
        CubeListBuilder legL = CubeListBuilder.create().texOffs(0, 12).mirror().addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F);

        root.addOrReplaceChild("leg_r1", legR, PartPose.offset(-3.0F, 18.0F, -2.0F));
        root.addOrReplaceChild("leg_r2", legR, PartPose.offset(-3.0F, 18.0F, 0.0F));
        root.addOrReplaceChild("leg_r3", legR, PartPose.offset(-3.0F, 18.0F, 2.0F));
        root.addOrReplaceChild("leg_r4", legR, PartPose.offset(-3.0F, 18.0F, 4.0F));
        root.addOrReplaceChild("leg_l1", legL, PartPose.offset(3.0F, 18.0F, -2.0F));
        root.addOrReplaceChild("leg_l2", legL, PartPose.offset(3.0F, 18.0F, 0.0F));
        root.addOrReplaceChild("leg_l3", legL, PartPose.offset(3.0F, 18.0F, 2.0F));
        root.addOrReplaceChild("leg_l4", legL, PartPose.offset(3.0F, 18.0F, 4.0F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        this.head.xRot = state.xRot * ((float) Math.PI / 180F);
        this.head.yRot = state.yRot * ((float) Math.PI / 180F);
        float f = state.walkAnimationPos;
        float f1 = state.walkAnimationSpeed;
        legR1.zRot = -Mth.cos(f * 0.6662F) * 0.8F * f1;
        legL1.zRot = Mth.cos(f * 0.6662F) * 0.8F * f1;
        legR2.zRot = -Mth.cos(f * 0.6662F + (float) Math.PI * 0.5F) * 0.8F * f1;
        legL2.zRot = Mth.cos(f * 0.6662F + (float) Math.PI * 0.5F) * 0.8F * f1;
        legR3.zRot = -Mth.cos(f * 0.6662F + (float) Math.PI) * 0.8F * f1;
        legL3.zRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 0.8F * f1;
        legR4.zRot = -Mth.cos(f * 0.6662F + (float) Math.PI * 1.5F) * 0.8F * f1;
        legL4.zRot = Mth.cos(f * 0.6662F + (float) Math.PI * 1.5F) * 0.8F * f1;
    }
}
