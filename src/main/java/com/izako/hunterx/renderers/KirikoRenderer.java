package com.izako.hunterx.renderers;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.models.KirikoModel;
import com.izako.hunterx.renderers.layers.GenericOverlayLayer;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.entity.MobEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class KirikoRenderer<T extends MobEntity, M extends EntityModel<T>> extends MobRenderer<T, M> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/kiriko.png");
	KirikoModel model;
	@SuppressWarnings("unchecked")
	public KirikoRenderer(EntityRendererManager p_i50961_1_, KirikoModel model) {
		super(p_i50961_1_, (M) model, 0.5f);
		this.model = model;
		this.addLayer(new GenericOverlayLayer((IEntityRenderer) this));

	}

	@Override
	public ResourceLocation getEntityTexture(T entity) {
		return TEXTURE;
	}


}
