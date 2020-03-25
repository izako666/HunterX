package com.izako.hunterx.data.hunterdata;

import java.util.HashMap;

public interface IHunterData {

	double getHealthStat();
	void setHealthStat(double stat);
	
	double getSpeedStat();
	void setSpeedStat(double stat);

	double getAttackStat();
	void setAttackStat(double stat);

	double getDefenseStat();
	void setDefenseStat(double stat);
	
	//quest API
	HashMap<String, Integer> getQuests();
	void giveQuest(String str, Integer value);
	void finishQuest(String str);
	Integer getProgress(String str);
	void setProgress(String str, Integer value);
	
	

}
