package com.izako.hunterx.renderers.layers;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.renderers.ModRenderTypes;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.AbstractEyesLayer;
import net.minecraft.client.renderer.entity.model.PlayerModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class GyoEyesLayer
		extends AbstractEyesLayer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> {

	public static final ResourceLocation loc = new ResourceLocation(Main.MODID, "textures/abilities/gyo_player.png");
	public static final RenderType RENDERTYPE = ModRenderTypes.getCustomEyes(loc);

	public GyoEyesLayer(
			IEntityRenderer<AbstractClientPlayerEntity, PlayerModel<AbstractClientPlayerEntity>> entityRendererIn) {
		super(entityRendererIn);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn,
			AbstractClientPlayerEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) {
		IAbilityData data = AbilityDataCapability.get(entitylivingbaseIn);
		if(data.getSlotAbility(ModAbilities.GYO_ABILITY) != null && data.getSlotAbility(ModAbilities.GYO_ABILITY).isInPassive()) {
		      IVertexBuilder ivertexbuilder = bufferIn.getBuffer(ModRenderTypes.getCustomEyes(loc));
		      this.getEntityModel().bipedHead.render(matrixStackIn, ivertexbuilder, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 0.5F);
		}
	}

	@Override
	public RenderType getRenderType() {
		return RENDERTYPE;
	}

}
