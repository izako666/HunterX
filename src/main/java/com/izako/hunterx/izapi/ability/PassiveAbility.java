package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.player.PlayerEntity;

public abstract class PassiveAbility extends Ability {

	//extend this class when you need a passive ability
	public PassiveAbility() {
		super();
		this.setType(AbilityType.PASSIVE);
	}
	//self explanatory methods
	public abstract void onStartPassive(PlayerEntity p);
	public abstract void duringPassive(PlayerEntity p);
	public abstract void onEndPassive(PlayerEntity p);
}
