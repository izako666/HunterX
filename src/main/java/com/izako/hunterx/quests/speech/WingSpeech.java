package com.izako.hunterx.quests.speech;

import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.NPCSpeech;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class WingSpeech extends NPCSpeech {

	public static SequencedString MSG1 = new SequencedString("Hello traveler, you seem to be fairly strong.", QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG2 = new SequencedString("Perhaps you'd be interested in the hunter exam?", QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG3 = new SequencedString("Too bad the exam registration will end in 2 days", QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG4 = new SequencedString("Who knows maybe you'll be able to get there by then?", QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);

	@Override
	public Quest[] getQuests(PlayerEntity p) {
		return new Quest[] {ModQuests.HUNTEREXAM01};
	}

	@Override
	public SequencedString[][] getSequencedStringFromQuest(int questIndex) {
		return new SequencedString[][] {
			new SequencedString[]{MSG1,MSG2,MSG3,MSG4},null,null
		};
	}

}
