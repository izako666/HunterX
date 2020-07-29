package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public abstract class ChargeableAbility  extends Ability{

	//extend this class for chargeable abilities
	public ChargeableAbility() {
		super();
	}
	//self explanatory methods.
	public  void onStartCharging(PlayerEntity p) {};
	public  void duringCharging(PlayerEntity p) {};
	public  void onEndCharging(PlayerEntity p) {};
	
	@Override
	public CompoundNBT writeData(int slot) {
		CompoundNBT nbt = super.writeData(slot);
		nbt.putInt("chargetimer", this.getChargingTimer());
		nbt.putBoolean("ischarging", this.isCharging());


		return nbt;

	}
	
	@Override
	public Ability readData(CompoundNBT nbt) {
		super.readData(nbt);
			this.setChargingTimer(nbt.getInt("chargetimer"));
			this.setCharging(nbt.getBoolean("ischarging"));

		return this;
	}
}
