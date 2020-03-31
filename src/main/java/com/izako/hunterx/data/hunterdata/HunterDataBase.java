package com.izako.hunterx.data.hunterdata;

import java.util.HashMap;

import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.Quest;

public class HunterDataBase implements IHunterData {

	double healthStat = 0;
	double speedStat = 0;
	double attackStat = 0;
	double defenseStat = 0;
	boolean isHunter = false;
	HashMap<String, Integer> quests = new HashMap<>();

	@Override
	public double getHealthStat() {
		// TODO Auto-generated method stub
		return this.healthStat;
	}

	@Override
	public void setHealthStat(double stat) {
		// TODO Auto-generated method stub
		if (stat <= 10) {
			this.healthStat = stat;
		} else {
			this.healthStat = 10;
		}
	}

	@Override
	public double getSpeedStat() {
		// TODO Auto-generated method stub
		return this.speedStat;
	}

	@Override
	public void setSpeedStat(double stat) {
		// TODO Auto-generated method stub
		if (stat <= 10) {
			this.speedStat = stat;
		} else {
			this.speedStat = 10;
		}
	}

	@Override
	public double getAttackStat() {
		// TODO Auto-generated method stub
		return this.attackStat;
	}

	@Override
	public void setAttackStat(double stat) {
		// TODO Auto-generated method stub
		if (stat <= 10) {
			this.attackStat = stat;
		} else {
			this.attackStat = 10;
		}
	}

	@Override
	public double getDefenseStat() {
		// TODO Auto-generated method stub
		return this.defenseStat;
	}

	@Override
	public void setDefenseStat(double stat) {
		// TODO Auto-generated method stub
		if (stat <= 10) {
			this.defenseStat = stat;
		} else {
			this.defenseStat = 10;
		}
	}

	@Override
	public HashMap<String, Integer> getQuests() {
		// TODO Auto-generated method stub
		return quests;
	}

	@Override
	public void giveQuest(String str, Integer value) {
		// TODO Auto-generated method stub
		if (!quests.containsKey(str)) {
			quests.put(str, value);
		}
	}

	@Override
	public void finishQuest(String str) {
		// TODO Auto-generated method stub

		if (quests.containsKey(str)) {
			this.setProgress(str, 101);
		}
	}

	@Override
	public Integer getProgress(String str) {
		// TODO Auto-generated method stub
		return quests.get(str);
	}

	@Override
	public void setProgress(String str, Integer value) {
		// TODO Auto-generated method stub
		if (value < 0) {
			System.out.println("false value");
			return;
		}
		if (quests.containsKey(str)) {
				quests.replace(str, quests.get(str), value);
			
		}
	}

	@Override
	public void setQuests(HashMap<String, Integer> quests) {
		// TODO Auto-generated method stub
		this.quests = quests;
	}

	@Override
	public void removeQuest(String str) {
		// TODO Auto-generated method stub
		
		if(this.quests.containsKey(str)) {
			this.quests.remove(str);
		}
	}

	@Override
	public boolean isHunter() {
		// TODO Auto-generated method stub
		return this.isHunter;
	}

	@Override
	public void setIsHunter(boolean val) {
		// TODO Auto-generated method stub
		this.isHunter = val;
	}



}
