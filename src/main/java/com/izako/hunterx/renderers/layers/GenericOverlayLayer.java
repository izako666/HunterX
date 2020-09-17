package com.izako.hunterx.renderers.layers;

import java.awt.Color;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.renderers.ModRenderTypes;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@SuppressWarnings({ "unchecked", "rawtypes" })
@OnlyIn(Dist.CLIENT)
public class GenericOverlayLayer<T extends LivingEntity, M extends EntityModel<T>> extends LayerRenderer<T, M> {

	LivingRenderer renderer;
	Color color;

	public GenericOverlayLayer(IEntityRenderer entityRendererIn) {
		super(entityRendererIn);
		this.renderer = (LivingRenderer) entityRendererIn;

	}

	/*
	 * @Override public void render(AbstractClientPlayerEntity entity, float
	 * limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float
	 * netHeadYaw, float headPitch, float scale) {
	 * 
	 * 
	 * IAbilityData abilities = AbilityDataCapability.get(entity); IAbilityData
	 * clientData = AbilityDataCapability.get(Minecraft.getInstance().player); float
	 * layerScale = 1.05f; for(Ability abl : abilities.getSlotAbilities()) { if(abl
	 * == null) continue; if(abl.equals(ModAbilities.TEN_ABILITY) &&
	 * abl.isInPassive()) { color = abilities.getAuraColor();
	 * 
	 * } else if(abl.equals(ModAbilities.ZETSU_ABILITY) && abl.isInPassive() &&
	 * entity.getUniqueID() == Minecraft.getInstance().player.getUniqueID()){ color
	 * = Color.BLACK; }else if(abl.equals(ModAbilities.REN_ABILITY) &&
	 * abl.isInPassive()) { color = abilities.getAuraColor(); layerScale = 1.1f; }
	 * else { continue; } GlStateManager.pushMatrix();
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
	 * GlStateManager.scaled(layerScale, layerScale - 0.01f, layerScale);
	 * 
	 * renderer.getEntityModel().render(entity, limbSwing, limbSwingAmount,
	 * ageInTicks, netHeadYaw, headPitch, scale);
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
	 * GlStateManager.popMatrix(); }
	 * 
	 * }
	 */

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {

		IAbilityData abilities = AbilityDataCapability.get(entitylivingbaseIn);
		float layerScale = 1.05f;
		boolean doGyoRendering = true;
		float transparency = 0.2f;
		boolean inActive = abilities.hasActiveAbility(ModAbilities.IN_ABILITY);
		if (entitylivingbaseIn instanceof PlayerEntity) {
			for (Ability abl : abilities.getSlotAbilities()) {
				if (abl == null)
					continue;
				if (abl.equals(ModAbilities.TEN_ABILITY) && abl.isInPassive() && !inActive) {
					color = abilities.getAuraColor();
					doGyoRendering = false;

				} else if (abl.equals(ModAbilities.ZETSU_ABILITY) && abl.isInPassive()
						&& entitylivingbaseIn.getUniqueID() == Minecraft.getInstance().player.getUniqueID()) {
					color = Color.BLACK;
					doGyoRendering = false;

				} else if (abl.equals(ModAbilities.REN_ABILITY) && abl.isInPassive() && !inActive) {
					color = abilities.getAuraColor();
					doGyoRendering = false;
				} else if (abl.equals(ModAbilities.IN_ABILITY) && abl.isInPassive()
						&& entitylivingbaseIn.getUniqueID() == Minecraft.getInstance().player.getUniqueID()) {
					color = Color.WHITE;
					doGyoRendering = false;
					transparency = 0.1f;
				} else if (abl.equals(ModAbilities.KEN_ABILITY) && abl.isInPassive()) {
					color = abilities.getAuraColor();
					doGyoRendering = false;
					layerScale = 1.1f;
				} else {
					continue;
				}
				matrixStackIn.push();

				matrixStackIn.scale(layerScale, layerScale - 0.01f, layerScale);
				IVertexBuilder vertexBuilder = bufferIn.getBuffer(ModRenderTypes.getTranslucentEntity());
				renderer.getEntityModel().render(matrixStackIn, vertexBuilder, packedLightIn, 1,
						(float) (color.getRed()) / 255, (float) (color.getGreen()) / 255,
						((float) color.getBlue()) / 255, transparency);

				matrixStackIn.pop();
			}
		} else {
			for (Ability abl : abilities.getAbilities()) {
				if (abl == null)
					continue;
				if (abl.equals(ModAbilities.TEN_ABILITY) && abl.isInPassive()) {
					color = abilities.getAuraColor();
					doGyoRendering = false;

				} else if (abl.equals(ModAbilities.ZETSU_ABILITY) && abl.isInPassive()
						&& entitylivingbaseIn.getUniqueID() == Minecraft.getInstance().player.getUniqueID()) {
					color = Color.BLACK;
					doGyoRendering = false;

				} else if (abl.equals(ModAbilities.REN_ABILITY) && abl.isInPassive()) {
					color = abilities.getAuraColor();
					layerScale = 1.1f;
					doGyoRendering = false;
				}else if (abl.equals(ModAbilities.KEN_ABILITY) && abl.isInPassive() && !inActive) {
					color = abilities.getAuraColor();
					doGyoRendering = false;
					layerScale = 1.1f;
				} else {
					continue;
				}
				matrixStackIn.push();

				matrixStackIn.scale(layerScale, layerScale - 0.01f, layerScale);
				IVertexBuilder vertexBuilder = bufferIn.getBuffer(ModRenderTypes.getTranslucentEntity());
				renderer.getEntityModel().render(matrixStackIn, vertexBuilder, packedLightIn, 1,
						(float) (color.getRed()) / 255, (float) (color.getGreen()) / 255,
						((float) color.getBlue()) / 255, 0.2f);

				matrixStackIn.pop();
			}

		}
		IAbilityData clientData = AbilityDataCapability.get(Minecraft.getInstance().player);
		if (doGyoRendering && clientData.getSlotAbility(ModAbilities.GYO_ABILITY) != null
				&& clientData.getSlotAbility(ModAbilities.GYO_ABILITY).isInPassive()
				&& Minecraft.getInstance().player.getUniqueID() != entitylivingbaseIn.getUniqueID()) {
			color = abilities.getAuraColor();

			matrixStackIn.push();

			matrixStackIn.scale(layerScale, layerScale - 0.01f, layerScale);
			IVertexBuilder vertexBuilder = bufferIn.getBuffer(ModRenderTypes.getTranslucentEntity());
			renderer.getEntityModel().render(matrixStackIn, vertexBuilder, packedLightIn, 1,
					(float) (color.getRed()) / 255, (float) (color.getGreen()) / 255, ((float) color.getBlue()) / 255,
					0.2f);

			matrixStackIn.pop();

		}

	}

}
