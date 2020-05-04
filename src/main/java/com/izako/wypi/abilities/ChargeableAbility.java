package com.izako.wypi.abilities;

import java.io.Serializable;

import com.izako.wypi.APIConfig.AbilityCategory;
import com.izako.wypi.data.ability.AbilityDataCapability;
import com.izako.wypi.data.ability.IAbilityData;
import com.izako.wypi.network.WyNetwork;
import com.izako.wypi.network.packets.server.SSyncAbilityDataPacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public abstract class ChargeableAbility extends Ability
{
	private int chargeTime;
	private int maxChargeTime;
	private boolean isCancelable;
	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnStartCharging onStartChargingEvent = (player) -> { return true; };
	protected IOnEndCharging onEndChargingEvent = (player) -> { return true; };
	protected IDuringCharging duringChargingEvent = (player, chargeTime) -> {};

	public ChargeableAbility(String name, AbilityCategory category)
	{
		super(name, category);
	}
	
	/*
	 *  Event Starters
	 */
	@Override
	public void use(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		
		if(this.isOnCooldown() && this.getCooldown() <= 10)
			this.stopCooldown(player);
		
		if(this.isCharging() && this.chargeTime > 0)
			this.stopCharging(player);
		else if(this.isOnStandby())
		{
			if(this.onStartChargingEvent.onStartCharging(player))
			{
				this.startCharging();
				IAbilityData props = AbilityDataCapability.get(player);
				WyNetwork.sendTo(new SSyncAbilityDataPacket(props), (ServerPlayerEntity)player);
			}
		}
	}
	
	/*
	 *  Setters / Getters
	 */
	public void setMaxChargeTime(int time)
	{
		this.maxChargeTime = time * 20;
		this.chargeTime = this.maxChargeTime;
	}
	
	public void startCharging()
	{
		this.setState(State.CHARGING);
	}
	
	public int getMaxChargeTime()
	{
		return this.maxChargeTime;
	}
	
	public int getChargeTime()
	{
		return this.chargeTime;
	}
	
	public void setChargeTime(int time)
	{
		this.chargeTime = time * 20;
	}
	
	public void setCancelable()
	{
		this.isCancelable = true;
	}

	public boolean isCancelable()
	{
		return this.isCancelable;
	}
	
	
	
	/*
	 *  Methods
	 */
	public void charging(PlayerEntity player)
	{
		//if(player.world.isRemote)
		//	return;
				
		if(this.isCharging() && this.chargeTime > 0)
		{
			this.chargeTime--;
			if(!player.world.isRemote)
				this.duringChargingEvent.duringCharging(player, this.chargeTime);
		}
		else if(this.isCharging() && this.chargeTime <= 0)
		{
			this.stopCharging(player);
		}
	}
	
	public void stopCharging(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		if (this.onEndChargingEvent.onEndCharging(player))
		{
			this.chargeTime = this.maxChargeTime;
			this.startCooldown();
			IAbilityData props = AbilityDataCapability.get(player);
			WyNetwork.sendTo(new SSyncAbilityDataPacket(props), (ServerPlayerEntity) player);
		}
	}
	
	
	
	/*
	 *	Interfaces
	 */
	public interface IDuringCharging extends Serializable
	{
		void duringCharging(PlayerEntity player, int chargeTime);
	}
	
	public interface IOnStartCharging extends Serializable
	{
		boolean onStartCharging(PlayerEntity player);
	}
	
	public interface IOnEndCharging extends Serializable
	{
		boolean onEndCharging(PlayerEntity player);
	}
}
