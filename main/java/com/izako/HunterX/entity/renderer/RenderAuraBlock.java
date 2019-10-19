package com.izako.HunterX.entity.renderer;

import java.awt.Color;

import com.izako.HunterX.util.Reference;

import assets.hntrx.models.AuraBlock;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;

public class RenderAuraBlock extends Render{

	private ModelPlayer model;
	ResourceLocation texture = new ResourceLocation(Reference.MOD_ID + ":textures/models/test.png");
	public RenderAuraBlock(ModelBase model) {
		super(Minecraft.getMinecraft().getRenderManager());
		// TODO Auto-generated constructor stub
		this.model = (ModelPlayer)model;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		// TODO Auto-generated method stub
		return texture;
	}
	
	@Override
	public void doRender(Entity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		
		if(entity instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) entity;
		GlStateManager.pushMatrix();

		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		
		GlStateManager.rotate(180, 1, 0, 0);
		GlStateManager.rotate(p.renderYawOffset, 0, 1, 0);
		GlStateManager.translate(entity.posX / 1000 ,(entity.posY / 1000) - 2.7,entity.posZ /1000);
		this.model.isSneak = p.isSneaking();
		this.model.isRiding = p.isRiding();
		this.model.render(p, p.limbSwing, p.limbSwingAmount, p.ticksExisted, p.rotationYawHead, p.rotationPitch, 0.109f);

		GlStateManager.popMatrix();
		}
	}

} 
