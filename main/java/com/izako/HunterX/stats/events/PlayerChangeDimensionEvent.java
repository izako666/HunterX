package com.izako.HunterX.stats.events;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerChangeDimensionEvent {

	@SubscribeEvent
	public void onRespawn(PlayerEvent.PlayerChangedDimensionEvent e) {
	
	EntityPlayer p = e.player;
	if(p instanceof EntityPlayerMP) {
		IEntityStats stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getSpeedStat(), 3, false), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getHealthStat(), 1, false), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getDefenseStat(), 2, false), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getAttackStat(), 4, false), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(1D, 5, stats.hasKilledKiriko()), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(1D, 6, stats.hasStarted2ndPhase()), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.timeHasRun(), 7, false), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(1D, 8, stats.hasKilledBoss()), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(1D, 9, stats.hasStarted3rdPhase()), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(1D, 10, stats.isHunter()), (EntityPlayerMP) p);

	}
	    
	}
}
