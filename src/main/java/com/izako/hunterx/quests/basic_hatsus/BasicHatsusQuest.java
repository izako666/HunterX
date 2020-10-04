package com.izako.hunterx.quests.basic_hatsus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.IMultipleChoiceQuest;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class BasicHatsusQuest extends Quest implements IMultipleChoiceQuest {

	@Override
	public String getId() {
		return "basic_hatsus";
	}

	@Override
	public String getName() {
		return "Learn all the basic hatsus";
	}

	@Override
	public String getDescription() {
		return "Go talk to wing and get another basic hatsu quest until you master all of them or until you've had enough.";
	}

	@Override
	public List<Quest> getChoices(PlayerEntity p) {
		return new ArrayList<>(Arrays.asList(ModQuests.BASIC_ENHANCER,ModQuests.BASIC_EMITTER,ModQuests.BASIC_CONJURER,ModQuests.BASIC_TRANSMUTER,ModQuests.BASIC_MANIPULATOR));
	}

	@Override
	public boolean mustFinishAllQuests() {
		return false;
	}

}
