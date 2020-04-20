package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.player.PlayerEntity;

public abstract class ChargeableAbility  extends Ability{

	//extend this class for chargeable abilities
	public ChargeableAbility() {
		super();
		this.setType(AbilityType.CHARGING);
	}
	//self explanatory methods.
	public abstract void onStartCharging(PlayerEntity p);
	public abstract void duringCharging(PlayerEntity p);
	public abstract void onEndCharging(PlayerEntity p);
}
