package com.izako.HunterX.events;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TimeRunEvent {

	@SubscribeEvent
	public void timeRun(TickEvent.PlayerTickEvent e) {
		IEntityStats stats = e.player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		if(stats.hasStarted2ndPhase() == true) {
			stats.setTimeHasRun(stats.timeHasRun() + 1);
			ModidPacketHandler.INSTANCE.sendToServer(new EntityStatsServerSync(stats.timeHasRun(), 7, false));
		}
		
	}
}
