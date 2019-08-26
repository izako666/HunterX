package com.izako.HunterX.network;

import com.izako.HunterX.izapi.abilities.Ability;
import com.izako.HunterX.network.packets.AbilityPacketSync;
import com.izako.HunterX.network.packets.EntityModifierServerChange;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class AbilityPacketServerSyncHandler  implements IMessageHandler<AbilityPacketSync, IMessage>{
	@Override
	public IMessage onMessage(AbilityPacketSync message, MessageContext ctx) {
	    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
	    Ability a = message.a;
	    int index = message.index;
	    int type = message.type;

	    serverPlayer.getServerWorld().addScheduledTask(() -> {
	    	  if(type == 1) {
	  	    	IEntityStats stats = serverPlayer.getCapability(EntityStatsProvider.ENTITY_STATS, null);
	  	    	stats.setAbilityToSlot(index, a);
	  		   } else if(type == 2) {
	  			   a.startAbility(serverPlayer);
	  		   } else if(type == 3) {
		  	    	IEntityStats stats = serverPlayer.getCapability(EntityStatsProvider.ENTITY_STATS, null);
	  			   stats.giveAbility(a);
	  		   }
	    });
		return null;
		
	}
	
}
