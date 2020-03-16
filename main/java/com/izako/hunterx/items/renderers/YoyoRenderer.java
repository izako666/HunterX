package com.izako.hunterx.items.renderers;


import com.izako.hunterx.Main;
import com.izako.hunterx.items.entities.YoyoEntity;
import com.mojang.blaze3d.platform.GlStateManager;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class YoyoRenderer<T extends Entity> extends  EntityRenderer<YoyoEntity> {

	   public YoyoRenderer(EntityRendererManager p_i50956_1_) {
		      super(p_i50956_1_);
		   }

	@Override
	protected ResourceLocation getEntityTexture(YoyoEntity entity) {
		// TODO Auto-generated method stub
		return new ResourceLocation(Main.MODID, "yoyo");
	}

	
	  @Override
	  public void doRender(YoyoEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
	     
		   System.out.println("shouldrender");
	         Tessellator tessellator = Tessellator.getInstance();
	        BufferBuilder bufferbuilder = tessellator.getBuffer();
	        this.bindEntityTexture(entity);
		  super.doRender(entity, x, y, z, entityYaw, partialTicks);
	        bufferbuilder.begin(3, DefaultVertexFormats.POSITION_COLOR);

	         for(int k = 0; k <= 16; ++k) {
	            float f6 = (float)k / 16.0F;
	            double bufX = Math.sqrt(entity.getThrower().posX - entity.posX * 2) * f6;
	            double bufY = Math.sqrt(entity.getThrower().posY - entity.posY * 2) * f6;
	            double bufZ = Math.sqrt(entity.getThrower().posZ - entity.posZ * 2) * f6;

	            bufferbuilder.pos(bufX,bufY,bufZ).endVertex();
	         }

	         tessellator.draw();
	         GlStateManager.enableLighting();
	         GlStateManager.enableTexture();


		  

	   } 
		  

}
