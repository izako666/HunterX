package com.izako.HunterX.izapi.quests;

import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;

public class Quests {

	//The id of the quest.
	public String  getID() {
		return null;
	}
	//the name of the quest.
	public String getName() {
		return null;
		
	}
	//quest description
	public String getDesc() {
		return null;
	}
	//check if the player has the quest via its id.
	public boolean hasQuest(EntityPlayer player) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		return stats.getQuests().containsKey(this.getID());
	}
	//here you write its "task" using if statements,
	public boolean doneTask(EntityPlayer player) {
		return false;
		
	}
	
	public boolean canProgress() {
		return true;
	}
	
	public void setProgress(EntityPlayer player, Integer value) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
           stats.setProgress(this.getID(), value);
	}
}
