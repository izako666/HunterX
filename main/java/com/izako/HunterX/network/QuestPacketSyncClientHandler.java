package com.izako.HunterX.network;

import com.izako.HunterX.network.packets.QuestPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class QuestPacketSyncClientHandler implements IMessageHandler<QuestPacketSync, IMessage> {
	// Do note that the default constructor is required, but implicitly defined in
	// this case

	@Override
	  public IMessage onMessage(QuestPacketSync message, MessageContext ctx) {
	    // This is the player the packet was sent to the server from
	    EntityPlayerSP clientPlayer = Minecraft.getMinecraft().player;
	    int statType = message.statType;
	    int amount = message.amount;
	    String str = message.str;
	    Minecraft.getMinecraft().addScheduledTask(() -> {
	     IEntityStats stats = clientPlayer.getCapability(EntityStatsProvider.ENTITY_STATS, null);
	    	if(statType == 1) {
	    		stats.setProgress(str, amount);
	    	} else if(statType == 2) {
	    		stats.giveQuest(str, amount);
	    		System.out.println(str);
	    		System.out.println(amount);
	    	}
	    }); 
			  
	 return null;
	  }
	
}
