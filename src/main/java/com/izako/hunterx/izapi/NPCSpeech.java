package com.izako.hunterx.izapi;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public abstract class NPCSpeech {

	public abstract Quest[] getQuests(PlayerEntity p);
	public  QuestState getStateFromQuest(Quest q, PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		if(data.hasQuest(q)) {
			 if(data.getQuest(q).canFinish() || data.getQuest(q).isFinished()) {
				return QuestState.FULFILLED;
			}
	       
			return QuestState.NOTFULFILLED;
		}
		return QuestState.NOTSTARTED;
	}
	public abstract SequencedString[][] getSequencedStringFromQuest(int questIndex);
	public SequencedString[] getSpeechFromState(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		if(Helper.getCurrentQuest(this.getQuests(p), p) != -1) {
		SequencedString[][] sqstrs = this.getSequencedStringFromQuest(Helper.getCurrentQuest(this.getQuests(p), p));
		Quest q = data.getQuest(getQuests(p)[Helper.getCurrentQuest(this.getQuests(p), p)]);
		switch(this.getStateFromQuest(data.getQuest(q), p)) {
		
		case NOTSTARTED:
			return sqstrs[0];
		case NOTFULFILLED:
			return sqstrs[1];
		case FULFILLED:
			return sqstrs[2];
		default:
			return sqstrs[0];
			
		
		}
		} 
		return null;
	}
	
	public enum QuestState {
		 NOTSTARTED, NOTFULFILLED, FULFILLED
	}
}
