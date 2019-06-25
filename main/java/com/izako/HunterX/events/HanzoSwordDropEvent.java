package com.izako.HunterX.events;

import com.izako.HunterX.init.ModItems;
import com.izako.HunterX.items.tools.ToolSword;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HanzoSwordDropEvent {

	@SubscribeEvent
	public void itemToss(ItemTossEvent event) {
		ItemStack stack = event.getEntityItem().getItem();
		if(stack.getItem() instanceof ToolSword) {
			event.setCanceled(true);
		}
	}
}
