package com.izako.hunterx.init;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.izapi.quest.QuestLine;
import com.izako.hunterx.quests.HunterExam01;

public class ModQuests {

	public static final Quest HUNTEREXAM01 = new HunterExam01();
	
	public static List<Quest> QUESTS = new ArrayList<>();
	
	public static void questRegister() {
		QUESTS.add(HUNTEREXAM01);
	}
	
	public static Quest getInstance(String id) {
		for(int i = 0; i <= QUESTS.size(); i++) {
			if(QUESTS.get(i).getId() == id) {
				return QUESTS.get(i);
			}
		}
		return null;
	}
}
