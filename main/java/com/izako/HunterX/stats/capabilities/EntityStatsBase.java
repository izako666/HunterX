package com.izako.HunterX.stats.capabilities;


import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.INBTSerializable;

public class EntityStatsBase implements IEntityStats{

	private double healthStat = 0;
	private double speedStat = 0;
	private double defenseStat = 0;
	private double attackStat = 0;
	private boolean isHunter = false;
	private boolean hasKilledKiriko = false;
	private boolean hasStarted2ndPhase = false;
	private double timeHasRun = 0;
	private boolean hasStarted3rdPhase = false;
	private boolean hasKilledBoss = false;
	@Override
	public double getHealthStat() {
		//gets the health stat
		return this.healthStat;
	}

	@Override
	public void setHealthStat(double value) {
		// sets the health stat
		this.healthStat = value;
		
	}

	@Override
	public double getSpeedStat() {
		// TODO Auto-generated method stub
		return this.speedStat;
	}

	@Override
	public void setSpeedStat(double value) {
		// TODO Auto-generated method stub
		this.speedStat = value;
	}

	@Override
	public double getDefenseStat() {
		// TODO Auto-generated method stub
		return this.defenseStat;
	}

	@Override
	public void setDefenseStat(double value) {
		// TODO Auto-generated method stub
		this.defenseStat = value;
	}

	@Override
	public double getAttackStat() {
		// TODO Auto-generated method stub
		return this.attackStat;
	}

	@Override
	public void setAttackStat(double value) {
		// TODO Auto-generated method stub
		this.attackStat = value;
	}

	@Override
	public boolean isHunter() {
		// TODO Auto-generated method stub
		return this.isHunter;
	}

	@Override
	public void setIsHunter(boolean value) {
		// TODO Auto-generated method stub
		this.isHunter = value;
		
	}

	@Override
	public boolean hasKilledKiriko() {
		return this.hasKilledKiriko;
	}

	@Override
	public void setHasKilledKiriko(boolean value) {

		this.hasKilledKiriko = value;
	}

	@Override
	public boolean hasStarted2ndPhase() {
		// TODO Auto-generated method stub
		return this.hasStarted2ndPhase;
	}

	@Override
	public void setHasStarted2ndPhase(boolean value) {
		// TODO Auto-generated method stub
		this.hasStarted2ndPhase = value;
		
	}

	@Override
	public Double timeHasRun() {
		// TODO Auto-generated method stub
		return this.timeHasRun;
	}

	@Override
	public void setTimeHasRun(Double value) {
		// TODO Auto-generated method stub
		timeHasRun = value;
		
	}

	@Override
	public boolean hasStarted3rdPhase() {
		// TODO Auto-generated method stub
		return this.hasStarted3rdPhase;
	}

	@Override
	public void setHasStarted3rdPhase(boolean value) {
		// TODO Auto-generated method stub
		this.hasStarted3rdPhase = value;
		
	}

	@Override
	public boolean hasKilledBoss() {
		// TODO Auto-generated method stub
		return this.hasKilledBoss;
	}

	@Override
	public void setHasKilledBoss(boolean value) {
		// TODO Auto-generated method stub
		this.hasKilledBoss = value;
		
	}

}
