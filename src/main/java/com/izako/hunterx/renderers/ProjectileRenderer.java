package com.izako.hunterx.renderers;

import java.awt.Color;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.entities.ProjectileEntity;
import com.izako.hunterx.entities.models.AuraBlastModel;
import com.izako.hunterx.init.ModAbilities;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class ProjectileRenderer<T extends ProjectileEntity> extends EntityRenderer<T>{

	private ResourceLocation tex;
	private EntityModel<?> model;
	private Color color;
	private float scaleX,scaleY,scaleZ;
	private boolean customScale = false;
	private boolean personalAuraColor = false;
	private boolean isNen;
	protected ProjectileRenderer(EntityRendererManager renderManager) {
		super(renderManager);
	}

	@Override
	public ResourceLocation getEntityTexture(T entity) {
		return this.tex;
	}

	@Override
	public void render(T entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn,
			IRenderTypeBuffer bufferIn, int packedLightIn) {
		IAbilityData cdata = AbilityDataCapability.get(Minecraft.getInstance().player);
		
		if(!cdata.isNenUser() && this.isNen) {
			return;
		}
		if(entityIn.owner == null) 
			return;
		IAbilityData data = AbilityDataCapability.get(entityIn.owner);

		if(data.hasActiveAbility(ModAbilities.IN_ABILITY) && this.isNen) {
			return;
		}
		matrixStackIn.push();
	  
		if(customScale) {
			matrixStackIn.translate(0, -entityIn.scale / 2, 0);

			matrixStackIn.scale((float)entityIn.scale, (float)entityIn.scale, (float)entityIn.scale);
		} else {
			matrixStackIn.translate(0, -scaleY / 2, 0);
		matrixStackIn.scale(scaleX, scaleY, scaleZ);
		}
		IVertexBuilder vertexBuilderIn = bufferIn.getBuffer(RenderType.getEntityTranslucent(tex));
		matrixStackIn.rotate(Vector3f.YP.rotationDegrees(MathHelper.lerp(partialTicks, entityIn.prevRotationYaw, entityIn.rotationYaw) + 90.0F));
	    matrixStackIn.rotate(Vector3f.ZP.rotationDegrees(entityIn.rotationPitch));	
	    

	      Color finalColor = color;
		if(this.personalAuraColor) {
		
			finalColor = data.getAuraColor();
			finalColor = new Color(finalColor.getRed(), finalColor.getGreen(), finalColor.getBlue(),color.getAlpha());
		}
		this.model.render(matrixStackIn, vertexBuilderIn, packedLightIn, OverlayTexture.NO_OVERLAY, finalColor.getRed() / 255f, finalColor.getGreen() / 255f, finalColor.getBlue() / 255f, finalColor.getAlpha() / 255f);
		matrixStackIn.pop();

		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	}

	public static class Factory<T extends ProjectileEntity> implements IRenderFactory<T> {

		float scaleX = 1f,scaleY = 1f,scaleZ = 1f;
		float r = 1f, g = 1f, b = 1f, a = 1f;
		boolean customScale = false;
		boolean personalAuraColor = false;
		boolean isNen = true;
		EntityModel<T> model = new AuraBlastModel<T>();
		public Factory<T> setColor(Color c) {
			this.r = c.getRed() / 255f;
			this.g = c.getGreen() / 255f; 
			this.b = c.getBlue() / 255f;
			this.a = c.getAlpha() / 255f;
			return this;
		}
		public Factory<T> setIsNen(boolean val) {
			this.isNen = val;
			return this;
		}
		public Factory<T> scaleX(float scaleX) {
			this.scaleX = scaleX;
			return this;
		}
		public Factory<T> scaleY(float scaleY) {
			this.scaleY = scaleY;
			return this;
		}
		public Factory<T> scaleZ(float scaleZ) {
			this.scaleZ = scaleZ;
			return this;
		}
		public Factory<T> setModel(EntityModel<T> model) {
			this.model = model;
			return this;
		}
		public Factory<T> setTex(ResourceLocation tex) {
			this.tex = tex;
			return this;
		}
		public Factory<T> setCustomScale(boolean val) {
			this.customScale = val;
			return this;
		}
		public Factory<T> setPersonalAuraColor(boolean val) {
			this.personalAuraColor = val;
			return this;
		}
		
		public Factory<T> scale(float scale) {
			this.scaleX = scale;
			this.scaleY = scale;
			this.scaleZ = scale;
			return this;
		}
		ResourceLocation tex = new ResourceLocation(Main.MODID, "");
		@Override
		public EntityRenderer<T> createRenderFor(EntityRendererManager manager) {
			ProjectileRenderer<T> renderer = new ProjectileRenderer<T>(manager);
			renderer.model = model;
			renderer.tex = tex;
			renderer.scaleX = scaleX;
			renderer.scaleY = scaleY;
			renderer.scaleZ = scaleZ;
			renderer.color = new Color(r,g,b,a);
			renderer.customScale = customScale;
			renderer.personalAuraColor = personalAuraColor;
			renderer.isNen = this.isNen;
			return renderer;
		}
		
		
	}
}
