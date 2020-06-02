package com.izako.hunterx.data.hunterdata;

import java.util.HashMap;

import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.nbt.CompoundNBT;

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
	
	//quest API
	HashMap<String, Integer> getQuests();
	void setQuests(HashMap<String, Integer> quests);
	void giveQuest(String str, Integer value);
	void finishQuest(String str);
	void removeQuest(String str);
	Integer getProgress(String str);
	void setProgress(String str, Integer value);
	CompoundNBT getExtraQuestData(String id);
	CompoundNBT getExtraQuestData(Quest quest);
	HashMap<String, CompoundNBT> getExtraQuestData();
	CompoundNBT getOrCreateExtraQuestData(Quest quest);
	CompoundNBT getOrCreateExtraQuestData(String id);

	
	

}
