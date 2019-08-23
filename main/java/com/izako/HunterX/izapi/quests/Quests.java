package com.izako.HunterX.izapi.quests;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.QuestPacketSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;

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
	
	public boolean canProgress(EntityPlayer player) {
		return true;
	}
	
	public void setProgress(EntityPlayer player, Integer value) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
           stats.setProgress(this.getID(), value);
           if(player instanceof EntityPlayerMP) {
           ModidPacketHandler.INSTANCE.sendTo(new QuestPacketSync(this.getID(), 1, stats.getProgress(this.getID())), (EntityPlayerMP) player);
           }
           ModidPacketHandler.INSTANCE.sendToServer(new QuestPacketSync(this.getID(), 1, stats.getProgress(this.getID())));

           
	}
	
	public void giveQuest(EntityPlayer player) {
		IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
        stats.giveQuest(this.getID(), 0);
        if(player instanceof EntityPlayerMP) {
        ModidPacketHandler.INSTANCE.sendTo(new QuestPacketSync(this.getID(), 2, stats.getProgress(this.getID())), (EntityPlayerMP) player);
        }
        ModidPacketHandler.INSTANCE.sendToServer(new QuestPacketSync(this.getID(), 2, stats.getProgress(this.getID())));

	}
}
