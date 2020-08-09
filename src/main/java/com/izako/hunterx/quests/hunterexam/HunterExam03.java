package com.izako.hunterx.quests.hunterexam;

import com.izako.hunterx.data.hunterdata.HunterDataCapability;
import com.izako.hunterx.data.hunterdata.IHunterData;
import com.izako.hunterx.izapi.quest.Quest;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public class HunterExam03 extends Quest{

	public double posX;
	public double posY;
	public double posZ;
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
	public boolean canFinish() {
		return this.getProgress() < 100;
	}

	@Override
	public boolean doneTask() {

		double prog = this.getProgress();
		if(prog < 100) {
			this.finishQuest();
			return true;
		}
		return false;
	}

	@Override
	public void giveQuest(PlayerEntity p) {
		IHunterData data = HunterDataCapability.get(p);
		data.giveQuest(this);
		this.posX = p.getPosX();
		this.posY = p.getPosY();
		this.posZ = p.getPosZ();
		
	}

	@Override
	public CompoundNBT writeData() {
		CompoundNBT nbt =  super.writeData();
		nbt.putDouble("posx", this.posX);
		nbt.putDouble("posy", this.posY);
		nbt.putDouble("posz", this.posZ);
		return nbt;

	}

	@Override
	public Quest readData(CompoundNBT nbt) {
		 super.readData(nbt);
		this.posX = nbt.getDouble("posx");
		this.posY = nbt.getDouble("posy");
		this.posZ = nbt.getDouble("posz");
		return this;

	}

}
