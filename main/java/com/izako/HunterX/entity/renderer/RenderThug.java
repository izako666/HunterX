package com.izako.HunterX.entity.renderer;

import com.izako.HunterX.entity.Thug;
import com.izako.HunterX.entity.model.ModelThug;
import com.izako.HunterX.util.Reference;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderThug extends RenderLiving<Thug> {
	
	public static final ResourceLocation TEXTURES = new ResourceLocation(Reference.MOD_ID + ":textures/entity/thug.png");

	public RenderThug(RenderManager manager) {
		
		super(manager, new ModelThug(1,true), 0.5F);
		
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Thug entity) {
		
		return TEXTURES;
	}
	
	@Override
	protected void applyRotations(Thug entityLiving, float p_77043_2_, float rotationYaw, float partialTicks) {
		
		
		super.applyRotations(entityLiving, p_77043_2_, rotationYaw, partialTicks);
	}

}
