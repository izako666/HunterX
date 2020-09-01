package com.izako.hunterx.renderers.layers;

import java.awt.Color;
import java.util.List;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.hunterx.renderers.ModRenderTypes;
import com.izako.hunterx.izapi.ability.IHandOverlay;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class HandOverlayLayer
		extends LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

	public HandOverlayLayer(
			IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> entityRendererIn) {
		super(entityRendererIn);
	}

	/*
	 * @Override public void render(AbstractClientPlayerEntity entity, float
	 * limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float
	 * netHeadYaw, float headPitch, float scale) {
	 * 
	 * IAbilityData data = AbilityDataCapability.get(entity); List<Ability>
	 * abilities = data.getAbilitiesOfType(AbilityType.PASSIVE); Color color =
	 * data.getAuraColor(); for(Ability abl : abilities) { if(abl == null) continue;
	 * 
	 * if(abl.props.type == AbilityType.PASSIVE && abl.isInPassive() && abl
	 * instanceof IHandOverlay) {
	 * 
	 * GlStateManager.pushMatrix();
	 * 
	 * {
	 * 
	 * GlStateManager.disableTexture();
	 * 
	 * GlStateManager.enableBlend();
	 * 
	 * GlStateManager.disableLighting();
	 * 
	 * GlStateManager.disableCull();
	 * 
	 * GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA,
	 * GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
	 * 
	 * GlStateManager.color4f((float)(color.getRed()) / 255,
	 * (float)(color.getGreen()) / 255 , ((float)color.getBlue()) / 255, 0.2f);
	 * 
	 * GlStateManager.scaled(1.1, 1.1, 1.1);
	 * 
	 * this.getEntityModel().bipedRightArm.render(scale);
	 * 
	 * 
	 * 
	 * GlStateManager.enableTexture(); GlStateManager.color4f(1f, 1f, 1f, 1f);
	 * 
	 * GlStateManager.enableCull();
	 * 
	 * GlStateManager.enableLighting();
	 * 
	 * GlStateManager.disableBlend();
	 * 
	 * }
	 * 
	 * GlStateManager.popMatrix();
	 * 
	 * } } }
	 */

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			AbstractClientPlayerEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {

		IAbilityData data = AbilityDataCapability.get(entitylivingbaseIn);
		List<Ability> abilities = data.getAbilitiesOfType(AbilityType.PASSIVE);
		Color color = data.getAuraColor();
		for (Ability abl : abilities) {
			if (abl == null)
				continue;


			if (abl.props.type == AbilityType.PASSIVE && abl.isInPassive() && abl instanceof IHandOverlay) {

				matrixStackIn.push();

				matrixStackIn.scale(1.1f, 1.1f, 1.1f);
				IVertexBuilder vertexBuilder = bufferIn.getBuffer(ModRenderTypes.getTranslucentEntity());
				this.getEntityModel().bipedRightArm.render(matrixStackIn, vertexBuilder, packedLightIn, 1,
						(float) (color.getRed()) / 255, (float) (color.getGreen()) / 255,
						((float) color.getBlue()) / 255, 0.2f);

				matrixStackIn.pop();

			}
		}
	}

}
