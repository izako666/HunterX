package com.izako.hunterx.izapi.ability;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;

public abstract class Ability {
	/*
	 * when making a new ability set the type in the constructor, if you set the
	 * ability type to a wrong type aka charging type for an ability that extends
	 * PassiveAbility then the game will crash and I will blame you.
	 *
	 */
	private int cooldown = 0;
	private int chargingTimer = 0;
	private int passiveTimer = 0;
	private boolean isInPassive = false;
	private boolean isCharging = false;
	private int slot = -1;
	private  int maxCharging = 100;
	private  int maxPassive = -1;
	private  int maxCooldown = 800;
	private static AbilityType type;

	public enum AbilityType {
		CHARGING, PASSIVE, ONUSE
	}

	// the id is the identification thats used to save the data and compare the
	// abilities to see if they are the same
	// don't add special characters or camelcase or any spaces,
	// smallletterandnospaces.
	public abstract String getId();

	// this is for rendering make it look good.
	public abstract String getName();

	// this method is currently nonuseful but when i update the gui it will be
	// required.
	public abstract void renderDesc(int x, int y);

	// this method will always be required for every ability you make but it will
	// only get
	// called for an ability that extends Ability not ChargeableAbility or
	// PassiveAbility
	public abstract void use(PlayerEntity p);

	// you can use this instead of touching capabilities to give an ability
	public void give(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		data.giveAbility(this);
	}

	// you probably don't need this, just don't.
	public void putInSlot(PlayerEntity p, int slot) {
		IAbilityData data = AbilityDataCapability.get(p);

		data.putAbilityInSlot(this, slot);

	}

	// dont touch or override this .
	@SuppressWarnings("static-access")
	public void onUse(PlayerEntity p) {

		if (this.getCooldown() <= 0) {
			switch (this.getType()) {

			case CHARGING:
				if (!this.isCharging()) {
					((ChargeableAbility) this).onStartCharging(p);
				}
				this.setCharging(true);
				break;
			case PASSIVE:
				if (!this.isInPassive()) {
					((PassiveAbility) this).onStartPassive(p);
				}
				this.setInPassive(!this.isInPassive());
				if (!this.isInPassive()) {
					((PassiveAbility) this).onEndPassive(p);
				}
				if (!this.isInPassive()) {
					this.setCooldown(this.getMaxCooldown());
				}
				break;
			case ONUSE:
				this.use(p);
				this.setCooldown(this.getMaxCooldown());
				break;
			}

		}
	}

	// if you need extra data override this and get an nbt from the super
	// put the extra data you need from that nbt and then return the nbt
	public CompoundNBT writeData(int slot) {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putInt("cooldown", this.getCooldown());
		nbt.putInt("chargetimer", this.getChargingTimer());
		nbt.putBoolean("isinpassive", this.isInPassive());
		nbt.putBoolean("ischarging", this.isCharging());
		nbt.putInt("passivetimer", this.getPassiveTimer());
		nbt.putInt("maxcharging", this.getMaxCharging());
		nbt.putInt("maxcooldown", this.getMaxCooldown());
		nbt.putInt("maxpassive", this.getMaxPassive());
		nbt.putInt("slotindex", slot);
		return nbt;
	}

	// override this and call super before reading your data.
	public Ability readData(CompoundNBT nbt) {
		this.setCooldown(nbt.getInt("cooldown"));
		this.setChargingTimer(nbt.getInt("chargetimer"));
		this.setInPassive(nbt.getBoolean("isinpassive"));
		this.setPassiveTimer(nbt.getInt("passivetimer"));
		this.setCharging(nbt.getBoolean("ischarging"));
		this.setMaxCharging(nbt.getInt("maxcharging"));
		this.setMaxPassive(nbt.getInt("maxpassive"));
		this.setMaxCooldown(nbt.getInt("maxcooldown"));

		this.setSlot(nbt.getInt("slotindex"));
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (o instanceof Ability) {
			Ability a = (Ability) o;
			if (this.getId().equalsIgnoreCase(a.getId())) {
				return true;
			}
		}
		return false;
	}

	// setters and getters
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

	public AbilityType getType() {
		return type;
	}

	public void setType(AbilityType type) {
		Ability.type = type;
	}

	public boolean isCharging() {
		return isCharging;
	}

	public void setCharging(boolean isCharging) {
		this.isCharging = isCharging;
	}

	// icon texture
	public abstract ResourceLocation getTexture();

	public  int getMaxCharging() {
		return maxCharging;
	}

	public  int getMaxPassive() {
		return maxPassive;
	}

	public  int getMaxCooldown() {
		return maxCooldown;
	}

	public  void setMaxCharging(int val) {
		maxCharging = val;
	}

	public  void setMaxPassive(int val) {
		maxPassive = val;
	}

	public  void setMaxCooldown(int val) {
		maxCooldown = val;
	}
}
