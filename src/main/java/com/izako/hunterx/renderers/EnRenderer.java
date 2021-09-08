package com.izako.hunterx.renderers;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.entities.EnEntity;
import com.izako.hunterx.entities.models.EnSphereModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.util.ResourceLocation;

public class EnRenderer<T extends EnEntity> extends EntityRenderer<T>{

	public EnSphereModel model;
	public static final ResourceLocation TEX = new ResourceLocation(Main.MODID, "textures/models/en_sphere.png");
	public EnRenderer(EntityRendererManager renderManager) {
		super(renderManager);
		this.model = new EnSphereModel();
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) {
		return TEX;
	}

	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		//matrixStackIn.translate(entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ());
		IAbilityData data = AbilityDataCapability.get(entityIn.owner);
		matrixStackIn.push();
		matrixStackIn.scale((float)entityIn.renderScale * 2.0f, (float)entityIn.renderScale * 2.0f  , (float)entityIn.renderScale * 2.0f);

		matrixStackIn.translate(0, -1, 0);
		IVertexBuilder builder = bufferIn.getBuffer(RenderType.getEntityTranslucent(this.getEntityTexture(entityIn)));
		this.model.render(matrixStackIn, builder, packedLightIn, packedLightIn, data.getAuraColor().getRed()/ 255f, data.getAuraColor().getGreen()/255f, data.getAuraColor().getBlue()/255f, 0.2f);
		matrixStackIn.pop();
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

}
