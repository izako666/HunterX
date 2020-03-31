package com.izako.hunterx.items.renderers;

import com.izako.hunterx.Main;
import com.izako.hunterx.items.entities.BulletEntity;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.platform.GlStateManager.DestFactor;
import com.mojang.blaze3d.platform.GlStateManager.SourceFactor;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

public class BulletRenderer<T extends Entity> extends EntityRenderer<BulletEntity> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/items/bullet.png");

	private EntityModel model;
	public BulletRenderer(EntityRendererManager p_i50957_1_, EntityModel model) {
		super(p_i50957_1_);
		this.model = model;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doRender(BulletEntity entity, double x, double y, double z, float entityYaw, float partialTicks) {
		GlStateManager.color4f(1, 1, 1, 1);
		GlStateManager.pushMatrix();
		{
			GlStateManager.translated(x, y + 0.25, z);
			
			GlStateManager.enableBlend();
			GlStateManager.blendFunc(SourceFactor.SRC_ALPHA, DestFactor.ONE_MINUS_SRC_ALPHA);



			

			GlStateManager.scaled(1, 1, 1);
			GlStateManager.translated(0, -(1 / 10), 0);

				Minecraft.getInstance().textureManager.bindTexture(this.getEntityTexture(entity));

			if (this.model != null)
				this.model.render(entity, (float) x, (float) y, (float) z, 0.0F, 0.0F, 0.0625F);

			GlStateManager.disableBlend();
		}
		GlStateManager.popMatrix();

	}

	@Override
	protected ResourceLocation getEntityTexture(BulletEntity entity) {
		// TODO Auto-generated method stub
		return new ResourceLocation(Main.MODID, "textures/models/bullet.png");
	}


}
