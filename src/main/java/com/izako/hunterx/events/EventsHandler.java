package com.izako.hunterx.events;

import com.izako.hunterx.data.abilitydata.RegisterAbilityDataEvent;
import com.izako.hunterx.data.hunterdata.HunterDataEvent;
import com.izako.hunterx.gui.AbilitySlotsEvent;

import net.minecraftforge.common.MinecraftForge;

public class EventsHandler {

	public static void registerEvents() {
		HanzoSwordEvent hanzoSwordEvent = new HanzoSwordEvent();
		HunterDataEvent hunterDataEvent = new HunterDataEvent();
		RegisterAbilityDataEvent abldataEvent = new RegisterAbilityDataEvent();
		
		MinecraftForge.EVENT_BUS.register(hanzoSwordEvent);
		MinecraftForge.EVENT_BUS.register(hunterDataEvent);
		MinecraftForge.EVENT_BUS.register(abldataEvent);

	}
}
