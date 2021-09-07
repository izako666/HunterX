package com.izako.hunterx.quests.hatsus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.izako.hunterx.gui.ListSlider;
import com.izako.hunterx.gui.ListSlider.Entry;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.quest.IMultipleChoiceQuest;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class CharacterHatsusQuest extends Quest implements IMultipleChoiceQuest {

	@Override
	public List<Quest> getChoices(PlayerEntity p) {
		return new ArrayList<Quest>(Arrays.asList(ModQuests.GON_HATSU_QUEST,ModQuests.KILLUA_HATSU_QUEST,ModQuests.LEORIO_HATSU_QUEST, ModQuests.GENTHRU_HATSU_QUEST));
	}

	@Override
	public boolean mustFinishAllQuests() {
		return false;
	}

	@Override
	public String getId() {
		return "char_hatsus_quest";
	}

	@Override
	public String getName() {
		return "Pick Your Hatsu";
	}

	@Override
	public String getDescription() {
		return "Pick what your personal hatsu will be.";
	}

	@Override
	public void onActivateEntry(Entry entry, ListSlider slider, PlayerEntity p) {
		Quest q = ModQuests.newInstance(entry.id);
		q.giveQuest(p);
	}

}
