package com.izako.hunterx.quests.basics;

import com.izako.hunterx.abilities.basics.GyoAbility;
import com.izako.hunterx.abilities.basics.KoAbility;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class GyoQuest extends Quest{

	@Override
	public String getId() {
		return "gyoquest";
	}


	@Override
	public String getName() {
		return "Learn Gyo";
	}

	@Override
	public String getDescription() {
		return "You must observe an opponents nen and truly focus on it to learn gyo.";
	}


	@Override
	public void finishQuest(PlayerEntity p) {

		new GyoAbility().give(p);
		new KoAbility().give(p);
		super.finishQuest(p);
	}

}
