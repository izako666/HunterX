package com.izako.hunterx.izapi.ability;

import java.util.List;
import java.util.Random;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.init.ModAbilities;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
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
	private double xp = 0;
	private boolean isInPassive = false;
	private boolean isCharging = false;
	private int slot = -1;
    public Ability.Properties props = new Ability.Properties(this);
	public enum AbilityType {
		CHARGING, PASSIVE, ONUSE
	}
	public enum AuraConsumptionType {
		PERCENTAGE, VALUE, NONE
	}
    public Random rand = new Random();

	// the id is the identification thats used to save the data and compare the
	// abilities to see if they are the same
	// don't add special characters or camelcase or any spaces,
	// smallletterandnospaces.
	public abstract String getId();

	// this is for rendering make it look good.
	public abstract String getName();

	// this method is currently nonuseful but when i update the gui it will be
	// required.
	public  void renderDesc(int x, int y) {
	}

	// only get
	// called for an ability that extends Ability not ChargeableAbility or
	// PassiveAbility
	public  void use(PlayerEntity p) {
	}

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
	public void onUse(PlayerEntity p) {

		IAbilityData data = AbilityDataCapability.get(p);
		List<Ability> tempAbilities = data.getAbilitiesOfType(AbilityType.PASSIVE);
	    tempAbilities.removeIf(this::ifInPassivePredicate);
		if (this.getCooldown() <= 0) {
			switch (this.props.type) {

			case CHARGING:
				if (!this.isCharging()) {
					((ChargeableAbility) this).onStartCharging(p);
				}
				this.setCharging(true);
				break;
			case PASSIVE:
				this.setInPassive(!this.isInPassive());
				this.setPassiveTimer(this.props.maxPassive);
				if (this.isInPassive()) {
					((PassiveAbility) this).onStartPassive(p);
					tempAbilities.forEach((a) -> {
						if(!a.equals(this)) {
						a.stopAbility();
						}
					});
					
				}

				if (!this.isInPassive()) {
					((PassiveAbility) this).onEndPassive(p);
					this.setCooldown(this.props.maxCooldown);
				}
				
				break;
			case ONUSE:
				
				this.use(p);
				this.setCooldown(this.props.maxCooldown);
				if(this instanceof ITrainable) {
				 ITrainable trainable = (ITrainable) this;
				 this.setXp(this.getXp() + trainable.getXPOnUsage() + (rand.nextDouble() - 0.5), p);
				}
				if(!this.consumeAura(p)) {
					this.stopAbility();
				}
				break;
			}

		}
	}
	private boolean ifInPassivePredicate(Ability a) {
		return !a.isInPassive();
	}

	public void endAbility(PlayerEntity p) {
		switch(this.props.type) {
		
		case PASSIVE:
			this.setInPassive(false);
			this.setPassiveTimer(this.props.maxPassive);
			this.setCooldown(this.props.maxCooldown);
		   ( (PassiveAbility) this).onEndPassive(p);
		   break;
		case CHARGING:
			this.setCharging(false);
			this.setChargingTimer(0);
			this.setCooldown(this.props.maxCooldown);
			((ChargeableAbility) this).onEndCharging(p);
			break;
		case ONUSE:
			this.setCooldown(this.props.maxCooldown);
			break;
		}
	}
	// if you need extra data override this and get an nbt from the super
	// put the extra data you need from that nbt and then return the nbt
	public CompoundNBT writeData(int slot) {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putInt("cooldown", this.getCooldown());
		nbt.putInt("slotindex", slot);
		if(this instanceof ITrainable) {
			nbt.putDouble("xp", this.getXp());
			nbt.putInt("maxcooldown", this.props.maxCooldown);
			
		}
		return nbt;
	}

	// override this and call super before reading your data.
	public Ability readData(CompoundNBT nbt) {
		this.setCooldown(nbt.getInt("cooldown"));
		this.setSlot(nbt.getInt("slotindex"));
		if(this instanceof ITrainable) {
			this.setXp(nbt.getDouble("xp"));
			this.props.setMaxCooldown(nbt.getInt("maxcooldown"));
		}

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
	public boolean consumeAura(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		int amount = this.props.auraCon.getAmount();
		switch(this.props.conType) {
		
		case PERCENTAGE:
			if(data.getCurrentNen() - (amount * 100) / data.getNenCapacity() >= 0) {
			data.setCurrentNen(data.getCurrentNen() - (amount * 100) / data.getNenCapacity());
			 return true;
			} else {
             return false;
			}
		case VALUE:
			if(data.getCurrentNen() - amount >= 0) {
			data.setCurrentNen(data.getCurrentNen() - amount);
			return true;
			} else {
				return false;
			}
		case NONE:
			return true;
		}
		return true;
	}

	//this halts the ability completely without calling any of the onEnd 
	//logic, used for when you haven't enough aura
	public void stopAbility() {
		switch(this.props.type) {
		
		case PASSIVE:
			this.setInPassive(false);
			this.setPassiveTimer(this.props.maxPassive);
			this.setCooldown(this.props.maxCooldown);
		   break;
		case CHARGING:
			this.setCharging(false);
			this.setChargingTimer(0);
			this.setCooldown(this.props.maxCooldown);
			break;
		case ONUSE:
			this.setCooldown(this.props.maxCooldown);
			break;
		}

		
	}
	
	//used for rendering syncing atm
	public void initiateAbility() {
		switch(this.props.type) {
		
		case PASSIVE:
			this.setInPassive(true);
			this.setPassiveTimer(this.props.maxPassive);
		   break;
		case CHARGING:
			this.setCharging(true);
			break;
		case ONUSE:
			break;
		}


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


	public boolean isCharging() {
		return isCharging;
	}

	public void setCharging(boolean isCharging) {
		this.isCharging = isCharging;
	}


	
	public class Properties {
		
		public AbilityType type;
		public AuraConsumptionType conType = AuraConsumptionType.NONE;
		public int maxCharging = 10;
		public int maxPassive = 10;
		public int maxCooldown = 10;
		public final Ability parent;
		public IAuraConsumption auraCon = () -> {return 0;};
		public ResourceLocation tex;

		public Properties(Ability abl) {
			parent = abl;
			this.tex = new ResourceLocation(Main.MODID, "textures/abilities/" + this.parent.getId() + ".png");
		}
		
		public  Properties setAbilityType(AbilityType typ) {
			type = typ;
			return this;
		}
		public  Properties setConsumptionType(AuraConsumptionType typ) {
			conType = typ;
			return this;
		}
		public  Properties setMaxCharging(int maxCharge) {
			maxCharging = maxCharge;
			return this;
		}
		public  Properties setMaxPassive(int maxPassiv) {
			maxPassive = maxPassiv;
			return this;
		}
		public  Properties setMaxCooldown(int maxcool) {
			maxCooldown = maxcool;
			return this;
		}

		public Properties setTexture(ResourceLocation res) {
			this.tex = res;
			return this;
		}


		public Properties setAuraConsumption(IAuraConsumption i) {
			this.auraCon = i;
			return this;
		}
	}
	public interface IAuraConsumption {
		int getAmount();
	}

	public static boolean canRegenAura(PlayerEntity p) {
		boolean canRegen = true;
		IAbilityData data  = AbilityDataCapability.get(p);
		for(Ability abl : data.getSlotAbilities()) {
			if(abl != null) {
				if(abl.isInPassive() && !abl.equals(ModAbilities.ZETSU_ABILITY)) {
					canRegen = false;
				}
			}
		}
		return canRegen;
	}

	public boolean isActive() {
		switch(this.props.type) {
		
		case PASSIVE:
		   return this.isInPassive();
		case CHARGING:
			return this.isCharging();
		case ONUSE:
         return false;
		}

		return false;
	}



	public double getXp() {
		return xp;
	}

	public void setXp(double xp, PlayerEntity p) {
		ITrainable trainable = (ITrainable) this;
		if(this.rand.nextInt(1000) > 998 && this.xp < xp) {
			IAbilityData data = AbilityDataCapability.get(p);
			data.setNenCapacity(data.getNenCapacity() + 1);
		}
		if(this.xp < trainable.getMaxXP()) {
		this.xp = xp;
		} else {
			this.xp = trainable.getMaxXP();
		}
		this.props.setMaxCooldown((int) (this.props.maxCooldown * this.getCurrentCooldownScale()));
	}

	public void setXp(double xp) {
		this.xp = xp;

	}
	
	public  double getCurrentPowerScale() {
		if(this instanceof ITrainable) {

		ITrainable trainable = (ITrainable) this;
		double currentScale = (this.xp * trainable.getPowerScale()) / (trainable.getMaxXP());
		return currentScale;
		}
		return -1;
	}
	public  double getCurrentAuraConScale() {
		if(this instanceof ITrainable) {
		ITrainable trainable = (ITrainable) this;
		double range = 1 - trainable.getAuraConsumptionScale();
		double percentage = this.getXp() * 100 / trainable.getMaxXP();
		double difference = ( percentage * range )/ 100;
		double currentScale = trainable.getAuraConsumptionScale() + difference;
		return currentScale;
		}
		return -1;
	}
	public  double getCurrentCooldownScale() {
		if(this instanceof ITrainable) {
		ITrainable trainable = (ITrainable) this;
		double currentScale = (this.xp * trainable.getCooldownScale()) / (trainable.getMaxXP());
		return currentScale;
		}
		return -1;
	}

}

