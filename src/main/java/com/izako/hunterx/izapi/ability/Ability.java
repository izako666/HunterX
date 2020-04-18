package com.izako.hunterx.izapi.ability;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public abstract class Ability {

	private int cooldown = 0;
	private int chargingTimer = 0;
	private int passiveTimer = 0;
	private boolean isInPassive = false;
	private boolean isCharging = false;
	private int slot = -1;
	private static AbilityType type;

	
	
	public enum AbilityType{
		CHARGING,
		PASSIVE,
		ONUSE
	}
	public abstract String getId();
	public abstract String getName();
	public abstract void renderDesc(int x, int y);
	public abstract void use(PlayerEntity p);
	public void give(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		data.giveAbility(this);
	}
	
	public void putInSlot(PlayerEntity p, int slot) {
		IAbilityData data = AbilityDataCapability.get(p);

		
		data.putAbilityInSlot(this, slot);
		
	}
	@SuppressWarnings("static-access")
	public void onUse(PlayerEntity p) {
		
		if(this.getCooldown() <= 0) {
		switch(this.getType()) {
		
		case CHARGING :
			((ChargeableAbility) this).onStartCharging(p);
			this.setCharging(true);
		case PASSIVE :
			((PassiveAbility) this).onStartPassive(p);
			this.setInPassive(!this.isInPassive());
			if(!this.isInPassive()) {
				this.setCooldown(this.getMaxCooldown());
			}
		case ONUSE : 
			this.use(p);
			this.setCooldown(this.getMaxCooldown());
		}

		}
	}
	public CompoundNBT writeData(CompoundNBT nbt, int slot) {
		if(slot != -1) {
		nbt.putString("slotid" + this.getId(), this.getId());
		nbt.putInt("slotindex", slot);
		} else {
			nbt.putString("abilityid" + this.getId(), this.getId());
		}
		nbt.putInt(this.getId() + "cooldown", this.getCooldown());
		nbt.putInt(this.getId() + "chargetimer", this.getChargingTimer());
		nbt.putBoolean(this.getId() + "isinpassive", this.isInPassive());
		nbt.putBoolean(this.getId() + "ischarging", this.isCharging());
		nbt.putInt(this.getId() + "passivetimer", this.getPassiveTimer());
		return nbt;
	}

	public Ability readData(CompoundNBT nbt, boolean slotAbility) {
		this.setCooldown(nbt.getInt(this.getId() + "cooldown"));
		this.setChargingTimer(nbt.getInt(this.getId() + "chargetimer"));
		this.setInPassive(nbt.getBoolean(this.getId() + "isinpassive"));
		this.setPassiveTimer(nbt.getInt(this.getId() + "passivetimer"));
		this.setCharging(nbt.getBoolean(this.getId() + "ischarging"));
		if(slotAbility) {
			this.setSlot(nbt.getInt("slotindex"));
		}
		return this;
	}
	@Override
	public boolean equals(Object o) {
		if(o instanceof Ability) {
			Ability a = (Ability) o;
			if(this.getId().equalsIgnoreCase(a.getId())) {
				return true;
			}
		}
		return false;
	}
	//setters and getters
	public int getCooldown() {
		return cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getChargingTimer() {
		return chargingTimer;
	}

	public void setChargingTimer(int chargingTimer) {
		this.chargingTimer = chargingTimer;
	}

	public boolean isInPassive() {
		return isInPassive;
	}

	public void setInPassive(boolean isInPassive) {
		this.isInPassive = isInPassive;
	}

	public int getSlot() {
		return slot;
	}

	public void setSlot(int slot) {
		this.slot = slot;
	}
	public int getPassiveTimer() {
		return passiveTimer;
	}
	public void setPassiveTimer(int passiveTimer) {
		this.passiveTimer = passiveTimer;
	}
	public static AbilityType getType() {
		return type;
	}
	public static void setType(AbilityType type) {
		Ability.type = type;
	}
	public boolean isCharging() {
		return isCharging;
	}
	public void setCharging(boolean isCharging) {
		this.isCharging = isCharging;
	}
	public  abstract int getMaxCooldown();
	public  abstract int getMaxCharging();
	public  abstract int getMaxPassive();
	public abstract ResourceLocation getTexture();
}
