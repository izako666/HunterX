package com.izako.HunterX.entity.renderer;

import com.izako.HunterX.entity.BossExam;
import com.izako.HunterX.entity.Thug;
import com.izako.HunterX.entity.model.ModelBossExam;
import com.izako.HunterX.entity.model.ModelThug;
import com.izako.HunterX.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderBossExam extends RenderLiving<BossExam> {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/bossexam.png");

	public RenderBossExam(RenderManager manager) {
		
		super(manager, new ModelBossExam(1,true), 0.5F);
		
	}
	
	@Override
	protected ResourceLocation getEntityTexture(BossExam entity) {
		
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(BossExam entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		
		
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

}
