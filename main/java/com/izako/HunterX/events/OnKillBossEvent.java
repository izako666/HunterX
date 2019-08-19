package com.izako.HunterX.events;

import com.izako.HunterX.entity.BossExam;
import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnKillBossEvent {

	@SubscribeEvent
	public void onKill(LivingDeathEvent e) {
		if(e.getSource().getTrueSource() instanceof EntityPlayer && e.getEntity() instanceof BossExam) {
			EntityPlayer p = (EntityPlayer) e.getSource().getTrueSource();
			IEntityStats stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
			stats.setHasKilledBoss(true);
			if(p instanceof EntityPlayerMP) {
			ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(1D, 8, stats.hasKilledBoss()), (EntityPlayerMP) p);
			}
		}
	}
}
