package com.izako.hunterx.data.hunterdata;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class HunterDataProvider implements ICapabilitySerializable<CompoundNBT>
{

	IHunterData instance = HunterDataCapability.INSTANCE.getDefaultInstance();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		// TODO Auto-generated method stub
		return HunterDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> instance));
	}

	@Override
	public CompoundNBT serializeNBT() {
		// TODO Auto-generated method stub
		return (CompoundNBT) HunterDataCapability.INSTANCE.getStorage().writeNBT(HunterDataCapability.INSTANCE, instance, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		// TODO Auto-generated method stub
		HunterDataCapability.INSTANCE.getStorage().readNBT(HunterDataCapability.INSTANCE, instance, null, nbt);
	}

}
