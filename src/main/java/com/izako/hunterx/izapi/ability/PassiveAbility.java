package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.player.PlayerEntity;

public abstract class PassiveAbility extends Ability {

	public abstract void onStartPassive(PlayerEntity p);
	public abstract void duringPassive(PlayerEntity p);
	public abstract void onEndPassive(PlayerEntity p);
}
