package com.izako.hunterx.quests.basics;

import com.izako.hunterx.abilities.basics.RenAbility;
import com.izako.hunterx.abilities.basics.TenAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class RenQuest extends Quest{


	@Override
	public void giveQuest(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		data.giveAbility(new TenAbility());
		data.setIsNenUser(true);
		super.giveQuest(p);
	}

	@Override
	public void finishQuest(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		data.giveAbility(new RenAbility());
		super.finishQuest(p);
	}

	@Override
	public String getId() {
		return "renquest";
	}

	@Override
	public String getName() {
		return "Learn Ren";
	}

	@Override
	public String getDescription() {
		return "You have to use ten for 1 day(20 minutes) to have the endurance required for ren.";
	}


}
