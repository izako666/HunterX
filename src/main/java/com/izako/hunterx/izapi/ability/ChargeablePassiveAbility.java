package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;

public abstract class ChargeablePassiveAbility extends Ability {



	//self explanatory methods.
	public  void onStartCharging(LivingEntity p) {};
	public  void duringCharging(LivingEntity p) {};
	public void onStartPassive(LivingEntity p) {};
	public void duringPassive(LivingEntity p) {};
	public void onEndPassive(LivingEntity p) {};
	
	@Override
	public CompoundNBT writeData(int slot) {
		CompoundNBT nbt = super.writeData(slot);
		nbt.putInt("chargetimer", this.getChargingTimer());
		nbt.putBoolean("isinpassive", this.isInPassive());
		nbt.putInt("passivetimer", this.getPassiveTimer());
		nbt.putBoolean("ischarging", this.isCharging());


		return nbt;

	}
	
	@Override
	public Ability readData(CompoundNBT nbt) {
		super.readData(nbt);
			this.setChargingTimer(nbt.getInt("chargetimer"));
			this.setCharging(nbt.getBoolean("ischarging"));
			this.setInPassive(nbt.getBoolean("isinpassive"));
			this.setPassiveTimer(nbt.getInt("passivetimer"));

		return this;
	}
	
}
