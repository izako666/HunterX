package com.izako.HunterX.entity.renderer;

import com.izako.HunterX.entity.EntityStats;
import com.izako.HunterX.entity.Examiner;
import com.izako.HunterX.entity.model.ModelEntityStats;
import com.izako.HunterX.entity.model.ModelExaminer;
import com.izako.HunterX.util.Reference;

import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderEntityStats  extends RenderLiving<EntityStats> {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/entitystats.png");

	public RenderEntityStats(RenderManager manager) {
		
		super(manager, new ModelEntityStats(1,true), 0.5F);
		
	}
	
	@Override
	protected ResourceLocation getEntityTexture(EntityStats entity) {
		
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(EntityStats entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		
		
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

	

}

