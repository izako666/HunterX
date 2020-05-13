package com.izako.hunterx.quests;

import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.izapi.quest.QuestLine;

import net.minecraft.client.Minecraft;

public class HunterExam02 extends Quest {

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "hunterexam02";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Kiriko Hunt";
	}

	@Override
	public String getDescription() {
		return "You must kill a kiriko to prove your skill.";
	}

	@Override
	public QuestLine getQuestLine() {
		return null;
	}

	@Override
	public void renderDesc(int x, int y) {
		// TODO Auto-generated method stub
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "You must kill a ", x, y, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "kiriko to prove your skill.", x, y + 20, 16777215);

	}

}
