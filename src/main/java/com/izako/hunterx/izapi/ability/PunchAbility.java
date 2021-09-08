package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.io.Serializable;

public abstract class PunchAbility extends PassiveAbility{

	public float onPunch(LivingEntity p, LivingEntity target)
	{
		return 0;
	}


	public abstract float onPunch(PlayerEntity p, LivingEntity target);
}
