package com.izako.hunterx.renderers;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.HanzoEntity;
import com.izako.hunterx.registerers.ClientSideRegistry;
import com.izako.hunterx.renderers.layers.GenericOverlayLayer;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class HanzoRenderer extends BipedRenderer<HanzoEntity, BipedModel<HanzoEntity>> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/hanzo.png");
	public HanzoRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new BipedModel<HanzoEntity>(ClientSideRegistry.entityModelFunc,1f, 0.0f, 64, 64), 0.5f);
		this.addLayer(new GenericOverlayLayer((IEntityRenderer) this));

	}

	@Override
	public ResourceLocation getEntityTexture(HanzoEntity entity) {

		return TEXTURE;
	}
}
