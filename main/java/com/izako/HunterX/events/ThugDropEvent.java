package com.izako.HunterX.events;

import java.util.Random;

import com.izako.HunterX.entity.Thug;
import com.izako.HunterX.init.ModItems;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ThugDropEvent {

	private static int randomWithRange(int min, int max) {return new Random().nextInt(max + 1 - min) + min;}
	@SubscribeEvent
	public void modDropLoot(LivingDropsEvent event) {
		if(event.getEntity() instanceof Thug) {
			int chance = randomWithRange(0, 10);
			if(chance <= 3) {
			
				int items = ModItems.ITEMS.size() - 1;
				ItemStack dropItem = new ItemStack(ModItems.ITEMS.get(randomWithRange(1, items)));
				if(dropItem.getItem() == ModItems.HUNTER_CARD || dropItem.getItem() == ModItems.HANZOS_SWORD) {
					event.setCanceled(true);
				} else {
				event.getEntity().dropItem(dropItem.getItem(), 1);
				}
			} else {
				event.setCanceled(true);
			}
		}
	}
}
