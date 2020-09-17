package com.izako.hunterx.quests.basics;

import com.izako.hunterx.abilities.basics.RyuDefenseAbility;
import com.izako.hunterx.abilities.basics.RyuOffenseAbility;
import com.izako.hunterx.init.ModAbilities;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class RyuQuest extends Quest {

	public int count = -1;
	public int iteration = 0;

	@Override
	public void finishQuest(PlayerEntity p) {
		new RyuDefenseAbility().give(p);
		new RyuOffenseAbility().give(p);
		super.finishQuest(p);
	}

	@Override
	public String getId() {
		return "ryuquest";
	}

	@Override
	public String getName() {
		return "Learn Ryu";
	}

	@Override
	public String getDescription() {
		return "You must vary your nen ability usage, swap between ko,ren,zetsu twice every 60 seconds.";
	}

}
