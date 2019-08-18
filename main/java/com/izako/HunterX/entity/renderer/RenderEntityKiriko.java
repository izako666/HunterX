package com.izako.HunterX.entity.renderer;

import com.izako.HunterX.entity.BossExam;
import com.izako.HunterX.entity.EntityKiriko;
import com.izako.HunterX.entity.model.ModelBossExam;
import com.izako.HunterX.entity.model.ModelKiriko;
import com.izako.HunterX.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEntityKiriko extends RenderLiving<EntityKiriko> {

	public static final ResourceLocation TEXTURES = new ResourceLocation(
			Reference.MOD_ID + ":textures/entity/kiriko.png");

	public RenderEntityKiriko(RenderManager manager) {

		super(manager, new ModelKiriko(), 0.5F);

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityKiriko entity) {

		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntityKiriko entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {

		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

}
