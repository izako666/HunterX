package com.izako.HunterX.quests;

import com.izako.HunterX.izapi.quests.Quests;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;

public class HunterExam01 extends Quests{

	@Override
	public String getID() {
		return "questHunterExam01";
	}

	//the name of the quest.
	@Override
	public String getName() {
		return "Kill a Kiriko.";
		
	}
	//quest description
	@Override
	public String getDesc() {
		return "You must kill a kiriko before procceeding";
	}

	//here you write its "task" using if statements
	@Override
	public boolean doneTask(EntityPlayer player) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		 if(stats.hasKilledKiriko()) {
			 return true;
		 }else {
		return false;
		 }
	}

}
