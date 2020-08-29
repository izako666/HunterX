package com.izako.hunterx.data.abilitydata;

import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.ability.NenType;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;

public class AbilityDataCapability {

	@CapabilityInject(IAbilityData.class)
	public static final Capability<IAbilityData> INSTANCE = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IAbilityData.class, new Capability.IStorage<IAbilityData>() {

			@Override
			public INBT writeNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side) {
				CompoundNBT props = new CompoundNBT();

				
				for (Ability abl : instance.getAbilities()) {
					if (abl != null) {
						props.put("abilityid" + abl.getId(), abl.writeData( -1));
					}
				}
				for (int i = 0; i < instance.getSlotAbilities().length; i++) {
					if (instance.getAbilityInSlot(i) != null) {
						props.put("slotid" + instance.getAbilityInSlot(i).getId(), instance.getAbilityInSlot(i).writeData(i));
					}
				}

				props.putInt("nencap", instance.getNenCapacity());
				props.putInt("nen", instance.getCurrentNen());
				if(instance.getNenType() != null) {
				     props.putInt("nentype", instance.getNenType().ordinal());
				}
				props.putInt("auracolor", instance.getAuraColor().getRGB());
				props.putBoolean("isnenuser", instance.isNenUser());
				props.putInt("activeability", instance.getActiveAbility());
				return props;
			}

			@Override
			public void readNBT(Capability<IAbilityData> capability, IAbilityData instance, Direction side, INBT nbt) {
				CompoundNBT props = (CompoundNBT) nbt;

				for(int i = 0; i < 5; i++) {
					instance.putAbilityInSlot(null, i);
				}
				for(String k : props.keySet()) {
					if (k.contains("abilityid")) {
						String newK = k.substring(9);
							instance.giveAbility(ModAbilities.getNewInstanceFromId(newK).readData(props.getCompound(k)));							
						
					}
					if (k.contains("slotid")) {
						String newK = k.substring(6);
						Ability abl = ModAbilities.getNewInstanceFromId(newK).readData(props.getCompound(k));
						if (abl != null) {
							if (abl.getSlot() != -1) {
								instance.putAbilityInSlot(abl, abl.getSlot());
							}
						}
					}
				}

				instance.setNenCapacity(props.getInt("nencap"));
				instance.setCurrentNen(props.getInt("nen"));
				instance.setNenType(NenType.getTypeFromOrdinal(props.getInt("nentype")));
				instance.setAuraColor(props.getInt("auracolor"));
				instance.setIsNenUser(props.getBoolean("isnenuser"));
				instance.setActiveAbility(props.getInt("activeability"));
			}

			
		}, AbilityDataBase::new);

	}

	public static IAbilityData get(final LivingEntity entity) {

		return entity.getCapability(INSTANCE, null).orElse(new AbilityDataBase());
	}

	public static LazyOptional<IAbilityData> getLazy(final LivingEntity entity) {
		return entity.getCapability(INSTANCE, null);
	}
}
