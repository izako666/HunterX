package com.izako.hunterx.izapi.ability;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.io.Serializable;

public abstract class PunchAbility extends PassiveAbility{

	public abstract float onPunch(PlayerEntity player, LivingEntity target);

	public interface IOnHitEntity extends Serializable
	{
		float onHitEntity(PlayerEntity player, LivingEntity target);
	}
	public IOnHitEntity onPunch = ((player, target) -> {return 0;});
	
	public float hitEntity(PlayerEntity player, LivingEntity target)
	{
		float result = this.onPunch.onHitEntity(player, target);
		
		this.endAbility(player);
		return result;
	}


}
