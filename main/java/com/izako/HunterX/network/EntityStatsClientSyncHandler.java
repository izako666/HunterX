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
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		Double amount = message.amount;
		int statType = message.statType;
		Minecraft.getMinecraft().addScheduledTask(() -> {
			IEntityStats stats = player.getCapability(EntityStatsProvider.ENTITY_STATS, null);
			if (statType == 1) {
				stats.setHealthStat(amount);
			} else if (statType == 2) {
				stats.setDefenseStat(amount);
			} else if (statType == 3) {
				stats.setSpeedStat(amount);
			} else if (statType == 4) {
				stats.setAttackStat(amount);
			}
		});
		return null;
	}

}
