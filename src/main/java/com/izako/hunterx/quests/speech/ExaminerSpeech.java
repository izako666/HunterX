package com.izako.hunterx.quests.speech;

import java.awt.Color;

import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.NPCSpeech;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;

public class ExaminerSpeech extends NPCSpeech {
	public static SequencedString SQSTR01 = new SequencedString("Oh are you here for the hunter exam?", 4 * 20,
			20 * 8);
	public static SequencedString SQSTR02 = new SequencedString("well You're just in time!", 4 * 20, 20 * 8);
	public static SequencedString SQSTR03 = new SequencedString("the first stage was finding this place.", 4 * 20,
			20 * 10);
	public static SequencedString SQSTR04 = new SequencedString("Now we will start the second phase", 4 * 20, 20 * 20);
	public static SequencedString SQSTR05 = new SequencedString("Hunt a kiriko to prove your skills.", 4 * 20, 20 * 20)
			.setColor(Color.blue.getRGB());
	public static SequencedString SQSTR06 = new SequencedString("Sorry the Hunter Exam this year is already over.",
			4 * 20, 20 * 20);
	public static SequencedString SQSTR07 = new SequencedString("Oh you killed it? I'm astonished!", 4 * 20, 20 * 20);
	public static SequencedString SQSTR08 = new SequencedString("The next exam will be a test of speed and stamina",
			4 * 20, 20 * 20);
	public static SequencedString SQSTR09 = new SequencedString(
			"lets see how far you can get away from here in hmmm...", 4 * 20, 20 * 20);
	public static SequencedString SQSTR10 = new SequencedString("2 minutes? timer starts now.", 4 * 20, 20 * 20);
	public static SequencedString SQSTR11 = new SequencedString("Congratulations for passing the third exam", 4 * 20,
			20 * 20);
	public static SequencedString SQSTR12 = new SequencedString(
			"now for the final exam you must get your targets badge", 4 * 20, 20 * 20);
	public static SequencedString SQSTR13 = new SequencedString("to earn the required points. Your target is hanzo.",
			4 * 20, 20 * 20);
	public static SequencedString SQSTR14 = new SequencedString("Congratulations! You are now a professional Hunter!",
			4 * 20, 20 * 20);
	public static SequencedString SQSTR15 = new SequencedString("This years hunter exam hasn't started yet.", 4 * 20,
			20 * 20);

	public static SequencedString[] SQSTRS01 = new SequencedString[] { SQSTR01, SQSTR02, SQSTR03, SQSTR04, SQSTR05 };
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
					SQSTRS01 };
		case 1:
			return new SequencedString[][] { null, null, SQSTRS02 };
		case 2:
			return new SequencedString[][] { null, null, null };
		case 3:
			return new SequencedString[][] { SQSTRS03, null, new SequencedString[] { SQSTR14 } };

		}
		return null;
	}

}
