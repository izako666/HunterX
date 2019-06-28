package com.izako.HunterX.stats.capabilties;

public class EntityStatsBase implements IEntityStats {

	private double  HealthStat,  MaxHealthStat,  SpeedStat,  MaxSpeedStat, 
	 AttackStat,  MaxAttackStat,  DefenseStat,  MaxDefenseStat,  EfficiencyStat,
	 MaxEfficiencyStat,  IntelligenceStat,  MaxIntelligenceStat;

	@Override
	public double getHealthStat() {
		// TODO Auto-generated method stub
		return this.HealthStat;
	}

	@Override
	public double getMaxHealthStat() {
		// TODO Auto-generated method stub
		return this.MaxHealthStat;
	}

	@Override
	public double getSpeedStat() {
		// TODO Auto-generated method stub
		return this.SpeedStat;
	}

	@Override
	public double getMaxSpeedStat() {
		// TODO Auto-generated method stub
		return this.MaxSpeedStat;
	}

	@Override
	public double getAttackStat() {
		// TODO Auto-generated method stub
		return this.AttackStat;
	}

	@Override
	public double getMaxAttackStat() {
		// TODO Auto-generated method stub
		return this.MaxAttackStat;
	}

	@Override
	public double getDefenseStat() {
		// TODO Auto-generated method stub
		return this.DefenseStat;
	}

	@Override
	public double getMaxDefenseStat() {
		// TODO Auto-generated method stub
		return this.MaxDefenseStat;
	}

	@Override
	public double getEfficiencyStat() {
		// TODO Auto-generated method stub
		return this.EfficiencyStat;
	}

	@Override
	public double getMaxEfficiencyStat() {
		// TODO Auto-generated method stub
		return this.MaxEfficiencyStat;
	}

	@Override
	public double getIntelligenceStat() {
		// TODO Auto-generated method stub
		return this.IntelligenceStat;
	}

	@Override
	public double getMaxIntelligenceStat() {
		// TODO Auto-generated method stub
		return this.MaxIntelligenceStat;
	}

	@Override
	public boolean isHunter() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setHealthStat(double value) {
		// TODO Auto-generated method stub
		this.HealthStat = value;
		
	}

}
