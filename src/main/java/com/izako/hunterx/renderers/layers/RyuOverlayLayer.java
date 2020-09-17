package com.izako.hunterx.renderers.layers;

import java.awt.Color;

import com.google.common.collect.ImmutableList;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.renderers.ModRenderTypes;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class RyuOverlayLayer<T extends LivingEntity, M extends BipedModel<T>> extends LayerRenderer<T, M> {

	IEntityRenderer<T, M> renderer;

	public RyuOverlayLayer(IEntityRenderer<T, M> entityRendererIn) {
		super(entityRendererIn);
		this.renderer = entityRendererIn;
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T entitylivingbaseIn,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {

		IAbilityData data = AbilityDataCapability.get(entitylivingbaseIn);

		Color color = data.getAuraColor();
		if (data.hasActiveAbility(ModAbilities.RYU_DEFENSE_ABILITY)) {
			IVertexBuilder vertexBuilder = bufferIn.getBuffer(ModRenderTypes.getTranslucentEntity());
			this.renderModel(matrixStackIn, vertexBuilder, packedLightIn, 1,
					(float) (color.getRed()) / 255, (float) (color.getGreen()) / 255, ((float) color.getBlue()) / 255,
					0.2f,renderer,true);


		} else if(data.hasActiveAbility(ModAbilities.RYU_OFFENSE_ABILITY)) {
			IVertexBuilder vertexBuilder = bufferIn.getBuffer(ModRenderTypes.getTranslucentEntity());
			this.renderModel(matrixStackIn, vertexBuilder, packedLightIn, 1,
					(float) (color.getRed()) / 255, (float) (color.getGreen()) / 255, ((float) color.getBlue()) / 255,
					0.2f,renderer,false);


		}
	}

	public void renderModel(MatrixStack matrixStackIn, IVertexBuilder bufferIn, int packedLightIn, int packedOverlayIn,
			float red, float green, float blue, float alpha, IEntityRenderer<T, M> renderer, boolean defense) {
		BipedModel<T> model = renderer.getEntityModel();

		this.getHeadParts(model).forEach((biped) -> {
			if (defense) {
				matrixStackIn.push();
				matrixStackIn.scale(1.2f, 1.19f, 1.2f);
			}
			biped.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			if (defense) {
				matrixStackIn.pop();
			}

		});
		this.getBodyParts(model).forEach((biped) -> {
			matrixStackIn.push();
			if(biped == model.bipedRightArm || biped == model.bipedLeftArm || biped == model.bipedRightLeg || biped == model.bipedLeftLeg) {
				if(!defense) {
				matrixStackIn.scale(1.2f, 1.2f, 1.2f);
				if(biped == model.bipedLeftLeg || biped == model.bipedRightLeg) {
				matrixStackIn.translate(0f, -0.2, 0f);
				}
				if(biped == model.bipedRightArm) {
					matrixStackIn.translate(0.05f, 0f, 0f);
				} if(biped == model.bipedLeftArm) {
					matrixStackIn.translate(-0.05f, 0f, 0f);
				}
				}
			} else {
				if(defense) {
					matrixStackIn.scale(1.2f, 1.19f, 1.2f);
				}
			}
			biped.render(matrixStackIn, bufferIn, packedLightIn, packedOverlayIn, red, green, blue, alpha);
			matrixStackIn.pop();

		
		});
	}

	protected Iterable<ModelRenderer> getHeadParts(BipedModel<T> model) {
		return ImmutableList.of(model.bipedHead,model.bipedHeadwear);
	}

	protected Iterable<ModelRenderer> getBodyParts(BipedModel<T> model) {
		return ImmutableList.of(model.bipedBody, model.bipedRightArm, model.bipedLeftArm, model.bipedRightLeg,
				model.bipedLeftLeg);
	}

}
