package com.izako.hunterx.init;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.quests.HunterExam01;
import com.izako.hunterx.quests.HunterExam02;
import com.izako.hunterx.quests.HunterExam03;
import com.izako.hunterx.quests.HunterExam04;

public class ModQuests {

	public static final Quest HUNTEREXAM01 = new HunterExam01();
	public static final Quest HUNTEREXAM02 = new HunterExam02();
	public static final Quest HUNTEREXAM03 = new HunterExam03();
	public static final Quest HUNTEREXAM04 = new HunterExam04();

	public static List<Quest> QUESTS = new ArrayList<>();

	public static void questRegister() {
		QUESTS.add(HUNTEREXAM01);
		QUESTS.add(HUNTEREXAM02);
		QUESTS.add(HUNTEREXAM03);
		QUESTS.add(HUNTEREXAM04);
	}

	public static Quest getInstance(String id) {
		for (int i = 0; i < (QUESTS.size()); i++) {
			if (QUESTS.get(i).getId().contains(id)) {
				return QUESTS.get(i);
			}
		}
		return null;
	}
}
