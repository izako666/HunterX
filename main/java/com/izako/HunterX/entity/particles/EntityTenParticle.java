package com.izako.HunterX.entity.particles;

import org.lwjgl.opengl.GL11;

import com.izako.HunterX.util.Reference;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleExplosion;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTenParticle extends Particle {

	public EntityTenParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		// TODO Auto-generated constructor stub
	}

	public EntityTenParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {

		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX,
			float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		GlStateManager.pushMatrix();

		float scale = 1F * particleScale;
		float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);

		float ticks = particleAge;

		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_NORMAL);
		Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(Reference.MOD_ID + ":/textures/items/bulle.png"));

		buffer.pos(posX * 10 * scale, posY * 10, posZ * 10).tex(0, 16).lightmap(1, 1).endVertex();
		buffer.pos(posX * 10 * scale, posY * 10, posZ * 10).tex(0, 16).lightmap(1, 1).endVertex();
		buffer.pos(posX * 10 * scale, posY * 10, posZ * 10).tex(0, 16).lightmap(1, 1).endVertex();
		buffer.pos(posX * 10 * scale, posY * 10, posZ * 10).tex(0, 16).lightmap(1, 1).endVertex();

		Tessellator.getInstance().draw();
		System.out.println("particle rendered");


		GlStateManager.popMatrix();

	}
	@Override
	public void onUpdate() {
		this.motionX *= 2;
		this.motionY *= 2;
		this.motionZ *= 2;

		
	}

	public String getID() {
		return "ParticleTen";
	}

	@Override
	public int getFXLayer() {
		return 3;

	}

}
