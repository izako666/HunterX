package com.izako.hunterx.data.hunterdata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.izako.hunterx.izapi.EnumStats;
import com.izako.hunterx.izapi.quest.Quest;

public class HunterDataBase implements IHunterData {

	double healthStat = 0;
	double speedStat = 0;
	double attackStat = 0;
	double defenseStat = 0;
	boolean isHunter = false;
	boolean isCharMade = false;
	boolean selectingAbility = false;
	EnumStats firstMaxed = null;
	EnumStats secondMaxed = null;
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
			this.checkStats(EnumStats.HEALTH);
			
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
			this.checkStats(EnumStats.SPEED);
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
			this.checkStats(EnumStats.ATTACK);
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
			this.checkStats(EnumStats.DEFENSE);
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

	@Override
	public EnumStats getFirstMaxed() {
		if(this.firstMaxed == null) {
			if(this.getHealthStat() > this.getSpeedStat() && this.getHealthStat() > this.getAttackStat() && this.getHealthStat() > this.getDefenseStat()) {
				return EnumStats.HEALTH;
			} else if(this.getAttackStat() > this.getHealthStat() && this.getAttackStat() > this.getSpeedStat() && this.getAttackStat() > this.getDefenseStat()) {
				return EnumStats.ATTACK;
			} else if(this.getDefenseStat() > this.getHealthStat() && this.getDefenseStat() > this.getAttackStat() && this.getDefenseStat() > this.getSpeedStat()) {
				return EnumStats.DEFENSE;
			} else {
				return EnumStats.SPEED;
			}
		}
		return this.firstMaxed;
	}

	@Override
	public EnumStats getSecondMaxed() {
		if(this.secondMaxed == null) {
			EnumStats firstMax = this.getFirstMaxed();
			List<EnumStats> stats = new ArrayList<>(Arrays.asList(EnumStats.HEALTH,EnumStats.ATTACK,EnumStats.DEFENSE,EnumStats.SPEED));
			stats.remove(firstMax);
		
			for(int i = 0; i < stats.size()  - 1; i++) {
				EnumStats stat = stats.get(i);
				EnumStats stat2 = stats.get(i + 1);
				if(this.getStat(stat) < this.getStat(stat2)) {
					stats.remove(stat);
					try {
					stats.add(i + 1, stat);
					} catch(Exception e) {
						stats.add(stat);
					}
				}
			}
			return stats.get(0);
		}
		return this.secondMaxed;

	}
	
	
	private void checkStats(EnumStats stat) {
		if(this.firstMaxed == null) {
			this.firstMaxed = stat;
		} else if(this.secondMaxed == null) {
			this.secondMaxed = stat;
		}
	}
	
	
	private double getStat(EnumStats stat) {
		switch(stat) {
		
		case HEALTH:
			return this.getHealthStat();
		case ATTACK:
			return this.getAttackStat();
		case DEFENSE:
			return this.getDefenseStat();
		case SPEED:
			return this.getSpeedStat();
		default:
			return this.getHealthStat();
		}
	}
}
