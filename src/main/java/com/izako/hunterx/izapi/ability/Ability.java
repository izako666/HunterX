package com.izako.hunterx.izapi.ability;

import java.util.List;
import java.util.Random;

import com.izako.hunterx.Main;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.events.custom.AbilityActivateEvent;
import com.izako.hunterx.events.custom.AbilityGiveEvent;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SyncAbilityRenderingPacket;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;

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
		CHARGING, PASSIVE, ONUSE, CHARGING_PASSIVE
	}
	public enum AuraConsumptionType {
		PERCENTAGE, VALUE, NONE
	}
    public Random rand = new Random();

    public abstract String getId();

	public abstract String getName();

	public  void renderDesc(int x, int y) {
	}

	public  void use(LivingEntity p) {
	}

	public void give(LivingEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		data.giveAbility(this);
		MinecraftForge.EVENT_BUS.post(new AbilityGiveEvent(this, p));
	}

	public void putInSlot(LivingEntity p, int slot) {
		IAbilityData data = AbilityDataCapability.get(p);

		data.putAbilityInSlot(this, slot);

	}


	public void onUse(LivingEntity p) {

		IAbilityData data = AbilityDataCapability.get(p);
		List<Ability> tempAbilities = data.getAbilitiesOfType(AbilityType.PASSIVE);
	    tempAbilities.removeIf(this::ifInPassivePredicate);
	    for(int i = 0; i < tempAbilities.size(); i++) {
	    	if(tempAbilities.get(i) instanceof IBlacklistPassive) {
	    		List<Ability> blacklists = ((IBlacklistPassive)tempAbilities.get(i)).getBlackList();
	    		if(!blacklists.contains(this)) {
	    			tempAbilities.remove(i);
	    		}
	    	}
	    }
	    List<Ability> blacklisted = null;
	    if(this instanceof IBlacklistPassive) {
	    	 blacklisted = ((IBlacklistPassive)this).getBlackList();
	    }
		if (this.getCooldown() <= 0) {
			switch (this.props.type) {

			case CHARGING:
				if (!this.isCharging()) {
					((ChargeableAbility) this).onStartCharging(p);
					MinecraftForge.EVENT_BUS.post(new AbilityActivateEvent(this, p));
				}
				this.setCharging(true);
				break;
			case PASSIVE:
				this.setInPassive(!this.isInPassive());
				this.setPassiveTimer(this.props.maxPassive);
				if (this.isInPassive()) {
					((PassiveAbility) this).onStartPassive(p);
					MinecraftForge.EVENT_BUS.post(new AbilityActivateEvent(this, p));
					if(this instanceof IOnPlayerRender) {
						if(!data.hasActiveAbility(ModAbilities.IN_ABILITY)) {
						if(!p.world.isRemote()) {
							PacketHandler.sendToTracking(p, new SyncAbilityRenderingPacket(this.getId(), p.getEntityId(), true));
						}
					}
					}
					if(blacklisted != null) {
						blacklisted.forEach(a -> {
							if(data.getSlotAbility(a) != null) {
								data.getSlotAbility(a).stopAbility(p);
							}
						});
					} else {
					tempAbilities.forEach((a) -> {
						if(!a.equals(this)) {
						a.stopAbility(p);
						}
					});
					}
					
				}

				if (!this.isInPassive()) {
					((PassiveAbility) this).onEndPassive(p);
					if(this instanceof IOnPlayerRender) {
					if(!data.hasActiveAbility(ModAbilities.IN_ABILITY)) {
							
						if(!p.world.isRemote()) {
							PacketHandler.sendToTracking(p, new SyncAbilityRenderingPacket(this.getId(), p.getEntityId(), false));
						}
					}
					}

					this.setCooldown(this.props.maxCooldown);
				}
				
				break;
			case ONUSE:
				
				this.use(p);
				MinecraftForge.EVENT_BUS.post(new AbilityActivateEvent(this, p));
				this.setCooldown(this.props.maxCooldown);
				if(this instanceof ITrainable) {
				 ITrainable trainable = (ITrainable) this;
				 this.setXp(this.getXp() + trainable.getXPOnUsage() + (rand.nextDouble() - 0.5), p);
				}
				if(!this.consumeAura(p)) {
					this.stopAbility(p);
				}
				break;
				
			case CHARGING_PASSIVE:
				if(this.isInPassive()) {
					this.setInPassive(false);
					((ChargeablePassiveAbility) this).onEndPassive(p);
					this.setChargingTimer(0);
					if(this instanceof IOnPlayerRender) {
					if(!data.hasActiveAbility(ModAbilities.IN_ABILITY)) {
							
						if(!p.world.isRemote()) {
							PacketHandler.sendToTracking(p, new SyncAbilityRenderingPacket(this.getId(), p.getEntityId(), false));
						}
					}
					}

					this.setCooldown(this.props.maxCooldown);

				} else {
					if (!this.isCharging()) {
						((ChargeablePassiveAbility) this).onStartCharging(p);
						MinecraftForge.EVENT_BUS.post(new AbilityActivateEvent(this, p));
					}
					this.setCharging(true);
					

				}
				break;
			}
			
			

		}
		
	}
	private boolean ifInPassivePredicate(Ability a) {
		return !a.isInPassive();
	}

	public void endAbility(LivingEntity p) {
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
		case CHARGING_PASSIVE:
			this.setInPassive(false);
			this.setPassiveTimer(this.props.maxPassive);
			this.setCooldown(this.props.maxCooldown);
			this.setCharging(false);
			this.setChargingTimer(0);
		   ( (ChargeablePassiveAbility) this).onEndPassive(p);

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
	public boolean consumeAura(LivingEntity p) {
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
	public void stopAbility(LivingEntity p) {
		switch(this.props.type) {
		
		case PASSIVE:
			this.setInPassive(false);
			this.setPassiveTimer(this.props.maxPassive);
			this.setCooldown(this.props.maxCooldown);
			((PassiveAbility)this).onEndPassive(p);
		   break;
		case CHARGING:
			this.setCharging(false);
			this.setChargingTimer(0);
			this.setCooldown(this.props.maxCooldown);
			break;
		case ONUSE:
			this.setCooldown(this.props.maxCooldown);
			break;
			
		case CHARGING_PASSIVE:
			this.setInPassive(false);
			this.setPassiveTimer(this.props.maxPassive);
			this.setCooldown(this.props.maxCooldown);
			((ChargeablePassiveAbility)this).onEndPassive(p);

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
		case CHARGING_PASSIVE:
			this.setInPassive(true);
			this.setPassiveTimer(this.props.maxPassive);

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

	public static boolean canRegenAura(LivingEntity p) {
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
		case CHARGING_PASSIVE:
			return this.isCharging() || this.isInPassive();
		}

		return false;
	}



	public double getXp() {
		return xp;
	}

	public void setXp(double xp, LivingEntity p) {
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
		double range = trainable.getPowerScale() - 1;
		double percentage = this.getXp() * 100 / trainable.getMaxXP();
		double difference = ( percentage * range )/ 100;
		double currentScale = 1 + difference;
		return currentScale;
		}
		return -1;
	}
	public  double getCurrentAuraConScale() {
		if(this instanceof ITrainable) {
		ITrainable trainable = (ITrainable) this;
		double range = trainable.getAuraConsumptionScale() - 1;
		double percentage = this.getXp() * 100 / trainable.getMaxXP();
		double difference = ( percentage * range )/ 100;
		double currentScale = 1 + difference;
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

