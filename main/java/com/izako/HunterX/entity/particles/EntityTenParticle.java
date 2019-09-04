package com.izako.HunterX.entity.particles;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class EntityTenParticle extends Particle{

	public EntityTenParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		// TODO Auto-generated constructor stub
	}

    public EntityTenParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn, double ySpeedIn, double zSpeedIn)
    {

    	super( worldIn,  xCoordIn,  yCoordIn,  zCoordIn,  xSpeedIn,  ySpeedIn,  zSpeedIn);
    }
    @Override
    public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ) {
		 GL11.glPushMatrix();
		GL11.glDepthMask(false);
		//GL11.glEnable(GL11.GL_BLEND);
		GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.003921569F);
		
		float scale = 0.1F * particleScale;
		float x = (float) (prevPosX + (posX - prevPosX) * partialTicks - interpPosX);
		float y = (float) (prevPosY + (posY - prevPosY) * partialTicks - interpPosY);
		float z = (float) (prevPosZ + (posZ - prevPosZ) * partialTicks - interpPosZ);
		
		float ticks = particleAge;
		
		
		buffer.pos(x - rotationX * scale - rotationXY * scale, y - rotationZ * scale, z - rotationYZ * scale - rotationXZ * scale);
		buffer.pos(x - rotationX * scale + rotationXY * scale, y + rotationZ * scale, z - rotationYZ * scale + rotationXZ * scale);
		buffer.pos(x + rotationX * scale + rotationXY * scale, y + rotationZ * scale, z + rotationYZ * scale + rotationXZ * scale);
        buffer.pos(x + rotationX * scale - rotationXY * scale, y - rotationZ * scale, z + rotationYZ * scale - rotationXZ * scale);      
        
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, 128.0F, 128.0F);		
		
		//GL11.glDisable(GL11.GL_BLEND);
		GL11.glDepthMask(true);
		GL11.glAlphaFunc(GL11.GL_GREATER, 0.1F);
		System.out.println("particle rendered");
		
		GL11.glPopMatrix();

    }
    public String getID() {
    	return "ParticleTen";
    }

}
