package com.izako.HunterX.network;

import com.izako.HunterX.init.ListQuests;
import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.network.packets.HanzoArmorBasePacket;
import com.izako.HunterX.network.packets.QuestPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class QuestPacketSyncServerHandler implements IMessageHandler<QuestPacketSync, IMessage> {
	// Do note that the default constructor is required, but implicitly defined in
	// this case

	@Override
	  public IMessage onMessage(QuestPacketSync message, MessageContext ctx) {
	    // This is the player the packet was sent to the server from
	    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
	    int statType = message.statType;
	    int amount = message.amount;
	    String str = message.str;
	    serverPlayer.getServerWorld().addScheduledTask(() -> {
	     IEntityStats stats = serverPlayer.getCapability(EntityStatsProvider.ENTITY_STATS, null);
	    	if(statType == 1) {
	    		stats.setProgress(str, amount);
	    	} else if(statType == 2) {
	    		stats.giveQuest(str, amount);
	    	}
	    }); 
			  
	 return null;
	  }
	
}
