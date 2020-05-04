package com.izako.wypi.abilities;

import java.io.Serializable;

import com.izako.wypi.APIConfig.AbilityCategory;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public abstract class PunchAbility extends ContinuousAbility
{

	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnHitEntity onHitEntityEvent = (player, target) -> { return 0; };
	
	public PunchAbility(String name, AbilityCategory category)
	{
		super(name, category);
	}
	
	
	
	/*
	 *  Methods 
	 */
	public float hitEntity(PlayerEntity player, LivingEntity target)
	{
		this.stopContinuity(player);
		return this.onHitEntityEvent.onHitEntity(player, target);
	}
	
	
	
	/*
	 *	Interfaces
	 */
	public interface IOnHitEntity extends Serializable
	{
		float onHitEntity(PlayerEntity player, LivingEntity target);
	}
}
