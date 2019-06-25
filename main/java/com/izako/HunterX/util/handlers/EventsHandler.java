package com.izako.HunterX.util.handlers;

import com.izako.HunterX.events.HanzoArmorBaseEvent;
import com.izako.HunterX.events.HanzoSwordDropEvent;
import com.izako.HunterX.events.HunterCardDropEvent;
import com.izako.HunterX.events.ThugDropEvent;
import com.izako.HunterX.events.stats.HealthStatEvent;
import com.izako.HunterX.events.stats.IntelligenceStatEvent;
import com.izako.HunterX.events.stats.SpeedStatEvent;

import net.minecraftforge.common.MinecraftForge;

public class EventsHandler {

	public static void registerEvents() {
		// TODO Auto-generated method stub
		HanzoArmorBaseEvent hanzoArmorBaseEvent = new HanzoArmorBaseEvent();
		HunterCardDropEvent hunterCardDropEvent = new HunterCardDropEvent();
		HanzoSwordDropEvent hanzoSwordDropEvent = new HanzoSwordDropEvent();
		ThugDropEvent thugDropEvent = new ThugDropEvent();
		HealthStatEvent healthStatEvent = new HealthStatEvent();
		SpeedStatEvent speedStatEvent = new SpeedStatEvent();
		IntelligenceStatEvent intelligenceStatEvent = new IntelligenceStatEvent();
		
		
		MinecraftForge.EVENT_BUS.register(hanzoArmorBaseEvent);
		MinecraftForge.EVENT_BUS.register(hunterCardDropEvent);
		MinecraftForge.EVENT_BUS.register(hanzoSwordDropEvent);
		MinecraftForge.EVENT_BUS.register(thugDropEvent);
		MinecraftForge.EVENT_BUS.register(healthStatEvent);
		MinecraftForge.EVENT_BUS.register(speedStatEvent);
		MinecraftForge.EVENT_BUS.register(intelligenceStatEvent);

	}

}
