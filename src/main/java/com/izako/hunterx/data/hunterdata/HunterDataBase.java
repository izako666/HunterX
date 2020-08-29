package com.izako.hunterx.data.hunterdata;

import java.util.ArrayList;
import java.util.List;

import com.izako.hunterx.izapi.quest.Quest;

public class HunterDataBase implements IHunterData {

	double healthStat = 0;
	double speedStat = 0;
	double attackStat = 0;
	double defenseStat = 0;
	boolean isHunter = false;
	boolean isCharMade = false;
	boolean selectingAbility = false;
	List<Quest> quests = new ArrayList<>();

	@Override
	public double getHealthStat() {
		return this.healthStat;
	}

	@Override
	public void setHealthStat(double stat) {
		if (stat <= 10) {
			this.healthStat = stat;
		} else {
			this.healthStat = 10;
		}
	}

	@Override
	public double getSpeedStat() {
		return this.speedStat;
	}

	@Override
	public void setSpeedStat(double stat) {
		if (stat <= 10) {
			this.speedStat = stat;
		} else {
			this.speedStat = 10;
		}
	}

	@Override
	public double getAttackStat() {
		return this.attackStat;
	}

	@Override
	public void setAttackStat(double stat) {
		if (stat <= 10) {
			this.attackStat = stat;
		} else {
			this.attackStat = 10;
		}
	}

	@Override
	public double getDefenseStat() {
		return this.defenseStat;
	}

	@Override
	public void setDefenseStat(double stat) {
		if (stat <= 10) {
			this.defenseStat = stat;
		} else {
			this.defenseStat = 10;
		}
	}

	@Override
	public void giveQuest(Quest q) {

		quests.remove(q);
		quests.add(q);

	}



	@Override
	public void removeQuest(Quest q) {

		this.quests.remove(q);
	}

	@Override
	public boolean isHunter() {
		return this.isHunter;
	}

	@Override
	public void setIsHunter(boolean val) {
		this.isHunter = val;
	}

	@Override
	public boolean isCharacterMade() {
		return isCharMade;
	}

	@Override
	public void setIsCharacterMade(boolean val) {
		isCharMade = val;
	}

	@Override
	public List<Quest> getQuests() {
		return this.quests;
	}

	@Override
	public void setQuests(List<Quest> quests) {
		this.quests = quests;



	}

	@Override
	public boolean hasQuest(Quest q) {
		return this.quests.contains(q);
	}

	@Override
	public Quest getQuest(Quest q) {
		if(this.quests.contains(q)) {
			for(int i = 0; i < this.quests.size(); i++) {
				if(this.quests.get(i).equals(q)) {
					return this.quests.get(i);
				}
			}
		}
		return null;
	}

	@Override
	public boolean isSelectingAbility() {
		return this.selectingAbility;
	}

	@Override
	public void setSelectingAbility(boolean val) {

		this.selectingAbility = val;
	}
}
