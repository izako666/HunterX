package com.izako.HunterX.network;

import com.izako.HunterX.network.packets.EntityStatsClientSync;
import com.izako.HunterX.network.packets.EntityStatsServerSync;
import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class EntityStatsClientSyncHandler implements IMessageHandler<EntityStatsClientSync, IMessage> {

	@Override
	public IMessage onMessage(EntityStatsClientSync message, MessageContext ctx) {
		Double amount = message.amount;
		int statType = message.statType;
		boolean value = message.value;
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayerSP player = Minecraft.getMinecraft().player;
			IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
			if (statType == 1) {
				stats.setHealthStat(amount);
			} else if (statType == 2) {
				stats.setDefenseStat(amount);
			} else if (statType == 3) {
				stats.setSpeedStat(amount);
			} else if (statType == 4) {
				stats.setAttackStat(amount);
			} else if(statType == 5 ) {
				stats.setHasKilledKiriko(value);
			} else if(statType == 6) {
				stats.setHasStarted2ndPhase(value);
			} else if(statType == 7) {
				stats.setTimeHasRun(amount);
			} else if(statType == 8) {
				stats.setHasKilledBoss(value);
			} else if(statType == 9) {
				stats.setHasStarted3rdPhase(value);
			} else if(statType == 10) {
				stats.setIsHunter(value);
			}
			
		});
		return null;
	}

}
