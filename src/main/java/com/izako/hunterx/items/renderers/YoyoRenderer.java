package com.izako.hunterx.items.renderers;


import com.izako.hunterx.Main;
import com.izako.hunterx.items.YoyoItem;
import com.izako.hunterx.items.entities.YoyoEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
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
		// TODO Auto-generated method stub
		return new ResourceLocation(Main.MODID, "textures/items/yoyo.png");
	}

	
	  @Override
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
		  

	   } 
		  

}
