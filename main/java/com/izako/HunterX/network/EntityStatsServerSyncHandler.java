package com.izako.HunterX.network;

import com.izako.HunterX.init.ListQuests;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
import com.izako.HunterX.network.packets.HanzoArmorBasePacket;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class EntityStatsServerSyncHandler implements IMessageHandler<EntityStatsServerSync, IMessage> {

	@Override
	public IMessage onMessage(EntityStatsServerSync message, MessageContext ctx) {
		// TODO Auto-generated method stub
	    EntityPlayerMP serverPlayer = ctx.getServerHandler().player;
	    Double amount = message.amount;
	    int statType = message.statType;
	    boolean value = message.value;

	    serverPlayer.getServerWorld().addScheduledTask(() -> {
		      IEntityStats stats = serverPlayer.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		      if(statType == 1) {
		    	  stats.setHealthStat(amount);
		      } else if(statType == 2) {
		    	  stats.setDefenseStat(amount);
		      } else if(statType == 3) {
		    	  stats.setSpeedStat(amount);
		      } else if(statType == 4) {
		    	  stats.setAttackStat(amount);
		      } else if(statType == 5) {
		    	  stats.setHasKilledKiriko(value);
		      } else if(statType == 6) {
		    	  stats.setHasStarted2ndPhase(value);
		      } else if(statType == 7) {
		    	  ListQuests.HUNTEREXAM02.setProgress((EntityPlayer)serverPlayer, (int)Math.round(amount));
		    	  System.out.println(stats.getProgress(ListQuests.HUNTEREXAM02.getID()));
		    	  stats.setTimeHasRun(amount);
		      } else if(statType == 8) {
		    	  stats.setIsHunter(value);
		      }
		    }); 
		return null;
	}

}
