package com.izako.hunterx.events;

import com.izako.hunterx.data.hunterdata.HunterDataEvent;

import net.minecraftforge.common.MinecraftForge;

public class EventsHandler {

	public static void registerEvents() {
		HanzoSwordEvent hanzoSwordEvent = new HanzoSwordEvent();
		HunterDataEvent hunterDataEvent = new HunterDataEvent();
		
		MinecraftForge.EVENT_BUS.register(hanzoSwordEvent);
		MinecraftForge.EVENT_BUS.register(hunterDataEvent);

	}
}
