package com.izako.HunterX.util.handlers;

import com.izako.HunterX.events.HanzoArmorBaseEvent;
import com.izako.HunterX.events.HanzoSwordDropEvent;
import com.izako.HunterX.events.OnKillBossEvent;
import com.izako.HunterX.events.OnKillKirikoEvent;
import com.izako.HunterX.events.ThugDropEvent;
import com.izako.HunterX.events.TimeRunEvent;
import com.izako.HunterX.stats.events.AttackStatEvent;
import com.izako.HunterX.stats.events.CapabilityAttachEvent;
import com.izako.HunterX.stats.events.DefenseStatEvent;
import com.izako.HunterX.stats.events.HealthStatEvent;
import com.izako.HunterX.stats.events.OnDeathStatResetEvent;
import com.izako.HunterX.stats.events.PlayerChangeDimensionEvent;
import com.izako.HunterX.stats.events.PlayerLoggedInEvent;
import com.izako.HunterX.stats.events.PlayerRespawnEvent;
import com.izako.HunterX.stats.events.SpeedStatEvent;

import net.minecraftforge.common.MinecraftForge;

public class EventsHandler {

	public static void registerEvents() {
		// TODO Auto-generated method stub
		HanzoArmorBaseEvent hanzoArmorBaseEvent = new HanzoArmorBaseEvent();
		HanzoSwordDropEvent hanzoSwordDropEvent = new HanzoSwordDropEvent();
		ThugDropEvent thugDropEvent = new ThugDropEvent();
		HealthStatEvent healthStatEvent = new HealthStatEvent();
		SpeedStatEvent speedStatEvent = new SpeedStatEvent();
		CapabilityAttachEvent capabilityEvent = new CapabilityAttachEvent();
		AttackStatEvent attackStatEvent = new AttackStatEvent();
		OnDeathStatResetEvent onDeathStatResetEvent = new OnDeathStatResetEvent();
		DefenseStatEvent defenseStatEvent = new DefenseStatEvent();
		PlayerRespawnEvent pre = new PlayerRespawnEvent();
		PlayerLoggedInEvent ple = new PlayerLoggedInEvent();
		PlayerChangeDimensionEvent pcd = new PlayerChangeDimensionEvent();
		OnKillKirikoEvent onKillKirikoEvent = new OnKillKirikoEvent();
		TimeRunEvent timeRunEvent = new TimeRunEvent();
		OnKillBossEvent onKillBossEvent = new OnKillBossEvent();
		
		
		MinecraftForge.EVENT_BUS.register(hanzoArmorBaseEvent);
		MinecraftForge.EVENT_BUS.register(hanzoSwordDropEvent);
		MinecraftForge.EVENT_BUS.register(thugDropEvent);
		MinecraftForge.EVENT_BUS.register(healthStatEvent);
		MinecraftForge.EVENT_BUS.register(speedStatEvent);
		MinecraftForge.EVENT_BUS.register(capabilityEvent);
		MinecraftForge.EVENT_BUS.register(attackStatEvent);
		MinecraftForge.EVENT_BUS.register(onDeathStatResetEvent);
		MinecraftForge.EVENT_BUS.register(defenseStatEvent);
		MinecraftForge.EVENT_BUS.register(pre);
		MinecraftForge.EVENT_BUS.register(ple);
		MinecraftForge.EVENT_BUS.register(pcd);
		MinecraftForge.EVENT_BUS.register(onKillKirikoEvent);
		MinecraftForge.EVENT_BUS.register(timeRunEvent);
		MinecraftForge.EVENT_BUS.register(onKillBossEvent);







		

	}

}
