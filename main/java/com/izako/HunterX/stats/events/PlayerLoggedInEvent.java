package com.izako.HunterX.stats.events;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerLoggedInEvent {
	@SubscribeEvent
	public void onRespawn(PlayerEvent.PlayerLoggedInEvent e) {
	
	EntityPlayer p = e.player;
	if(p instanceof EntityPlayerMP) {
		IEntityStats stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getSpeedStat(), 3), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getHealthStat(), 1), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getDefenseStat(), 2), (EntityPlayerMP) p);
		ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getAttackStat(), 4), (EntityPlayerMP) p);
	}
		}
}
