package eastonium.nuicraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

/**
 * Ported from 1.12.1 ModelMahi - detailed Bionicle Mahi (crab-like Rahi) model.
 * Original texture: 64x32.
 */
public class MahiModel extends EntityModel<LivingEntityRenderState> {
    private final ModelPart headBaseMain;
    private final ModelPart legRF, legRB, legLF, legLB;

    public MahiModel(ModelPart root) {
        super(root);
        this.headBaseMain = root.getChild("head");
        this.legRF = root.getChild("leg_rf");
        this.legRB = root.getChild("leg_rb");
        this.legLF = root.getChild("leg_lf");
        this.legLB = root.getChild("leg_lb");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        // HeadBaseMain (root for head parts) - contains eyes, head segments, nose, horns
        PartDefinition head = root.addOrReplaceChild("head",
                CubeListBuilder.create().texOffs(23, 3).addBox(-2F, 0F, 0F, 4, 4, 4),
                PartPose.offsetAndRotation(0F, 7F, -9F, 0.4363323F, 0F, 0F));

        head.addOrReplaceChild("eye_l", CubeListBuilder.create().texOffs(56, 0).addBox(2F, -2F, 0F, 2, 2, 2), PartPose.ZERO);
        head.addOrReplaceChild("eye_r", CubeListBuilder.create().texOffs(56, 0).addBox(-4F, -2F, 0F, 2, 2, 2), PartPose.ZERO);
        head.addOrReplaceChild("head_base1", CubeListBuilder.create().texOffs(32, 7).addBox(-2F, -2F, -3F, 4, 3, 7), PartPose.offsetAndRotation(0F, 2F, 0F, 0.4363323F, 0F, 0F));
        head.addOrReplaceChild("head_base2", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -0.3F, -5.3F, 5, 3, 5), PartPose.offsetAndRotation(0F, 0F, 0F, 0.4363323F, 0F, 0F));
        head.addOrReplaceChild("head_base3", CubeListBuilder.create().texOffs(42, 18).addBox(-2.5F, 2F, -5F, 5, 3, 6), PartPose.ZERO);
        head.addOrReplaceChild("head_nose1", CubeListBuilder.create().texOffs(54, 12).addBox(-1F, -0.9F, -5F, 2, 3, 3), PartPose.offsetAndRotation(0F, 0F, 0F, 0.2617994F, 0F, 0F));
        head.addOrReplaceChild("head_nose2", CubeListBuilder.create().texOffs(35, 0).addBox(-1F, -2F, -0.5F, 2, 2, 5), PartPose.offsetAndRotation(0F, 0F, 0F, -0.0698132F, 0F, 0F));
        head.addOrReplaceChild("head_nose3", CubeListBuilder.create().texOffs(35, 0).addBox(-1F, -2F, -4.5F, 2, 2, 5), PartPose.offsetAndRotation(0F, 0F, 0F, 0.4886922F, 0F, 0F));

