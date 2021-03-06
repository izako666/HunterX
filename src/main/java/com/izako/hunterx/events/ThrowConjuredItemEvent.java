package com.izako.hunterx.events;

import com.izako.hunterx.Main;
import com.izako.hunterx.init.ModItems;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Main.MODID)
public class ThrowConjuredItemEvent {

	@SubscribeEvent
	public static void throwEvent(ItemTossEvent evt) {
		ItemStack stack = evt.getEntityItem().getItem();

		if (stack.hasTag() && stack.getTag().hasUniqueId("nenowner")) {
			evt.setCanceled(true);
		}
		
		if(stack.getItem() == ModItems.HANZO_SWORD) {
			evt.setCanceled(true);
		}
	}
}
