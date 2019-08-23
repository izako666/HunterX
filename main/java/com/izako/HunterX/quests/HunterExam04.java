package com.izako.HunterX.quests;

import com.izako.HunterX.init.ListQuests;
import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.izapi.quests.Quests;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class HunterExam04 extends Quests {


	//The id of the quest.
	@Override
	public String  getID() {
		return "questHunterExam04";
	}
	//the name of the quest.
	@Override
	public String getName() {
		return "beat hanzo";
		
	}
	//quest description
	@Override
	public String getDesc() {
		return "beat a fellow friend in battle";
	}
	
	@Override
	public boolean doneTask(EntityPlayer player) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		if(this.canProgress(player)) {
		if(stats.hasKilledBoss()) {
			return true;
			
		}
		}
		return false;
		
	}
	@Override
	public boolean canProgress(EntityPlayer player) {
		return ListQuests.HUNTEREXAM03.doneTask(player);
	}
}
