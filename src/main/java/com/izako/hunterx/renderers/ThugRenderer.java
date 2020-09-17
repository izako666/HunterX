package com.izako.hunterx.renderers;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.ThugEntity;
import com.izako.hunterx.izapi.IZAHelper;
import com.izako.hunterx.renderers.layers.GenericOverlayLayer;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.LivingRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ThugRenderer extends BipedRenderer<ThugEntity, BipedModel<ThugEntity>> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(
			Main.MODID + ":textures/entity/thug.png");
	public static final ResourceLocation TEXTURE2 = new ResourceLocation(
			Main.MODID + ":textures/entity/thug2.png");
	public static final ResourceLocation TEXTURE3 = new ResourceLocation(
			Main.MODID + ":textures/entity/thug3.png");


	public ThugRenderer(EntityRendererManager manager) {
		super(manager, new BipedModel<ThugEntity>(IZAHelper.entityModelFunc,1f, 0.0f, 64, 64), 0.5f);
		// TODO Auto-generated constructor stub
		this.addLayer(new GenericOverlayLayer((IEntityRenderer) this));
	}

	@Override
	public ResourceLocation getEntityTexture(ThugEntity entity) {
		// TODO Auto-generated method stub
		switch(entity.getTextureId()) {
		case 0: 
			return TEXTURE;
		case 1:
		return TEXTURE2;
		case 2: 
			return TEXTURE3;
		default: 
			return TEXTURE;
		}
	}
	

}
