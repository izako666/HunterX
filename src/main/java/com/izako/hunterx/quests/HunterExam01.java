package com.izako.hunterx.quests;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.entities.ExaminerEntity;
import com.izako.hunterx.entities.WingEntity;
import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.izapi.NPCSpeech.QuestState;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.izapi.quest.QuestLine;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public class HunterExam01 extends Quest {

	@Override
	public String getId() {
		return "hunterexam01";
	}

	@Override
	public String getName() {
		return "Exam Location";
	}

	@Override
	public String getDescription() {
		return "Wing has informed you that you must get to the hunter exam in two days.";
	}

	@Override
	public QuestLine getQuestLine() {
		return null;
	}

	@Override
	public void renderDesc(int x, int y) {
		
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "Wing has informed you ", x, y, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "that you must get to the", x, y + 20, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "hunter exam in two days.", x, y + 40, 16777215);

	}


	
	@Override
	public boolean canFinish(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		if(this.hasQuest(p)) {
		if(data.getProgress(this.getId()) < 100) {
			return true;
		}
		}
		return false;

	}

	@Override
	public boolean doneTask(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		int prog = data.getProgress(this.getId());
		if(prog < 100) {
			data.finishQuest(getId());
			return true;
		}
		return false;
	}

	@Override
	public QuestScreenEndReturnType applyQuestScreenEndLogic(QuestScreen scr) {
		QuestState state = scr.qgiver.getSpeech().getStateFromQuest(this, scr.p);
		switch(state) {
		case NOTSTARTED:
			if(scr.qgiver instanceof ExaminerEntity) {return QuestScreenEndReturnType.NULL;}
			return QuestScreenEndReturnType.QUEST;
		case NOTFULFILLED:
			if(scr.qgiver instanceof WingEntity) {return QuestScreenEndReturnType.NULL;}
			this.removeQuest(scr.p);
			return QuestScreenEndReturnType.NULL;
		case FULFILLED:
			this.finishQuest(scr.p);
			return QuestScreenEndReturnType.NULL;
		}

		return QuestScreenEndReturnType.NULL;
		
	}

}
