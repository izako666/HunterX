package com.izako.HunterX.entity.renderer;

import com.izako.HunterX.entity.EntityFoxbear;
import com.izako.HunterX.entity.EntityKiriko;
import com.izako.HunterX.entity.model.ModelFoxbear;
import com.izako.HunterX.entity.model.ModelKiriko;
import com.izako.HunterX.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEntityFoxbear extends RenderLiving<EntityFoxbear> {

	public static final ResourceLocation TEXTURES = new ResourceLocation(
			Reference.MOD_ID + ":textures/entity/foxbear.png");

	public RenderEntityFoxbear(RenderManager manager) {

		super(manager, new ModelFoxbear(), 0.5F);

	}

	@Override
	protected ResourceLocation getEntityTexture(EntityFoxbear entity) {

		return TEXTURES;
	}

	@Override
	protected void applyRotations(EntityFoxbear entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {

		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

}
