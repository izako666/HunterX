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
		stats.setHasKilledKiriko(oldStats.hasKilledKiriko());
		stats.setHasStarted2ndPhase(oldStats.hasStarted2ndPhase());
		stats.setTimeHasRun(oldStats.timeHasRun());
		stats.setHasStarted3rdPhase(oldStats.hasStarted3rdPhase());
		stats.setHasKilledBoss(oldStats.hasKilledBoss());
		stats.setNenCapacity(oldStats.getNenCapacity());
		oldStats.getQuests().forEach((k, v) -> {
			stats.giveQuest(k, v);
		});
		
		oldStats.getAbilities().forEach((k) -> {
			stats.giveAbility(k);
		});
		
		for(int i = 0; i < oldStats.getSlotsList().length; i++) {
			stats.setAbilityToSlot(i, oldStats.getSlotsList()[i]);
		}
	
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
     if(hm != null) {
		h.applyModifier(hm);
     }
     if(dm != null) {
		d.applyModifier(dm);
     }
     if(sm != null)  {
		s.applyModifier(sm);
     } if(am != null) {
		a.applyModifier(am);
     }
		

	}

}
