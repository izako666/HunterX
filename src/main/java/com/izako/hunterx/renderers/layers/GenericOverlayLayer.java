package com.izako.hunterx.renderers.layers;

import java.awt.Color;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.util.math.Vec3d;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class GenericOverlayLayer extends LayerRenderer<AbstractClientPlayerEntity,PlayerModel<AbstractClientPlayerEntity>>{

	LivingRenderer renderer;
	Color color;
	public GenericOverlayLayer(IEntityRenderer entityRendererIn) {
		super(entityRendererIn);
		this.renderer = (LivingRenderer) entityRendererIn;

	}

	@Override
	public void render(AbstractClientPlayerEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		
		
		IAbilityData abilities = AbilityDataCapability.get(entity);
		IAbilityData clientData = AbilityDataCapability.get(Minecraft.getInstance().player);
		for(Ability abl : abilities.getSlotAbilities()) {
			if(abl == null)
				continue;
			if(abl.equals(ModAbilities.TEN_ABILITY) && abl.isInPassive()) {
				color = abilities.getAuraColor();
				
			} else if(abl.equals(ModAbilities.ZETSU_ABILITY) && abl.isInPassive() && entity.getUniqueID() == Minecraft.getInstance().player.getUniqueID()){
				color = Color.BLACK;
			}else {
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

	                   GlStateManager.scaled(1.05, 1.04, 1.05);

	                    renderer.getEntityModel().render(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, scale);

	    

	                    GlStateManager.enableTexture();
	                    GlStateManager.color4f(1f, 1f, 1f, 1f);

	                    GlStateManager.enableCull();

	                  GlStateManager.enableLighting();

	                  GlStateManager.disableBlend();

	                }

	                GlStateManager.popMatrix();
		}
		
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
