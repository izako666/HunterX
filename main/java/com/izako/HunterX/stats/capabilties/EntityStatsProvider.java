package com.izako.HunterX.stats.capabilties;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class EntityStatsProvider implements ICapabilitySerializable<NBTTagCompound>{
	@CapabilityInject(IEntityStats.class)
	public static final Capability<IEntityStats> ENTITY_STATS = null;

	private IEntityStats instance = ENTITY_STATS.getDefaultInstance();

	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing)
	{
	return capability == ENTITY_STATS;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing)
	{
	return capability == ENTITY_STATS ? ENTITY_STATS.<T> cast(this.instance) : null;
	}



	@Override
	public NBTTagCompound serializeNBT() {
	
		return (NBTTagCompound) ENTITY_STATS.getStorage().writeNBT(ENTITY_STATS, this.instance, null);
	}
	

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		ENTITY_STATS.getStorage().readNBT(ENTITY_STATS, this.instance, null, nbt);
		
	}
	}

