package com.izako.hunterx.quests.speech;

import java.awt.Color;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.IZAHelper;
import com.izako.hunterx.izapi.NPCSpeech;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.entity.player.PlayerEntity;

public class WingSpeech extends NPCSpeech {

	public static SequencedString MSG1 = new SequencedString("Hello traveler, you seem to be fairly strong.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG2 = new SequencedString("Perhaps you'd be interested in the hunter exam?",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG3 = new SequencedString("Too bad the exam registration will end in 2 days",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG4 = new SequencedString("Who knows maybe you'll be able to get there by then?",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);

	public static SequencedString MSG5 = new SequencedString("Oh, You're a hunter now? Seems like I'll be your Nen teacher.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG6 = new SequencedString("Explaining to you what Nen is would take a while",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG7 = new SequencedString("so I'll just open your nodes and you can see for yourself.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG8 = new SequencedString("Your aura nodes have opened.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());
	public static SequencedString MSG9 = new SequencedString("If you want to learn Ren you'll have to use Ten for a while.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG10 = new SequencedString("I guess you're ready to learn Ren.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG11 = new SequencedString("You have learnt Ren.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());


	@Override
	public Quest[] getQuests(PlayerEntity p) {
		return new Quest[] { ModQuests.HUNTEREXAM01, ModQuests.RENQUEST};
	}

	@Override
	public SequencedString[][] getSequencedStringFromQuest(int questIndex) {
		switch (questIndex) {
		case 0:
			return new SequencedString[][] { new SequencedString[] { MSG1, MSG2, MSG3, MSG4 }, null, null };
		case 1:
			return new SequencedString[][] { new SequencedString[] {
					MSG5,MSG6,MSG7,MSG8,MSG9
			},null, new SequencedString[] {MSG10,MSG11}
        
			};
		}
		;
		return null;
	}

	@Override
	public SequencedString[] getSpeechFromState(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		if(IZAHelper.getCurrentQuest(this.getQuests(p), p) != -1) {
		SequencedString[][] sqstrs = this.getSequencedStringFromQuest(IZAHelper.getCurrentQuest(this.getQuests(p), p));
		Quest q = this.getQuests(p)[IZAHelper.getCurrentQuest(this.getQuests(p), p)];
		
		if(q.equals(ModQuests.RENQUEST) && !data.isHunter()) {
			return null;
		}
		switch(this.getStateFromQuest(q, p)) {
		
		case NOTSTARTED:
			return sqstrs[0];
		case NOTFULFILLED:
			return sqstrs[1];
		case FULFILLED:
			return sqstrs[2];
		default:
			return sqstrs[0];
			
		
		}
		} 
		return null;
	}

}
