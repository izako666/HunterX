package com.izako.HunterX.stats.capabilties;

import com.izako.HunterX.stats.events.HealthStatEvent;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;

public  class EntityStatsCapability  {

	@CapabilityInject(IEntityStats.class)
	public static final Capability<IEntityStats> INSTANCE = null;


	public static IEntityStats get(EntityLivingBase player)
	{
		IEntityStats props = player.getCapability(EntityStatsCapability.INSTANCE, null);
		
		return props;
	}
	
	public static void register() 
	{
		CapabilityManager.INSTANCE.register(IEntityStats.class, new Capability.IStorage<IEntityStats>()
		{
	 @Override
	  public NBTBase writeNBT(Capability<IEntityStats> capability, IEntityStats instance, EnumFacing side) {
	    // return an NBT tag
		 NBTTagCompound player = new NBTTagCompound();

		 player.setDouble("health stat", instance.getHealthStat());
		 return player;
	 }

	  @Override
	  public void readNBT(Capability<IEntityStats> capability, IEntityStats instance, EnumFacing side, NBTBase nbt) {
	    // load from the NBT tag
		  NBTTagCompound props = (NBTTagCompound) nbt;
		  instance.setHealthStat(props.getDouble("health stat"));
	  }


}, () -> new EntityStatsBase());}}