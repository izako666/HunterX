package com.izako.hunterx.izapi.quest;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.gui.QuestScreen;
import com.izako.hunterx.gui.SequencedString;
import com.izako.hunterx.init.ModQuests;
import com.izako.hunterx.izapi.NPCSpeech.QuestState;
import com.izako.hunterx.izapi.ability.Ability;
import com.izako.hunterx.networking.PacketHandler;
import com.izako.hunterx.networking.packets.SetQuestPacket;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class Quest implements Cloneable {

	
	public abstract String getId();
	public abstract String getName();
	public abstract String getDescription();
	private double progress = 0;
	
	public boolean doneTask() {
		double prog = this.getProgress();
		if(prog >= 100) {
			this.finishQuest();
			return true;
		}
		return false;
	}
	

	public void finishQuest() {
		this.setProgress(101);
	}
	
	public void finishQuest(PlayerEntity p) {
		this.setProgress(101);
	}
	
	public boolean isFinished() {
		return this.getProgress() > 100;
	}
	
	public boolean canFinish() {
		return this.getProgress() == 100;
	}
	@OnlyIn(Dist.CLIENT)
	public QuestScreenEndReturnType finishedTalkingEvent(QuestScreen scr) {
		QuestState state = scr.qgiver.getSpeech().getStateFromQuest(scr.currentQuest, scr.p);
		switch(state) {
		case NOTFULFILLED:
			return QuestScreenEndReturnType.NULL;
		case FULFILLED:
			this.finishQuest();
			PacketHandler.INSTANCE.sendToServer(new SetQuestPacket(this.getId(), false));
			return QuestScreenEndReturnType.NULL;
		}
		return QuestScreenEndReturnType.NULL;
		
	}
	
	public void removeQuest(PlayerEntity p) {

		IHunterData data = HunterDataCapability.get(p);
		data.removeQuest(this);
	}

	public void giveQuest(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		data.giveQuest(this);
	}
	
	@OnlyIn(Dist.CLIENT)
	public SequencedString[] getAdditionalMessage(QuestScreen scr) {
		return null;
	}

	
	public enum QuestScreenEndReturnType {
		NULL,QUEST,MESSAGE
	}
	
	public CompoundNBT writeData() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putDouble("progress", this.getProgress());
		return nbt;
	}


	public Quest readData(CompoundNBT nbt) {
		this.setProgress(nbt.getDouble("progress"));
		return this;
	}
	public double getProgress() {
		return this.progress;
	}
	
	public void setProgress(double val) {
		this.progress = val;
	}
	
	@Override
	public Object clone() {
		Quest quest = ModQuests.newInstance(this.getId());
		quest.readData(this.writeData());
		return quest;
	}
	
	@Override
	public boolean equals(Object obj) {
		Quest q = (Quest) obj;
		return this.getId().equalsIgnoreCase(q.getId());
	}
}
