/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  com.mojang.blaze3d.vertex.PoseStack
 *  com.mojang.blaze3d.vertex.VertexConsumer
 *  net.minecraft.client.model.EntityModel
 *  net.minecraft.client.model.geom.ModelLayerLocation
 *  net.minecraft.client.model.geom.ModelPart
 *  net.minecraft.client.model.geom.PartPose
 *  net.minecraft.client.model.geom.builders.CubeDeformation
 *  net.minecraft.client.model.geom.builders.CubeListBuilder
 *  net.minecraft.client.model.geom.builders.LayerDefinition
 *  net.minecraft.client.model.geom.builders.MeshDefinition
 *  net.minecraft.client.model.geom.builders.PartDefinition
 *  net.minecraft.resources.ResourceLocation
 *  net.minecraft.util.Mth
 *  net.minecraft.world.entity.Entity
 */
package bionicleqfn.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;

public class ModelNuiJaga<T extends Entity>
extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("bionicle_qfn", "model_nui_jaga"), "main");
    public final ModelPart body;
    public final ModelPart LegL1;
    public final ModelPart LegL2;
    public final ModelPart LegL3;
    public final ModelPart LegR1;
    public final ModelPart LegR2;
    public final ModelPart LegR3;
    public final ModelPart mask1;
    public final ModelPart mask2;

    public ModelNuiJaga(ModelPart root) {
        this.body = root.m_171324_("body");
        this.LegL1 = root.m_171324_("LegL1");
        this.LegL2 = root.m_171324_("LegL2");
        this.LegL3 = root.m_171324_("LegL3");
        this.LegR1 = root.m_171324_("LegR1");
        this.LegR2 = root.m_171324_("LegR2");
        this.LegR3 = root.m_171324_("LegR3");
        this.mask1 = root.m_171324_("mask1");
        this.mask2 = root.m_171324_("mask2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.m_171576_();
        PartDefinition body = partdefinition.m_171599_("body", CubeListBuilder.m_171558_().m_171514_(0, 0).m_171488_(-1.0f, -4.0f, -7.0f, 5.0f, 3.0f, 17.0f, new CubeDeformation(0.0f)).m_171514_(23, 20).m_171488_(0.0f, -1.0f, -6.5f, 3.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(50, 36).m_171488_(1.0f, -4.5f, -10.5f, 1.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(31, 37).m_171488_(2.5f, -4.5f, -10.0f, 1.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(31, 34).m_171488_(-0.5f, -4.5f, -10.0f, 1.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 20).m_171488_(-1.0f, -5.0f, -3.0f, 5.0f, 1.0f, 13.0f, new CubeDeformation(0.0f)).m_171514_(0, 34).m_171488_(-1.0f, -21.5f, 1.0f, 5.0f, 2.0f, 7.0f, new CubeDeformation(0.0f)).m_171514_(0, 0).m_171488_(0.0f, -22.5f, 4.0f, 3.0f, 1.0f, 5.0f, new CubeDeformation(0.0f)).m_171514_(0, 23).m_171488_(0.0f, -21.5f, 8.0f, 3.0f, 2.0f, 3.0f, new CubeDeformation(0.0f)).m_171514_(0, 43).m_171488_(-2.0f, -5.0f, -8.0f, 1.0f, 2.0f, 8.0f, new CubeDeformation(0.0f)).m_171514_(38, 41).m_171488_(4.0f, -5.0f, -8.0f, 1.0f, 2.0f, 8.0f, new CubeDeformation(0.0f)).m_171514_(52, 51).m_171488_(-3.0f, -5.0f, -2.0f, 1.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(30, 52).m_171488_(5.0f, -5.0f, -2.0f, 1.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(0, 34).m_171488_(5.0f, -4.0f, -2.0f, 1.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(11, 0).m_171488_(-3.0f, -4.0f, -2.0f, 1.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(42, 31).m_171488_(4.0f, -3.0f, -2.0f, 1.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(16, 52).m_171488_(-3.0f, -4.0f, 1.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(0, 38).m_171488_(-2.0f, -3.0f, -2.0f, 1.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(10, 48).m_171488_(-3.0f, -5.0f, 1.0f, 2.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(38, 45).m_171488_(-3.0f, -5.0f, 4.0f, 2.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(44, 51).m_171488_(-3.0f, -4.0f, 4.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(23, 27).m_171488_(4.0f, -5.0f, 4.0f, 2.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(48, 43).m_171488_(4.0f, -4.0f, 4.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(44, 17).m_171488_(4.0f, -5.0f, 1.0f, 2.0f, 1.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(36, 51).m_171488_(4.0f, -4.0f, 1.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)-2.0f, (float)21.0f, (float)0.0f));
        PartDefinition cube_r1 = body.m_171599_("cube_r1", CubeListBuilder.m_171558_().m_171514_(42, 28).m_171488_(0.0f, -18.0f, -8.0f, 3.0f, 1.0f, 7.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-3.0f, (float)3.0f, (float)-0.7854f, (float)0.0f, (float)0.0f));
        PartDefinition cube_r2 = body.m_171599_("cube_r2", CubeListBuilder.m_171558_().m_171514_(48, 36).m_171488_(0.0f, 10.0f, 7.5f, 3.0f, 1.0f, 6.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-3.0f, (float)4.0f, (float)1.5708f, (float)0.0f, (float)0.0f));
        PartDefinition cube_r3 = body.m_171599_("cube_r3", CubeListBuilder.m_171558_().m_171514_(23, 20).m_171488_(2.5f, -1.5f, -15.0f, 2.0f, 1.0f, 10.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-3.0f, (float)0.0f, (float)0.0f, (float)0.7854f, (float)0.0f));
        PartDefinition cube_r4 = body.m_171599_("cube_r4", CubeListBuilder.m_171558_().m_171514_(27, 0).m_171488_(-2.5f, -1.5f, -17.0f, 2.0f, 1.0f, 10.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-3.0f, (float)0.0f, (float)0.0f, (float)-0.7854f, (float)0.0f));
        PartDefinition cube_r5 = body.m_171599_("cube_r5", CubeListBuilder.m_171558_().m_171514_(27, 31).m_171488_(0.0f, 1.5f, 4.0f, 3.0f, 1.0f, 9.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-3.0f, (float)4.0f, (float)0.7854f, (float)0.0f, (float)0.0f));
        PartDefinition cube_r6 = body.m_171599_("cube_r6", CubeListBuilder.m_171558_().m_171514_(52, 17).m_171488_(-1.0f, 0.0f, -9.0f, 2.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(24, 52).m_171488_(2.0f, 0.0f, -9.0f, 2.0f, 2.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 28).m_171488_(-1.0f, 0.0f, -8.0f, 5.0f, 3.0f, 1.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-3.0f, (float)0.0f, (float)-0.3927f, (float)0.0f, (float)0.0f));
        PartDefinition cube_r7 = body.m_171599_("cube_r7", CubeListBuilder.m_171558_().m_171514_(15, 44).m_171488_(1.0f, 2.5f, -9.0f, 1.0f, 1.0f, 3.0f, new CubeDeformation(0.0f)).m_171514_(33, 45).m_171488_(-0.5f, 2.5f, -9.0f, 1.0f, 1.0f, 3.0f, new CubeDeformation(0.0f)).m_171514_(0, 47).m_171488_(2.5f, 2.5f, -9.0f, 1.0f, 1.0f, 3.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)-3.0f, (float)0.0f, (float)-0.2618f, (float)0.0f, (float)0.0f));
        PartDefinition LegL1 = partdefinition.m_171599_("LegL1", CubeListBuilder.m_171558_(), PartPose.m_171423_((float)3.0f, (float)16.0f, (float)-1.0f, (float)0.0f, (float)0.7854f, (float)0.0873f));
        PartDefinition cube_r8 = LegL1.m_171599_("cube_r8", CubeListBuilder.m_171558_().m_171514_(42, 36).m_171488_(12.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(37, 28).m_171488_(10.0f, -1.5f, -0.5f, 2.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(41, 0).m_171488_(0.0f, -2.0f, -1.0f, 10.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-1.0f, (float)2.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.4363f));
        PartDefinition LegL2 = partdefinition.m_171599_("LegL2", CubeListBuilder.m_171558_(), PartPose.m_171423_((float)3.0f, (float)17.0f, (float)2.0f, (float)0.0f, (float)0.0f, (float)0.0873f));
        PartDefinition cube_r9 = LegL2.m_171599_("cube_r9", CubeListBuilder.m_171558_().m_171514_(10, 43).m_171488_(13.0f, -2.0f, -1.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(43, 28).m_171488_(11.0f, -1.5f, -0.5f, 2.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(22, 41).m_171488_(1.0f, -2.0f, -1.0f, 10.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-1.0f, (float)1.0f, (float)0.0f, (float)0.0f, (float)0.0f, (float)0.4363f));
        PartDefinition LegL3 = partdefinition.m_171599_("LegL3", CubeListBuilder.m_171558_(), PartPose.m_171423_((float)3.0f, (float)17.0f, (float)5.0f, (float)0.0f, (float)-0.7854f, (float)0.0873f));
        PartDefinition cube_r10 = LegL3.m_171599_("cube_r10", CubeListBuilder.m_171558_().m_171514_(0, 43).m_171488_(12.0f, 0.0f, 2.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(41, 8).m_171488_(10.0f, 0.5f, 2.5f, 2.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(41, 4).m_171488_(0.0f, 0.0f, 2.0f, 10.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)0.0f, (float)0.0f, (float)-3.0f, (float)0.0f, (float)0.0f, (float)0.4363f));
        PartDefinition LegR1 = partdefinition.m_171599_("LegR1", CubeListBuilder.m_171558_(), PartPose.m_171423_((float)-3.317f, (float)17.8904f, (float)3.0f, (float)0.0f, (float)0.0f, (float)-0.8727f));
        PartDefinition cube_r11 = LegR1.m_171599_("cube_r11", CubeListBuilder.m_171558_().m_171514_(29, 31).m_171488_(-12.3333f, -0.5f, -0.5f, 2.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(27, 4).m_171488_(-14.3333f, -1.0f, -1.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(37, 24).m_171488_(-10.3333f, -1.0f, -1.0f, 10.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-0.0342f, (float)-0.3278f, (float)-1.0f, (float)0.0f, (float)0.0f, (float)0.4363f));
        PartDefinition LegR2 = partdefinition.m_171599_("LegR2", CubeListBuilder.m_171558_().m_171514_(37, 20).m_171488_(-10.0f, -1.0f, -1.5f, 10.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(27, 0).m_171488_(-14.0f, -1.0f, -1.5f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(23, 31).m_171488_(-12.0f, -0.5f, -1.0f, 2.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-4.0f, (float)18.0f, (float)-1.0f, (float)0.0f, (float)-0.7854f, (float)-0.6109f));
        PartDefinition LegR3 = partdefinition.m_171599_("LegR3", CubeListBuilder.m_171558_().m_171514_(27, 11).m_171488_(-10.0f, 1.0f, -1.0f, 10.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(23, 23).m_171488_(-14.0f, 1.0f, -1.0f, 2.0f, 2.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(27, 8).m_171488_(-12.0f, 1.5f, -0.5f, 2.0f, 1.0f, 1.0f, new CubeDeformation(0.0f)), PartPose.m_171423_((float)-4.0f, (float)16.0f, (float)5.0f, (float)0.0f, (float)0.7854f, (float)-0.6981f));
        PartDefinition mask1 = partdefinition.m_171599_("mask1", CubeListBuilder.m_171558_().m_171514_(46, 10).m_171488_(8.5f, -4.5f, -17.0f, 5.0f, 2.0f, 5.0f, new CubeDeformation(0.0f)).m_171514_(12, 10).m_171488_(9.5f, -5.0f, -15.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(12, 8).m_171488_(10.5f, -5.0f, -17.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(12, 6).m_171488_(13.5f, -5.0f, -17.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(10, 12).m_171488_(11.5f, -5.0f, -15.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(0, 32).m_171488_(7.5f, -5.0f, -15.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(10, 10).m_171488_(7.5f, -5.0f, -17.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(13, 13).m_171488_(10.5f, -5.0f, -15.0f, 1.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(26, 15).m_171488_(7.5f, -5.0f, -12.0f, 7.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(28, 28).m_171488_(8.5f, -5.0f, -17.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(28, 24).m_171488_(7.5f, -5.0f, -18.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(28, 23).m_171488_(12.5f, -5.0f, -18.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(28, 27).m_171488_(11.5f, -5.0f, -17.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 14).m_171488_(14.5f, -5.0f, -18.0f, 0.0f, 3.0f, 6.0f, new CubeDeformation(0.0f)).m_171514_(35, 0).m_171488_(14.5f, -5.0f, -12.0f, 0.0f, 3.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 6).m_171488_(7.5f, -5.0f, -18.0f, 0.0f, 3.0f, 6.0f, new CubeDeformation(0.0f)).m_171514_(31, 8).m_171488_(12.5f, -5.0f, -15.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(26, 16).m_171488_(7.5f, -5.0f, -13.0f, 7.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 47).m_171488_(7.5f, -5.0f, -11.0f, 1.0f, 3.0f, 0.0f, new CubeDeformation(0.0f)).m_171514_(33, 45).m_171488_(13.5f, -5.0f, -11.0f, 1.0f, 3.0f, 0.0f, new CubeDeformation(0.0f)).m_171514_(35, 3).m_171488_(7.5f, -5.0f, -12.0f, 0.0f, 3.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(17, 37).m_171488_(7.5f, -5.0f, -18.0f, 7.0f, 3.0f, 0.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)-1.0f, (float)20.0f, (float)0.0f));
        PartDefinition mask2 = partdefinition.m_171599_("mask2", CubeListBuilder.m_171558_().m_171514_(18, 45).m_171488_(7.5f, -5.5f, -17.0f, 5.0f, 2.0f, 5.0f, new CubeDeformation(0.0f)).m_171514_(10, 8).m_171488_(8.5f, -6.0f, -15.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(10, 6).m_171488_(9.5f, -6.0f, -17.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(9, 0).m_171488_(12.5f, -6.0f, -17.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(0, 2).m_171488_(10.5f, -6.0f, -15.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(8, 25).m_171488_(6.5f, -6.0f, -15.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 0).m_171488_(6.5f, -6.0f, -17.0f, 1.0f, 0.0f, 2.0f, new CubeDeformation(0.0f)).m_171514_(13, 12).m_171488_(9.5f, -6.0f, -15.0f, 1.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 16).m_171488_(6.5f, -6.0f, -12.0f, 7.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(8, 24).m_171488_(7.5f, -6.0f, -17.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(8, 23).m_171488_(6.5f, -6.0f, -18.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(11, 14).m_171488_(11.5f, -6.0f, -18.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(10, 4).m_171488_(10.5f, -6.0f, -17.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 3).m_171488_(13.5f, -6.0f, -18.0f, 0.0f, 3.0f, 6.0f, new CubeDeformation(0.0f)).m_171514_(0, 22).m_171488_(13.5f, -6.0f, -12.0f, 0.0f, 3.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 0).m_171488_(6.5f, -6.0f, -18.0f, 0.0f, 3.0f, 6.0f, new CubeDeformation(0.0f)).m_171514_(0, 4).m_171488_(11.5f, -6.0f, -15.0f, 2.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(0, 15).m_171488_(6.5f, -6.0f, -13.0f, 7.0f, 0.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(44, 15).m_171488_(6.5f, -6.0f, -11.0f, 1.0f, 3.0f, 0.0f, new CubeDeformation(0.0f)).m_171514_(20, 43).m_171488_(12.5f, -6.0f, -11.0f, 1.0f, 3.0f, 0.0f, new CubeDeformation(0.0f)).m_171514_(0, 0).m_171488_(6.5f, -6.0f, -12.0f, 0.0f, 3.0f, 1.0f, new CubeDeformation(0.0f)).m_171514_(17, 34).m_171488_(6.5f, -6.0f, -18.0f, 7.0f, 3.0f, 0.0f, new CubeDeformation(0.0f)), PartPose.m_171419_((float)-21.0f, (float)21.0f, (float)0.0f));
        return LayerDefinition.m_171565_((MeshDefinition)meshdefinition, (int)128, (int)128);
    }

    public void m_7695_(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.body.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegL1.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegL2.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegL3.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegR1.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegR2.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.LegR3.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mask1.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        this.mask2.m_104306_(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    public void m_6973_(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.LegR2.f_104204_ = Mth.m_14089_((float)(limbSwing * 0.6662f + (float)Math.PI)) * limbSwingAmount;
        this.LegR3.f_104205_ = Mth.m_14089_((float)(limbSwing * 0.6662f + (float)Math.PI)) * limbSwingAmount;
        this.LegR1.f_104205_ = Mth.m_14089_((float)(limbSwing * 0.6662f + (float)Math.PI)) * limbSwingAmount;
        this.LegL2.f_104204_ = Mth.m_14089_((float)(limbSwing * 0.6662f)) * limbSwingAmount;
        this.LegL3.f_104205_ = Mth.m_14089_((float)(limbSwing * 0.6662f)) * limbSwingAmount;
        this.LegL1.f_104205_ = Mth.m_14089_((float)(limbSwing * 0.6662f)) * limbSwingAmount;
    }
}
