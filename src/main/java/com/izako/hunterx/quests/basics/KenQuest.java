package com.izako.hunterx.quests.basics;

import com.izako.hunterx.abilities.basics.KenAbility;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class KenQuest extends Quest {

	@Override
	public String getId() {
		return "kenquest";
	}

	@Override
	public String getName() {
		return "Learn Ken";
	}

	@Override
	public String getDescription() {
		return "You must use Ren for an entire day to have the stamina for ken.";
	}

	@Override
	public void finishQuest(PlayerEntity p) {
		new KenAbility().give(p);
		super.finishQuest(p);
	}

}
