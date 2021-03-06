package com.izako.hunterx.data.hunterdata;

import java.util.List;

import com.izako.hunterx.izapi.EnumStats;
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
	
	boolean isCharacterMade();
	void setIsCharacterMade(boolean val);
	
	boolean isSelectingAbility();
	void setSelectingAbility(boolean val);
	
	//quest API
	List<Quest> getQuests();
	void setQuests(List<Quest> quests);
	void giveQuest(Quest q);
	void removeQuest(Quest q);
	boolean hasQuest(Quest q);
	Quest getQuest(Quest q);

	EnumStats getFirstMaxed();
	EnumStats getSecondMaxed();
	
	
	int getJenny();
	void setJenny(int jenny);
	
	
	

}
