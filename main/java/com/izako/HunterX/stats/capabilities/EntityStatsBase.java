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

}
