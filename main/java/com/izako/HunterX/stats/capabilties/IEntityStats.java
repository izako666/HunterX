package com.izako.HunterX.stats.capabilties;

public interface IEntityStats {

	public  double getHealthStat();
	public double getMaxHealthStat();
	
	
	public double getSpeedStat();
	public double getMaxSpeedStat();
	
	
	public double getAttackStat();
	public double getMaxAttackStat();
	
	
	public double getDefenseStat();
	public double getMaxDefenseStat();
	
	
	public double getEfficiencyStat();
	public double getMaxEfficiencyStat();
	
	
	public double getIntelligenceStat();
	public double getMaxIntelligenceStat();
	
	
	public boolean isHunter();
	
	public void setHealthStat(double value);
}
