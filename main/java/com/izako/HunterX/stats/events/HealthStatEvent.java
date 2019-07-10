package com.izako.HunterX.stats.events;

import java.util.UUID;

import com.izako.HunterX.stats.capabilities.EntityStatsProvider;
import com.izako.HunterX.stats.capabilities.IEntityStats;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.IAttribute;
import net.minecraft.entity.ai.attributes.IAttributeInstance;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class HealthStatEvent {

	private double healthStatCap = 0;
	private double healthStatPerm = 20.0D;
	private AttributeModifier healthModifier;
	UUID attribute_uuid = UUID.randomUUID();

	@SubscribeEvent
	public void onHurt(LivingHurtEvent event) {
		Entity playerIn = event.getEntity();
		
		IAttributeInstance attribute = ((EntityLivingBase) playerIn)
				.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH);
		IEntityStats stats = playerIn.getCapability(EntityStatsProvider.ENTITY_STATS, null);

		if (playerIn instanceof EntityPlayer) {
			if (healthStatCap < 20.0D) {
				stats.setHealthStat(healthStatCap + 1);
				healthStatCap = stats.getHealthStat();
				healthModifier = new AttributeModifier(attribute_uuid, "healthStatIncrease", healthStatCap, 0)
						.setSaved(true);
				attribute.removeModifier(healthModifier);
				attribute.applyModifier(healthModifier);

				playerIn.sendMessage(new TextComponentString(Double.toString(healthStatCap)));
			} else if (healthStatCap >= 20.0D) {

				healthModifier = new AttributeModifier(attribute_uuid, "healthStatIncrease", healthStatPerm, 0)
						.setSaved(true);
				attribute.removeModifier(healthModifier);
				attribute.applyModifier(healthModifier);
				playerIn.sendMessage(new TextComponentString(Double.toString(healthStatCap)));

			}
		}

	}
}