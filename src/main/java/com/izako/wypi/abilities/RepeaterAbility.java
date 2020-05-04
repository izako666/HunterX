package com.izako.wypi.abilities;

import com.izako.wypi.APIConfig.AbilityCategory;
import com.izako.wypi.data.ability.AbilityDataCapability;
import com.izako.wypi.data.ability.IAbilityData;
import com.izako.wypi.network.WyNetwork;
import com.izako.wypi.network.packets.server.SSyncAbilityDataPacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public abstract class RepeaterAbility extends Ability
{
	private int repeaterCount;
	private int maxRepeaterCount;
	private int repeaterInterval;
	
	public RepeaterAbility(String name, AbilityCategory category)
	{
		super(name, category);
	}
	
	
	/*
	 * 	Setters/Getters
	 */	
	public void setMaxRepearCount(int count, int interval)
	{
		this.maxRepeaterCount = count;
		this.repeaterCount = this.maxRepeaterCount;
		this.repeaterInterval = interval;
		
		this.maxCooldown += (this.maxRepeaterCount * this.repeaterInterval);
		this.cooldown = this.maxCooldown;	
	}
	
	public void setRepeaterCount(int count)
	{
		this.repeaterCount = count;
	}
	
	
	/*
	 * 	Methods
	 */
	@Override
	public void cooldown(PlayerEntity player)
	{
		//if(player.world.isRemote)
		//	return;

		if(this.isOnCooldown() && this.cooldown > 0)
		{
			this.cooldown--;
			
			if(!player.world.isRemote)
			{
				if(this.repeaterCount > 0 && this.cooldown % this.repeaterInterval == 0)
				{
					this.onUseEvent.onUse(player);
					this.repeaterCount--;
				}
				
				this.duringCooldownEvent.duringCooldown(player, (int) this.cooldown);			
			}
		}
		else if(this.isOnCooldown() && this.cooldown <= 0)
		{
			if(player.world.isRemote)
				return;
			this.cooldown = this.maxCooldown;				
			this.repeaterCount = this.maxRepeaterCount;
			this.startStandby();
			IAbilityData props = AbilityDataCapability.get(player);
			WyNetwork.sendTo(new SSyncAbilityDataPacket(props), (ServerPlayerEntity)player);
		}
	}
}
