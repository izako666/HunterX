package com.izako.hunterx.quests.basics;

import com.izako.hunterx.abilities.basics.ShuAbility;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class ShuQuest extends Quest {

	@Override
	public String getId() {
		return "shuquest";
	}

	@Override
	public String getName() {
		return "Learn Shu";
	}

	@Override
	public String getDescription() {
		return "Shu is an ability you get when you need it, simply mining while using Ren should teach you it. (mine 100 hard blocks with a shovel)";
	}

	@Override
	public void finishQuest(PlayerEntity p) {
		new ShuAbility().give(p);
		super.finishQuest(p);
	}

}
