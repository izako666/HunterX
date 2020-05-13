package com.izako.hunterx.quests.speech;

import java.awt.Color;

import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.NPCSpeech;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class ExaminerSpeech extends NPCSpeech {
	public static SequencedString SQSTR01 = new SequencedString("Oh are you here for the hunter exam?", QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString SQSTR02 = new SequencedString("well You're just in time!", QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString SQSTR03 = new SequencedString("the first stage was finding this place.", QuestScreen.defaultChatboxStringLength,20 * 10).setTicksFromLength(true);
	public static SequencedString SQSTR04 = new SequencedString("Now we will start the second phase", QuestScreen.defaultChatboxStringLength, 20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR05 = new SequencedString("Hunt a kiriko to prove your skills.", QuestScreen.defaultChatboxStringLength, 20 * 20).setColor(Color.blue.getRGB()).setTicksFromLength(true);
	public static SequencedString SQSTR06 = new SequencedString("Sorry the Hunter Exam this year is already over.",QuestScreen.defaultChatboxStringLength, 20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR07 = new SequencedString("Oh you killed it? I'm astonished!", QuestScreen.defaultChatboxStringLength, 20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR08 = new SequencedString("The next exam will be a test of speed and stamina",QuestScreen.defaultChatboxStringLength, 20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR09 = new SequencedString("lets see how far you can get away from here in hmmm...", QuestScreen.defaultChatboxStringLength, 20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR10 = new SequencedString("2 minutes? timer starts now.", QuestScreen.defaultChatboxStringLength, 20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR11 = new SequencedString("Congratulations for passing the third exam", QuestScreen.defaultChatboxStringLength,20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR12 = new SequencedString("now for the final exam you must get your targets badge", QuestScreen.defaultChatboxStringLength, 20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR13 = new SequencedString("to earn the required points. Your target is hanzo.",QuestScreen.defaultChatboxStringLength, 20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR14 = new SequencedString("Oh did you Kill Hanzo?",QuestScreen.defaultChatboxStringLength, 20 * 20).setTicksFromLength(true);
	public static SequencedString SQSTR15 = new SequencedString("This years hunter exam hasn't started yet.", QuestScreen.defaultChatboxStringLength,20 * 20).setTicksFromLength(true);

	public static SequencedString[] SQSTRS01 = new SequencedString[] {  SQSTR03, SQSTR04, SQSTR05 };
	public static SequencedString[] SQSTRS02 = new SequencedString[] { SQSTR07, SQSTR08, SQSTR09, SQSTR10 };
	public static SequencedString[] SQSTRS03 = new SequencedString[] { SQSTR11, SQSTR12, SQSTR13 };

	@Override
	public Quest[] getQuests(PlayerEntity p) {
		return new Quest[] { ModQuests.HUNTEREXAM01, ModQuests.HUNTEREXAM02, ModQuests.HUNTEREXAM03,
				ModQuests.HUNTEREXAM04 };
	}

	@Override
	public SequencedString[][] getSequencedStringFromQuest(int questIndex) {
		switch (questIndex) {

		case 0:
			return new SequencedString[][] { new SequencedString[] { SQSTR15 }, new SequencedString[] { SQSTR06 },
					new SequencedString[] {SQSTR01,SQSTR02} };
		case 1:
			return new SequencedString[][] { SQSTRS01, null, new SequencedString[] {SQSTR07} };
		case 2:
			return new SequencedString[][] { SQSTRS02, null, null };
		case 3:
			return new SequencedString[][] { SQSTRS03, null, new SequencedString[] { SQSTR14 } };

		}
		return null;
	}

}
