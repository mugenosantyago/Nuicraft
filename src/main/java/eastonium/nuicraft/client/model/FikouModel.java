package eastonium.nuicraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

/**
 * Ported from 1.12.1 ModelFikou - Bionicle Fikou (spider-like Rahi) model.
 * Original texture: 32x32.
 */
public class FikouModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart legL1, legL2, legL3;
    private final ModelPart legR1, legR2, legR3;

    public FikouModel(ModelPart root) {
        super(root);
        this.legL1 = root.getChild("leg_l1");
        this.legL2 = root.getChild("leg_l2");
        this.legL3 = root.getChild("leg_l3");
        this.legR1 = root.getChild("leg_r1");
        this.legR2 = root.getChild("leg_r2");
        this.legR3 = root.getChild("leg_r3");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // Body - 3 boxes: Mask, Body1, Body2 (original order)
        root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(0F, 1F, 0F, 8, 5, 8)
                        .texOffs(0, 13).addBox(1F, 2F, 1F, 6, 4, 6)
                        .texOffs(0, 23).addBox(2F, 4F, -2F, 4, 2, 5),
                PartPose.offset(-4F, 16F, 0F));

        // Head - 3 boxes: Head, Eye1, Eye2
        root.addOrReplaceChild("head",
                CubeListBuilder.create()
                        .texOffs(0, 23).addBox(0F, 0F, 0F, 4, 2, 5)
                        .texOffs(24, 13).addBox(-2F, 0F, 2F, 2, 2, 2)
                        .texOffs(24, 13).addBox(4F, 0F, 2F, 2, 2, 2),
                PartPose.offset(-2F, 19F, -6F));

        // Legs - original: 6 legs (3 left, 3 right), each 6x1x1 box
        root.addOrReplaceChild("leg_l1", CubeListBuilder.create().texOffs(18, 23).addBox(0F, 0F, 0F, 6, 1, 1),
                PartPose.offsetAndRotation(1F, 21F, -2F, 0F, 0.2617994F, 0.4363323F));
        root.addOrReplaceChild("leg_l2", CubeListBuilder.create().texOffs(18, 23).addBox(0F, 0F, 0F, 6, 1, 1),
                PartPose.offsetAndRotation(1F, 21F, 0F, 0F, 0F, 0.4363323F));
        root.addOrReplaceChild("leg_l3", CubeListBuilder.create().texOffs(18, 23).addBox(0F, 0F, 0F, 6, 1, 1),
                PartPose.offsetAndRotation(1F, 21F, 2F, 0F, -0.2617994F, 0.4363323F));
        root.addOrReplaceChild("leg_r1", CubeListBuilder.create().texOffs(18, 23).mirror().addBox(-6F, 0F, 0F, 6, 1, 1),
                PartPose.offsetAndRotation(-1F, 21F, -2F, 0F, -0.2617994F, -0.4363323F));
        root.addOrReplaceChild("leg_r2", CubeListBuilder.create().texOffs(18, 23).mirror().addBox(-6F, 0F, 0F, 6, 1, 1),
                PartPose.offsetAndRotation(-1F, 21F, 0F, 0F, 0F, -0.4363323F));
        root.addOrReplaceChild("leg_r3", CubeListBuilder.create().texOffs(18, 23).mirror().addBox(-6F, 0F, 0F, 6, 1, 1),
                PartPose.offsetAndRotation(-1F, 21F, 2F, 0F, 0.2617994F, -0.4363323F));

        return LayerDefinition.create(mesh, 32, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float f6 = -((float) Math.PI / 4F);
        legL1.zRot = -f6 * 0.75F;
        legR1.zRot = f6 * 0.75F;
        legL2.zRot = -f6 * 0.6F;
        legR2.zRot = f6 * 0.6F;
        legL3.zRot = -f6 * 0.75F;
        legR3.zRot = f6 * 0.75F;

        float f7 = 0F;
        float f8 = 0.3926991F;
        legL1.yRot = f8 * 1.0F + f7;
        legR1.yRot = -f8 * 1.0F - f7;
        legL2.yRot = f7;
        legR2.yRot = -f7;
        legL3.yRot = -f8 * 1.0F + f7;
        legR3.yRot = f8 * 1.0F - f7;

        float f = state.walkAnimationPos;
        float f1 = state.walkAnimationSpeed;
        float f9 = -(Mth.cos(f * 0.6662F * 2.0F + 0.0F) * 0.4F) * f1;
        float f10 = -(Mth.cos(f * 0.6662F * 2.0F + (float) Math.PI) * 0.4F) * f1;
        float f11 = -(Mth.cos(f * 0.6662F * 2.0F + ((float) Math.PI / 2F)) * 0.4F) * f1;
        float f12 = -(Mth.cos(f * 0.6662F * 2.0F + ((float) Math.PI * 3F / 2F)) * 0.4F) * f1;
        float f13 = Math.abs(Mth.sin(f * 0.6662F + 0.0F) * 0.4F) * f1;
        float f14 = Math.abs(Mth.sin(f * 0.6662F + (float) Math.PI) * 0.4F) * f1;
        float f15 = Math.abs(Mth.sin(f * 0.6662F + ((float) Math.PI / 2F)) * 0.4F) * f1;
        float f16 = Math.abs(Mth.sin(f * 0.6662F + ((float) Math.PI * 3F / 2F)) * 0.4F) * f1;

        legL1.yRot += f9;
        legR1.yRot += -f9;
        legL2.yRot += (f10 + f11) / 2;
        legR2.yRot += -(f10 + f11) / 2;
        legL3.yRot += f12;
        legR3.yRot += -f12;
        legL1.zRot += f13;
        legR1.zRot += -f13;
        legL2.zRot += (f14 + f15) / 2;
        legR2.zRot += -(f14 + f15) / 2;
        legL3.zRot += f16;
        legR3.zRot += -f16;
    }
}
