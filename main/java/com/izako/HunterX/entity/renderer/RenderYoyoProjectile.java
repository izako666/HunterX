package com.izako.HunterX.entity.renderer;

import com.izako.HunterX.items.entities.YoyoProjectile;

import net.minecraft.client.renderer.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class RenderYoyoProjectile extends RenderSnowball<YoyoProjectile> {

	public RenderYoyoProjectile(RenderManager renderManagerIn, Item itemIn, RenderItem itemRendererIn) {
		
		super(renderManagerIn, itemIn, itemRendererIn);	
	}
	
	@Override
	public void doRender(YoyoProjectile entity, double x, double y, double z, float entityYaw, float partialTicks) {
		super.doRender(entity, x, y, z, entityYaw, partialTicks);
	}
	
	@Override
	public ItemStack getStackToRender(YoyoProjectile entityIn) {
		
		return super.getStackToRender(entityIn);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(YoyoProjectile entity) {
		
		return super.getEntityTexture(entity);
	}
	

}
