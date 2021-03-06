package com.izako.hunterx.data.hunterdata;

import java.util.ArrayList;

import com.izako.hunterx.init.ModQuests;
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
					instance.getQuests().forEach(q -> {
						props.put("questid" + q.getId(), q.writeData());
					});
					
				props.putInt("jenny",instance.getJenny());
					
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
                instance.setQuests(new ArrayList<Quest>());
				props.keySet().forEach((k) -> {
					if (k.contains("questid")) {
						String newK = k.substring(7, k.length());
						Quest q = ModQuests.newInstance(newK);
						q.readData((CompoundNBT) props.get(k));
						instance.giveQuest(q);
					}
				});
				
			instance.setJenny(props.getInt("jenny"));
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
