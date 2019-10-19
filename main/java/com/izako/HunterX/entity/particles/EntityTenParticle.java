package com.izako.HunterX.entity.particles;

import org.lwjgl.opengl.GL11;

import com.izako.HunterX.util.Reference;
import com.izako.HunterX.util.handlers.EventsHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleExplosion;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTenParticle extends Particle {

	float initialParticleScale;

	EntityPlayer p;
	double posX;
	double posY;
	double posZ;
	Entity entity;
	
	public EntityTenParticle(World world, double x, double y, double z, Entity entity) {
		this(world, x, y, z, 1.0F, entity);
	}

	public EntityTenParticle(World world, double x, double y, double z, float scale, Entity entity) {
		super(world, x, y, z);
		this.particleScale *= 2.75F;
		this.particleScale *= scale;
		this.initialParticleScale = this.particleScale;
		this.particleMaxAge = 10000;
		this.particleMaxAge = (int) ((float) this.particleMaxAge * scale);
		this.canCollide = true;
		this.posX = x;
		this.posY = y;
		this.posZ = z;
		this.entity = entity;

		this.particleTexture = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite("uwu");

		this.onUpdate();
	}

	@Override
	public void onUpdate() {
		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (this.particleAge++ >= this.particleMaxAge) {
			this.setExpired();
		}

	
		this.posX = entity.posX;
		this.posY = entity.posY;
		this.posZ = entity.posZ;

		
	}

	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX,
			float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {

		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_NORMAL);
		buffer.pos(posX + 2f, posY + 3f, posZ).tex(0, 16).lightmap(0, 1).endVertex();
		buffer.pos(posX + 4f, posY + 3f, posZ).tex(0, 16).lightmap(0, 1).endVertex();
		buffer.pos(posX + 2f, posY + 5f, posZ).tex(0, 16).lightmap(0, 1).endVertex();
		buffer.pos(posX + 4f, posY + 5f, posZ).tex(0, 16).lightmap(0, 1).endVertex();
	
		Tessellator.getInstance().draw();

	}

	@Override
	public int getFXLayer() {
		return 1;
	}

}
