package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public abstract class PassiveAbility extends Ability {

	// extend this class when you need a passive ability
	public PassiveAbility() {
		super();

	}

	// self explanatory methods
	public abstract void onStartPassive(PlayerEntity p);

	public abstract void duringPassive(PlayerEntity p);

	public abstract void onEndPassive(PlayerEntity p);

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
