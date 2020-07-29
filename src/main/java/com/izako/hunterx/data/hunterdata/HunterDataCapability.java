package com.izako.hunterx.data.hunterdata;

import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.IAdditionalQuestData;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.util.LazyOptional;

public class HunterDataCapability {

	@CapabilityInject(IHunterData.class)
	public static final Capability<IHunterData> INSTANCE = null;

	public static void register() {
		CapabilityManager.INSTANCE.register(IHunterData.class, new Capability.IStorage<IHunterData>() {

			@Override
			public INBT writeNBT(Capability<IHunterData> capability, IHunterData instance, Direction side) {
				CompoundNBT props = new CompoundNBT();

				props.putDouble("health_stat", instance.getHealthStat());
				props.putDouble("speed_stat", instance.getSpeedStat());
				props.putDouble("defense_stat", instance.getDefenseStat());
				props.putDouble("attack_stat", instance.getAttackStat());
			    props.putBoolean("ishunter", instance.isHunter());
			    props.putBoolean("ischarmade", instance.isCharacterMade());
					instance.getQuests().forEach((k, v) -> {
						props.putInt("quest" + k, v);
					});
				instance.getExtraQuestData().forEach((k,v) -> {
					props.put("extradata" + k, v);
				});
				return props;
			}

			@Override
			public void readNBT(Capability<IHunterData> capability, IHunterData instance, Direction side, INBT nbt) {
				CompoundNBT props = (CompoundNBT) nbt;

				instance.setHealthStat(props.getDouble("health_stat"));
				instance.setSpeedStat(props.getDouble("speed_stat"));
				instance.setDefenseStat(props.getDouble("defense_stat"));
				instance.setAttackStat(props.getDouble("attack_stat"));
                instance.setIsHunter(props.getBoolean("ishunter"));
                instance.setIsCharacterMade(props.getBoolean("ischarmade"));
				props.keySet().forEach((k) -> {
					if (k.contains("quest")) {
						String newK = k.substring(5, k.length());
						instance.removeQuest(newK);
						instance.giveQuest(newK, props.getInt(k));
					}
				});
				
				props.keySet().forEach((k) -> {
					if(k.contains("extradata")) {
						String newK = k.substring(9);
						instance.getExtraQuestData().put(newK, props.getCompound(k));
					}
				});
			}

		}, HunterDataBase::new);

	}

	public static IHunterData get(final LivingEntity entity) {

		return entity.getCapability(INSTANCE, null).orElse(new HunterDataBase());
	}

	public static LazyOptional<IHunterData> getLazy(final LivingEntity entity) {
		return entity.getCapability(INSTANCE, null);
	}
}
