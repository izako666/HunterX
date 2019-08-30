package com.izako.HunterX.stats.capabilities;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.izako.HunterX.init.ListAbilities;
import com.izako.HunterX.izapi.abilities.Ability;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.AbilityPacketSync;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
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
    private HashMap<String, Integer> quests = new HashMap<String, Integer>();
    private List<Ability> abilities = new ArrayList<Ability>();
    private HashMap<String, Boolean> isPassiveActive = new HashMap<String, Boolean>();
    private HashMap<String, Boolean> isOnCooldown = new HashMap<String, Boolean>();
    private Ability[] Slots = new Ability[9];
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



	@Override
	public void setProgress(String str, Integer value) {
		// TODO Auto-generated method stub
		this.quests.replace(str, this.quests.get(str), value);
	}

	@Override
	public Integer getProgress(String value) {
		// TODO Auto-generated method stub
		return this.quests.get(value);
	}

	@Override
	public HashMap<String, Integer> getQuests() {
		// TODO Auto-generated method stub
		return quests;
	}

	@Override
	public void giveQuest(String str, Integer value) {
		// TODO Auto-generated method stub
		this.quests.put(str, value);
		
	}

	@Override
	public void giveAbility(Ability ability) {
		// TODO Auto-generated method stub
		this.abilities.add(ability);
	}

	@Override
	public void removeAbility(Ability ability) {
		// TODO Auto-generated method stub
		this.abilities.remove(ability);
	}

	@Override
	public List<Ability> getAbilities() {
		// TODO Auto-generated method stub
		return this.abilities;
	}

	@Override
	public boolean isPassiveActive(String str) {
		// TODO Auto-generated method stub
		return this.isPassiveActive.get(str);
	}

	@Override
	public boolean isOnCooldown(String str) {
		// TODO Auto-generated method stub
		return this.isOnCooldown.get(str);
	}

	@Override
	public void setIsPassiveActive(boolean value, String str) {
		// TODO Auto-generated method stub
		if(this.isPassiveActive.get(str) == null) {
		this.isPassiveActive.put(str, value);
		} else {
			this.isPassiveActive.replace(str, this.isPassiveActive.get(str), value);
		}
	}

	@Override
	public void setIsOnCooldown(boolean value, String str) {
		if(this.isPassiveActive.get(str) == null) {
		this.isPassiveActive.put(str, value);
		} else {
			this.isPassiveActive.replace(str, this.isPassiveActive.get(str), value);
		}
		
	}

	@Override
	public HashMap<String, Boolean> getIsPassiveActiveAll() {
		// TODO Auto-generated method stub
		return this.isPassiveActive;
	}

	@Override
	public HashMap<String, Boolean> getIsOnCooldownAll() {
		// TODO Auto-generated method stub
		return this.isOnCooldown;
	}

	@Override
	public void setAbilityToSlot(Integer slot, Ability a) {
		// TODO Auto-generated method stub
		
       for(int i = 0; i < this.Slots.length; i++) {
    	   if(this.Slots[i] == a) {
    		   this.Slots[i] = null;
    		   
    	   }
       }
       this.Slots[slot] = a;
       System.out.println(this.Slots[slot]);
	}

	@Override
	public void removeAbilityFromSlot(Ability a) {
		// TODO Auto-generated method stub
		
		for(int i =0; i < this.Slots.length; i++) {
			if(this.Slots[i] == a) {
				this.Slots[i] = null;
			}
		}
		
	}

	@Override
	public Ability[] getSlotsList() {
		// TODO Auto-generated method stub
		return this.Slots;
	}

	@Override
	public Ability getAbilityNonNull(Integer slot) {
		// TODO Auto-generated method stub
		return this.Slots[slot];
		
	}





}
