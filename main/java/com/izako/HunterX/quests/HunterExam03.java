package com.izako.HunterX.quests;

import com.izako.HunterX.init.ListQuests;
import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.izapi.quests.Quests;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;

public class HunterExam03 extends Quests {

	//The id of the quest.
	@Override
	public String  getID() {
		return "questHunterExam03";
	}
	//the name of the quest.
	@Override
	public String getName() {
		return "Get a good weapon.";
		
	}
	//quest description
	@Override
	public String getDesc() {
		return "Get a good weapon to continue, with your current equipment, you won't last.";
	}
	
	@Override
	public boolean doneTask(EntityPlayer player) {
		if(this.canProgress(player)) {
		if(player.inventory.hasItemStack(new ItemStack(ModItems.HANZOS_SWORD))
				|| player.inventory.hasItemStack(new ItemStack(ModItems.KURAPIKAS_SWORD))
				|| player.inventory.hasItemStack(new ItemStack(ModItems.KURAPIKAS_SWORD_UNSHEATHED))
				|| player.inventory.hasItemStack(new ItemStack(ModItems.GONS_FISHING_ROD))
				|| player.inventory.hasItemStack(new ItemStack(ModItems.YOYO))) {
			return true;
			
		}
		return false;
		}
		return false;
		
	}
	@Override
	public boolean canProgress(EntityPlayer player) {
		if(ListQuests.HUNTEREXAM02.doneTask(player)) {
			return true;
		}
		return false;
	}
}
