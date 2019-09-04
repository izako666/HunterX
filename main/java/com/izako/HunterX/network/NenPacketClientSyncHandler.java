package com.izako.HunterX.network;

import com.izako.HunterX.abilities.EnumNenType;
import com.izako.HunterX.network.packets.NenPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NenPacketClientSyncHandler  implements IMessageHandler<NenPacketSync, IMessage>{
	@Override
	public IMessage onMessage(NenPacketSync message, MessageContext ctx) {
	    int index = message.index;
	    int type = message.type;

	    Minecraft.getMinecraft().addScheduledTask(() -> {
		    EntityPlayerSP clientPlayer = Minecraft.getMinecraft().player;
  	    	IEntityStats stats = clientPlayer.getCapability(EntityStatsProvider.ENTITY_STATS, null);
	    	if(type == 1) {
	    		stats.setNenCapacity(index);
	    	} else if (type == 2) {
	    		if(index == 1) {
	    			stats.setNenType(EnumNenType.CONJURATION);
	    		} else if(index == 2) {
	    			stats.setNenType(EnumNenType.EHANCEMENT);
	    		} else if(index == 3) {
	    			stats.setNenType(EnumNenType.EMMISSION);
	    		}else if(index == 4) {
	    			stats.setNenType(EnumNenType.MANIPULATION);
	    		}else if(index == 5) {
	    			stats.setNenType(EnumNenType.SPECIALIZATION);
	    		}else if(index == 6) {
	    			stats.setNenType(EnumNenType.TRANSMUTATION);
	    		}
	    	}
	    });
		return null;
		
	}
	
}