package com.izako.hunterx.izapi.quest;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;

import net.minecraft.entity.player.PlayerEntity;

public abstract class Quest {

	
	public abstract String getId();
	public abstract String getName();
	public abstract String getDescription();
	public abstract QuestLine getQuestLine();
	public abstract void renderDesc(int x, int y);
	
	public boolean doneTask(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		int prog = data.getProgress(this.getId());
		if(prog >= 100) {
			data.finishQuest(getId());
			return true;
		}
		return false;
	}
	

	public boolean hasQuest(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		return data.getQuests().get(this.getId()) != null ? true : false;
	}
	
	public void finishQuest(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		data.finishQuest(this.getId());
	}
	
	public boolean isFinished(PlayerEntity p ) {
		IHunterData data = HunterDataCapability.get(p);
		if(this.hasQuest(p)) {
		if(data.getProgress(this.getId()) > 100) {
			return true;
		}
		}
		return false;
	}
	
	public boolean canFinish(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		if(this.hasQuest(p)) {
		if(data.getProgress(this.getId()) == 100) {
			return true;
		}
		}
		return false;

	}
	

}
