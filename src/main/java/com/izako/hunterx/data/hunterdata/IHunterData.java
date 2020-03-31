package com.izako.hunterx.data.hunterdata;

import java.util.HashMap;

import com.izako.hunterx.izapi.quest.Quest;

public interface IHunterData {

	double getHealthStat();
	void setHealthStat(double stat);
	
	double getSpeedStat();
	void setSpeedStat(double stat);

	double getAttackStat();
	void setAttackStat(double stat);

	double getDefenseStat();
	void setDefenseStat(double stat);
	
	boolean isHunter();
	void setIsHunter(boolean val);
	
	//quest API
	HashMap<String, Integer> getQuests();
	void setQuests(HashMap<String, Integer> quests);
	void giveQuest(String str, Integer value);
	void finishQuest(String str);
	void removeQuest(String str);
	Integer getProgress(String str);
	void setProgress(String str, Integer value);
	
	

}
