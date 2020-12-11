package com.izako.hunterx.entities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.util.HandSide;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public class SharpOutlineModel extends BipedModel<LivingEntity> {
	private final ModelRenderer Head;
	private final ModelRenderer Body;
	private final ModelRenderer sharpbodyline;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer sharpbodyline2;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer sharpbodyline3;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer sharpbodyline4;
	private final ModelRenderer bone9;
	private final ModelRenderer bone10;
	private final ModelRenderer sharpbodyline5;
	private final ModelRenderer bone11;
	private final ModelRenderer bone12;
	private final ModelRenderer sharpbodyline6;
	private final ModelRenderer bone13;
	private final ModelRenderer bone14;
	private final ModelRenderer RightArm;
	private final ModelRenderer sharplinearm;
	private final ModelRenderer bone2;
	private final ModelRenderer bone;
	private final ModelRenderer LeftArm;
	private final ModelRenderer sharplinearm2;
	private final ModelRenderer bone15;
	private final ModelRenderer bone16;
	private final ModelRenderer RightLeg;
	private final ModelRenderer sharplinearm4;
	private final ModelRenderer bone19;
	private final ModelRenderer bone20;
	private final ModelRenderer LeftLeg;
	private final ModelRenderer sharplinearm3;
	private final ModelRenderer bone17;
	private final ModelRenderer bone18;

	public SharpOutlineModel() {
		super(1f);
		textureWidth = 64;
		textureHeight = 64;

		Head = new ModelRenderer(this);
		Head.setRotationPoint(0.0F, 0.0F, 0.0F);
		setRotationAngle(Head, -0.1047F, 0.0873F, 0.0F);
		Head.setTextureOffset(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.0F, false);
		Head.setTextureOffset(32, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, 0.5F, false);

		Body = new ModelRenderer(this);
		Body.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.setTextureOffset(16, 16).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.0F, false);
		Body.setTextureOffset(16, 32).addBox(-4.0F, 0.0F, -2.0F, 8.0F, 12.0F, 4.0F, 0.25F, false);

		sharpbodyline = new ModelRenderer(this);
		sharpbodyline.setRotationPoint(0.0F, 0.0F, 0.0F);
		Body.addChild(sharpbodyline);
		sharpbodyline.setTextureOffset(0, 0).addBox(2.0F, 3.0F, 2.0F, 0.0F, 6.0F, 1.0F, 0.0F, false);

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(2.0F, 2.8F, 2.0F);
		sharpbodyline.addChild(bone3);
		setRotationAngle(bone3, 0.4363F, 0.0F, 0.0F);
		bone3.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(2.001F, 8.8636F, 1.9708F);
		sharpbodyline.addChild(bone4);
		setRotationAngle(bone4, 1.1781F, 0.0F, 0.0F);
		bone4.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		sharpbodyline2 = new ModelRenderer(this);
		sharpbodyline2.setRotationPoint(-2.0F, 0.0F, 0.0F);
		Body.addChild(sharpbodyline2);
		sharpbodyline2.setTextureOffset(0, 0).addBox(2.0F, 3.0F, 2.0F, 0.0F, 6.0F, 1.0F, 0.0F, false);

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(2.0F, 2.8F, 2.0F);
		sharpbodyline2.addChild(bone5);
		setRotationAngle(bone5, 0.4363F, 0.0F, 0.0F);
		bone5.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(2.001F, 8.8636F, 1.9708F);
		sharpbodyline2.addChild(bone6);
		setRotationAngle(bone6, 1.1781F, 0.0F, 0.0F);
		bone6.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		sharpbodyline3 = new ModelRenderer(this);
		sharpbodyline3.setRotationPoint(-4.0F, 0.0F, 0.0F);
		Body.addChild(sharpbodyline3);
		sharpbodyline3.setTextureOffset(0, 0).addBox(2.0F, 3.0F, 2.0F, 0.0F, 6.0F, 1.0F, 0.0F, false);

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(2.0F, 2.8F, 2.0F);
		sharpbodyline3.addChild(bone7);
		setRotationAngle(bone7, 0.4363F, 0.0F, 0.0F);
		bone7.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(2.001F, 8.8636F, 1.9708F);
		sharpbodyline3.addChild(bone8);
		setRotationAngle(bone8, 1.1781F, 0.0F, 0.0F);
		bone8.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		sharpbodyline4 = new ModelRenderer(this);
		sharpbodyline4.setRotationPoint(-0.6667F, 5.0F, -1.8333F);
		Body.addChild(sharpbodyline4);
		sharpbodyline4.setTextureOffset(0, 0).addBox(-1.3333F, -2.0F, -1.1667F, 0.0F, 6.0F, 1.0F, 0.0F, false);

		bone9 = new ModelRenderer(this);
		bone9.setRotationPoint(-1.3333F, -2.2F, -0.1667F);
		sharpbodyline4.addChild(bone9);
		setRotationAngle(bone9, -0.4363F, 0.0F, 0.0F);
		bone9.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		bone10 = new ModelRenderer(this);
		bone10.setRotationPoint(-1.3323F, 3.8636F, -0.1374F);
		sharpbodyline4.addChild(bone10);
		setRotationAngle(bone10, -1.1781F, 0.0F, 0.0F);
		bone10.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		sharpbodyline5 = new ModelRenderer(this);
		sharpbodyline5.setRotationPoint(1.3333F, 5.0F, -1.8333F);
		Body.addChild(sharpbodyline5);
		sharpbodyline5.setTextureOffset(0, 0).addBox(-1.3333F, -2.0F, -1.1667F, 0.0F, 6.0F, 1.0F, 0.0F, false);

		bone11 = new ModelRenderer(this);
		bone11.setRotationPoint(-1.3333F, -2.2F, -0.1667F);
		sharpbodyline5.addChild(bone11);
		setRotationAngle(bone11, -0.4363F, 0.0F, 0.0F);
		bone11.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		bone12 = new ModelRenderer(this);
		bone12.setRotationPoint(-1.3323F, 3.8636F, -0.1374F);
		sharpbodyline5.addChild(bone12);
		setRotationAngle(bone12, -1.1781F, 0.0F, 0.0F);
		bone12.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		sharpbodyline6 = new ModelRenderer(this);
		sharpbodyline6.setRotationPoint(3.3333F, 5.0F, -1.8333F);
		Body.addChild(sharpbodyline6);
		sharpbodyline6.setTextureOffset(0, 0).addBox(-1.3333F, -2.0F, -1.1667F, 0.0F, 6.0F, 1.0F, 0.0F, false);

		bone13 = new ModelRenderer(this);
		bone13.setRotationPoint(-1.3333F, -2.2F, -0.1667F);
		sharpbodyline6.addChild(bone13);
		setRotationAngle(bone13, -0.4363F, 0.0F, 0.0F);
		bone13.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		bone14 = new ModelRenderer(this);
		bone14.setRotationPoint(-1.3323F, 3.8636F, -0.1374F);
		sharpbodyline6.addChild(bone14);
		setRotationAngle(bone14, -1.1781F, 0.0F, 0.0F);
		bone14.setTextureOffset(0, 0).addBox(0.0F, -0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, false);

		RightArm = new ModelRenderer(this);
		RightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		RightArm.setTextureOffset(40, 16).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		RightArm.setTextureOffset(40, 32).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		sharplinearm = new ModelRenderer(this);
		sharplinearm.setRotationPoint(0.4F, 0.0F, 0.0F);
		RightArm.addChild(sharplinearm);
		sharplinearm.setTextureOffset(0, 0).addBox(-5.0F, -1.0F, 0.1F, 2.0F, 5.0F, 0.0F, 0.0F, false);
		sharplinearm.setTextureOffset(0, 0).addBox(-4.0671F, 5.5526F, 0.1F, 1.0F, 3.0F, 0.0F, 0.0F, false);

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(-4.0F, 2.5F, 0.1F);
		sharplinearm.addChild(bone2);
		setRotationAngle(bone2, 1.5708F, 0.0F, 0.0F);
		bone2.setTextureOffset(0, 0).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);

		bone = new ModelRenderer(this);
		bone.setRotationPoint(-4.7472F, 3.823F, 0.1F);
		sharplinearm.addChild(bone);
		setRotationAngle(bone, 0.0F, 0.0F, -0.4363F);
		bone.setTextureOffset(0, 0).addBox(-0.1146F, -0.145F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);

		LeftArm = new ModelRenderer(this);
		LeftArm.setRotationPoint(5.0F, 2.0F, 0.0F);
		setRotationAngle(LeftArm, -0.0524F, 0.0F, 0.0F);
		LeftArm.setTextureOffset(32, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		LeftArm.setTextureOffset(48, 48).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		sharplinearm2 = new ModelRenderer(this);
		sharplinearm2.setRotationPoint(3.9918F, 2.1381F, 0.05F);
		LeftArm.addChild(sharplinearm2);
		sharplinearm2.setTextureOffset(0, 0).addBox(-1.3918F, -3.1381F, 0.05F, 2.0F, 5.0F, 0.0F, 0.0F, true);
		sharplinearm2.setTextureOffset(0, 0).addBox(-1.3247F, 3.4144F, 0.05F, 1.0F, 3.0F, 0.0F, 0.0F, true);

		bone15 = new ModelRenderer(this);
		bone15.setRotationPoint(-0.3918F, 0.3619F, 0.05F);
		sharplinearm2.addChild(bone15);
		setRotationAngle(bone15, 1.5708F, 0.0F, 0.0F);
		bone15.setTextureOffset(0, 0).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, true);

		bone16 = new ModelRenderer(this);
		bone16.setRotationPoint(0.3555F, 1.6848F, 0.05F);
		sharplinearm2.addChild(bone16);
		setRotationAngle(bone16, 0.0F, 0.0F, 0.4363F);
		bone16.setTextureOffset(0, 0).addBox(-1.8854F, -0.145F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, true);

		RightLeg = new ModelRenderer(this);
		RightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		RightLeg.setTextureOffset(0, 16).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		RightLeg.setTextureOffset(0, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		sharplinearm4 = new ModelRenderer(this);
		sharplinearm4.setRotationPoint(-2.7F, -10.0F, 0.0F);
		RightLeg.addChild(sharplinearm4);
		sharplinearm4.setTextureOffset(0, 0).addBox(-1.0F, 11.0F, 0.1F, 2.0F, 5.0F, 0.0F, 0.0F, false);
		sharplinearm4.setTextureOffset(0, 0).addBox(-0.0671F, 17.5526F, 0.1F, 1.0F, 3.0F, 0.0F, 0.0F, false);

		bone19 = new ModelRenderer(this);
		bone19.setRotationPoint(-4.0F, 2.5F, 0.1F);
		sharplinearm4.addChild(bone19);
		setRotationAngle(bone19, 1.5708F, 0.0F, 0.0F);
		bone19.setTextureOffset(0, 0).addBox(3.0F, -1.5F, -12.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);

		bone20 = new ModelRenderer(this);
		bone20.setRotationPoint(-4.7472F, 3.823F, 0.1F);
		sharplinearm4.addChild(bone20);
		setRotationAngle(bone20, 0.0F, 0.0F, -0.4363F);
		bone20.setTextureOffset(0, 0).addBox(-1.5608F, 12.4212F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, false);

		LeftLeg = new ModelRenderer(this);
		LeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		LeftLeg.setTextureOffset(16, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.0F, false);
		LeftLeg.setTextureOffset(0, 48).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, 0.25F, false);

		sharplinearm3 = new ModelRenderer(this);
		sharplinearm3.setRotationPoint(3.0918F, 4.1381F, 0.05F);
		LeftLeg.addChild(sharplinearm3);
		sharplinearm3.setTextureOffset(0, 0).addBox(-1.3918F, -3.1381F, 0.05F, 2.0F, 5.0F, 0.0F, 0.0F, true);
		sharplinearm3.setTextureOffset(0, 0).addBox(-1.3247F, 3.4144F, 0.05F, 1.0F, 3.0F, 0.0F, 0.0F, true);

		bone17 = new ModelRenderer(this);
		bone17.setRotationPoint(-0.3918F, 0.3619F, 0.05F);
		sharplinearm3.addChild(bone17);
		setRotationAngle(bone17, 1.5708F, 0.0F, 0.0F);
		bone17.setTextureOffset(0, 0).addBox(-1.0F, -1.5F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, true);

		bone18 = new ModelRenderer(this);
		bone18.setRotationPoint(0.3555F, 1.6848F, 0.05F);
		sharplinearm3.addChild(bone18);
		setRotationAngle(bone18, 0.0F, 0.0F, 0.4363F);
		bone18.setTextureOffset(0, 0).addBox(-1.8854F, -0.145F, 0.0F, 2.0F, 3.0F, 0.0F, 0.0F, true);
	}

	@Override
	public void setRotationAngles(LivingEntity entityIn, float limbSwing, float limbSwingAmount, float ageInTicks,
			float netHeadYaw, float headPitch) {
	      boolean flag = entityIn.getTicksElytraFlying() > 4;
	      boolean flag1 = entityIn.isActualySwimming();
	      this.Head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
	      if (flag) {
	         this.Head.rotateAngleX = (-(float)Math.PI / 4F);
	      } else if (this.swimAnimation > 0.0F) {
	         if (flag1) {
	            this.Head.rotateAngleX = this.rotLerpRad(this.Head.rotateAngleX, (-(float)Math.PI / 4F), this.swimAnimation);
	         } else {
	            this.Head.rotateAngleX = this.rotLerpRad(this.Head.rotateAngleX, headPitch * ((float)Math.PI / 180F), this.swimAnimation);
	         }
	      } else {
	         this.Head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
	      }

	      this.bipedBody.rotateAngleY = 0.0F;
	      this.RightArm.rotationPointZ = 0.0F;
	      this.RightArm.rotationPointX = -5.0F;
	      this.LeftArm.rotationPointZ = 0.0F;
	      this.LeftArm.rotationPointX = 5.0F;
	      float f = 1.0F;
	      if (flag) {
	         f = (float)entityIn.getMotion().lengthSquared();
	         f = f / 0.2F;
	         f = f * f * f;
	      }

	      if (f < 1.0F) {
	         f = 1.0F;
	      }

	      this.RightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 2.0F * limbSwingAmount * 0.5F / f;
	      this.LeftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 2.0F * limbSwingAmount * 0.5F / f;
	      this.RightArm.rotateAngleZ = 0.0F;
	      this.LeftArm.rotateAngleZ = 0.0F;
	      this.RightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount / f;
	      this.LeftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount / f;
	      this.RightLeg.rotateAngleY = 0.0F;
	      this.LeftLeg.rotateAngleY = 0.0F;
	      this.RightLeg.rotateAngleZ = 0.0F;
	      this.LeftLeg.rotateAngleZ = 0.0F;
	      if (this.isSitting) {
	         this.RightArm.rotateAngleX += (-(float)Math.PI / 5F);
	         this.LeftArm.rotateAngleX += (-(float)Math.PI / 5F);
	         this.RightLeg.rotateAngleX = -1.4137167F;
	         this.RightLeg.rotateAngleY = ((float)Math.PI / 10F);
	         this.RightLeg.rotateAngleZ = 0.07853982F;
	         this.LeftLeg.rotateAngleX = -1.4137167F;
	         this.LeftLeg.rotateAngleY = (-(float)Math.PI / 10F);
	         this.LeftLeg.rotateAngleZ = -0.07853982F;
	      }

	      this.RightArm.rotateAngleY = 0.0F;
	      this.RightArm.rotateAngleZ = 0.0F;
	      switch(this.leftArmPose) {
	      case EMPTY:
	         this.LeftArm.rotateAngleY = 0.0F;
	         break;
	      case BLOCK:
	         this.LeftArm.rotateAngleX = this.LeftArm.rotateAngleX * 0.5F - 0.9424779F;
	         this.LeftArm.rotateAngleY = ((float)Math.PI / 6F);
	         break;
	      case ITEM:
	         this.LeftArm.rotateAngleX = this.LeftArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
	         this.LeftArm.rotateAngleY = 0.0F;
	      }

	      switch(this.rightArmPose) {
	      case EMPTY:
	         this.RightArm.rotateAngleY = 0.0F;
	         break;
	      case BLOCK:
	         this.RightArm.rotateAngleX = this.RightArm.rotateAngleX * 0.5F - 0.9424779F;
	         this.RightArm.rotateAngleY = (-(float)Math.PI / 6F);
	         break;
	      case ITEM:
	         this.RightArm.rotateAngleX = this.RightArm.rotateAngleX * 0.5F - ((float)Math.PI / 10F);
	         this.RightArm.rotateAngleY = 0.0F;
	         break;
	      case THROW_SPEAR:
	         this.RightArm.rotateAngleX = this.RightArm.rotateAngleX * 0.5F - (float)Math.PI;
	         this.RightArm.rotateAngleY = 0.0F;
	      }

	      if (this.leftArmPose == BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BLOCK && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BOW_AND_ARROW) {
	         this.LeftArm.rotateAngleX = this.LeftArm.rotateAngleX * 0.5F - (float)Math.PI;
	         this.LeftArm.rotateAngleY = 0.0F;
	      }

	      if (this.swingProgress > 0.0F) {
	         HandSide handside = this.getMainHand(entityIn);
	         ModelRenderer modelrenderer = this.getArmForSide(handside);
	         float f1 = this.swingProgress;
	         this.bipedBody.rotateAngleY = MathHelper.sin(MathHelper.sqrt(f1) * ((float)Math.PI * 2F)) * 0.2F;
	         if (handside == HandSide.LEFT) {
	            this.bipedBody.rotateAngleY *= -1.0F;
	         }

	         this.RightArm.rotationPointZ = MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
	         this.RightArm.rotationPointX = -MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
	         this.LeftArm.rotationPointZ = -MathHelper.sin(this.bipedBody.rotateAngleY) * 5.0F;
	         this.LeftArm.rotationPointX = MathHelper.cos(this.bipedBody.rotateAngleY) * 5.0F;
	         this.RightArm.rotateAngleY += this.bipedBody.rotateAngleY;
	         this.LeftArm.rotateAngleY += this.bipedBody.rotateAngleY;
	         this.LeftArm.rotateAngleX += this.bipedBody.rotateAngleY;
	         f1 = 1.0F - this.swingProgress;
	         f1 = f1 * f1;
	         f1 = f1 * f1;
	         f1 = 1.0F - f1;
	         float f2 = MathHelper.sin(f1 * (float)Math.PI);
	         float f3 = MathHelper.sin(this.swingProgress * (float)Math.PI) * -(this.Head.rotateAngleX - 0.7F) * 0.75F;
	         modelrenderer.rotateAngleX = (float)((double)modelrenderer.rotateAngleX - ((double)f2 * 1.2D + (double)f3));
	         modelrenderer.rotateAngleY += this.bipedBody.rotateAngleY * 2.0F;
	         modelrenderer.rotateAngleZ += MathHelper.sin(this.swingProgress * (float)Math.PI) * -0.4F;
	      }

	      if (this.isSneak) {
	         this.bipedBody.rotateAngleX = 0.5F;
	         this.RightArm.rotateAngleX += 0.4F;
	         this.LeftArm.rotateAngleX += 0.4F;
	         this.RightLeg.rotationPointZ = 4.0F;
	         this.LeftLeg.rotationPointZ = 4.0F;
	         this.RightLeg.rotationPointY = 12.2F;
	         this.LeftLeg.rotationPointY = 12.2F;
	         this.Head.rotationPointY = 4.2F;
	         this.bipedBody.rotationPointY = 3.2F;
	         this.LeftArm.rotationPointY = 5.2F;
	         this.RightArm.rotationPointY = 5.2F;
	      } else {
	         this.bipedBody.rotateAngleX = 0.0F;
	         this.RightLeg.rotationPointZ = 0.1F;
	         this.LeftLeg.rotationPointZ = 0.1F;
	         this.RightLeg.rotationPointY = 12.0F;
	         this.LeftLeg.rotationPointY = 12.0F;
	         this.Head.rotationPointY = 0.0F;
	         this.bipedBody.rotationPointY = 0.0F;
	         this.LeftArm.rotationPointY = 2.0F;
	         this.RightArm.rotationPointY = 2.0F;
	      }

	      this.RightArm.rotateAngleZ += MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
	      this.LeftArm.rotateAngleZ -= MathHelper.cos(ageInTicks * 0.09F) * 0.05F + 0.05F;
	      this.RightArm.rotateAngleX += MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	      this.LeftArm.rotateAngleX -= MathHelper.sin(ageInTicks * 0.067F) * 0.05F;
	      if (this.rightArmPose == BipedModel.ArmPose.BOW_AND_ARROW) {
	         this.RightArm.rotateAngleY = -0.1F + this.Head.rotateAngleY;
	         this.LeftArm.rotateAngleY = 0.1F + this.Head.rotateAngleY + 0.4F;
	         this.RightArm.rotateAngleX = (-(float)Math.PI / 2F) + this.Head.rotateAngleX;
	         this.LeftArm.rotateAngleX = (-(float)Math.PI / 2F) + this.Head.rotateAngleX;
	      } else if (this.leftArmPose == BipedModel.ArmPose.BOW_AND_ARROW && this.rightArmPose != BipedModel.ArmPose.THROW_SPEAR && this.rightArmPose != BipedModel.ArmPose.BLOCK) {
	         this.RightArm.rotateAngleY = -0.1F + this.Head.rotateAngleY - 0.4F;
	         this.LeftArm.rotateAngleY = 0.1F + this.Head.rotateAngleY;
	         this.RightArm.rotateAngleX = (-(float)Math.PI / 2F) + this.Head.rotateAngleX;
	         this.LeftArm.rotateAngleX = (-(float)Math.PI / 2F) + this.Head.rotateAngleX;
	      }

	      float remainingItemUseTime = ObfuscationReflectionHelper.getPrivateValue(BipedModel.class, this, "field_217149_a");
	      float f4 = (float)CrossbowItem.getChargeTime(entityIn.getActiveItemStack());
	      if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
	         this.RightArm.rotateAngleY = -0.8F;
	         this.RightArm.rotateAngleX = -0.97079635F;
	         this.LeftArm.rotateAngleX = -0.97079635F;
	         float f5 = MathHelper.clamp(remainingItemUseTime, 0.0F, f4);
	         this.LeftArm.rotateAngleY = MathHelper.lerp(f5 / f4, 0.4F, 0.85F);
	         this.LeftArm.rotateAngleX = MathHelper.lerp(f5 / f4, this.LeftArm.rotateAngleX, (-(float)Math.PI / 2F));
	      } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_CHARGE) {
	         this.LeftArm.rotateAngleY = 0.8F;
	         this.RightArm.rotateAngleX = -0.97079635F;
	         this.LeftArm.rotateAngleX = -0.97079635F;
	         float f6 = MathHelper.clamp(remainingItemUseTime, 0.0F, f4);
	         this.RightArm.rotateAngleY = MathHelper.lerp(f6 / f4, -0.4F, -0.85F);
	         this.RightArm.rotateAngleX = MathHelper.lerp(f6 / f4, this.RightArm.rotateAngleX, (-(float)Math.PI / 2F));
	      }

	      if (this.rightArmPose == BipedModel.ArmPose.CROSSBOW_HOLD && this.swingProgress <= 0.0F) {
	         this.RightArm.rotateAngleY = -0.3F + this.Head.rotateAngleY;
	         this.LeftArm.rotateAngleY = 0.6F + this.Head.rotateAngleY;
	         this.RightArm.rotateAngleX = (-(float)Math.PI / 2F) + this.Head.rotateAngleX + 0.1F;
	         this.LeftArm.rotateAngleX = -1.5F + this.Head.rotateAngleX;
	      } else if (this.leftArmPose == BipedModel.ArmPose.CROSSBOW_HOLD) {
	         this.RightArm.rotateAngleY = -0.6F + this.Head.rotateAngleY;
	         this.LeftArm.rotateAngleY = 0.3F + this.Head.rotateAngleY;
	         this.RightArm.rotateAngleX = -1.5F + this.Head.rotateAngleX;
	         this.LeftArm.rotateAngleX = (-(float)Math.PI / 2F) + this.Head.rotateAngleX + 0.1F;
	      }

	      if (this.swimAnimation > 0.0F) {
	         float f7 = limbSwing % 26.0F;
	         float f8 = this.swingProgress > 0.0F ? 0.0F : this.swimAnimation;
	         if (f7 < 14.0F) {
	            this.LeftArm.rotateAngleX = this.rotLerpRad(this.LeftArm.rotateAngleX, 0.0F, this.swimAnimation);
	            this.RightArm.rotateAngleX = MathHelper.lerp(f8, this.RightArm.rotateAngleX, 0.0F);
	            this.LeftArm.rotateAngleY = this.rotLerpRad(this.LeftArm.rotateAngleY, (float)Math.PI, this.swimAnimation);
	            this.RightArm.rotateAngleY = MathHelper.lerp(f8, this.RightArm.rotateAngleY, (float)Math.PI);
	            this.LeftArm.rotateAngleZ = this.rotLerpRad(this.LeftArm.rotateAngleZ, (float)Math.PI + 1.8707964F * this.getArmAngle(f7) / this.getArmAngle(14.0F), this.swimAnimation);
	            this.RightArm.rotateAngleZ = MathHelper.lerp(f8, this.RightArm.rotateAngleZ, (float)Math.PI - 1.8707964F * this.getArmAngle(f7) / this.getArmAngle(14.0F));
	         } else if (f7 >= 14.0F && f7 < 22.0F) {
	            float f10 = (f7 - 14.0F) / 8.0F;
	            this.LeftArm.rotateAngleX = this.rotLerpRad(this.LeftArm.rotateAngleX, ((float)Math.PI / 2F) * f10, this.swimAnimation);
	            this.RightArm.rotateAngleX = MathHelper.lerp(f8, this.RightArm.rotateAngleX, ((float)Math.PI / 2F) * f10);
	            this.LeftArm.rotateAngleY = this.rotLerpRad(this.LeftArm.rotateAngleY, (float)Math.PI, this.swimAnimation);
	            this.RightArm.rotateAngleY = MathHelper.lerp(f8, this.RightArm.rotateAngleY, (float)Math.PI);
	            this.LeftArm.rotateAngleZ = this.rotLerpRad(this.LeftArm.rotateAngleZ, 5.012389F - 1.8707964F * f10, this.swimAnimation);
	            this.RightArm.rotateAngleZ = MathHelper.lerp(f8, this.RightArm.rotateAngleZ, 1.2707963F + 1.8707964F * f10);
	         } else if (f7 >= 22.0F && f7 < 26.0F) {
	            float f9 = (f7 - 22.0F) / 4.0F;
	            this.LeftArm.rotateAngleX = this.rotLerpRad(this.LeftArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f9, this.swimAnimation);
	            this.RightArm.rotateAngleX = MathHelper.lerp(f8, this.RightArm.rotateAngleX, ((float)Math.PI / 2F) - ((float)Math.PI / 2F) * f9);
	            this.LeftArm.rotateAngleY = this.rotLerpRad(this.LeftArm.rotateAngleY, (float)Math.PI, this.swimAnimation);
	            this.RightArm.rotateAngleY = MathHelper.lerp(f8, this.RightArm.rotateAngleY, (float)Math.PI);
	            this.LeftArm.rotateAngleZ = this.rotLerpRad(this.LeftArm.rotateAngleZ, (float)Math.PI, this.swimAnimation);
	            this.RightArm.rotateAngleZ = MathHelper.lerp(f8, this.RightArm.rotateAngleZ, (float)Math.PI);
	         }

	         float f11 = 0.3F;
	         float f12 = 0.33333334F;
	         this.LeftLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.LeftLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F + (float)Math.PI));
	         this.RightLeg.rotateAngleX = MathHelper.lerp(this.swimAnimation, this.RightLeg.rotateAngleX, 0.3F * MathHelper.cos(limbSwing * 0.33333334F));
	      }

	      this.bipedHeadwear.copyModelAngles(this.Head);
	}

	   private float getArmAngle(float limbSwing) {
		      return -65.0F * limbSwing + limbSwing * limbSwing;
		   }

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float r, float g, float b, float a){
		Head.render(matrixStack, buffer, packedLight, packedOverlay,r,g,b,a);
		Body.render(matrixStack, buffer, packedLight, packedOverlay,r,g,b,a);
		RightArm.render(matrixStack, buffer, packedLight, packedOverlay,r,g,b,a);
		LeftArm.render(matrixStack, buffer, packedLight, packedOverlay,r,g,b,a);
		RightLeg.render(matrixStack, buffer, packedLight, packedOverlay,r,g,b,a);
		LeftLeg.render(matrixStack, buffer, packedLight, packedOverlay,r,g,b,a);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}