package com.izako.wypi.data.quest;

import java.util.List;

import com.izako.wypi.quests.Quest;

public interface IQuestData
{
	boolean addInProgressQuest(Quest quest);
	boolean setInProgressQuest(int slot, Quest quest);
	boolean removeInProgressQuest(Quest quest);
	boolean hasInProgressQuest(Quest quest);
	<T extends Quest> T getInProgressQuest(T quest);
	<T extends Quest> T getInProgressQuest(int slot);
	Quest[] getInProgressQuests();
	void clearInProgressQuests(); 
	int countInProgressQuests();
	
	boolean addFinishedQuest(Quest quest);
	boolean removeFinishedQuest(Quest quest);
	boolean hasFinishedQuest(Quest quest);
	<T extends Quest> T getFinishedQuest(T quest);
	List<Quest> getFinishedQuests();
	void clearFinishedQuests(); 
	int countFinishedQuests();
}
