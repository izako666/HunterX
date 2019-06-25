package com.izako.HunterX.events.stats;

import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.ItemCraftedEvent;

public class IntelligenceStatEvent {

	public static int intelligenceStat = 0;
	@SubscribeEvent
	public void onCraft(ItemCraftedEvent event) {
		
		intelligenceStat = intelligenceStat + 1;

	}
}
