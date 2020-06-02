package com.izako.hunterx.data.hunterdata;

import java.util.HashMap;

import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.nbt.CompoundNBT;

public class HunterDataBase implements IHunterData {

	double healthStat = 0;
	double speedStat = 0;
	double attackStat = 0;
	double defenseStat = 0;
	boolean isHunter = false;
	boolean isCharMade = false;
	HashMap<String, Integer> quests = new HashMap<>();
	HashMap<String, CompoundNBT> EXTRAQUESTDATA = new HashMap<>();

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
	public HashMap<String, Integer> getQuests() {
		return quests;
	}

	@Override
	public void giveQuest(String str, Integer value) {
		if (!quests.containsKey(str)) {
			quests.put(str, value);
		}
	}

	@Override
	public void finishQuest(String str) {

		if (quests.containsKey(str)) {
			this.setProgress(str, 101);
		}
	}

	@Override
	public Integer getProgress(String str) {
		return quests.get(str);
	}

	@Override
	public void setProgress(String str, Integer value) {
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
		this.quests = quests;
	}

	@Override
	public void removeQuest(String str) {
		
		if(this.quests.containsKey(str)) {
			this.quests.remove(str);
		}
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
	public CompoundNBT getExtraQuestData(String id) {
		return EXTRAQUESTDATA.get(id);
	}

	@Override
	public CompoundNBT getExtraQuestData(Quest quest) {
		return EXTRAQUESTDATA.get(quest.getId());
	}

	@Override
	public HashMap<String, CompoundNBT> getExtraQuestData() {
		return EXTRAQUESTDATA;
	}

	@Override
	public CompoundNBT getOrCreateExtraQuestData(Quest quest) {
		CompoundNBT nbt = this.getExtraQuestData(quest);
		if(nbt == null) {
			this.EXTRAQUESTDATA.put(quest.getId(), new CompoundNBT());
			nbt = this.getExtraQuestData(quest);
		}
		return nbt;
	}

	@Override
	public CompoundNBT getOrCreateExtraQuestData(String id) {
		CompoundNBT nbt = this.getExtraQuestData(id);
		if(nbt == null) {
			this.EXTRAQUESTDATA.put(id, new CompoundNBT());
			nbt = this.getExtraQuestData(id);
		}
		return nbt;
	}



}
