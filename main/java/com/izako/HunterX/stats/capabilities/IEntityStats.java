package com.izako.HunterX.stats.capabilities;

import java.util.HashMap;
import java.util.List;

public interface IEntityStats {

	//healthstat
	double getHealthStat();
	
	void setHealthStat(double value);
	
	//speedstat
	double getSpeedStat();
	
	void setSpeedStat(double value);
	
	//defensestat
	double getDefenseStat();
	
	void setDefenseStat(double value);
	
	//attackstat
	double getAttackStat();
	
	void setAttackStat(double value);
	
	//exam
	boolean isHunter();
	void setIsHunter(boolean value);
	
	boolean hasKilledKiriko();
	void setHasKilledKiriko(boolean value);
	
	boolean hasStarted2ndPhase();
	void setHasStarted2ndPhase(boolean value);
	
	Double timeHasRun();
	void setTimeHasRun(Double value);
	
	boolean hasStarted3rdPhase();
	void setHasStarted3rdPhase(boolean value);
	
	boolean hasKilledBoss();
	void setHasKilledBoss(boolean value);
	
	//quest api
	
	void giveQuest(String str, Integer value);
	
	HashMap<String, Integer> getQuests();

	void setProgress(String str, Integer value);
	
	
	Integer getProgress(String value);
	
	
	
}
