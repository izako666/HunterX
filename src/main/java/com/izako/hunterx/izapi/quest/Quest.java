package com.izako.hunterx.izapi.quest;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.izapi.NPCSpeech.QuestState;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SetQuestPacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class Quest {

	
	public abstract String getId();
	public abstract String getName();
	public abstract String getDescription();
	public abstract QuestLine getQuestLine();
	@OnlyIn(Dist.CLIENT)
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
	@OnlyIn(Dist.CLIENT)
	public QuestScreenEndReturnType applyQuestScreenEndLogic(QuestScreen scr) {
		QuestState state = scr.qgiver.getSpeech().getStateFromQuest(scr.currentQuest, scr.p);
		switch(state) {
		case NOTSTARTED:
			return QuestScreenEndReturnType.QUEST;
		case NOTFULFILLED:
			return QuestScreenEndReturnType.NULL;
		case FULFILLED:
			this.finishQuest(scr.p);
			PacketHandler.INSTANCE.sendToServer(new SetQuestPacket(this.getId(), false));
			return QuestScreenEndReturnType.NULL;
		}
		return QuestScreenEndReturnType.NULL;
		
	}
	
	public void removeQuest(PlayerEntity p) {

		IHunterData data = HunterDataCapability.get(p);
		data.removeQuest(this.getId());
	}

	public void giveQuest(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		data.giveQuest(getId(), 0);
	}
	
	@OnlyIn(Dist.CLIENT)
	public SequencedString[] getAdditionalMessage(QuestScreen scr) {
		return null;
	}

	
	public enum QuestScreenEndReturnType {
		NULL,QUEST,MESSAGE
	}
}
