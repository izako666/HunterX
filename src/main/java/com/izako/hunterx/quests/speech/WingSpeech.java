package com.izako.hunterx.quests.speech;

import java.awt.Color;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.Helper;
import com.izako.hunterx.izapi.NPCSpeech;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.client.Minecraft;
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
	public static SequencedString MSG5 = new SequencedString(
			"Oh, You're a hunter now? Seems like I'll be your Nen teacher.", QuestScreen.defaultChatboxStringLength,
			20 * 8).setTicksFromLength(true);
	public static SequencedString MSG6 = new SequencedString("Explaining to you what Nen is would take a while",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG7 = new SequencedString(
			"so I'll just open your nodes and you can see for yourself.", QuestScreen.defaultChatboxStringLength,
			20 * 8).setTicksFromLength(true);
	public static SequencedString MSG8 = new SequencedString("Your aura nodes have opened.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());
	public static SequencedString MSG9 = new SequencedString(
			"If you want to learn Ren you'll have to use Ten for a while.", QuestScreen.defaultChatboxStringLength,
			20 * 8).setTicksFromLength(true);
	public static SequencedString MSG10 = new SequencedString("I guess you're ready to learn Ren.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG11 = new SequencedString("You have learnt Ren.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());
	public static SequencedString MSG12 = new SequencedString(
			"In order to learn the next principle, Zetsu. You must kill 10 mobs",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG13 = new SequencedString("to learn how to truly hide yourself.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG14 = new SequencedString("Alright, I'll teach you how to learn zetsu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG15 = new SequencedString("You have learnt Zetsu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());
	public static SequencedString MSG16 = new SequencedString("It's Time I taught you gyo.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG17 = new SequencedString("Gyo is immensely useful in battle and day to day usage.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG18 = new SequencedString("A nen user who doesn't know Gyo is severely disadvantaged.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG19 = new SequencedString("Try looking at someone using nen while having ren active,",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG20 = new SequencedString("Then focus that nen to your eyes and it will come naturally.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG21 = new SequencedString("I knew you were capable of learning gyo!",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG22 = new SequencedString("Now that you know gyo you'll find that Ko",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG23 = new SequencedString("Comes to you naturally",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG24 = new SequencedString("You have learnt Gyo and Ko",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());
	public static SequencedString MSG25 = new SequencedString("The next Nen ability I want to teach you",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG26 = new SequencedString("is called Shu. It's a very useful ability",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG27 = new SequencedString("both in utility and combat.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG28 = new SequencedString("Go mine 100 hard blocks with a shovel while using ren.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG29 = new SequencedString("And then come back to me for further instructions.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG30 = new SequencedString("Ah you've done it? It seems like you already learnt it",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG31 = new SequencedString("Seeing how your aura gravitates to your shovel as you mine.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG32 = new SequencedString("You have learnt Shu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());
	public static SequencedString MSG33 = new SequencedString("I will now teach you In. In is a powerful tool for concealing",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG34 = new SequencedString("your aura. While Zetsu cancels out your aura entirely. Sometimes Zetsu",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG35 = new SequencedString("could be very dangerous as you are defenseless while its being used.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG36 = new SequencedString("In keeps your defenses up while concealing your aura.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG37 = new SequencedString("The two abilities are very similar so if you just use zetsu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG38 = new SequencedString("you'll eventually get a feel for in.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG39 = new SequencedString("Use zetsu for 30 minutes.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.blue.getRGB());
	public static SequencedString MSG40 = new SequencedString("Now you are ready to learn In.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG41 = new SequencedString("You have learnt in.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());
	public static SequencedString MSG42 = new SequencedString("This next ability could very well replace Ren as your main combat",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG43 = new SequencedString("technique. It's basically a mix of ren and ten but with better defense and offense.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG44 = new SequencedString("You'll be able to learn it after you get used to ren.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG45 = new SequencedString("Use ren for 20 minutes",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.BLUE.getRGB());
	public static SequencedString MSG46 = new SequencedString("Very well you are ready to learn Ken.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG47 = new SequencedString("You have learnt Ken.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());
	public static SequencedString MSG48 = new SequencedString("Now it's time to learn ryu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG49 = new SequencedString("Ryu is all about focus, You need to focus to manage your aura properly.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG50 = new SequencedString("Ryu is all about managing the percentage of aura around your body ",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG51 = new SequencedString("so that You're defense and offense is properly accounted for.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG52 = new SequencedString("Try to maneuver between three different nen abilities every 60 seconds to get a grasp of the focus required for ryu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG53 = new SequencedString("Now I shall teach you ryu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG54 = new SequencedString("You have learnt Ryu: Offense",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.RED.getRGB());
	public static SequencedString MSG55 = new SequencedString("You have learnt Ryu: Defense",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.red.getRGB());
	public static SequencedString MSG56 = new SequencedString("This is the final generic Nen ability I'm going to teach you.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG57 = new SequencedString("It's quite advanced but I have faith in you.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG58 = new SequencedString("Now part of this ability is being able to detect nen and who it belongs to; What it belongs to.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG59 = new SequencedString("Take a look at 20 different mob species with gyo to figure out their nen signal",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG60 = new SequencedString("Amazing, as usual.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG61 = new SequencedString("You have learnt En.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true).setColor(Color.red.getRGB());
	public static SequencedString MSG62 = new SequencedString("Before we move on to hatsus, you must find out your nen type.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG63 = new SequencedString("You can find it out through nen divination. Simply get a cup with a leave on it.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG64 = new SequencedString("Then, cast your aura by using Ren or Ten and right click the glass. You will know then what is your hatsu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);

	public static SequencedString MSG65 = new SequencedString("Now we will move on to hatsu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG66 = new SequencedString("Hatsu, unlike all the other abilities is very different depending on the user.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG67 = new SequencedString("hatsu depends on your nen type, it's best you learn your nen type before you start learning hatsu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG68 = new SequencedString("First you will learn some general basic hatsus.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG69 = new SequencedString("Anyone can learn these no matter the nen type.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG70 = new SequencedString("What would you like to learn?",QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);

	public static SequencedString MSG71 = new SequencedString("Congratulations on mastering this hatsu ability.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG72 = new SequencedString("Congratulations you mastered all basic hatsu abilities.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG73 = new SequencedString("The only thing left for me to teach you, is a personal hatsu.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG74 = new SequencedString("Unfortunately nowadays, your species is to weak to create their own hatsus, so they must be taught.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	public static SequencedString MSG75 = new SequencedString("I know three hatsus I could teach you, you may pick which you want. Some may prove more potent than others.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
    public static SequencedString MSG76 = new SequencedString("choose wisely.",
			QuestScreen.defaultChatboxStringLength, 20 * 8).setTicksFromLength(true);
	
	@Override
	public Quest[] getQuests(PlayerEntity p) {
		return new Quest[] { ModQuests.HUNTEREXAM01, ModQuests.RENQUEST, ModQuests.ZETSUQUEST, ModQuests.GYOQUEST, ModQuests.SHUQUEST ,ModQuests.INQUEST,ModQuests.KENQUEST,ModQuests.RYUQUEST, ModQuests.ENQUEST,ModQuests.DISCOVER_NENTYPE_QUEST,ModQuests.BASIC_HATSU_QUEST,ModQuests.CHARACTER_HATSUS_QUEST};
	}

	@Override
	public SequencedString[][] getSequencedStringFromQuest(int questIndex) {
		switch (questIndex) {
		case 0:
			return new SequencedString[][] { new SequencedString[] { MSG1, MSG2, MSG3, MSG4 }, null, null };
		case 1:
			return new SequencedString[][] { new SequencedString[] { MSG5, MSG6, MSG7, MSG8, MSG9 }, null,
					new SequencedString[] { MSG10, MSG11 }

			};
		case 2:
			return new SequencedString[][] { new SequencedString[] { MSG12, MSG13 }, null,
					new SequencedString[] { MSG14, MSG15 } };
					
		case 3:
			return new SequencedString[][] {
				new SequencedString[] {MSG17,MSG18,MSG19,MSG20},null,new SequencedString[] {MSG21,MSG22,MSG23,MSG24}
			};
			
		case 4:
			return new SequencedString[][] {new SequencedString[] {MSG25,MSG26,MSG27,MSG28,MSG29},null, new SequencedString[] {MSG30,MSG31,MSG32}};
		
		case 5:
			return new SequencedString[][] {new SequencedString[] {MSG33,MSG34,MSG35,MSG36,MSG37,MSG38,MSG39},null, new SequencedString[] {MSG40,MSG41}};
	
		case 6:
			return new SequencedString[][] {new SequencedString[] {MSG42,MSG43,MSG44,MSG45},null, new SequencedString[] {MSG46,MSG47}};

		case 7:
			return new SequencedString[][] {new SequencedString[] {MSG48,MSG49,MSG50,MSG51,MSG52},null, new SequencedString[] {MSG53,MSG54,MSG55}};

		case 8:
			return new SequencedString[][] {new SequencedString[] {MSG56,MSG57,MSG58,MSG59},null, new SequencedString[] {MSG60,MSG61}};

		case 9:
			return new SequencedString[][] {new SequencedString[] {MSG62,MSG63,MSG64},null,null};
			
		case 10:
			return new SequencedString[][] {new SequencedString[] {MSG65,MSG66,MSG67,MSG68,MSG69},new SequencedString[] {MSG70}, null};
		case 11:
			return new SequencedString[][] {new SequencedString[] {MSG73,MSG74,MSG75},new SequencedString[] {MSG76},null};
		}
		;
		return null;
	}

	@Override
	public SequencedString[] getSpeechFromState(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		if (Helper.getCurrentQuest(this.getQuests(p), p) != -1) {
			SequencedString[][] sqstrs = this
					.getSequencedStringFromQuest(Helper.getCurrentQuest(this.getQuests(p), p));
			Quest q = this.getQuests(p)[Helper.getCurrentQuest(this.getQuests(p), p)];

			if(q.equals(ModQuests.BASIC_HATSU_QUEST)) {
				Quest rq = data.getQuest(q);
				if(q.isFinished()) {
					
				} else {
					Quest[] quests = new Quest[] { ModQuests.BASIC_CONJURER,ModQuests.BASIC_EMITTER,ModQuests.BASIC_ENHANCER,ModQuests.BASIC_MANIPULATOR,ModQuests.BASIC_TRANSMUTER};
					for(int i = 0; i < 5; i++) {
						Quest hq = data.getQuest(quests[i]);
						if(hq != null && hq.canFinish()) {
							hq.finishQuest(p);
							SequencedString sq =Helper.getNewSqStringInstance(MSG71);
							sq.event = () -> {if(Minecraft.getInstance().currentScreen instanceof QuestScreen) {
								Minecraft.getInstance().currentScreen = null;
							}};
							return new SequencedString[] {sq};
						}
					}
					boolean allFin = true;
					for(int i = 0; i< 5; i++) {
						Quest hq = data.getQuest(quests[i]);
						if(hq != null && !hq.isFinished()) {
							return null;
						}
						if(hq == null) {
							allFin = false;
						}
						
						if(i == 4) {
							if(allFin) {
								data.getQuest(ModQuests.BASIC_HATSU_QUEST).setProgress(100);
								SequencedString str = Helper.getNewSqStringInstance(MSG72);
								str.event = () -> {if(Minecraft.getInstance().currentScreen instanceof QuestScreen) {
									Minecraft.getInstance().currentScreen = null;
								}};
								return new SequencedString[] {str};
							}
						}
					} 
					
					

					for(int i = 0; i < 5; i++) {
						Quest hq = data.getQuest(quests[i]);
						Quest hatsuQuest = data.getQuest(ModQuests.BASIC_HATSU_QUEST);
						if(hq == null) {
							if(hatsuQuest == null) {
							return new SequencedString[] {MSG65,MSG66,MSG67,MSG68,MSG69};
							} else if(!hatsuQuest.isFinished()) {
								return new SequencedString[] {MSG70};
							}
							}
					}

				}
			}
			if (q.equals(ModQuests.RENQUEST) && !data.isHunter()) {
				return null;
			}
			switch (this.getStateFromQuest(q, p)) {

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
