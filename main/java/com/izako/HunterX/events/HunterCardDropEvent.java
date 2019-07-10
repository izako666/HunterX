package com.izako.HunterX.events;

import com.izako.HunterX.entity.BossExam;
import com.izako.HunterX.init.ModItems;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HunterCardDropEvent {

	@SubscribeEvent
	public void modDropLoot(LivingDropsEvent event) {
		if(event.getEntity() instanceof BossExam && event.getSource().getTrueSource() != null) {
			ItemStack card = new ItemStack(ModItems.HUNTER_CARD);
			card.setStackDisplayName(event.getSource().getTrueSource().getName() + "'s License");
			event.getEntity().entityDropItem(card, 0.0F);
			
		}
	}
}
