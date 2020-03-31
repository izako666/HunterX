package com.izako.hunterx.quests;

import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.izapi.quest.QuestLine;

import net.minecraft.client.Minecraft;

public class HunterExam03 extends Quest{

	@Override
	public String getId() {
		// TODO Auto-generated method stub
		return "hunterexam03";
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Run.";
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return "You must get atleast 100 blocks far away from the examiner in 2 minutes.";
	}

	@Override
	public QuestLine getQuestLine() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void renderDesc(int x, int y) {
		// TODO Auto-generated method stub
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "You must get atleast 100", x, y, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "blocks away from the ", x, y + 20, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "examiner in 2 minutes.", x, y + 40, 16777215);

	}

}
