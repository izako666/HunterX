package com.izako.hunterx.quests.basics;

import java.util.HashSet;
import java.util.Set;

import com.izako.hunterx.abilities.basics.EnAbility;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;

public class EnQuest extends Quest {

	public Set<ResourceLocation> MONSTERS_DISCOVERED = new HashSet<>();
	@Override
	public String getId() {
		return "enquest";
	}

	@Override
	public String getName() {
		return "Learn En";
	}

	@Override
	public String getDescription() {
		return "Discover 20 mob species while gyo is active.";
	}

	@Override
	public CompoundNBT writeData() {
		CompoundNBT nbt =  super.writeData();
		ResourceLocation[] mons = MONSTERS_DISCOVERED.toArray(new ResourceLocation[0]);
		for(int i = 0; i < mons.length; i++) {
			ResourceLocation mon = mons[i];
			nbt.putString("monster" + String.valueOf(i), mon.toString());
		}
		
		return nbt;
	}

	@Override
	public Quest readData(CompoundNBT nbt) {
		nbt.keySet().forEach(s -> {
			if(s.contains("monster")) {
				ResourceLocation loc = new ResourceLocation(nbt.getString(s));
			
				this.MONSTERS_DISCOVERED.add(loc);
			}
		});
		return super.readData(nbt);
	}

	@Override
	public void finishQuest(PlayerEntity p) {

		new EnAbility().give(p);
		super.finishQuest(p);
	}

}
