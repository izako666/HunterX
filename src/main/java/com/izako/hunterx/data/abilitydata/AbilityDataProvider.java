package com.izako.hunterx.data.abilitydata;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

public class AbilityDataProvider  implements ICapabilitySerializable<CompoundNBT>{


	IAbilityData instance = AbilityDataCapability.INSTANCE.getDefaultInstance();

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
		// TODO Auto-generated method stub
		return AbilityDataCapability.INSTANCE.orEmpty(cap, LazyOptional.of(() -> instance));
	}

	@Override
	public CompoundNBT serializeNBT() {
		// TODO Auto-generated method stub
		return (CompoundNBT) AbilityDataCapability.INSTANCE.getStorage().writeNBT(AbilityDataCapability.INSTANCE, instance, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
		// TODO Auto-generated method stub
		AbilityDataCapability.INSTANCE.getStorage().readNBT(AbilityDataCapability.INSTANCE, instance, null, nbt);
	}

}
