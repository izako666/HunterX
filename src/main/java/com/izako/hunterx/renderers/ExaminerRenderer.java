package com.izako.hunterx.renderers;

import com.izako.hunterx.Main;
import com.izako.hunterx.entities.ExaminerEntity;

import net.minecraft.client.renderer.entity.BipedRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.util.ResourceLocation;

public class ExaminerRenderer extends BipedRenderer<ExaminerEntity, BipedModel<ExaminerEntity>> {

	public static final ResourceLocation TEXTURE = new ResourceLocation(Main.MODID, "textures/entity/wing.png");
	public ExaminerRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new BipedModel<ExaminerEntity>(1f, 0.0f, 64, 64), 0.5f);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected ResourceLocation getEntityTexture(ExaminerEntity entity) {

		return TEXTURE;
	}
}
