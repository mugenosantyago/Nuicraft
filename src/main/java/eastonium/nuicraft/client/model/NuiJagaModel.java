package eastonium.nuicraft.client.model;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.util.Mth;

/**
 * Custom Nui-Jaga model ported from bionicle_qfn ModelNuiJaga.
 * Texture: 128x128 (nui_jaga.png from bionicle_qfn).
 */
public class NuiJagaModel extends EntityModel<LivingEntityRenderState> {
    private static final CubeDeformation DEF = CubeDeformation.NONE;

    public final ModelPart body;
    public final ModelPart LegL1;
    public final ModelPart LegL2;
    public final ModelPart LegL3;
    public final ModelPart LegR1;
    public final ModelPart LegR2;
    public final ModelPart LegR3;
    public final ModelPart mask1;
    public final ModelPart mask2;

    public NuiJagaModel(ModelPart root) {
        super(root);
        this.body = root.getChild("body");
        this.LegL1 = root.getChild("LegL1");
        this.LegL2 = root.getChild("LegL2");
        this.LegL3 = root.getChild("LegL3");
        this.LegR1 = root.getChild("LegR1");
        this.LegR2 = root.getChild("LegR2");
        this.LegR3 = root.getChild("LegR3");
        this.mask1 = root.getChild("mask1");
        this.mask2 = root.getChild("mask2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();

        PartDefinition body = root.addOrReplaceChild("body",
                CubeListBuilder.create()
                        .texOffs(0, 0).addBox(-1.0f, -4.0f, -7.0f, 5.0f, 3.0f, 17.0f, DEF)
                        .texOffs(23, 20).addBox(0.0f, -1.0f, -6.5f, 3.0f, 1.0f, 2.0f, DEF)
                        .texOffs(50, 36).addBox(1.0f, -4.5f, -10.5f, 1.0f, 2.0f, 1.0f, DEF)
                        .texOffs(31, 37).addBox(2.5f, -4.5f, -10.0f, 1.0f, 2.0f, 1.0f, DEF)
                        .texOffs(31, 34).addBox(-0.5f, -4.5f, -10.0f, 1.0f, 2.0f, 1.0f, DEF)
                        .texOffs(0, 20).addBox(-1.0f, -5.0f, -3.0f, 5.0f, 1.0f, 13.0f, DEF)
                        .texOffs(0, 34).addBox(-1.0f, -21.5f, 1.0f, 5.0f, 2.0f, 7.0f, DEF)
                        .texOffs(0, 0).addBox(0.0f, -22.5f, 4.0f, 3.0f, 1.0f, 5.0f, DEF)
                        .texOffs(0, 23).addBox(0.0f, -21.5f, 8.0f, 3.0f, 2.0f, 3.0f, DEF)
                        .texOffs(0, 43).addBox(-2.0f, -5.0f, -8.0f, 1.0f, 2.0f, 8.0f, DEF)
                        .texOffs(38, 41).addBox(4.0f, -5.0f, -8.0f, 1.0f, 2.0f, 8.0f, DEF)
                        .texOffs(52, 51).addBox(-3.0f, -5.0f, -2.0f, 1.0f, 1.0f, 2.0f, DEF)
                        .texOffs(30, 52).addBox(5.0f, -5.0f, -2.0f, 1.0f, 1.0f, 2.0f, DEF)
                        .texOffs(0, 34).addBox(5.0f, -4.0f, -2.0f, 1.0f, 2.0f, 2.0f, DEF)
                        .texOffs(11, 0).addBox(-3.0f, -4.0f, -2.0f, 1.0f, 2.0f, 2.0f, DEF)
                        .texOffs(42, 31).addBox(4.0f, -3.0f, -2.0f, 1.0f, 1.0f, 2.0f, DEF)
                        .texOffs(16, 52).addBox(-3.0f, -4.0f, 1.0f, 2.0f, 2.0f, 2.0f, DEF)
                        .texOffs(0, 38).addBox(-2.0f, -3.0f, -2.0f, 1.0f, 1.0f, 2.0f, DEF)
                        .texOffs(10, 48).addBox(-3.0f, -5.0f, 1.0f, 2.0f, 1.0f, 2.0f, DEF)
                        .texOffs(38, 45).addBox(-3.0f, -5.0f, 4.0f, 2.0f, 1.0f, 2.0f, DEF)
                        .texOffs(44, 51).addBox(-3.0f, -4.0f, 4.0f, 2.0f, 2.0f, 2.0f, DEF)
                        .texOffs(23, 27).addBox(4.0f, -5.0f, 4.0f, 2.0f, 1.0f, 2.0f, DEF)
                        .texOffs(48, 43).addBox(4.0f, -4.0f, 4.0f, 2.0f, 2.0f, 2.0f, DEF)
                        .texOffs(44, 17).addBox(4.0f, -5.0f, 1.0f, 2.0f, 1.0f, 2.0f, DEF)
                        .texOffs(36, 51).addBox(4.0f, -4.0f, 1.0f, 2.0f, 2.0f, 2.0f, DEF),
                PartPose.offset(-2.0f, 21.0f, 0.0f));

        body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(42, 28).addBox(0.0f, -18.0f, -8.0f, 3.0f, 1.0f, 7.0f, DEF), PartPose.offsetAndRotation(0.0f, -3.0f, 3.0f, -0.7854f, 0.0f, 0.0f));
        body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(48, 36).addBox(0.0f, 10.0f, 7.5f, 3.0f, 1.0f, 6.0f, DEF), PartPose.offsetAndRotation(0.0f, -3.0f, 4.0f, 1.5708f, 0.0f, 0.0f));
        body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(23, 20).addBox(2.5f, -1.5f, -15.0f, 2.0f, 1.0f, 10.0f, DEF), PartPose.offsetAndRotation(0.0f, -3.0f, 0.0f, 0.0f, 0.7854f, 0.0f));
        body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(27, 0).addBox(-2.5f, -1.5f, -17.0f, 2.0f, 1.0f, 10.0f, DEF), PartPose.offsetAndRotation(0.0f, -3.0f, 0.0f, 0.0f, -0.7854f, 0.0f));
        body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(27, 31).addBox(0.0f, 1.5f, 4.0f, 3.0f, 1.0f, 9.0f, DEF), PartPose.offsetAndRotation(0.0f, -3.0f, 4.0f, 0.7854f, 0.0f, 0.0f));
        body.addOrReplaceChild("cube_r6", CubeListBuilder.create()
                .texOffs(52, 17).addBox(-1.0f, 0.0f, -9.0f, 2.0f, 2.0f, 1.0f, DEF)
                .texOffs(24, 52).addBox(2.0f, 0.0f, -9.0f, 2.0f, 2.0f, 1.0f, DEF)
                .texOffs(0, 28).addBox(-1.0f, 0.0f, -8.0f, 5.0f, 3.0f, 1.0f, DEF), PartPose.offsetAndRotation(0.0f, -3.0f, 0.0f, -0.3927f, 0.0f, 0.0f));
        body.addOrReplaceChild("cube_r7", CubeListBuilder.create()
                .texOffs(15, 44).addBox(1.0f, 2.5f, -9.0f, 1.0f, 1.0f, 3.0f, DEF)
                .texOffs(33, 45).addBox(-0.5f, 2.5f, -9.0f, 1.0f, 1.0f, 3.0f, DEF)
                .texOffs(0, 47).addBox(2.5f, 2.5f, -9.0f, 1.0f, 1.0f, 3.0f, DEF), PartPose.offsetAndRotation(0.0f, -3.0f, 0.0f, -0.2618f, 0.0f, 0.0f));

        PartDefinition LegL1 = root.addOrReplaceChild("LegL1", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0f, 16.0f, -1.0f, 0.0f, 0.7854f, 0.0873f));
        LegL1.addOrReplaceChild("cube_r8", CubeListBuilder.create()
                .texOffs(42, 36).addBox(12.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, DEF)
                .texOffs(37, 28).addBox(10.0f, -1.5f, -0.5f, 2.0f, 1.0f, 1.0f, DEF)
                .texOffs(41, 0).addBox(0.0f, -2.0f, -1.0f, 10.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(-1.0f, 2.0f, 0.0f, 0.0f, 0.0f, 0.4363f));

        PartDefinition LegL2 = root.addOrReplaceChild("LegL2", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0f, 17.0f, 2.0f, 0.0f, 0.0f, 0.0873f));
        LegL2.addOrReplaceChild("cube_r9", CubeListBuilder.create()
                .texOffs(10, 43).addBox(13.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, DEF)
                .texOffs(43, 28).addBox(11.0f, -1.5f, -0.5f, 2.0f, 1.0f, 1.0f, DEF)
                .texOffs(22, 41).addBox(1.0f, -2.0f, -1.0f, 10.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(-1.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.4363f));

        PartDefinition LegL3 = root.addOrReplaceChild("LegL3", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0f, 17.0f, 5.0f, 0.0f, -0.7854f, 0.0873f));
        LegL3.addOrReplaceChild("cube_r10", CubeListBuilder.create()
                .texOffs(0, 43).addBox(12.0f, 0.0f, 2.0f, 2.0f, 2.0f, 2.0f, DEF)
                .texOffs(41, 8).addBox(10.0f, 0.5f, 2.5f, 2.0f, 1.0f, 1.0f, DEF)
                .texOffs(41, 4).addBox(0.0f, 0.0f, 2.0f, 10.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(0.0f, 0.0f, -3.0f, 0.0f, 0.0f, 0.4363f));

        PartDefinition LegR1 = root.addOrReplaceChild("LegR1", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.317f, 17.8904f, 3.0f, 0.0f, 0.0f, -0.8727f));
        LegR1.addOrReplaceChild("cube_r11", CubeListBuilder.create()
                .texOffs(29, 31).addBox(-12.3333f, -0.5f, -0.5f, 2.0f, 1.0f, 1.0f, DEF)
                .texOffs(27, 4).addBox(-14.3333f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, DEF)
                .texOffs(37, 24).addBox(-10.3333f, -1.0f, -1.0f, 10.0f, 2.0f, 2.0f, DEF), PartPose.offsetAndRotation(-0.0342f, -0.3278f, -1.0f, 0.0f, 0.0f, 0.4363f));

        root.addOrReplaceChild("LegR2", CubeListBuilder.create()
                .texOffs(37, 20).addBox(-10.0f, -1.0f, -1.5f, 10.0f, 2.0f, 2.0f, DEF)
                .texOffs(27, 0).addBox(-14.0f, -1.0f, -1.5f, 2.0f, 2.0f, 2.0f, DEF)
                .texOffs(23, 31).addBox(-12.0f, -0.5f, -1.0f, 2.0f, 1.0f, 1.0f, DEF), PartPose.offsetAndRotation(-4.0f, 18.0f, -1.0f, 0.0f, -0.7854f, -0.6109f));

        root.addOrReplaceChild("LegR3", CubeListBuilder.create()
                .texOffs(27, 11).addBox(-10.0f, 1.0f, -1.0f, 10.0f, 2.0f, 2.0f, DEF)
                .texOffs(23, 23).addBox(-14.0f, 1.0f, -1.0f, 2.0f, 2.0f, 2.0f, DEF)
                .texOffs(27, 8).addBox(-12.0f, 1.5f, -0.5f, 2.0f, 1.0f, 1.0f, DEF), PartPose.offsetAndRotation(-4.0f, 16.0f, 5.0f, 0.0f, 0.7854f, -0.6981f));

        root.addOrReplaceChild("mask1", CubeListBuilder.create()
                .texOffs(46, 10).addBox(8.5f, -4.5f, -17.0f, 5.0f, 2.0f, 5.0f, DEF)
                .texOffs(12, 10).addBox(9.5f, -5.0f, -15.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(12, 8).addBox(10.5f, -5.0f, -17.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(12, 6).addBox(13.5f, -5.0f, -17.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(10, 12).addBox(11.5f, -5.0f, -15.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(0, 32).addBox(7.5f, -5.0f, -15.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(10, 10).addBox(7.5f, -5.0f, -17.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(13, 13).addBox(10.5f, -5.0f, -15.0f, 1.0f, 0.0f, 1.0f, DEF)
                .texOffs(26, 15).addBox(7.5f, -5.0f, -12.0f, 7.0f, 0.0f, 1.0f, DEF)
                .texOffs(28, 28).addBox(8.5f, -5.0f, -17.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(28, 24).addBox(7.5f, -5.0f, -18.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(28, 23).addBox(12.5f, -5.0f, -18.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(28, 27).addBox(11.5f, -5.0f, -17.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(0, 14).addBox(14.5f, -5.0f, -18.0f, 0.0f, 3.0f, 6.0f, DEF)
                .texOffs(35, 0).addBox(14.5f, -5.0f, -12.0f, 0.0f, 3.0f, 1.0f, DEF)
                .texOffs(0, 6).addBox(7.5f, -5.0f, -18.0f, 0.0f, 3.0f, 6.0f, DEF)
                .texOffs(31, 8).addBox(12.5f, -5.0f, -15.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(26, 16).addBox(7.5f, -5.0f, -13.0f, 7.0f, 0.0f, 1.0f, DEF)
                .texOffs(0, 47).addBox(7.5f, -5.0f, -11.0f, 1.0f, 3.0f, 0.0f, DEF)
                .texOffs(33, 45).addBox(13.5f, -5.0f, -11.0f, 1.0f, 3.0f, 0.0f, DEF)
                .texOffs(35, 3).addBox(7.5f, -5.0f, -12.0f, 0.0f, 3.0f, 1.0f, DEF)
                .texOffs(17, 37).addBox(7.5f, -5.0f, -18.0f, 7.0f, 3.0f, 0.0f, DEF),
                PartPose.offset(-1.0f, 20.0f, 0.0f));

        root.addOrReplaceChild("mask2", CubeListBuilder.create()
                .texOffs(18, 45).addBox(7.5f, -5.5f, -17.0f, 5.0f, 2.0f, 5.0f, DEF)
                .texOffs(10, 8).addBox(8.5f, -6.0f, -15.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(10, 6).addBox(9.5f, -6.0f, -17.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(9, 0).addBox(12.5f, -6.0f, -17.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(0, 2).addBox(10.5f, -6.0f, -15.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(8, 25).addBox(6.5f, -6.0f, -15.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(0, 0).addBox(6.5f, -6.0f, -17.0f, 1.0f, 0.0f, 2.0f, DEF)
                .texOffs(13, 12).addBox(9.5f, -6.0f, -15.0f, 1.0f, 0.0f, 1.0f, DEF)
                .texOffs(0, 16).addBox(6.5f, -6.0f, -12.0f, 7.0f, 0.0f, 1.0f, DEF)
                .texOffs(8, 24).addBox(7.5f, -6.0f, -17.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(8, 23).addBox(6.5f, -6.0f, -18.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(11, 14).addBox(11.5f, -6.0f, -18.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(10, 4).addBox(10.5f, -6.0f, -17.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(0, 3).addBox(13.5f, -6.0f, -18.0f, 0.0f, 3.0f, 6.0f, DEF)
                .texOffs(0, 22).addBox(13.5f, -6.0f, -12.0f, 0.0f, 3.0f, 1.0f, DEF)
                .texOffs(0, 0).addBox(6.5f, -6.0f, -18.0f, 0.0f, 3.0f, 6.0f, DEF)
                .texOffs(0, 4).addBox(11.5f, -6.0f, -15.0f, 2.0f, 0.0f, 1.0f, DEF)
                .texOffs(0, 15).addBox(6.5f, -6.0f, -13.0f, 7.0f, 0.0f, 1.0f, DEF)
                .texOffs(44, 15).addBox(6.5f, -6.0f, -11.0f, 1.0f, 3.0f, 0.0f, DEF)
                .texOffs(20, 43).addBox(12.5f, -6.0f, -11.0f, 1.0f, 3.0f, 0.0f, DEF)
                .texOffs(0, 0).addBox(6.5f, -6.0f, -12.0f, 0.0f, 3.0f, 1.0f, DEF)
                .texOffs(17, 34).addBox(6.5f, -6.0f, -18.0f, 7.0f, 3.0f, 0.0f, DEF),
                PartPose.offset(-21.0f, 21.0f, 0.0f));

        return LayerDefinition.create(mesh, 128, 128);
    }

    @Override
    public void setupAnim(LivingEntityRenderState state) {
        super.setupAnim(state);
        float f = state.walkAnimationPos;
        float f1 = state.walkAnimationSpeed;
        LegR2.xRot = Mth.cos(f * 0.6662f + (float) Math.PI) * f1;
        LegR3.yRot = Mth.cos(f * 0.6662f + (float) Math.PI) * f1;
        LegR1.yRot = Mth.cos(f * 0.6662f + (float) Math.PI) * f1;
        LegL2.xRot = Mth.cos(f * 0.6662f) * f1;
        LegL3.yRot = Mth.cos(f * 0.6662f) * f1;
        LegL1.yRot = Mth.cos(f * 0.6662f) * f1;
    }
}
