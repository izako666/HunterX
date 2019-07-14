package com.izako.HunterX.stats.events;

import java.util.UUID;

import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnDeathStatResetEvent {

	@SubscribeEvent
	public void onRespawn(PlayerEvent.Clone event) {

		EntityPlayer p = event.getEntityPlayer();
		IEntityStats stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		IEntityStats oldStats = event.getOriginal().getCapability(EntityStatsProvider.ENTITY_STATS, null);
		stats.setHealthStat(oldStats.getHealthStat());
		stats.setSpeedStat(oldStats.getSpeedStat());
		stats.setDefenseStat(oldStats.getDefenseStat());
		stats.setAttackStat(oldStats.getAttackStat());
		stats.setIsHunter(oldStats.isHunter());
		
		p.sendMessage(new TextComponentString(Double.toString(stats.getSpeedStat())));

	}
}
