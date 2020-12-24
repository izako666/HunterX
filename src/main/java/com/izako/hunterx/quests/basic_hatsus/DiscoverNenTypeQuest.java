package com.izako.hunterx.quests.basic_hatsus;

import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.ability.NenType;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class DiscoverNenTypeQuest extends Quest {

	@Override
	public String getId() {
		return "discover_nen_type";
	}

	@Override
	public String getName() {
		return "Discover your Nen Type";
	}

	@Override
	public String getDescription() {
		return "Find out what your nen type is through water divination.";
	}

	@Override
	public void giveQuest(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		if(data.getNenType() != NenType.UNKNOWN) {
			this.setProgress(101);
		}
		super.giveQuest(p);
	}

}
