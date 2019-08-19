package com.izako.HunterX.events;

import com.izako.HunterX.entity.EntityKiriko;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnKillKirikoEvent {

	@SubscribeEvent
	public void  onKill(LivingDeathEvent e) {
		
		if(e.getSource().getTrueSource() instanceof EntityPlayer) {
			EntityPlayer p = (EntityPlayer) e.getSource().getTrueSource();
			if(e.getEntity() instanceof EntityKiriko) {
	IEntityStats stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
    stats.setHasKilledKiriko(true);

	if(p instanceof EntityPlayerMP) {
      ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(1D, 5, true), (EntityPlayerMP) p);
	}
      System.out.println(Boolean.toString(stats.hasKilledKiriko()));

			}
		}
	
	}
}
