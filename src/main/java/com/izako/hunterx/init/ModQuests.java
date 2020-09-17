package com.izako.hunterx.init;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.quests.basics.GyoQuest;
import com.izako.hunterx.quests.basics.InQuest;
import com.izako.hunterx.quests.basics.KenQuest;
import com.izako.hunterx.quests.basics.RenQuest;
import com.izako.hunterx.quests.basics.RyuQuest;
import com.izako.hunterx.quests.basics.ShuQuest;
import com.izako.hunterx.quests.basics.ZetsuQuest;
import com.izako.hunterx.quests.hunterexam.HunterExam01;
import com.izako.hunterx.quests.hunterexam.HunterExam02;
import com.izako.hunterx.quests.hunterexam.HunterExam03;
import com.izako.hunterx.quests.hunterexam.HunterExam04;

public class ModQuests {

	public static final Quest HUNTEREXAM01 = new HunterExam01();
	public static final Quest HUNTEREXAM02 = new HunterExam02();
	public static final Quest HUNTEREXAM03 = new HunterExam03();
	public static final Quest HUNTEREXAM04 = new HunterExam04();
	public static final Quest RENQUEST = new RenQuest();
	public static final Quest ZETSUQUEST = new ZetsuQuest();
	public static final Quest GYOQUEST = new GyoQuest();
	public static final Quest SHUQUEST = new ShuQuest();
	public static final Quest INQUEST = new InQuest();
	public static final Quest KENQUEST = new KenQuest();
	public static final Quest RYUQUEST = new RyuQuest();

	public static List<Quest> QUESTS = new ArrayList<>();

	public static void questRegister() {
		QUESTS.add(HUNTEREXAM01);
		QUESTS.add(HUNTEREXAM02);
		QUESTS.add(HUNTEREXAM03);
		QUESTS.add(HUNTEREXAM04);
		QUESTS.add(RENQUEST);
		QUESTS.add(ZETSUQUEST);
		QUESTS.add(GYOQUEST);
		QUESTS.add(SHUQUEST);
		QUESTS.add(INQUEST);
		QUESTS.add(KENQUEST);
		QUESTS.add(RYUQUEST);
		}

	public static Quest newInstance(String id) {
		for (int i = 0; i < (QUESTS.size()); i++) {
			if (QUESTS.get(i).getId().contains(id)) {
				try {
					return QUESTS.get(i).getClass().newInstance();
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
		}
		return null;
	}
	
	public static Quest getInstance(String id) {
		for(int i = 0; i < QUESTS.size(); i++) {
			if(QUESTS.get(i).getId().equalsIgnoreCase(id)) {
				return QUESTS.get(i);
			}
		}
		return null;
	}
}
