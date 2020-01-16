package com.izako.HunterX.quests;

import com.izako.HunterX.init.ListQuests;
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
		return "Level up your Speed stat (5 minutes running)";
		
	}
	//quest description
	@Override
	public String getDesc() {
		return "Prove your speed to me, or I cant let you advance";
	}

	@Override
	public void setProgress(EntityPlayer player, Integer value) {
		if(ListQuests.HUNTEREXAM02.hasQuest(player)) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
           stats.setProgress(this.getID(), (int) Math.round(stats.timeHasRun()));
           
           if(player instanceof EntityPlayerMP) {
           ModidPacketHandler.INSTANCE.sendTo(new QuestPacketSync(this.getID(), 1, stats.getProgress(this.getID())), (EntityPlayerMP) player);

           }
           
           ModidPacketHandler.INSTANCE.sendToServer(new QuestPacketSync(this.getID(), 1, stats.getProgress(this.getID())));
		}
           
	}
	@Override
	public boolean doneTask(EntityPlayer player) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		
		System.out.println(stats.getSpeedStat());
		if(stats.getSpeedStat() > 0.05) {
			
		
		return true;
		
		}
		return false;
	}

}
