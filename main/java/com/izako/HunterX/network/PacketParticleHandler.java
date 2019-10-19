package com.izako.HunterX.network;

import com.izako.HunterX.Main;
import com.izako.HunterX.abilities.EnumNenType;
import com.izako.HunterX.entity.particles.EntityTenParticle;
import com.izako.HunterX.network.packets.NenPacketSync;
import com.izako.HunterX.network.packets.PacketParticles;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketParticleHandler  implements IMessageHandler<PacketParticles, IMessage>{
	@Override
	public IMessage onMessage(PacketParticles message, MessageContext ctx) {
	    
	    Minecraft.getMinecraft().addScheduledTask(() -> {
			double posX = message.posX;
			double posY = message.posY;
			double posZ = message.posZ;
			String particle = message.particle;
			EntityPlayerSP p = Minecraft.getMinecraft().player;

			if(particle.equalsIgnoreCase("ParticleTen")) {
				Main.proxy.spawnCustomParticles(new EntityTenParticle(Minecraft.getMinecraft().player.world, posX, posY, posZ, p));
		
			}
	   
  	    	
  	    	
	    });
		return null;
		
	}


}
