package com.izako.hunterx.items.renderers;


import com.izako.hunterx.Main;
import com.izako.hunterx.items.YoyoItem;
import com.izako.hunterx.items.entities.YoyoEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.Matrix3f;
import net.minecraft.client.renderer.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.Vector3f;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.entity.IRendersAsItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.HandSide;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public class YoyoRenderer<T extends Entity> extends  SpriteRenderer<YoyoEntity> {

	   public YoyoRenderer(EntityRendererManager p_i50956_1_) {
		      super(p_i50956_1_, Minecraft.getInstance().getItemRenderer());
		   }

	
	protected ResourceLocation getYoyoEntityTexture(YoyoEntity entity) {
		return new ResourceLocation(Main.MODID, "textures/items/yoyo.png");
	}

	
	/*  @Override
	  public void doRender(YoyoEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
	        PlayerEntity p = (PlayerEntity) entity.world.getEntityByID(entity.entityId);
		  if(p != null) {
	         Tessellator tessellator = Tessellator.getInstance();
	        BufferBuilder bufferbuilder = tessellator.getBuffer();
	         int i = p.getPrimaryHand() == HandSide.RIGHT ? 1 : -1;
	         ItemStack itemstack = p.getHeldItemMainhand();
	         if (!(itemstack.getItem() instanceof YoyoItem)) {
	            i = -i;
	         }
	         float f3 = p.getSwingProgress(partialTicks);
	         float f4 = MathHelper.sin(MathHelper.sqrt(f3) * (float)Math.PI);
	         float f5 = MathHelper.lerp(partialTicks, p.prevRenderYawOffset, p.renderYawOffset) * ((float)Math.PI / 180F);
	         double d0 = (double)MathHelper.sin(f5);
	         double d1 = (double)MathHelper.cos(f5);
	         double d2 = (double)i * 0.35D;
	         double d3 = 0.8D;
	         double d4;
	         double d5;
	         double d6;
	         double d7;
	         if ((this.renderManager.options == null || this.renderManager.options.thirdPersonView <= 0) && p == Minecraft.getInstance().player) {
	            double d8 = this.renderManager.options.fov;
	            d8 = d8 / 100.0D;
	            Vec3d vec3d = new Vec3d((double)i * -0.36D * d8, -0.045D * d8, 0.4D);
	            vec3d = vec3d.rotatePitch(-MathHelper.lerp(partialTicks, p.prevRotationPitch, p.rotationPitch) * ((float)Math.PI / 180F));
	            vec3d = vec3d.rotateYaw(-MathHelper.lerp(partialTicks, p.prevRotationYaw, p.rotationYaw) * ((float)Math.PI / 180F));
	            vec3d = vec3d.rotateYaw(f4 * 0.5F);
	            vec3d = vec3d.rotatePitch(-f4 * 0.7F);
	            d4 = MathHelper.lerp((double)partialTicks, p.prevPosX, p.posX) + vec3d.x;
	            d5 = MathHelper.lerp((double)partialTicks, p.prevPosY, p.posY) + vec3d.y;
	            d6 = MathHelper.lerp((double)partialTicks, p.prevPosZ, p.posZ) + vec3d.z;
	            d7 = (double)p.getEyeHeight();
	         } else {
	            d4 = MathHelper.lerp((double)partialTicks, p.prevPosX, p.posX) - d1 * d2 - d0 * 0.8D;
	            d5 = p.prevPosY + (double)p.getEyeHeight() + (p.posY - p.prevPosY) * (double)partialTicks - 0.45D;
	            d6 = MathHelper.lerp((double)partialTicks, p.prevPosZ, p.posZ) - d0 * d2 + d1 * 0.8D;
	            d7 = p.shouldRenderSneaking() ? -0.1875D : 0.0D;
	         }

           double d13 = MathHelper.lerp((double)partialTicks, entity.prevPosX, entity.posX);
           double d14 = MathHelper.lerp((double)partialTicks, entity.prevPosY, entity.posY) + 0.25D;
           double d9 = MathHelper.lerp((double)partialTicks, entity.prevPosZ, entity.posZ);
           double d10 = (double)((float)(d4 - d13));
           double d11 = (double)((float)(d5 - d14)) + d7;
           double d12 = (double)((float)(d6 - d9));

	        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);
	        GlStateManager.lineWidth(3f);

	        GlStateManager.disableLighting();
	        GlStateManager.disableTexture();
	         for(int k = 0; k <= 16; ++k) {
	            float f6 = (float)k / 16.0F;

	            bufferbuilder.pos(x + d10 * (double)f6, y + d11 * (double)(f6 * f6 + f6) * 0.5D + 0.25D, z + d12 * (double)f6).color(255,255,255,255).endVertex();

	            
	         }

	         tessellator.draw();
	         GlStateManager.enableLighting();
	         GlStateManager.enableTexture();

		        this.bindTexture(getYoyoEntityTexture(entity));
				  super.doRender(entity, x, y, z, entityYaw, partialTicks);

	     }
		  

	   } */
	  @Override
	   public void render(YoyoEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
	      PlayerEntity playerentity = entityIn.getOwner();
	      if (playerentity != null) {
	         matrixStackIn.push();
	         matrixStackIn.push();
	         matrixStackIn.scale(0.5F, 0.5F, 0.5F);
	         matrixStackIn.rotate(this.renderManager.getCameraOrientation());
	         matrixStackIn.rotate(Vector3f.YP.rotationDegrees(180.0F));
	         MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();
	         Matrix4f matrix4f = matrixstack$entry.getMatrix();
	         Matrix3f matrix3f = matrixstack$entry.getNormal();
	         IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderType.getEntityCutout(this.getYoyoEntityTexture(entityIn)));
	         func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 0, 0, 1);
	         func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 0, 1, 1);
	         func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 1.0F, 1, 1, 0);
	         func_229106_a_(ivertexbuilder, matrix4f, matrix3f, packedLightIn, 0.0F, 1, 0, 0);
	         matrixStackIn.pop();
	         int i = playerentity.getPrimaryHand() == HandSide.RIGHT ? 1 : -1;
	         ItemStack itemstack = playerentity.getHeldItemMainhand();

	         float f = 1.0f;
	         float f1 = MathHelper.sin(MathHelper.sqrt(f) * (float)Math.PI);
	         float f2 = MathHelper.lerp(partialTicks, playerentity.prevRenderYawOffset, playerentity.renderYawOffset) * ((float)Math.PI / 180F);
	         double d0 = (double)MathHelper.sin(f2);
	         double d1 = (double)MathHelper.cos(f2);
	         double d2 = (double)i * 0.35D;
	         double d3 = 0.8D;
	         double d4;
	         double d5;
	         double d6;
	         float f3;
	         if ((this.renderManager.options == null || this.renderManager.options.thirdPersonView <= 0) && playerentity == Minecraft.getInstance().player) {
	            double d7 = this.renderManager.options.fov;
	            d7 = d7 / 100.0D;
	            Vec3d vec3d = new Vec3d((double)i * -0.36D * d7, -0.045D * d7, 0.4D);
	            vec3d = vec3d.rotatePitch(-MathHelper.lerp(partialTicks, playerentity.prevRotationPitch, playerentity.rotationPitch) * ((float)Math.PI / 180F));
	            vec3d = vec3d.rotateYaw(-MathHelper.lerp(partialTicks, playerentity.prevRotationYaw, playerentity.rotationYaw) * ((float)Math.PI / 180F));
	            vec3d = vec3d.rotateYaw(f1 * 0.5F);
	            vec3d = vec3d.rotatePitch(-f1 * 0.7F);
	            d4 = MathHelper.lerp((double)partialTicks, playerentity.prevPosX, playerentity.getPosX()) + vec3d.x;
	            d5 = MathHelper.lerp((double)partialTicks, playerentity.prevPosY, playerentity.getPosY()) + vec3d.y;
	            d6 = MathHelper.lerp((double)partialTicks, playerentity.prevPosZ, playerentity.getPosZ()) + vec3d.z;
	            f3 = playerentity.getEyeHeight();
	         } else {
	            d4 = MathHelper.lerp((double)partialTicks, playerentity.prevPosX, playerentity.getPosX()) - d1 * d2 - d0 * 0.8D;
	            d5 = playerentity.prevPosY + (double)playerentity.getEyeHeight() + (playerentity.getPosY() - playerentity.prevPosY) * (double)partialTicks - 0.45D;
	            d6 = MathHelper.lerp((double)partialTicks, playerentity.prevPosZ, playerentity.getPosZ()) - d0 * d2 + d1 * 0.8D;
	            f3 = playerentity.isCrouching() ? -0.1875F : 0.0F;
	         }

	         double d9 = MathHelper.lerp((double)partialTicks, entityIn.prevPosX, entityIn.getPosX());
	         double d10 = MathHelper.lerp((double)partialTicks, entityIn.prevPosY, entityIn.getPosY()) + 0.25D;
	         double d8 = MathHelper.lerp((double)partialTicks, entityIn.prevPosZ, entityIn.getPosZ());
	         float f4 = (float)(d4 - d9);
	         float f5 = (float)(d5 - d10) + f3;
	         float f6 = (float)(d6 - d8);
	         IVertexBuilder ivertexbuilder1 = bufferIn.getBuffer(RenderType.getLines());
	         Matrix4f matrix4f1 = matrixStackIn.getLast().getMatrix();
	         int j = 16;

	         for(int k = 0; k < 16; ++k) {
	            func_229104_a_(f4, f5, f6, ivertexbuilder1, matrix4f1, func_229105_a_(k, 16));
	            func_229104_a_(f4, f5, f6, ivertexbuilder1, matrix4f1, func_229105_a_(k + 1, 16));
	         }

	         matrixStackIn.pop();
	         super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
	      }
		   }

	   private static float func_229105_a_(int p_229105_0_, int p_229105_1_) {
	      return (float)p_229105_0_ / (float)p_229105_1_;
	   }

	   private static void func_229106_a_(IVertexBuilder p_229106_0_, Matrix4f p_229106_1_, Matrix3f p_229106_2_, int p_229106_3_, float p_229106_4_, int p_229106_5_, int p_229106_6_, int p_229106_7_) {
	      p_229106_0_.pos(p_229106_1_, p_229106_4_ - 0.5F, (float)p_229106_5_ - 0.5F, 0.0F).color(255, 255, 255, 255).tex((float)p_229106_6_, (float)p_229106_7_).overlay(OverlayTexture.NO_OVERLAY).lightmap(p_229106_3_).normal(p_229106_2_, 0.0F, 1.0F, 0.0F).endVertex();
	   }

	   private static void func_229104_a_(float p_229104_0_, float p_229104_1_, float p_229104_2_, IVertexBuilder p_229104_3_, Matrix4f p_229104_4_, float p_229104_5_) {
	      p_229104_3_.pos(p_229104_4_, p_229104_0_ * p_229104_5_, p_229104_1_ * (p_229104_5_ * p_229104_5_ + p_229104_5_) * 0.5F + 0.25F, p_229104_2_ * p_229104_5_).color(0, 0, 0, 255).endVertex();
	   }


}
