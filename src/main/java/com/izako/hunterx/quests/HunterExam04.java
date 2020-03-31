package com.izako.hunterx.quests;

import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.izapi.quest.QuestLine;

import net.minecraft.client.Minecraft;

public class HunterExam04 extends Quest{

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "hunterexam04";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Badge Collection";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "The final stage to the hunter exam is getting the required badge from an exam participant, Hanzo is your target.";
	}

	@Override
	public QuestLine getQuestLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void renderDesc(int x, int y) {
		// TODO Auto-generated method stub
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "The final stage to the ", x, y, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "hunter exam is getting ", x, y + 20, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "the required badge from", x, y + 40, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "an exam participant, ", x, y + 60, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "Hanzo is your target.", x, y + 80, 16777215);

	}

}
