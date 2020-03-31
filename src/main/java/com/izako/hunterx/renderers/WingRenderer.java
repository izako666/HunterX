package com.izako.hunterx.renderers;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.ThugEntity;
import com.izako.hunterx.entities.WingEntity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

public class WingRenderer extends BipedRenderer<WingEntity, BipedModel<WingEntity>> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/wing.png");
	public WingRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new BipedModel<WingEntity>(1f, 0.0f, 64, 64), 0.5f);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ResourceLocation getEntityTexture(WingEntity entity) {

		return TEXTURE;
	}
}
