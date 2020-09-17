package com.izako.hunterx.entities.models;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class EnSphereModel extends EntityModel<Entity> {
	private final ModelRenderer bone;

	public EnSphereModel() {
		textureWidth = 128;
		textureHeight = 128;

		bone = new ModelRenderer(this);
		bone.setRotationPoint(0.0F, 24.0F, 0.0F);
		bone.setTextureOffset(0, 38).addBox(-1.0F, -11.0F, 0.0F, 3.0F, 11.0F, 3.0F, 0.0F, false);
		bone.setTextureOffset(36, 54).addBox(-1.0F, -11.0F, 3.0F, 3.0F, 11.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(24, 56).addBox(-1.0F, -11.0F, -1.0F, 3.0F, 11.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(48, 32).addBox(2.0F, -11.0F, 0.0F, 1.0F, 11.0F, 3.0F, 0.0F, false);
		bone.setTextureOffset(48, 48).addBox(-2.0F, -11.0F, 0.0F, 1.0F, 11.0F, 3.0F, 0.0F, false);
		bone.setTextureOffset(28, 28).addBox(-2.0F, -10.0F, -1.0F, 5.0F, 9.0F, 5.0F, 0.0F, false);
		bone.setTextureOffset(0, 24).addBox(-3.0F, -9.0F, -2.0F, 7.0F, 7.0F, 7.0F, 0.0F, false);
		bone.setTextureOffset(0, 12).addBox(-4.0F, -8.0F, -2.0F, 9.0F, 5.0F, 7.0F, 0.0F, false);
		bone.setTextureOffset(25, 5).addBox(-2.0F, -7.0F, -2.0F, 7.0F, 3.0F, 7.0F, 0.0F, false);
		bone.setTextureOffset(0, 52).addBox(-2.0F, -10.0F, -2.0F, 5.0F, 9.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(46, 0).addBox(-2.0F, -10.0F, 4.0F, 5.0F, 9.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(12, 38).addBox(3.0F, -10.0F, -1.0F, 1.0F, 9.0F, 5.0F, 0.0F, false);
		bone.setTextureOffset(24, 42).addBox(-3.0F, -10.0F, -1.0F, 1.0F, 9.0F, 5.0F, 0.0F, false);
		bone.setTextureOffset(36, 42).addBox(-4.0F, -9.0F, -1.0F, 1.0F, 7.0F, 5.0F, 0.0F, false);
		bone.setTextureOffset(32, 15).addBox(4.0F, -9.0F, -1.0F, 1.0F, 7.0F, 5.0F, 0.0F, false);
		bone.setTextureOffset(56, 32).addBox(-2.0F, -9.0F, 5.0F, 5.0F, 7.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(56, 56).addBox(-2.0F, -9.0F, -3.0F, 5.0F, 7.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(43, 26).addBox(-3.0F, -8.0F, -3.0F, 7.0F, 5.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(44, 15).addBox(-3.0F, -8.0F, 5.0F, 7.0F, 5.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(21, 24).addBox(-1.0F, -8.0F, 6.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(58, 21).addBox(-1.0F, -8.0F, -4.0F, 3.0F, 5.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(23, 0).addBox(-2.0F, -7.0F, -4.0F, 5.0F, 3.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(25, 15).addBox(-2.0F, -7.0F, 6.0F, 5.0F, 3.0F, 1.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(5.0F, -8.0F, 0.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);
		bone.setTextureOffset(53, 43).addBox(-5.0F, -8.0F, 0.0F, 1.0F, 5.0F, 3.0F, 0.0F, false);
		bone.setTextureOffset(53, 5).addBox(-5.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		bone.setTextureOffset(12, 52).addBox(5.0F, -7.0F, -1.0F, 1.0F, 3.0F, 5.0F, 0.0F, false);
		bone.setTextureOffset(0, 0).addBox(-3.0F, -7.0F, -3.0F, 7.0F, 3.0F, 9.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){

	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		bone.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}