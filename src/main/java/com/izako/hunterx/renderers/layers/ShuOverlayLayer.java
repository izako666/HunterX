package com.izako.hunterx.renderers.layers;

import java.awt.Color;
import java.util.List;

import com.izako.hunterx.abilities.basics.ShuAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.IZAHelper;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.Ability.AbilityType;
import com.izako.hunterx.renderers.ModRenderTypes;
import com.mojang.blaze3d.matrix.MatrixStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ShuOverlayLayer extends  LayerRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>>{

	public ShuOverlayLayer(
			IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			AbstractClientPlayerEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
		
		IAbilityData data = AbilityDataCapability.get(entitylivingbaseIn);
		List<Ability> abilities = data.getAbilitiesOfType(AbilityType.PASSIVE);
		Color color = data.getAuraColor();
		color = new Color(color.getRed(),color.getGreen(), color.getBlue(), 150);
		for (Ability abl : abilities) {
			if (abl == null)
				continue;


			if (abl.props.type == AbilityType.PASSIVE && abl.isInPassive() && abl instanceof ShuAbility) {

				matrixStackIn.push();
				matrixStackIn.translate(0.04, -0.05, 0.08);
				matrixStackIn.scale(1.1f, 1.1f, 1.1f);

				IZAHelper.renderItemOnPlayer(this, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch, ModRenderTypes.genTranslucentItem(AtlasTexture.LOCATION_BLOCKS_TEXTURE), color);
				matrixStackIn.pop();

			}
		}
	}

}
