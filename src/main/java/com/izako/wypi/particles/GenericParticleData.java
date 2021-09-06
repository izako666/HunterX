package com.izako.wypi.particles;


import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModParticleTypes;
import com.izako.wypi.APIConfig;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

import net.minecraft.client.particle.IParticleRenderType;
import net.minecraft.network.PacketBuffer;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.util.ResourceLocation;

public class GenericParticleData implements IParticleData {


	public static final IDeserializer<GenericParticleData> DESERIALIZER = new IDeserializer<GenericParticleData>()
	{
		@Override
		public GenericParticleData deserialize(ParticleType<GenericParticleData> particleType, StringReader stringReader) throws CommandSyntaxException
		{
			stringReader.expect(' ');
			float red = stringReader.readFloat();
			float green = stringReader.readFloat();
			float blue = stringReader.readFloat();
			float alpha = stringReader.readFloat();
			float size = stringReader.readFloat();
			int life = stringReader.readInt();
			boolean hasRotation = stringReader.readBoolean();
			boolean hasMotionDecat = stringReader.readBoolean();
			
			double motionX = stringReader.readDouble();
			double motionY = stringReader.readDouble();
			double motionZ = stringReader.readDouble();

			
			GenericParticleData data = new GenericParticleData(particleType);
			data.setColor(red, green, blue, alpha);
			data.setMotion(motionX, motionY, motionZ);
			data.setSize(size);
			data.setLife(life);
			if(hasRotation) data.setHasRotation();
			data.setHasMotionDecay(hasMotionDecat);
			
			return data;
		}

		@Override
		public GenericParticleData read(ParticleType<GenericParticleData> particleType, PacketBuffer packetBuffer)
		{
			float red = packetBuffer.readFloat();
			float green = packetBuffer.readFloat();
			float blue = packetBuffer.readFloat();
			float alpha = packetBuffer.readFloat();
			float size = packetBuffer.readFloat();
			int life = packetBuffer.readInt();
			boolean hasRotation = packetBuffer.readBoolean();
			boolean hasMotionDecat = packetBuffer.readBoolean();
			
			double motionX = packetBuffer.readDouble();
			double motionY = packetBuffer.readDouble();
			double motionZ = packetBuffer.readDouble();
			
			
			GenericParticleData data = new GenericParticleData(particleType);
			data.setColor(red, green, blue, alpha);
			data.setMotion(motionX, motionY, motionZ);
			data.setSize(size);
			data.setLife(life);
			if(hasRotation) data.setHasRotation();
			data.setHasMotionDecay(hasMotionDecat);
						
			return data;
		}
	};

	private float red = 1, green = 1, blue = 1;
	private double motionX, motionY, motionZ;
	private float alpha = 1.0F;
	private float size = 1;
	private int life = 10;
	private boolean hasRotation = false;
	private boolean hasMotionDecay = true;
	private ResourceLocation texture = new ResourceLocation(Main.MODID, "");
	private ParticleType type;
	public GenericParticleData(ParticleType type) {
		this.type = type;
	}

	@Override
	public ParticleType<?> getType()
	{
		return this.type;
	}

	@Override
	public void write(PacketBuffer buffer)
	{
		buffer.writeFloat(this.red);
		buffer.writeFloat(this.green);
		buffer.writeFloat(this.blue);
		buffer.writeFloat(this.alpha);
		buffer.writeFloat(this.size);
		buffer.writeInt(this.life);
		buffer.writeBoolean(this.hasRotation);
		buffer.writeBoolean(this.hasMotionDecay);

		buffer.writeDouble(this.motionX);
		buffer.writeDouble(this.motionY);
		buffer.writeDouble(this.motionZ);
		
	}

	public void setMotion(double motionX, double motionY, double motionZ)
	{
		this.motionX = motionX;
		this.motionY = motionY;
		this.motionZ = motionZ;
	}
	
	public void setColor(float red, float green, float blue)
	{
		this.setColor(red, green, blue, 1.0F);
	}
	
	public void setColor(float red, float green, float blue, float alpha)
	{
		this.red = red;
		this.green = green;
		this.blue = blue;
		this.alpha = alpha;
	}
	
	public void setSize(float size)
	{
		this.size = size;
	}

	public void setLife(int life)
	{
		this.life = life;
	}
	
	public void setHasRotation()
	{
		this.hasRotation = true;
	}
	
	public void setHasMotionDecay(boolean flag)
	{
		this.hasMotionDecay = flag;
	}
	
	@Override
	public String getParameters()
	{
		return this.getType().getRegistryName().toString();
	}

	public float getRed()
	{
		return this.red;
	}
	
	public float getGreen()
	{
		return this.green;
	}
	
	public float getBlue()
	{
		return this.blue;
	}
	
	public float getAlpha()
	{
		return this.alpha;
	}
	
	public float getSize()
	{
		return this.size;
	}
	
	public int getLife()
	{
		return this.life;
	}
	
	public double getMotionX()
	{
		return this.motionX;
	}
	
	public double getMotionY()
	{
		return this.motionY;
	}
	
	public double getMotionZ()
	{
		return this.motionZ;
	}
	
	public boolean hasRotation()
	{
		return this.hasRotation;
	}
	
	public boolean hasMotionDecay()
	{
		return this.hasMotionDecay;
	}
}
