package com.izako.hunterx.quests;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.izapi.quest.IAdditionalQuestData;
import com.izako.hunterx.izapi.quest.Quest;
import com.izako.hunterx.izapi.quest.QuestLine;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class HunterExam03 extends Quest implements IAdditionalQuestData{

	@Override
	public String getId() {
		return "hunterexam03";
	}

	@Override
	public String getName() {
		return "Run.";
	}

	@Override
	public String getDescription() {
		return "You must get atleast 100 blocks far away from the examiner in 2 minutes.";
	}

	@Override
	public QuestLine getQuestLine() {
		return null;
	}

	
	@Override
	public boolean canFinish(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		if(this.hasQuest(p)) {
		if(data.getProgress(this.getId()) < 100) {
			return true;
		}
		}
		return false;

	}

	@Override
	public boolean doneTask(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		int prog = data.getProgress(this.getId());
		if(prog < 100) {
			data.finishQuest(getId());
			return true;
		}
		return false;
	}

	@Override
	public void renderDesc(int x, int y) {
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "You must get atleast 100", x, y, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "blocks away from the ", x, y + 20, 16777215);
		Minecraft.getInstance().currentScreen.drawString(Minecraft.getInstance().fontRenderer, "examiner in 2 minutes.", x, y + 40, 16777215);

	}

	@Override
	public void giveQuest(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		data.giveQuest(this.getId(), 0);
		CompoundNBT questData = data.getOrCreateExtraQuestData(this);
		questData.putDouble("posx", p.getPosX());
		questData.putDouble("posy", p.getPosY());
		questData.putDouble("posz", p.getPosZ());
		
	}

}
