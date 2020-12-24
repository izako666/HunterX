package com.izako.hunterx.entities.models;

import com.izako.hunterx.entities.projectiles.ArmEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.Entity;


public class ArmModel extends EntityModel<ArmEntity> {
	private final ModelRenderer arm;

	public ArmModel() {
		textureWidth = 32;
		textureHeight = 32;

		arm = new ModelRenderer(this);
		arm.setRotationPoint(-2.0F, 6.5F, 2.0F);
		setRotationAngle(arm, 1.5708F, 0.0F, 0.0F);
		arm.setTextureOffset(0, 0).addBox(-2.0F, -8.5F, -2.0F, 4.0F, 17.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(ArmEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		arm.render(matrixStack, buffer, packedLight, packedOverlay,red,green,blue,alpha);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}