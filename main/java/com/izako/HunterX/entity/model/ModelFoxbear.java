package com.izako.HunterX.entity.model;
//Made with Blockbench
//Paste this code into your mod.

import org.lwjgl.opengl.GL11;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

public class ModelFoxbear extends ModelBase {
	private final ModelRenderer bone;
	private final ModelRenderer bone2;
	private final ModelRenderer bone3;
	private final ModelRenderer bone4;
	private final ModelRenderer bone5;
	private final ModelRenderer bone6;
	private final ModelRenderer bone7;
	private final ModelRenderer bone8;
	private final ModelRenderer FrontLleg1;
	private final ModelRenderer FrontLleg2;
	private final ModelRenderer BackLleg1;
	private final ModelRenderer BackLleg2;
	private final ModelRenderer BackRleg1;
	private final ModelRenderer BackRleg2;
	private final ModelRenderer FrontRleg1;
	private final ModelRenderer FrontRleg2;

	public ModelFoxbear () {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(2.0F, 15.0F, 3.0F);
		setRotationAngle(bone, -0.2618F, 0.0F, 0.0F);
		bone.cubeList.add(new ModelBox(bone, 56, 56, -8.8F, -15.9969F, -4.3024F, 14, 12, 12, 0.0F, false));

		bone2 = new ModelRenderer(this);
		bone2.setRotationPoint(2.0F, 13.9F, -5.5F);
		bone2.cubeList.add(new ModelBox(bone2, 0, 36, -9.8F, -16.1F, -9.5F, 16, 14, 18, 0.0F, false));
		bone2.cubeList.add(new ModelBox(bone2, 0, 0, -7.8F, -14.6F, -11.5F, 12, 14, 22, 0.0F, false));

		bone3 = new ModelRenderer(this);
		bone3.setRotationPoint(0.0F, 0.25F, -2.25F);
		setRotationAngle(bone3, 0.2618F, 0.0F, 0.0F);
		bone2.addChild(bone3);

		bone4 = new ModelRenderer(this);
		bone4.setRotationPoint(2.0F, 14.5F, 7.25F);
		setRotationAngle(bone4, -0.6109F, 0.0F, 0.0F);
		bone4.cubeList.add(new ModelBox(bone4, 24, 68, -3.8F, -11.7861F, -1.223F, 4, 4, 3, 0.0F, false));

		bone5 = new ModelRenderer(this);
		bone5.setRotationPoint(2.0F, 15.05F, 6.8F);
		setRotationAngle(bone5, 0.2618F, 0.0F, 0.0F);
		bone5.cubeList.add(new ModelBox(bone5, 50, 36, -2.8F, -5.8591F, 7.8267F, 2, 12, 2, 0.0F, false));
		bone5.cubeList.add(new ModelBox(bone5, 68, 48, -3.8F, 5.7313F, 6.9134F, 4, 4, 4, 0.0F, false));

		bone6 = new ModelRenderer(this);
		bone6.setRotationPoint(0.0F, 23.6F, 0.9F);
		bone6.cubeList.add(new ModelBox(bone6, 60, 28, -3.8F, -25.0F, -25.6F, 8, 12, 8, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 46, 0, -5.8F, -23.0F, -25.6F, 12, 10, 8, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 12, -3.8F, -21.0F, -27.6F, 8, 8, 2, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 0, 94, -1.8F, -18.0F, -31.6F, 4, 5, 4, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 46, 18, -5.8F, -18.0F, -28.2F, 4, 4, 0, 0.0F, false));
		bone6.cubeList.add(new ModelBox(bone6, 46, 4, 2.2F, -18.0F, -28.45F, 4, 4, 0, 0.0F, false));

		bone7 = new ModelRenderer(this);
		bone7.setRotationPoint(-0.75F, 10.75F, -11.5F);
		setRotationAngle(bone7, 0.0F, -0.3491F, -0.3491F);
		bone7.cubeList.add(new ModelBox(bone7, 68, 18, -6.1813F, -16.3281F, -10.1869F, 4, 6, 2, 0.0F, false));
		bone7.cubeList.add(new ModelBox(bone7, 0, 48, -5.1813F, -18.3281F, -10.1869F, 2, 2, 2, 0.0F, false));

		bone8 = new ModelRenderer(this);
		bone8.setRotationPoint(4.75F, 10.75F, -11.5F);
		setRotationAngle(bone8, 0.0F, 0.3491F, 0.3491F);
		bone8.cubeList.add(new ModelBox(bone8, 55, 96, -0.8189F, -14.5744F, -12.7348F, 4, 6, 2, 0.0F, false));
		bone8.cubeList.add(new ModelBox(bone8, 46, 0, 0.1811F, -16.5744F, -12.7348F, 2, 2, 2, 0.0F, false));

		FrontLleg1 = new ModelRenderer(this);
		FrontLleg1.setRotationPoint(4.75F, 18.25F, -6.25F);
		setRotationAngle(FrontLleg1, 0.3491F, 0.0F, 0.0F);
		FrontLleg1.cubeList.add(new ModelBox(FrontLleg1, 84, 84, -1.05F, -10.356F, -7.1139F, 6, 8, 6, 0.0F, false));

		FrontLleg2 = new ModelRenderer(this);
		FrontLleg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		FrontLleg2.cubeList.add(new ModelBox(FrontLleg2, 0, 36, 4.8F, -8.7F, -13.0F, 4, 8, 4, 0.0F, false));
		FrontLleg2.cubeList.add(new ModelBox(FrontLleg2, 78, 0, 3.8F, -2.5F, -15.6F, 6, 2, 6, 0.0F, false));

		BackLleg1 = new ModelRenderer(this);
		BackLleg1.setRotationPoint(4.75F, 18.0F, 1.5F);
		setRotationAngle(BackLleg1, -0.2618F, 0.0F, 0.0F);
		BackLleg1.cubeList.add(new ModelBox(BackLleg1, 58, 80, -2.55F, -9.9482F, -2.6932F, 7, 10, 6, 0.0F, false));

		BackLleg2 = new ModelRenderer(this);
		BackLleg2.setRotationPoint(0.0F, 24.0F, 0.0F);
		BackLleg2.cubeList.add(new ModelBox(BackLleg2, 0, 0, 3.6F, -7.5F, -0.6F, 5, 7, 5, 0.0F, false));
		BackLleg2.cubeList.add(new ModelBox(BackLleg2, 85, 41, 3.1F, -2.1F, -2.6F, 6, 2, 7, 0.0F, false));

		BackRleg1 = new ModelRenderer(this);
		BackRleg1.setRotationPoint(-0.7F, 18.0F, 1.5F);
		setRotationAngle(BackRleg1, -0.2618F, 0.0F, 0.0F);
		BackRleg1.cubeList.add(new ModelBox(BackRleg1, 32, 74, -7.5F, -9.8258F, -2.7639F, 7, 10, 6, 0.0F, false));

		BackRleg2 = new ModelRenderer(this);
		BackRleg2.setRotationPoint(-5.0F, 24.0F, 0.0F);
		BackRleg2.cubeList.add(new ModelBox(BackRleg2, 19, 90, -2.2F, -7.5F, -0.7F, 5, 7, 5, 0.0F, false));
		BackRleg2.cubeList.add(new ModelBox(BackRleg2, 84, 26, -2.7F, -2.1F, -2.7F, 6, 2, 7, 0.0F, false));

		FrontRleg1 = new ModelRenderer(this);
		FrontRleg1.setRotationPoint(-1.8F, 18.25F, -6.25F);
		setRotationAngle(FrontRleg1, 0.3491F, 0.0F, 0.0F);
		FrontRleg1.cubeList.add(new ModelBox(FrontRleg1, 80, 12, -7.6F, -10.3646F, -7.43F, 6, 8, 6, 0.0F, false));

		FrontRleg2 = new ModelRenderer(this);
		FrontRleg2.setRotationPoint(-6.6F, 24.0F, 0.0F);
		FrontRleg2.cubeList.add(new ModelBox(FrontRleg2, 39, 90, -1.8F, -8.7F, -13.0F, 4, 8, 4, 0.0F, false));
		FrontRleg2.cubeList.add(new ModelBox(FrontRleg2, 0, 86, -2.8F, -2.5F, -15.6F, 6, 2, 6, 0.0F, false));
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		bone.render(f5);
		bone2.render(f5);
		bone4.render(f5);
		bone5.render(f5);
		bone6.render(f5);
		bone7.render(f5);
		bone8.render(f5);
		FrontLleg1.render(f5);
		FrontLleg2.render(f5);
		BackLleg1.render(f5);
		BackLleg2.render(f5);
		BackRleg1.render(f5);
		BackRleg2.render(f5);
		FrontRleg1.render(f5);
		FrontRleg2.render(f5);
	}
	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
	
	public void setRotationAngles(float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw,
			float headPitch, float scaleFactor, Entity entityIn) { 
		FrontLleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount ;
		FrontRleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F  + (float) Math.PI) * 0.5F * limbSwingAmount ;
		
		FrontLleg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.1F * limbSwingAmount;
		FrontRleg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F  + (float) Math.PI) * 0.1F * limbSwingAmount;
		
		BackLleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.5F * limbSwingAmount ;
		BackRleg1.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F  + (float) Math.PI) * 0.5F * limbSwingAmount;
		
		BackLleg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F) * 0.1F * limbSwingAmount ;
		BackRleg2.rotateAngleX = MathHelper.cos(limbSwing * 0.6662F  + (float) Math.PI) * 0.1F * limbSwingAmount;
		
		FrontLleg2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount;
		FrontRleg2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F  + (float) Math.PI) * 0.2F * limbSwingAmount;
		
		BackLleg2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F) * 0.2F * limbSwingAmount ;
		BackRleg2.rotateAngleY = MathHelper.cos(limbSwing * 0.6662F  + (float) Math.PI) * 0.2F * limbSwingAmount;
		
		
		
	}
}