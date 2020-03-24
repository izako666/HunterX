package com.izako.hunterx.events;

import net.minecraftforge.common.MinecraftForge;

public class EventsHandler {

	public static void registerEvents() {
		HanzoSwordEvent hanzoSwordEvent = new HanzoSwordEvent();
		
		MinecraftForge.EVENT_BUS.register(hanzoSwordEvent);
	}
}
