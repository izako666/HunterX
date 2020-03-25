package com.izako.hunterx.izapi.quest;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;

import net.minecraft.entity.player.PlayerEntity;

public abstract class QuestLine {

	public abstract String getID();
	public abstract String getName();
	
	public abstract Quest[] getQuests();
	
	public void progress(PlayerEntity p) {
		if(this.getQuests()[0].hasQuest(p)) {
		if(this.getQuests()[0].doneTask(p)) {
			IHunterData data = HunterDataCapability.get(p);
			data.finishQuest(this.getQuests()[1].getId());
			if(this.getQuests()[0].hasQuest(p))
			  data.giveQuest(this.getQuests()[1].getId(), 0);
		}
		}
	}
	
	public void giveQuestLine(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		data.giveQuest(this.getQuests()[0].getId(), 0);
	}
}
