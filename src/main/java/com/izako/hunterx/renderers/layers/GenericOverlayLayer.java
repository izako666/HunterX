package com.izako.hunterx.renderers.layers;

import java.awt.Color;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.renderers.ModRenderTypes;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenericOverlayLayer extends LayerRenderer<AbstractClientPlayerEntity,PlayerModel<AbstractClientPlayerEntity>>{

	LivingRenderer renderer;
	Color color;
	public GenericOverlayLayer(IEntityRenderer entityRendererIn) {
		super(entityRendererIn);
		this.renderer = (LivingRenderer) entityRendererIn;

	}

	/*@Override
	public void render(AbstractClientPlayerEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		
		IAbilityData abilities = AbilityDataCapability.get(entity);
		IAbilityData clientData = AbilityDataCapability.get(Minecraft.getInstance().player);
		float layerScale = 1.05f;
		for(Ability abl : abilities.getSlotAbilities()) {
			if(abl == null)
				continue;
			if(abl.equals(ModAbilities.TEN_ABILITY) && abl.isInPassive()) {
				color = abilities.getAuraColor();
				
			} else if(abl.equals(ModAbilities.ZETSU_ABILITY) && abl.isInPassive() && entity.getUniqueID() == Minecraft.getInstance().player.getUniqueID()){
				color = Color.BLACK;
			}else if(abl.equals(ModAbilities.REN_ABILITY) && abl.isInPassive()) {
				color = abilities.getAuraColor();
				layerScale = 1.1f;
			} else {
				continue;
			}
	    GlStateManager.pushMatrix();

	                {

	                    GlStateManager.disableTexture();

	                    GlStateManager.enableBlend();

	                    GlStateManager.disableLighting();

	                    GlStateManager.disableCull();

	                    GlStateManager.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);

	                    GlStateManager.color4f((float)(color.getRed()) / 255, (float)(color.getGreen()) / 255 , ((float)color.getBlue()) / 255, 0.2f);

	                   GlStateManager.scaled(layerScale, layerScale - 0.01f, layerScale);

	                    renderer.getEntityModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

	    

	                    GlStateManager.enableTexture();
	                    GlStateManager.color4f(1f, 1f, 1f, 1f);

	                    GlStateManager.enableCull();

	                  GlStateManager.enableLighting();

	                  GlStateManager.disableBlend();

	                }

	                GlStateManager.popMatrix();
		}
		
	}*/



	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			AbstractClientPlayerEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
		
		IAbilityData abilities = AbilityDataCapability.get(entitylivingbaseIn);
		float layerScale = 1.05f;
		for(Ability abl : abilities.getSlotAbilities()) {
			if(abl == null)
				continue;
			if(abl.equals(ModAbilities.TEN_ABILITY) && abl.isInPassive()) {
				color = abilities.getAuraColor();
				
			} else if(abl.equals(ModAbilities.ZETSU_ABILITY) && abl.isInPassive() && entitylivingbaseIn.getUniqueID() == Minecraft.getInstance().player.getUniqueID()){
				color = Color.BLACK;
			}else if(abl.equals(ModAbilities.REN_ABILITY) && abl.isInPassive()) {
				color = abilities.getAuraColor();
				layerScale = 1.1f;
			} else {
				continue;
			}
	    matrixStackIn.push();

	                









	                   matrixStackIn.scale(layerScale, layerScale - 0.01f, layerScale);
	                   IVertexBuilder vertexBuilder = bufferIn.getBuffer(ModRenderTypes.getTranslucentEntity());
	                  renderer.getEntityModel().render(matrixStackIn, vertexBuilder, packedLightIn, 1, (float)(color.getRed()) , (float)(color.getGreen()) , ((float)color.getBlue()), 0.2f);
	    	                
	                matrixStackIn.pop();
		}
		

	}

}
