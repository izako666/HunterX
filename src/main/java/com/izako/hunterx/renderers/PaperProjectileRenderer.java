package com.izako.hunterx.renderers;

import com.izako.hunterx.entities.projectiles.PaperProjectileEntity;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class PaperProjectileRenderer<T extends PaperProjectileEntity> extends EntityRenderer<T>{

	public PaperProjectileRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) {
		return null;
	}

	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
	}

}
