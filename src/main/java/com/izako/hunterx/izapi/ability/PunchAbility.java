package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public abstract class PunchAbility extends PassiveAbility{

	public float onPunch(PlayerEntity p, LivingEntity target) {
		return 0;
	}
}