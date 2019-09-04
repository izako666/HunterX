package com.izako.HunterX.network.packets;

import com.izako.HunterX.init.ListAbilities;
import com.izako.HunterX.izapi.abilities.Ability;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;

public class PacketParticles implements IMessage{

	  // A default constructor is always required
	  public PacketParticles(){}

	  public String particle;
	  public double posX;
	  public double posY;
	  public double posZ;
	  
 public PacketParticles(String particle, EntityLivingBase entity) {

	 this.particle = particle;
	 this.posX = entity.posX;
	 this.posY = entity.posY;
	 this.posZ = entity.posZ;
 }

	  @Override public void toBytes(ByteBuf buf) {
	    // Writes the int into the buf
		  PacketBuffer pbuf = new PacketBuffer(buf);
		  pbuf.writeInt(particle.length());
		  pbuf.writeString(particle);
		  pbuf.writeDouble(posX);
		  pbuf.writeDouble(posY);
		  pbuf.writeDouble(posZ);

	    
	  }

	  @Override public void fromBytes(ByteBuf buf) {
	    // Reads the int back from the buf. Note that if you have multiple values, you must read in the same order you wrote.
	  PacketBuffer pbuf = new PacketBuffer(buf);
	 int length = pbuf.readInt();
	 particle = pbuf.readString(length);
	 posX = pbuf.readDouble();
	 posY = pbuf.readDouble();
	 posZ = pbuf.readDouble();

	 
	  }

	
}
