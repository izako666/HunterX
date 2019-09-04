package com.izako.HunterX.network;

import com.izako.HunterX.abilities.EnumNenType;
import com.izako.HunterX.izapi.abilities.Ability;
import com.izako.HunterX.network.packets.AbilityPacketSync;
import com.izako.HunterX.network.packets.NenPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class NenPacketServerSyncHandler  implements IMessageHandler<NenPacketSync, IMessage>{
	@Override
	public IMessage onMessage(NenPacketSync message, MessageContext ctx) {
	    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
	    int index = message.index;
	    int type = message.type;

	    serverPlayer.getServerWorld().addScheduledTask(() -> {
  	    	IEntityStats stats = serverPlayer.getCapability(EntityStatsProvider.ENTITY_STATS, null);
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