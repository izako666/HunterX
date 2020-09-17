package com.izako.hunterx.quests.basics;

import com.izako.hunterx.abilities.basics.InAbility;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class InQuest extends Quest {

	@Override
	public String getId() {
		return "inquest";
	}

	@Override
	public String getName() {
		return "Learn In";
	}

	@Override
	public String getDescription() {
		return "Use Zetsu for 30 minutes to get a feel for In. ";
	}

	@Override
	public void finishQuest(PlayerEntity p) {

		new InAbility().give(p);
		super.finishQuest(p);
	}

}