        // Horns left
        head.addOrReplaceChild("horn_l1", CubeListBuilder.create().texOffs(30, 23).addBox(0.7F, -4.4F, 2F, 4, 2, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.9599311F));
        head.addOrReplaceChild("horn_l2", CubeListBuilder.create().texOffs(34, 27).addBox(2.5F, 4F, 2F, 7, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.5235988F));
        head.addOrReplaceChild("horn_l3", CubeListBuilder.create().texOffs(40, 17).addBox(8.7F, -3.9F, 2F, 2, 5, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.1047198F));
        head.addOrReplaceChild("horn_l4", CubeListBuilder.create().texOffs(30, 23).addBox(-0.2F, -10.7F, 2F, 4, 2, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.9948377F));
        head.addOrReplaceChild("horn_l5", CubeListBuilder.create().texOffs(34, 30).addBox(2.4F, -1.6F, 2.5F, 8, 1, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.2617994F));
        head.addOrReplaceChild("horn_l6", CubeListBuilder.create().texOffs(30, 19).addBox(1F, -2F, 2F, 3, 2, 2), PartPose.ZERO);
        // Horns right (mirrored)
        head.addOrReplaceChild("horn_r1", CubeListBuilder.create().texOffs(30, 23).mirror().addBox(-4.7F, -4.4F, 2F, 4, 2, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.9599311F));
        head.addOrReplaceChild("horn_r2", CubeListBuilder.create().texOffs(34, 27).mirror().addBox(-9.5F, 4F, 2F, 7, 1, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, 0.5235988F));
        head.addOrReplaceChild("horn_r3", CubeListBuilder.create().texOffs(40, 17).mirror().addBox(-10.7F, -3.9F, 2F, 2, 5, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.1047198F));
        head.addOrReplaceChild("horn_r4", CubeListBuilder.create().texOffs(30, 23).mirror().addBox(-3.7F, -10.7F, 2F, 4, 2, 2), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.9948377F));
        head.addOrReplaceChild("horn_r5", CubeListBuilder.create().texOffs(34, 30).mirror().addBox(-10.5F, -1.6F, 2.5F, 8, 1, 1), PartPose.offsetAndRotation(0F, 0F, 0F, 0F, 0F, -0.2617994F));
        head.addOrReplaceChild("horn_r6", CubeListBuilder.create().texOffs(30, 19).mirror().addBox(-4F, -2F, 2F, 3, 2, 2), PartPose.ZERO);

        // Neck
        root.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(0, 25).addBox(0F, 0F, 0F, 2, 5, 2),
                PartPose.offsetAndRotation(-1F, 9F, -7F, 0.4363323F, 0F, 0F));

        // Body
        root.addOrReplaceChild("body_base", CubeListBuilder.create().texOffs(2, 9).addBox(0F, 0F, 0F, 4, 4, 10),
                PartPose.offset(-2F, 12F, -5F));
        root.addOrReplaceChild("body_angle_rf", CubeListBuilder.create().texOffs(22, 23).addBox(0F, 0F, 0F, 2, 5, 4),
                PartPose.offset(-4F, 12F, -6F));
        root.addOrReplaceChild("body_angle_lf", CubeListBuilder.create().texOffs(22, 23).mirror().addBox(0F, 0F, 0F, 2, 5, 4),
                PartPose.offset(2F, 12F, -6F));
        root.addOrReplaceChild("body_angle_rb", CubeListBuilder.create().texOffs(22, 23).addBox(0F, 0F, 0F, 2, 5, 4),
                PartPose.offset(-4F, 12F, 2F));
        root.addOrReplaceChild("body_angle_lb", CubeListBuilder.create().texOffs(22, 23).mirror().addBox(0F, 0F, 0F, 2, 5, 4),
                PartPose.offset(2F, 12F, 2F));
        root.addOrReplaceChild("body_flanc_r", CubeListBuilder.create().texOffs(12, 26).addBox(0F, 0F, 0F, 1, 2, 4),
                PartPose.offset(2F, 12F, -2F));
        root.addOrReplaceChild("body_flanc_l", CubeListBuilder.create().texOffs(12, 26).mirror().addBox(0F, 0F, 0F, 1, 2, 4),
                PartPose.offset(-3F, 12F, -2F));
        root.addOrReplaceChild("body_tail", CubeListBuilder.create().texOffs(22, 23).addBox(0F, 0F, 0F, 2, 5, 4),
                PartPose.offsetAndRotation(-1F, 8F, 7F, -0.8726646F, 0F, 0F));

        // Legs (3 boxes each - upper, middle, foot)
        CubeListBuilder legBox = CubeListBuilder.create()
                .texOffs(56, 4).addBox(0F, -1F, -1F, 2, 6, 2)
                .texOffs(44, 0).addBox(0.5F, 5F, -0.5F, 1, 3, 1)
                .texOffs(48, 0).addBox(0F, 7F, -1F, 2, 2, 2);
        root.addOrReplaceChild("leg_rf", legBox, PartPose.offset(-5F, 15F, -4F));
        root.addOrReplaceChild("leg_rb", legBox, PartPose.offset(-5F, 15F, 4F));
        root.addOrReplaceChild("leg_lf", CubeListBuilder.create()
                .texOffs(56, 4).mirror().addBox(0F, -1F, -1F, 2, 6, 2)
                .texOffs(44, 0).mirror().addBox(0.5F, 5F, -0.5F, 1, 3, 1)
                .texOffs(48, 0).mirror().addBox(0F, 7F, -1F, 2, 2, 2),
                PartPose.offset(3F, 15F, -4F));
        root.addOrReplaceChild("leg_lb", CubeListBuilder.create()
                .texOffs(56, 4).mirror().addBox(0F, -1F, -1F, 2, 6, 2)
                .texOffs(44, 0).mirror().addBox(0.5F, 5F, -0.5F, 1, 3, 1)
                .texOffs(48, 0).mirror().addBox(0F, 7F, -1F, 2, 2, 2),
                PartPose.offset(3F, 15F, 4F));

        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float headYaw = state.yRot * ((float) Math.PI / 180F);
        float headPitch = state.xRot * ((float) Math.PI / 180F);
        this.headBaseMain.yRot = headYaw;
        this.headBaseMain.xRot = headPitch;

        float f = state.walkAnimationPos;
        float f1 = state.walkAnimationSpeed;
        this.legLF.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
        this.legRF.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.legLB.xRot = Mth.cos(f * 0.6662F + (float) Math.PI) * 1.4F * f1;
        this.legRB.xRot = Mth.cos(f * 0.6662F) * 1.4F * f1;
    }
}
