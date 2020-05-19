package com.izako.hunterx.izapi.ability;

import com.izako.hunterx.Main;
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
    public Ability.Properties props;
	public enum AbilityType {
		CHARGING, PASSIVE, ONUSE
	}
	public enum AuraConsumptionType {
		PERCENTAGE, VALUE, NONE
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
	public  void renderDesc(int x, int y) {
	}

	// this method will always be required for every ability you make but it will
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
	@SuppressWarnings("static-access")
	public void onUse(PlayerEntity p) {

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
				}

				if (!this.isInPassive()) {
					((PassiveAbility) this).onEndPassive(p);
				}
				if (!this.isInPassive()) {
					this.setCooldown(this.props.maxCooldown);
				}
				break;
			case ONUSE:
				this.use(p);
				this.setCooldown(this.props.maxCooldown);
				this.consumeAura(p);
				break;
			}

		}
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
		return nbt;
	}

	// override this and call super before reading your data.
	public Ability readData(CompoundNBT nbt) {
		this.setCooldown(nbt.getInt("cooldown"));

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
	public void consumeAura(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		switch(this.props.conType) {
		
		case PERCENTAGE:
			if(data.getCurrentNen() - (this.props.auraCon.getAmount() * 100) / data.getNenCapacity() >= 0) {
			data.setCurrentNen(data.getCurrentNen() - (this.props.auraCon.getAmount() * 100) / data.getNenCapacity());
			} else {
				data.setCurrentNen(0);
				this.endAbility(p);
			}
			break;
		case VALUE:
			if(data.getCurrentNen() - this.props.auraCon.getAmount() >= 0) {
			data.setCurrentNen(data.getCurrentNen() - this.props.auraCon.getAmount());
			} else {
				data.setCurrentNen(0);
				this.endAbility(p);
			}
			break;
		case NONE:
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


	
	public   class Properties {
		
		public  AbilityType type;
		public  AuraConsumptionType conType;
		public   int maxCharging;
		public   int maxPassive;
		public   int maxCooldown;
		public Ability parent;
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
				if(abl.isInPassive()) {
					canRegen = false;
				}
			}
		}
		return canRegen;
	}

}

