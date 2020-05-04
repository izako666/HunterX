package com.izako.wypi.abilities.models;

import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.entity.model.RendererModel;
import net.minecraft.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SphereModel extends EntityModel
{
	public RendererModel shape1;
	public RendererModel shape2;
	public RendererModel shape3;
	public RendererModel shape4;
	public RendererModel shape5;
	public RendererModel shape6;
	public RendererModel shape7;

	public SphereModel()
	{
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.shape4 = new RendererModel(this, 0, 0);
		this.shape4.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape4.addBox(-1.5F, -1.5F, -2.5F, 3, 3, 1, 0.0F);
		this.shape5 = new RendererModel(this, 0, 0);
		this.shape5.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape5.addBox(-1.5F, -1.5F, 1.5F, 3, 3, 1, 0.0F);
		this.shape7 = new RendererModel(this, 0, 0);
		this.shape7.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape7.addBox(1.5F, -1.5F, -1.5F, 1, 3, 3, 0.0F);
		this.shape2 = new RendererModel(this, 0, 0);
		this.shape2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape2.addBox(-1.5F, -2.5F, -1.5F, 3, 1, 3, 0.0F);
		this.shape3 = new RendererModel(this, 0, 0);
		this.shape3.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape3.addBox(-1.5F, 1.5F, -1.5F, 3, 1, 3, 0.0F);
		this.shape6 = new RendererModel(this, 0, 0);
		this.shape6.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape6.addBox(-2.5F, -1.5F, -1.5F, 1, 3, 3, 0.0F);
		this.shape1 = new RendererModel(this, 0, 0);
		this.shape1.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.shape1.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4, 0.0F);
		this.shape1.addChild(this.shape4);
		this.shape1.addChild(this.shape5);
		this.shape1.addChild(this.shape7);
		this.shape1.addChild(this.shape2);
		this.shape1.addChild(this.shape3);
		this.shape1.addChild(this.shape6);
	}

	@Override
	public void render(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, float scale)
	{
		this.shape1.render(scale);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(RendererModel model, float x, float y, float z)
	{
		model.rotateAngleX = x;
		model.rotateAngleY = y;
		model.rotateAngleZ = z;
	}
	
	public RendererModel getModel()
	{
		return this.shape1;
	}
}
