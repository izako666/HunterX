package com.izako.HunterX.quests;

import com.izako.HunterX.izapi.quests.Quests;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.QuestPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

public class HunterExam02 extends Quests {

	@Override
	public String getID() {
		return "questHunterExam02";
	}

	//the name of the quest.
	@Override
	public String getName() {
		return "Run for 5 Minutes.";
		
	}
	//quest description
	@Override
	public String getDesc() {
		return "You must run for 5 minutes to prove your endurance";
	}

	@Override
	public void setProgress(EntityPlayer player, Integer value) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
           stats.setProgress(this.getID(), (int) Math.round(stats.timeHasRun()));
           if(player instanceof EntityPlayerMP) {
           ModidPacketHandler.INSTANCE.sendTo(new QuestPacketSync(this.getID(), 1, stats.getProgress(this.getID())), (EntityPlayerMP) player);
           }
           ModidPacketHandler.INSTANCE.sendToServer(new QuestPacketSync(this.getID(), 1, stats.getProgress(this.getID())));

	}
	@Override
	public boolean doneTask(EntityPlayer player) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		
		System.out.println(stats.getProgress(this.getID()));
		if(stats.getProgress(this.getID()) > 60) {
			
		
		return true;
		
		}
		return false;
	}

}
