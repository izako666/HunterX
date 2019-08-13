package com.izako.HunterX.stats.events;

import java.util.Collection;
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
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class OnDeathStatResetEvent {

	@SubscribeEvent
	public void onRespawn(PlayerEvent.Clone event) {

		EntityPlayerMP p = (EntityPlayerMP) event.getEntityPlayer();
		IEntityStats stats = p.getCapability(EntityStatsProvider.ENTITY_STATS, null);
		IEntityStats oldStats = event.getOriginal().getCapability(EntityStatsProvider.ENTITY_STATS, null);
		stats.setHealthStat(oldStats.getHealthStat());
		stats.setSpeedStat(oldStats.getSpeedStat());
		stats.setDefenseStat(oldStats.getDefenseStat());
		stats.setAttackStat(oldStats.getAttackStat());
		stats.setIsHunter(oldStats.isHunter());
	//resetting the modifiers after death 
		IAttributeInstance h = ((EntityLivingBase) p)
				.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		IAttributeInstance d = ((EntityLivingBase) p)
				.getEntityAttribute(SharedMonsterAttributes.ARMOR);
		IAttributeInstance s = ((EntityLivingBase) p)
				.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED);
		IAttributeInstance a = ((EntityLivingBase) p)
				.getEntityAttribute(SharedMonsterAttributes.ATTACK_DAMAGE);

		AttributeModifier hm = event.getOriginal().getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MAX_HEALTH).getModifier(HealthStatEvent.health_attributemodifier_uuid);
		AttributeModifier dm = event.getOriginal().getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ARMOR).getModifier(DefenseStatEvent.defense_attributemodifier_uuid);
		AttributeModifier sm = event.getOriginal().getAttributeMap().getAttributeInstance(SharedMonsterAttributes.MOVEMENT_SPEED).getModifier(SpeedStatEvent.speed_attribute_modifier);
		AttributeModifier am = event.getOriginal().getAttributeMap().getAttributeInstance(SharedMonsterAttributes.ATTACK_DAMAGE).getModifier(AttackStatEvent.attack_attributemodifier_uuid);

		h.applyModifier(hm);
		d.applyModifier(dm);
		s.applyModifier(sm);
		a.applyModifier(am);
	}

}
