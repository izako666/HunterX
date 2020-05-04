package com.izako.wypi.abilities;

import java.io.Serializable;

import com.izako.wypi.APIConfig.AbilityCategory;
import com.izako.wypi.data.ability.AbilityDataCapability;
import com.izako.wypi.data.ability.IAbilityData;
import com.izako.wypi.network.WyNetwork;
import com.izako.wypi.network.packets.server.SSyncAbilityDataPacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;

public abstract class ContinuousAbility extends Ability
{
	private int threshold = 0;
	protected int continueTime = 0;
	
	// Setting the defaults so that no crash occurs and so they will be null safe.
	protected IOnStartContinuity onStartContinuityEvent = (player) -> { return true; };
	protected IOnEndContinuity onEndContinuityEvent = (player) -> { return true; };
	protected IDuringContinuity duringContinuityEvent = (player, passiveTime) -> {};
	
	public ContinuousAbility(String name, AbilityCategory category)
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

		if(!this.isContinuous())
		{
			if(!this.isOnStandby())
				return;
			
			if (this.onStartContinuityEvent.onStartContinuity(player))
			{
				this.startContinuity();
				IAbilityData props = AbilityDataCapability.get(player);
				WyNetwork.sendTo(new SSyncAbilityDataPacket(props), (ServerPlayerEntity) player);
			}
		}
		else
		{
			if (this.onEndContinuityEvent.onEndContinuity(player))
			{
				this.continueTime = 0;
				this.startCooldown();
				IAbilityData props = AbilityDataCapability.get(player);
				WyNetwork.sendTo(new SSyncAbilityDataPacket(props), (ServerPlayerEntity) player);
			}
		}
	}
	
	
	
	/*
	 * 	Setters/Getters
	 */	
	public void setThreshold(int threshold)
	{
		this.threshold = threshold * 20;
	}
	
	public int getThreshold()
	{
		return this.threshold;
	}
	
	public void startContinuity()
	{
		this.setState(State.CONTINUOUS);
	}
	
	public void setContinueTime(int time)
	{
		this.continueTime = time * 20;;
	}
	
	public int getContinueTime()
	{
		return this.continueTime;
	}

	
	
	/*
	 * 	Methods
	 */
	public void tick(PlayerEntity player)
	{
		//if(player.world.isRemote)
		//	return;

		if(this.isContinuous())
		{
			this.continueTime++;
			if(!player.world.isRemote)
				this.duringContinuityEvent.duringContinuity(player, this.continueTime);
			
			if(this.threshold > 0 && this.continueTime >= this.threshold)
				this.stopContinuity(player);
		}
	}
	
	public void stopContinuity(PlayerEntity player)
	{
		if(player.world.isRemote)
			return;
		if(this.onEndContinuityEvent.onEndContinuity(player))
		{
			this.continueTime = 0;
			this.startCooldown();	
			IAbilityData props = AbilityDataCapability.get(player);
			WyNetwork.sendTo(new SSyncAbilityDataPacket(props), player);
		}
	}
	
	/*
	 *	Interfaces
	 */
	public interface IDuringContinuity extends Serializable
	{
		void duringContinuity(PlayerEntity player, int passiveTime);
	}
	
	public interface IOnStartContinuity extends Serializable
	{
		boolean onStartContinuity(PlayerEntity player);
	}
	
	public interface IOnEndContinuity extends Serializable
	{
		boolean onEndContinuity(PlayerEntity player);
	}
}
