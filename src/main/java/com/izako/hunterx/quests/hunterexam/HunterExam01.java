package com.izako.hunterx.quests.hunterexam;

import com.izako.hunterx.entities.ExaminerEntity;
import com.izako.hunterx.entities.WingEntity;
import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.izapi.NPCSpeech.QuestState;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.client.Minecraft;

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
	public boolean canFinish() {
		return this.getProgress() < 100;
	}

	@Override
	public boolean doneTask() {
		double prog = this.getProgress();
		if(prog < 100) {
			this.finishQuest();
			return true;
		}
		return false;
	}

	@Override
	public QuestScreenEndReturnType finishedTalkingEvent(QuestScreen scr) {
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
