package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;

public abstract class PassiveAbility extends Ability {

	// extend this class when you need a passive ability
	public PassiveAbility() {
		super();

	}

	// self explanatory methods
	public  void onStartPassive(LivingEntity p) {};

	public  void duringPassive(LivingEntity p) {};

	public  void onEndPassive(LivingEntity p) {};

	@Override
	public CompoundNBT writeData(int slot) {
		CompoundNBT data = super.writeData(slot);
		data.putBoolean("isinpassive", this.isInPassive());
		data.putInt("passivetimer", this.getPassiveTimer());

		return data;
	}

	@Override
	public Ability readData(CompoundNBT nbt) {
		super.readData(nbt);
		this.setInPassive(nbt.getBoolean("isinpassive"));
		this.setPassiveTimer(nbt.getInt("passivetimer"));

		return this;
	}
}
