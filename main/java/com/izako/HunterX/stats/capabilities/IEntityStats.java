package com.izako.HunterX.stats.capabilities;

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
	
	//misc
	boolean isHunter();
	void setIsHunter(boolean value);
}
