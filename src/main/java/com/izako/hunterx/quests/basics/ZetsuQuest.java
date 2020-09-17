package com.izako.hunterx.quests.basics;

import com.izako.hunterx.abilities.basics.ZetsuAbility;
import com.izako.hunterx.data.abilitydata.AbilityDataCapability;
import com.izako.hunterx.data.abilitydata.IAbilityData;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class ZetsuQuest extends Quest{

	@Override
	public String getId() {
		return "zetsuquest";
	}

	@Override
	public String getName() {
		return "Learn Zetsu";
	}

	@Override
	public String getDescription() {
		return "Defeat 10 mobs without aggroing them and you'll be ready to learn zetsu.";
	}

	@Override
	public void finishQuest(PlayerEntity p) {
		IAbilityData data = AbilityDataCapability.get(p);
		new ZetsuAbility().give(p);
		super.finishQuest(p);
	}

}
