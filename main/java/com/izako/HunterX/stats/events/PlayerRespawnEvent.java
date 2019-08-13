package com.izako.HunterX.stats.events;

import java.util.UUID;

import com.izako.HunterX.network.ModidPacketHandler;
import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class PlayerRespawnEvent {

	@SubscribeEvent
	public void onRespawn(PlayerEvent.PlayerRespawnEvent e) {

		EntityPlayer p = e.player;
		if (p instanceof EntityPlayerMP) {
			IEntityStats stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
			ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getSpeedStat(), 3), (EntityPlayerMP) p);
			ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getHealthStat(), 1), (EntityPlayerMP) p);
			ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getDefenseStat(), 2),
					(EntityPlayerMP) p);
			ModidPacketHandler.INSTANCE.sendTo(new EntityStatsClientSync(stats.getAttackStat(), 4), (EntityPlayerMP) p);

		}
	}
}
